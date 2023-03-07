package com.ffc.geekyevent.viewmodel

import android.app.Application
import android.graphics.Paint
import android.graphics.Rect
import android.util.Half.toFloat
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.model.Stand
import org.simpleframework.xml.*
import org.simpleframework.xml.core.Persister
import java.io.InputStreamReader
data class RectStand(val x : Int,val y: Int, val width:Int,val height : Int,val id:String) {
    val rect = Rect(x,y,width,height)
    var color = Paint()
    init{
        color.setARGB(255,0,0,0)
    }
    override fun toString(): String {
        return "RectStand(x=$x, y=$y, width=$width, height=$height, id='$id', rect=$rect, color=$color)"
    }

}
class ViewModel(application: Application) : AndroidViewModel(application) {
    private var _listeStand : List<Stand>
    val listeStand :List<Stand>
        get() = _listeStand

    val localisationStand : Svg
    var standFormate: MutableList<RectStand> = ArrayList()
    var fontFomate: MutableList<Rect> = ArrayList()

    init{
        _listeStand = Datasource().loadStand()
        localisationStand = loadSVG()
        localisationStand.rect.forEach { r-> Log.i("aa",r.toString()) }
    }

    fun loadSVG() : Svg{
        val xmlToParse = InputStreamReader(getApplication<Application>().resources.openRawResource(R.raw.carte)).readText()
        val serializer: Serializer = Persister()
        val res= serializer.read(Svg::class.java, xmlToParse)
        return res
    }

    fun annalyserClick(x:Float,y:Float){
        Log.i("ImageView", "$x $y")
        Log.i("ImageView", localisationStand.rect[0].toString())
        val res= standFormate.find { r-> x> r.rect.left.toFloat() &&
                                    x<r.rect.right.toFloat()  &&
                                    y>r.rect.top.toFloat()  &&
                                    y<r.rect.bottom.toFloat()  }
        Log.i("ImageView",res.toString())
        res?.color?.setARGB(128,255,0,0)
    }

    fun formatRect(propX: Int, propY: Int) {
        localisationStand.rect.forEach { r->
            standFormate.add(RectStand(r.x.toDouble().toInt()*propX,
                r.y.toDouble().toInt()*propY,
                (r.x.toDouble().toInt()+r.width.toDouble().toInt())*propX,
                (r.y.toDouble().toInt()+r.height.toDouble().toInt())*propY,r.id))
        }
        localisationStand.g.rect.forEach { r->
            fontFomate.add(Rect(r.x.toDouble().toInt()*propX,
                r.y.toDouble().toInt()*propY,
                (r.x.toDouble().toInt()+r.width.toDouble().toInt())*propX,
                (r.y.toDouble().toInt()+r.height.toDouble().toInt())*propY))
        }
    }
}

@Root(name = "svg", strict = false)
class Svg {
    @field:ElementList(inline = true, entry = "rect")
    lateinit var rect: List<RectSVG>

    @field:Element(name="g")
    lateinit var g:classG
}
@Root(strict = false, name = "g")
class classG {
    @field:ElementList(inline = true, entry = "rect")
    lateinit var rect: List<RectSVG>
}
@Root(strict = false, name = "rect")
class RectSVG {
    @field:Attribute(name = "id", required = true)
    var id:String=""
    @field:Attribute(name = "width", required = false)
    var width: String =""
    @field:Attribute(name = "height", required = false)
    var height: String =""
    @field:Attribute(name = "x", required = false)
    var x: String = ""
    @field:Attribute(name = "y", required = false)
    var y: String = ""
    override fun toString(): String {
        return "RectSVG(width=$width, height=$height, x=$x, y=$y, id=$id)"
    }

}