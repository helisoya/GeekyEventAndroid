package com.ffc.geekyevent.vue

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ffc.geekyevent.R
import com.ffc.geekyevent.databinding.FragmentDetailStandBinding
import com.ffc.geekyevent.model.Event
import com.ffc.geekyevent.viewmodel.StandViewModel

class DetailStand : Fragment() {

    private val standViewModel: StandViewModel by activityViewModels()
    lateinit var dataBinding : FragmentDetailStandBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_stand, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailStandArgs.fromBundle(requireArguments())

        val data = standViewModel.listeStand.find { s -> s.id == args.idStand }
        val dataEvent = standViewModel.listeEvenement.filter { s -> s.idStand == args.idStand }
        if (data==null){
            view.findNavController().navigate(DetailStandDirections.actionDetailStand2ToVueStandFragment())
            return
        }
        dataBinding.nomStand.text = data.nom
        dataBinding.idStand.text = data.id.toString()
        dataBinding.descriptionStand.text = data.description
        dataBinding.typeStand.text = data.typeStand
        dataBinding.listeCommentaire.adapter = ItemCommentaireAdapter(data.commentaires)
        dataBinding.listeCommentaire.layoutManager = LinearLayoutManager(view.context)
        dataBinding.listeEvent.adapter = ItemEventAdapter(dataEvent)
        dataBinding.listeEvent.layoutManager = LinearLayoutManager(view.context)

        dataBinding.buttonDeleteStand.isVisible = standViewModel.isconnected && standViewModel.user?.id == data.presta
        dataBinding.buttonDeleteStand.setOnClickListener{
            val events = standViewModel.listeEvenement.filter { s -> s.idStand == data.id }
            for (event in events){
                standViewModel.removeEvent(event)
            }
            standViewModel.removeStand(data)
            view.findNavController().navigate(DetailStandDirections.actionDetailStand2ToVueStandFragment())
        }
        dataBinding.buttonPresta.setOnClickListener {
            view.findNavController().navigate(DetailStandDirections.actionDetailStand2ToDetailPrestataire(data.presta))
        }
        dataBinding.addCommentaire.setOnClickListener {
            Log.i("ufhekjdc",dataBinding.editCommentaire.text.toString())
            data.commentaires.add(dataBinding.editCommentaire.text.toString())
            dataBinding.listeCommentaire.adapter?.notifyItemInserted(data.commentaires.size - 1);
        }

        (activity as AppCompatActivity?)?.supportActionBar?.title = "Detail stand"
    }

}

class ItemCommentaireAdapter(private val dataset: List<String>)
    : RecyclerView.Adapter<ItemCommentaireAdapter.ItemViewHolder>(){

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.textViewEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_commentaire, parent, false)
        val itemHolder = ItemViewHolder(adapterLayout)
        Log.i("------",itemHolder.toString())
        adapterLayout.setOnClickListener {
//            parent.findNavController().navigate(VueStandFragmentDirections.actionVueStandFragmentToDetailStand2(itemHolder.idStand.text.toString().toInt()))
        }//utiliser si on veut supprimer un commentaire

        return itemHolder
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.text.text =  item
    }
}

class ItemEventAdapter(private val dataset: List<Event>)
    : RecyclerView.Adapter<ItemEventAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.textViewEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        val itemHolder = ItemViewHolder(adapterLayout)
        Log.i("------",itemHolder.toString())
        adapterLayout.setOnClickListener {
            parent.findNavController().navigate(DetailStandDirections.actionDetailStand2ToDetailEvent(dataset[viewType].id))
        }//utiliser si on veut aller sur la page de l'event

        return itemHolder
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.text.text =  item.nom
    }
}