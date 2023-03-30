package com.ffc.geekyevent.vue

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import android.widget.Filter
import android.widget.Filterable
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Event
import com.ffc.geekyevent.viewmodel.StandViewModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEvents.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEvents : Fragment(), SearchView.OnQueryTextListener {
    private val standViewModel: StandViewModel by activityViewModels()
    private lateinit var itemEventsAdapter: ItemEventsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("StringFormatInvalid", "StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_events, container, false)

        val recyclerView = inflate.findViewById<RecyclerView>(R.id.recycler_view_events)
        // Inflate the layout for this fragment
        val data = standViewModel.listeEvenement
        itemEventsAdapter = ItemEventsAdapter(data)
        recyclerView.adapter = itemEventsAdapter
        recyclerView.setHasFixedSize(true)

        val listPresta = (standViewModel.listeStand.map { it.presta })

        inflate.findViewById<TextView>(R.id.textView2).text = activity?.applicationContext?.
        getString(R.string.nombre_de_events,data.size)

        inflate.findViewById<TextView>(R.id.button6).isVisible = standViewModel.user?.id in listPresta
        inflate.findViewById<TextView>(R.id.button6).setOnClickListener{
            container?.findNavController()?.navigate(R.id.action_FragmentEvents_to_addEvent)
        }
        
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
        itemEventsAdapter.filter.filter(newText)
        return true
    }
}


class ItemEventsAdapter(private val dataset: List<Event>)
    : RecyclerView.Adapter<ItemEventsAdapter.ItemViewHolder>(), Filterable {

    private var filteredDataset = dataset

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
        return filteredDataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Event = filteredDataset[position]
        holder.nomEvent.text = item.nom;
        holder.idEvent.text = item.id.toString();
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterString = constraint.toString().toLowerCase(Locale.ROOT).trim()

                filteredDataset = if (filterString.isEmpty()) {
                    dataset
                } else {
                    val resultList = ArrayList<Event>()
                    for (row in dataset) {
                        if (row.nom.toLowerCase().contains(filterString.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredDataset
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredDataset = results?.values as ArrayList<Event>
                notifyDataSetChanged()
            }
        }
    }
}