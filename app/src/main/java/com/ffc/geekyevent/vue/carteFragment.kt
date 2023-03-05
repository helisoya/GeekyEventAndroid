package com.ffc.geekyevent.vue

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ffc.geekyevent.R
import com.ffc.geekyevent.viewmodel.ViewModel
import org.simpleframework.xml.*
import java.io.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [carteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class carteFragment : Fragment() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate =  inflater.inflate(R.layout.fragment_carte, container, false)

        return inflate;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            var m = context?.resources?.displayMetrics!!
            Log.i("ImageView","${m.density},${m.densityDpi},${m.widthPixels},${m.heightPixels},${m.xdpi},${m.ydpi}");
        view.findViewById<ImageView>(R.id.imageCarte).setOnTouchListener { view, motionEvent ->
            Log.i("ImageView",motionEvent.toString());
            Log.i("ImageView",motionEvent.x.toString());
            var m = context?.resources?.displayMetrics!!
            Log.i("ImageView","${m.density},${m.densityDpi},${m.widthPixels},${m.heightPixels},${m.xdpi},${m.ydpi}");

            viewModel.annalyserClick(motionEvent.x/m.widthPixels*116,motionEvent.y)
            true;
        }
    }

    private fun dpToPixel(dp: Float): Float {
//        val metrics = this.resources.displayMetrics
//        return dp * (metrics.densityDpi / 160f)
//        return context.getResources().getDimensionPixelSize(dp)(Float);
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, dp, context?.resources?.displayMetrics)
    }
}
