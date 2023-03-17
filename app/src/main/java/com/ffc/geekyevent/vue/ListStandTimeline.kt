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
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Event
import com.ffc.geekyevent.viewmodel.StandViewModel


class ListStandTimeline : Fragment() {

    private val standViewModel: StandViewModel by activityViewModels()

    @SuppressLint("StringFormatInvalid", "StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_liste_stand_timeline, container, false)

        val recyclerView = inflate.findViewById<RecyclerView>(R.id.recycler_view_timeline)

        recyclerView.adapter = ScheduleStandAdapter(standViewModel.listeEvenement)
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Timeline"
        return inflate ;
    }
}

class ScheduleStandAdapter(private val dataset: List<Event>)
    : RecyclerView.Adapter<ScheduleStandAdapter.ItemViewHolder>(){

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nomEvenement: TextView = view.findViewById(R.id.nomEvenement)
        val nbJoueurs: TextView = view.findViewById(R.id.nbJoueurs)
        val joueursMax: TextView = view.findViewById(R.id.joueursMax)
        val heureDebut: TextView = view.findViewById(R.id.hour)
        val heureFin: TextView = view.findViewById(R.id.hour2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.timeline_item, parent, false)
        val eventDetail = ItemViewHolder(adapterLayout)
//        adapterLayout.setOnClickListener {
////            parent.findNavController().navigate(R.id.action_vueStandFragment_to_detailStand2)
//            parent.findNavController().navigate(FragmentEvents.action_fragment2View_to_detailEvent(eventDetail.itemId))
//        }

        return eventDetail
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item : Event = dataset[position]
        holder.heureDebut.text = item.dateDebut.toString()
        holder.heureFin.text = item.dateFin.toString()
        holder.nomEvenement.text =  item.nom;
        holder.nbJoueurs.text = item.nbJoueursInscrit.toString();
        holder.joueursMax.text =  item.nbJoueursMax.toString();
    }
}