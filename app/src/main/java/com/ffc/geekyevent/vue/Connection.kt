package com.ffc.geekyevent.vue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import com.ffc.geekyevent.R
import com.ffc.geekyevent.databinding.FragmentConnectionBinding
import com.ffc.geekyevent.model.Prestataire
import com.ffc.geekyevent.viewmodel.StandViewModel
import com.google.android.material.button.MaterialButton

/**
 * A simple [Fragment] subclass.
 * Use the [Connection.newInstance] factory method to
 * create an instance of this fragment.
 */
class Connection : Fragment() {

    lateinit var pseudo : MaterialButton
    lateinit var password : MaterialButton

    lateinit var dataBinding: FragmentConnectionBinding
    private val standViewModel: StandViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_connection,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.ButtonConnection).setOnClickListener { view->
            if (verif(dataBinding.InputPseudo.text.toString(),dataBinding.InputPassword.text.toString())!=null)
                Toast.makeText(context,"OK",Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context,"ERROR",Toast.LENGTH_LONG).show()
        }
    }

    fun verif(pseudo: String,password:String):Prestataire?{
        return standViewModel.listePresta.find { p->
            p.username==pseudo && p.password==password
        }
    }
}