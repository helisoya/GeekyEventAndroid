package com.ffc.geekyevent.vue

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.ffc.geekyevent.R
import com.ffc.geekyevent.viewmodel.ViewModel

class DetailStand : Fragment() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_stand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailStandArgs.fromBundle(requireArguments())

        val data = viewModel.listeStand.find { s -> s.id == args.idStand }
        if (data != null) {
            view.findViewById<TextView>(R.id.nomStand).text = data.nom
            view.findViewById<TextView>(R.id.idStand).text = data.id.toString()
            view.findViewById<TextView>(R.id.descriptionStand).text = data.description
            view.findViewById<TextView>(R.id.typeStand).text = data.typeStand
            view.findViewById<TextView>(R.id.nomPresta).text = data.presta
        }
    }

}