package com.ffc.geekyevent.vue

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.ffc.geekyevent.R
import com.ffc.geekyevent.databinding.FragmentAddStandBinding
import com.ffc.geekyevent.model.Prestataire
import com.ffc.geekyevent.viewmodel.StandViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [AddStand.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddStand : Fragment() {
    lateinit var dataB: FragmentAddStandBinding
    private val standViewModel : StandViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_stand, container, false)
        dataB = DataBindingUtil.inflate(inflater,R.layout.fragment_add_stand,container,false)
        return dataB.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = AddStandArgs.fromBundle(requireArguments()).nstand
        dataB.textView6.setText("Ajouter un stand ($args)", TextView.BufferType.EDITABLE)

        val data = standViewModel.listePresta
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dataB.spinner.adapter = adapter

        val typeStand = standViewModel.listeTypeStand
        val adapterTypeStand = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, typeStand)
        adapterTypeStand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dataB.editTypeStand.adapter = adapterTypeStand

        dataB.button.setOnClickListener {
            val presta = dataB.spinner.selectedItem as Prestataire
            standViewModel.addStand(args.toInt(),dataB.editNomStand.text.toString(),dataB.editDescriptionStand.text.toString(),
                dataB.editTypeStand.selectedItem.toString(),presta)
            Toast.makeText(view.context,"Stand $args ajoute avec succes",Toast.LENGTH_LONG).show()
            view.findNavController().navigate(AddStandDirections.actionAddStandToCarteFragment())
        }
    }

}