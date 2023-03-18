package com.ffc.geekyevent.vue

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Event
import com.ffc.geekyevent.model.Stand
import com.ffc.geekyevent.viewmodel.StandViewModel


class ListStandTimeline : Fragment() {

    private val standViewModel: StandViewModel by activityViewModels()
    private lateinit var currentStand: Stand
    private lateinit var recyclerView: RecyclerView
    private lateinit var inflate: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_liste_stand_timeline, container, false)
        val firstStandId: Int = standViewModel.listeEvenement.first().idStand

        val buttonNext: Button = inflate.findViewById(R.id.buttonNext)
        buttonNext.setOnClickListener{
            nextStand()
        }

        val buttonPrevious: Button = inflate.findViewById(R.id.buttonPrevious)
        buttonPrevious.setOnClickListener{
            previousStand()
        }

        currentStand = standViewModel.listeStand.find { stand -> stand.id == firstStandId }!!

        inflate.findViewById<TextView>(R.id.standName).text = currentStand.nom

        recyclerView = inflate.findViewById(R.id.recycler_view_timeline)

        recyclerView.adapter = ScheduleStandAdapter(standViewModel.listeEvenement.filter { event ->
            event.idStand == firstStandId
        })
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Timeline"
        return inflate
        }

    private fun nextStand(){
        val indexCurrent = standViewModel.listeStand.indexOf(currentStand)
        val stand: Stand? = standViewModel.listeStand.getOrNull(indexCurrent+1)
        Log.i("ListStandTimeline", "ButtonClicked, stand : " + stand.toString())
        if (stand != null){
            recyclerView.adapter = ScheduleStandAdapter(standViewModel.listeEvenement.filter { event ->
                event.idStand == stand.id
            })
            inflate.findViewById<TextView>(R.id.standName).text = currentStand.nom
            currentStand = stand
        }else {
            Toast.makeText( context, "No Next Stand !", Toast.LENGTH_SHORT).show()
        }
    }
    private fun previousStand(){
        val indexCurrent = standViewModel.listeStand.indexOf(currentStand)
        val stand: Stand? = standViewModel.listeStand.getOrNull(indexCurrent-1)
        Log.i("ListStandTimeline", "ButtonClicked, currentID :" + indexCurrent+ " stand : " + stand.toString())
        if (stand != null){
            recyclerView.adapter = ScheduleStandAdapter(standViewModel.listeEvenement.filter { event ->
                event.idStand == stand.id
            })
            inflate.findViewById<TextView>(R.id.standName).text = currentStand.nom
            currentStand = stand
        }else {
            Toast.makeText(context, "No Previous Stand !", Toast.LENGTH_SHORT).show()
        }
    }
}

class ScheduleStandAdapter(private val dataset: List<Event>)
    : RecyclerView.Adapter<ScheduleStandAdapter.ItemViewHolder>(){

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomEvenement: TextView = view.findViewById(R.id.nomEvenement)
        val nbJoueurs: TextView = view.findViewById(R.id.nbJoueurs)
        val joueursMax: TextView = view.findViewById(R.id.joueursMax)
        val heureDebut: TextView = view.findViewById(R.id.hour)
        val heureFin: TextView = view.findViewById(R.id.hour2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.timeline_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item : Event = dataset[position]
        holder.heureDebut.text = item.dateDebut
        holder.heureFin.text = item.dateFin
        holder.nomEvenement.text =  item.nom
        holder.nbJoueurs.text = item.nbJoueursInscrit.toString()
        holder.joueursMax.text =  item.nbJoueursMax.toString()
    }


}