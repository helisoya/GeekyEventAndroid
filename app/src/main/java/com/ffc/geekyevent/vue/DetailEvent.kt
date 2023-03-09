package com.ffc.geekyevent.vue

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.viewmodel.StandViewModel
import java.nio.file.Files.find

class DetailEvent : Fragment() {

    private val standViewModel: StandViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailEventArgs.fromBundle(requireArguments())
        Log.println(Log.INFO,"Hello","Start")


        val source = Datasource()
        val data = source.loadEvents().find { s -> s.id == args.idEvent }
        Log.println(Log.INFO,"Hello","Got Source")
        if (data != null) {
            view.findViewById<TextView>(R.id.nomEvent).text = data.nom
            Log.println(Log.INFO,"Hello","NomEvent")
            view.findViewById<TextView>(R.id.idEvent).text = data.id.toString()
            Log.println(Log.INFO,"Hello","IdEvent")
            view.findViewById<TextView>(R.id.date).text = data.dateDebut+" - "+data.dateFin
            Log.println(Log.INFO,"Hello","Date")
            view.findViewById<TextView>(R.id.nbJoueurs).text = "Joueurs : "+data.nbJoueursInscrit.toString()+"/"+data.nbJoueursMax.toString()
            Log.println(Log.INFO,"Hello","NbJoueurs")
        }
        view.findViewById<TextView>(R.id.buttonStand).setOnClickListener {
            if (data != null) {
                DetailEventDirections.actionDetailEventToDetailStand2(
                    data.idStand
                )
            }
        }
        Log.println(Log.INFO,"Hello","Bouton")
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Detail stand"
    }

}