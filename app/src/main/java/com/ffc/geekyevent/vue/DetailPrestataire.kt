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
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Prestataire
import com.ffc.geekyevent.viewmodel.StandViewModel

class DetailPrestataire : Fragment() {

    private lateinit var data: Prestataire
    private val standViewModel: StandViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_prestataire, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailPrestataireArgs.fromBundle(requireArguments())


//        val source = Datasource()
        data = standViewModel.listePresta.find { s -> s.id == args.id}!!
        view.findViewById<TextView>(R.id.nomPresta).text = data.prenom + " " + data.nom
        view.findViewById<TextView>(R.id.mailPresta).text = data.mail
        view.findViewById<TextView>(R.id.pseudoPrest).text = data.username

        (activity as AppCompatActivity?)?.supportActionBar?.title = "Detail evenement"
    }

}