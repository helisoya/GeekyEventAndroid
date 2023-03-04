package com.ffc.geekyevent.vue

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Stand
import com.ffc.geekyevent.viewmodel.ViewModel


class VueStandFragment : Fragment() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("StringFormatInvalid", "StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_vue_stand, container, false)

        val recyclerView = inflate.findViewById<RecyclerView>(R.id.recycler_view_stand)

        recyclerView.adapter = ItemStandAdapter(viewModel.listeStand)
        recyclerView.setHasFixedSize(true) //=veut dire que la liste a la meme taille

        inflate.findViewById<TextView>(R.id.textView2).text = activity?.applicationContext?.
            getString(R.string.nombre_de_stand,viewModel.listeStand.size)

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