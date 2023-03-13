package com.ffc.geekyevent.vue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.model.Event
import com.ffc.geekyevent.model.Prestataire
import com.ffc.geekyevent.model.Stand
import javax.sql.DataSource

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEvents.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPrestataires : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_liste_prestataire, container, false)

        val recyclerView = inflate.findViewById<RecyclerView>(R.id.recycler_view_presta)
        // Inflate the layout for this fragment
        val source =  Datasource()
        val data = source.loadPrestataires()
        recyclerView.adapter = ItemPrestataireAdapter(data)
        recyclerView.setHasFixedSize(true)

        inflate.findViewById<TextView>(R.id.textView2).text = activity?.applicationContext?.
        getString(R.string.nombre_de_presta,data.size)

        (activity as AppCompatActivity?)?.supportActionBar?.title = "Liste des evenements"
        return inflate
    }

}


class ItemPrestataireAdapter(private val dataset: List<Prestataire>)
    : RecyclerView.Adapter<ItemPrestataireAdapter.ItemViewHolder>() {

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
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Prestataire = dataset[position]
        holder.nomPresta.text = item.prenom + " " +item.nom;
        holder.idPresta.text = item.id.toString();
    }
}