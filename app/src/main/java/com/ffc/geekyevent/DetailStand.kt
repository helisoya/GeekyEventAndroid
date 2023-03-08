package com.ffc.geekyevent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.ffc.geekyevent.viewmodel.ViewModel

class DetailStand : Fragment() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stand_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = viewModel.listeStand[0]
        view.findViewById<TextView>(R.id.nomStand).text = data.nom
        view.findViewById<TextView>(R.id.idStand).text = data.id.toString()
        view.findViewById<TextView>(R.id.descriptionStand).text = data.description
        view.findViewById<TextView>(R.id.typeStand).text = data.typeStand
        view.findViewById<TextView>(R.id.nomPresta).text = data.presta
    }

}