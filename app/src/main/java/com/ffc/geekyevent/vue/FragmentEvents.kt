package com.ffc.geekyevent.vue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.model.Event
import com.ffc.geekyevent.model.Stand
import com.ffc.geekyevent.viewmodel.StandViewModel
import javax.sql.DataSource

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEvents.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEvents : Fragment() {
    private val standViewModel: StandViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_events, container, false)

        val recyclerView = inflate.findViewById<RecyclerView>(R.id.recycler_view_events)
        // Inflate the layout for this fragment
        val data = standViewModel.listeEvenement
        recyclerView.adapter = ItemEventsAdapter(data)
        recyclerView.setHasFixedSize(true)


        inflate.findViewById<TextView>(R.id.textView2).text = activity?.applicationContext?.
        getString(R.string.nombre_de_events,data.size)

        inflate.findViewById<TextView>(R.id.button6).isVisible = standViewModel.isconnected
        inflate.findViewById<TextView>(R.id.button6).setOnClickListener{
            container?.findNavController()?.navigate(R.id.action_FragmentEvents_to_addEvent)
        }
        
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Liste des evenements"
        return inflate
    }

}


class ItemEventsAdapter(private val dataset: List<Event>)
    : RecyclerView.Adapter<ItemEventsAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nomEvent: TextView = view.findViewById(R.id.nomEvent)
        val idEvent: TextView = view.findViewById(R.id.idEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_item, parent, false)
        val detailEvent = ItemViewHolder(adapterLayout)
        adapterLayout.setOnClickListener {
//            parent.findNavController().navigate(R.id.action_vueStandFragment_to_detailStand2)

            parent.findNavController().navigate(
                FragmentEventsDirections.actionFragment2ViewToDetailEvent(
                    detailEvent.idEvent.text.toString().toInt()
                )
            )
        }

        return detailEvent
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Event = dataset[position]
        holder.nomEvent.text = item.nom;
        holder.idEvent.text = item.id.toString();
    }
}