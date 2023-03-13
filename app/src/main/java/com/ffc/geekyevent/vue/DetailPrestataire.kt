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
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.model.Event
import com.ffc.geekyevent.model.Prestataire
import com.ffc.geekyevent.viewmodel.StandViewModel
import java.nio.file.Files.find

class DetailPrestataire : Fragment() {

    private lateinit var data: Prestataire

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_prestataire, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailPrestataireArgs.fromBundle(requireArguments())


        val source = Datasource()
        data = source.loadPrestataires().find { s -> s.id == args.id}!!
        if (data != null) {
            view.findViewById<TextView>(R.id.nomPresta).text = data.prenom + " " + data.nom
            view.findViewById<TextView>(R.id.mailPresta).text = data.mail
            view.findViewById<TextView>(R.id.pseudoPrest).text = data.username
        }

        (activity as AppCompatActivity?)?.supportActionBar?.title = "Detail evenement"
    }

}