package com.ffc.geekyevent.vue;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment;
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.ffc.geekyevent.R
import com.ffc.geekyevent.databinding.FragmentAddEventBinding
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
        val data = standViewModel.listeStand
        val args = standViewModel.listeEvenement.size+1
        val adapter = data.filter { it.presta == standViewModel.user?.id}.map { it.nom }
        val newAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, adapter)
        newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dataB.spinner2.adapter = newAdapter

        dataB.button4.setOnClickListener{
            withTimePicker(view, 1)
        }
        dataB.button5.setOnClickListener{
            withTimePicker(view, 0)
        }

        dataB.button3.setOnClickListener{
            val stand = data.find { it.nom == dataB.spinner2.selectedItem } as Stand
            if (!((dataB.textView10.text.split("h")[0].toInt() <= dataB.textView12.text.split("h")[0].toInt() && dataB.textView10.text.split("h")[1].toInt() < dataB.textView12.text.split("h")[1].toInt()) || (dataB.textView10.text.split("h")[0].toInt() < dataB.textView12.text.split("h")[0].toInt()))){
                Toast.makeText(view.context,"L'heure de debut doit etre inferieur a l'heure de fin",Toast.LENGTH_LONG).show()
            } else {
                val heureDebut =
                    dataB.textView10.text.toString()
                val heureFin =
                    dataB.textView12.text.toString()
                standViewModel.addEvent(args, dataB.editTextTextPersonName2.text.toString(), heureDebut, heureFin, stand, dataB.editTextNumber3.text.toString().toInt())
                Toast.makeText(view.context,"Event $args ajoute avec succes",Toast.LENGTH_LONG).show()
                view.findNavController().navigate(AddEventDirections.actionAddEventToFragmentEvents())
            }
        }

    }

    fun withTimePicker(view: View, i: Int) {
        val builder = context?.let { AlertDialog.Builder(it) }
        val inflater = layoutInflater
        if (builder != null) {
            val dialogLayout = inflater.inflate(R.layout.popup, null)
            val editText  = dialogLayout.findViewById<TimePicker>(R.id.timePicker)
            builder.setView(dialogLayout)
            builder.setPositiveButton("OK") { dialog, which ->
                if (i == 1){
                    dataB.textView10.text = editText.hour.toString() + "h" + editText.minute.toString()
                } else {
                    dataB.textView12.text = editText.hour.toString() + "h" + editText.minute.toString()
                }
            }
            builder.show()
        }

    }

}
