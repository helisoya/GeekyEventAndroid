package com.ffc.geekyevent.vue

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.ffc.geekyevent.R
import com.ffc.geekyevent.databinding.FragmentHomeBinding
import com.ffc.geekyevent.viewmodel.StandViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val standViewModel: StandViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false);

        binding.button.setOnClickListener { view : View ->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFragment2View())
        }
        binding.button3.setOnClickListener { view->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToVueStandFragment())
        }
        binding.button4.setOnClickListener { view->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCarteFragment())
        }

        binding.textView5.text = "Nombre de stands sur l'vevenement : "+standViewModel.listeStand.size

//        setHasOptionsMenu(true)//menu option (3 petits points)
        return binding.root
    }

    //clic sur menu option
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.menu_option, menu)
//    }
}