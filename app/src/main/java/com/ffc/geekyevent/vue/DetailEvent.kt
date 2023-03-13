package com.ffc.geekyevent.vue

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.model.Event
import com.ffc.geekyevent.viewmodel.StandViewModel
import java.nio.file.Files.find

class DetailEvent : Fragment() {

    private lateinit var data: Event
    private val standViewModel: StandViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailEventArgs.fromBundle(requireArguments())


        data = standViewModel.listeEvenement.find { s -> s.id == args.idEvent }!!
        if (data != null) {
            view.findViewById<TextView>(R.id.nomEvent).text = data.nom
            view.findViewById<TextView>(R.id.idEvent).text = data.id.toString()
            view.findViewById<TextView>(R.id.date).text = data.dateDebut+" - "+data.dateFin
            view.findViewById<TextView>(R.id.nbJoueurs).text = "Joueurs : "+data.nbJoueursInscrit.toString()+"/"+data.nbJoueursMax.toString()
        }
        view.findViewById<TextView>(R.id.buttonStand).setOnClickListener {
            Log.println(Log.INFO,"Hello","Start")
            view.findNavController().navigate(
            DetailEventDirections.actionDetailEventToDetailStand2(
                data.idStand
            ))
        }
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Detail evenement"
    }

}