package com.ffc.geekyevent.vue

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.ffc.geekyevent.R
import com.ffc.geekyevent.databinding.FragmentConnectionBinding
import com.ffc.geekyevent.viewmodel.StandViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [Connection.newInstance] factory method to
 * create an instance of this fragment.
 */
class Connection : Fragment() {

    lateinit var dataBinding: FragmentConnectionBinding
    private val standViewModel: StandViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_connection,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val t = view.findViewById<TextView>(R.id.messageValidConnection)
        if (standViewModel.isconnected)
            t.text="Bienvenue : "+ standViewModel.user?.username
        else
            t.text=""

        view.findViewById<Button>(R.id.ButtonConnection).setOnClickListener {
            if (standViewModel.connection(dataBinding.InputPseudo.text.toString(),dataBinding.InputPassword.text.toString())) {
                Toast.makeText(context, "OK", Toast.LENGTH_LONG).show()
                t.text="Bienvenue : "+ standViewModel.user?.username
                t.setBackgroundColor(Color.GREEN);
            }
            else {
                Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show()
                t.text="Mauvais identifiant ou mot de passe"
                t.setBackgroundColor(Color.RED);
            }
        }
    }

}