package com.ffc.geekyevent.vue

import android.annotation.SuppressLint
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.ffc.geekyevent.R
import com.ffc.geekyevent.viewmodel.StandViewModel
import org.simpleframework.xml.*
import java.io.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [carteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class carteFragment : Fragment() {

    private lateinit var mCanvas :Canvas
    lateinit var mBitmap : Bitmap
    lateinit var mImageView : ImageView
    private val mPaint: Paint = Paint(Paint.UNDERLINE_TEXT_FLAG)

    private val standViewModel: StandViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Carte"
        return inflater.inflate(R.layout.fragment_carte, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mImageView = view.findViewById(R.id.imageView2)
        mImageView.setOnTouchListener{ view, event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                val res= standViewModel.annalyserClick(event.x,event.y)
//                    drawSomething()
                if (res != null && res.exist) {
                    view.findNavController().navigate(carteFragmentDirections.actionCarteFragmentToDetailStand2(res.id.toInt()))
                }
            }
            return@setOnTouchListener true
        }
        view.findViewById<Button>(R.id.button2).setOnClickListener {
            view.findNavController().navigate(carteFragmentDirections.actionCarteFragmentToVueStandFragment())
        }
        drawSomething()
    }



    fun drawSomething() {
        val colorBlanc = Paint(Paint.LINEAR_TEXT_FLAG)
        colorBlanc.setARGB(255,255,255,255)
        colorBlanc.textSize=50F

        mBitmap = Bitmap.createBitmap(standViewModel.WIDTH, standViewModel.HEIGHT, Bitmap.Config.ARGB_8888)
        mImageView.setImageBitmap(mBitmap);
        mCanvas = Canvas(mBitmap)
        mCanvas.drawColor(0)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.colorFond,null);
        standViewModel.fontFomate.forEach { r->
            mCanvas.drawRect(r,mPaint)
        }
        standViewModel.standFormate.forEach { r->
            mCanvas.drawRect(r.rect,r.color)
            mCanvas.drawText(r.id,((r.x+r.width)/2-45).toFloat(),r.rect.bottom.toFloat()-20,colorBlanc)
        }
    }

}
