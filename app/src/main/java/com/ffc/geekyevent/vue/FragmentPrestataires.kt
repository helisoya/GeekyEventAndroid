package com.ffc.geekyevent.vue

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import android.widget.Filter
import android.widget.Filterable
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.model.Event
import com.ffc.geekyevent.model.Prestataire
import com.ffc.geekyevent.model.Stand
import com.ffc.geekyevent.viewmodel.StandViewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEvents.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPrestataires : Fragment(), SearchView.OnQueryTextListener {

    private val standViewModel: StandViewModel by activityViewModels()
    private lateinit var itemPrestataireAdapter: ItemPrestataireAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("StringFormatInvalid", "StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_liste_prestataire, container, false)

        val recyclerView = inflate.findViewById<RecyclerView>(R.id.recycler_view_presta)
        // Inflate the layout for this fragment
//        val source =  Datasource()
        val data = standViewModel.listePresta
        itemPrestataireAdapter = ItemPrestataireAdapter(data)
        recyclerView.adapter = itemPrestataireAdapter
        recyclerView.setHasFixedSize(true)

        inflate.findViewById<TextView>(R.id.textView2).text = activity?.applicationContext?.
        getString(R.string.nombre_de_presta,data.size)

        (activity as AppCompatActivity?)?.supportActionBar?.title = "Liste des evenements"
        return inflate
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        itemPrestataireAdapter.filter.filter(newText)
        return true
    }
}


class ItemPrestataireAdapter(private val dataset: List<Prestataire>)
    : RecyclerView.Adapter<ItemPrestataireAdapter.ItemViewHolder>(), Filterable {

    private var filteredDataset: List<Prestataire> = dataset

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nomPresta: TextView = view.findViewById(R.id.nomPresta)
        val idPresta: TextView = view.findViewById(R.id.idPresta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.prestataire_item, parent, false)
        val detailPresta = ItemViewHolder(adapterLayout)
        adapterLayout.setOnClickListener {
            parent.findNavController().navigate(
                FragmentPrestatairesDirections.actionFragmentPrestatairesToDetailPrestataire(
                    detailPresta.idPresta.text.toString().toInt()
                )
            )
        }

        return detailPresta
    }

    override fun getItemCount(): Int {
        return filteredDataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Prestataire = filteredDataset[position]
        holder.nomPresta.text = item.prenom + " " +item.nom;
        holder.idPresta.text = item.id.toString();
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterString = constraint.toString().toLowerCase(Locale.ROOT).trim()

                filteredDataset = if (filterString.isEmpty()) {
                    dataset
                } else {
                    dataset.filter { presta ->
                        presta.nom.toLowerCase(Locale.ROOT).contains(filterString)
                                || presta.prenom.toLowerCase(Locale.ROOT).contains(filterString)
                                || presta.username.toLowerCase(Locale.ROOT).contains(filterString)
                    }
                }
                return FilterResults().apply {
                    values = filteredDataset
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.values?.let {
                    filteredDataset = it as List<Prestataire>
                    notifyDataSetChanged()
                }
            }
        }
    }
}