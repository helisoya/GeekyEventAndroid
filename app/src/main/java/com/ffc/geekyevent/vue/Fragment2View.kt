package com.ffc.geekyevent.vue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ffc.geekyevent.R

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2View.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2View : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2_view, container, false)
    }

}