package com.ffc.geekyevent.vue

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.model.Stand

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VueStandFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("StringFormatInvalid")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_vue_stand, container, false)

        val recyclerView = inflate.findViewById<RecyclerView>(R.id.recycler_view_stand)
        val myDataset = Datasource().loadStand()
        recyclerView.adapter = ItemStandAdapter(myDataset)
        recyclerView.setHasFixedSize(true) //=veut dire que la liste a la meme taille

        inflate.findViewById<TextView>(R.id.textView2).text = activity?.applicationContext?.getString(R.string.nombre_de_stand,myDataset.size)

        return inflate ;
    }
}

class ItemStandAdapter(private val dataset: List<Stand>)
    : RecyclerView.Adapter<ItemStandAdapter.ItemViewHolder>(){

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nomStand: TextView = view.findViewById(R.id.nomStand)
        val idStand: TextView = view.findViewById(R.id.idStand)
        val descriptionStand: TextView = view.findViewById(R.id.descriptionStand)
        val typeStand: TextView = view.findViewById(R.id.typeStand)
        val nomPresta: TextView = view.findViewById(R.id.nomPresta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.stand_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item : Stand = dataset[position]
        holder.nomStand.text =  item.nom;
        holder.idStand.text = item.id.toString();
        holder.descriptionStand.text =  item.description;
        holder.typeStand.text = item.typeStand
        holder.nomPresta.text =  item.presta;
    }
}