package com.ffc.geekyevent.vue

import android.R.attr.left
import android.R.attr.right
import android.annotation.SuppressLint
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
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

    private lateinit var mCanvas :Canvas
    lateinit var mBitmap : Bitmap
    lateinit var mImageView : ImageView
    private val mPaint: Paint = Paint(Paint.UNDERLINE_TEXT_FLAG)
    private val mRect: Rect = Rect()
    private val mBounds: Rect = Rect()
    private val OFFSET = 120
    private var mOffset = OFFSET

    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate =  inflater.inflate(com.ffc.geekyevent.R.layout.fragment_carte, container, false)

        return inflate
    }

    val WIDTH = 1700;
    val HEIGHT = 1200;
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mImageView = view.findViewById(com.ffc.geekyevent.R.id.imageView2)
        mImageView.setOnTouchListener(View.OnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                Log.i("aa","Touch coordinates : " +
                        event.x + "x" + event.y
                )
                drawSomething(WIDTH,HEIGHT)
            }
            return@OnTouchListener true
        })

//        val propX = WIDTH/126
//        val propY = HEIGHT/88
//        viewModel.formatRect(propX,propY)
//        drawSomething(WIDTH,HEIGHT)
    }

    override fun onStart() {
        super.onStart()
        val propX = WIDTH/126
        val propY = HEIGHT/88
        viewModel.formatRect(propX,propY)
        drawSomething(WIDTH,HEIGHT)
    }


    fun drawSomething(width:Int,height:Int) {
        val colorBack = ResourcesCompat.getColor(resources, R.color.colorBackground,null)

            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            mImageView.setImageBitmap(mBitmap);
            mCanvas = Canvas(mBitmap)
            mCanvas.drawColor(colorBack)

            mPaint.setColor(ResourcesCompat.getColor(resources, R.color.colorFond,null));
            viewModel.fontFomate.forEach {r->
                mCanvas.drawRect(r,mPaint)
            }
            mPaint.setColor(ResourcesCompat.getColor(resources, R.color.colorRectangle,null));
            viewModel.standFormate.forEach {r->
                mCanvas.drawRect(r,mPaint)
            }
    }
}
