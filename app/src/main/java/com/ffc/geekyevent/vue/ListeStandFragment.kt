package com.ffc.geekyevent.vue

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Filter
import android.widget.Filterable
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Stand
import com.ffc.geekyevent.viewmodel.StandViewModel
import java.util.*


class VueStandFragment : Fragment(), SearchView.OnQueryTextListener {

    private val standViewModel: StandViewModel by activityViewModels()
    private lateinit var itemStandAdapter: ItemStandAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("StringFormatInvalid", "StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_liste_stand, container, false)

        val recyclerView = inflate.findViewById<RecyclerView>(R.id.recycler_view_presta)

        itemStandAdapter = ItemStandAdapter(standViewModel.listeStand)
        recyclerView.adapter = itemStandAdapter
        recyclerView.setHasFixedSize(true)

        inflate.findViewById<TextView>(R.id.textView2).text = activity?.applicationContext?.
        getString(R.string.nombre_de_stand, standViewModel.listeStand.size)
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Liste des stands"
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
        itemStandAdapter.filter.filter(newText)
        return true
    }
}

class ItemStandAdapter(private val dataset: List<Stand>)
    : RecyclerView.Adapter<ItemStandAdapter.ItemViewHolder>(), Filterable {

    private var filteredDataset: List<Stand> = dataset

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nomStand: TextView = view.findViewById(R.id.nomStand)
        val idStand: TextView = view.findViewById(R.id.idStand)
        val descriptionStand: TextView = view.findViewById(R.id.descriptionStand)
        val typeStand: TextView = view.findViewById(R.id.typeStand)
        val nomPresta: TextView = view.findViewById(R.id.nomPresta)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.stand_item, parent, false)
        val detailStand = ItemViewHolder(adapterLayout)
        adapterLayout.setOnClickListener {
            parent.findNavController().navigate(
                VueStandFragmentDirections.actionVueStandFragmentToDetailStand2(
                    detailStand.idStand.text.toString().toInt()))
        }
        return detailStand
    }
    override fun getItemCount(): Int {
        return filteredDataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Stand = filteredDataset[position]
        holder.nomStand.text = item.nom
        holder.idStand.text = item.id.toString()
        holder.descriptionStand.text = item.description
        holder.typeStand.text = item.typeStand
        holder.nomPresta.text = item.presta.toString()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterString = constraint.toString().toLowerCase(Locale.ROOT).trim()

                filteredDataset = if (filterString.isEmpty()) {
                    dataset
                } else {
                    dataset.filter { stand ->
                        stand.nom.toLowerCase(Locale.ROOT).contains(filterString)
                                || stand.description.toLowerCase(Locale.ROOT).contains(filterString)
                                || stand.typeStand.toLowerCase(Locale.ROOT).contains(filterString)
                    }
                }
                return FilterResults().apply {
                    values = filteredDataset
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.values?.let {
                    filteredDataset = it as List<Stand>
                    notifyDataSetChanged()
                }
            }
        }
    }
}