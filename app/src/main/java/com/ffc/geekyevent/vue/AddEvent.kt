package com.ffc.geekyevent.vue;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment;
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.ffc.geekyevent.R
import com.ffc.geekyevent.databinding.FragmentAddEventBinding
import com.ffc.geekyevent.databinding.FragmentAddStandBinding
import com.ffc.geekyevent.model.Prestataire
import com.ffc.geekyevent.model.Stand
import com.ffc.geekyevent.viewmodel.StandViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [AddEvent.newInstance] factory method to
 * create an instance of this fragment.
 */

class AddEvent: Fragment(){

    lateinit var dataB: FragmentAddEventBinding
    private val standViewModel : StandViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataB = DataBindingUtil.inflate(inflater,R.layout.fragment_add_event,container,false)
        return dataB.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataB.editTextTime.setIs24HourView(true)
        dataB.editTextTime3.setIs24HourView(true)
        val data = standViewModel.listeStand
        val args = standViewModel.listeEvenement.size+1
        val adapter = data.filter { it.presta == standViewModel.user?.id}.map { it.nom }
        val newAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, adapter)
        newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dataB.spinner2.adapter = newAdapter

        dataB.button3.setOnClickListener{
            val stand = data.find { it.nom == dataB.spinner2.selectedItem } as Stand
            if (!((dataB.editTextTime3.hour <= dataB.editTextTime.hour && dataB.editTextTime3.minute < dataB.editTextTime.minute) || (dataB.editTextTime3.hour < dataB.editTextTime.hour))){
                Toast.makeText(view.context,"L'heure de debut doit etre inferieur a l'heure de fin",Toast.LENGTH_LONG).show()
            } else {
                val heureDebut =
                    dataB.editTextTime3.hour.toString() + "h" + dataB.editTextTime3.minute.toString()
                val heureFin =
                    dataB.editTextTime.hour.toString() + "h" + dataB.editTextTime.minute.toString()
                standViewModel.addEvent(args, dataB.editTextTextPersonName2.text.toString(), heureDebut, heureFin, stand, dataB.editTextNumber3.text.toString().toInt())
                Toast.makeText(view.context,"Event $args ajoute avec succes",Toast.LENGTH_LONG).show()
                view.findNavController().navigate(AddEventDirections.actionAddEventToFragmentEvents())
            }
        }

    }

}
