package com.ffc.geekyevent.vue

import android.R.attr.left
import android.R.attr.right
import android.annotation.SuppressLint
import android.graphics.*
import android.os.Bundle
import android.util.Half.toFloat
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
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

    private lateinit var mCanvas :Canvas
    lateinit var mBitmap : Bitmap
    lateinit var mImageView : ImageView
    private val mPaint: Paint = Paint(Paint.UNDERLINE_TEXT_FLAG)

    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate =  inflater.inflate(R.layout.fragment_carte, container, false)

        return inflate
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mImageView = view.findViewById(R.id.imageView2)
        mImageView.setOnTouchListener(View.OnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                val res= viewModel.annalyserClick(event.x,event.y)
                if (res != null && res.exist) {
//                    viewModel.color(res)
//                    drawSomething()
                    view.findNavController().navigate(carteFragmentDirections.actionCarteFragmentToDetailStand2(res.id.toInt()))
                }
            }
            return@OnTouchListener true
        })

        drawSomething()
    }



    fun drawSomething() {
        val colorBlanc = Paint(Paint.LINEAR_TEXT_FLAG)
        colorBlanc.setARGB(255,255,255,255)
        colorBlanc.textSize=50F

        mBitmap = Bitmap.createBitmap(viewModel.WIDTH, viewModel.HEIGHT, Bitmap.Config.ARGB_8888)
        mImageView.setImageBitmap(mBitmap);
        mCanvas = Canvas(mBitmap)
        mCanvas.drawColor(0)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.colorFond,null);
        viewModel.fontFomate.forEach {r->
            mCanvas.drawRect(r,mPaint)
        }
        viewModel.standFormate.forEach {r->
//            mPaint.color=r.color
            mCanvas.drawRect(r.rect,r.color)
            mCanvas.drawText(r.id,((r.x+r.width)/2-45).toFloat(),r.rect.bottom.toFloat()-20,colorBlanc)
        }
    }
}
