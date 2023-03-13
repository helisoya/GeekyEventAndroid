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
import androidx.navigation.findNavController
import com.ffc.geekyevent.R
import com.ffc.geekyevent.viewmodel.StandViewModel

class DetailStand : Fragment() {

    private val standViewModel: StandViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_stand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailStandArgs.fromBundle(requireArguments())

        val data = standViewModel.listeStand.find { s -> s.id == args.idStand }
        if (data != null) {
            view.findViewById<TextView>(R.id.nomStand).text = data.nom
            view.findViewById<TextView>(R.id.idStand).text = data.id.toString()
            view.findViewById<TextView>(R.id.descriptionStand).text = data.description
            view.findViewById<TextView>(R.id.typeStand).text = data.typeStand
        }
        view.findViewById<TextView>(R.id.buttonPresta).setOnClickListener {
            if(data != null){
                view.findNavController().navigate(
                    DetailStandDirections.actionDetailStand2ToDetailPrestataire(
                        data.presta
                    ))
            }

        }
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Detail stand"
    }

}