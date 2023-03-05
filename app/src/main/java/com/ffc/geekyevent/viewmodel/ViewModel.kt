package com.ffc.geekyevent.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.model.Stand
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister
import java.io.InputStreamReader

class ViewModel(application: Application) : AndroidViewModel(application) {
    private var _listeStand : List<Stand>
    val listeStand :List<Stand>
        get() = _listeStand

    val localisationStand : Svg

    init{
        _listeStand = Datasource().loadStand()
        localisationStand = loadSVG()
        localisationStand.rect.forEach { r-> Log.i("aa",r.toString()) }
    }

    fun loadSVG() : Svg{
        val xmlToParse = InputStreamReader(getApplication<Application>().resources.openRawResource(R.raw.carte)).readText()
        val serializer: Serializer = Persister()
        return serializer.read(Svg::class.java, xmlToParse)
    }

    fun annalyserClick(x:Float,y:Float){
        Log.i("ImageView", "$x $y")
        Log.i("ImageView", localisationStand.rect[0].toString())
        val res= localisationStand.rect.find { r-> x> r.x.toFloat() &&
                                    x<r.x.toFloat() +r.width.toFloat()  &&
                                    y>r.y.toFloat()  &&
                                    y<r.y.toFloat() +r.height.toFloat()  }
        Log.i("ImageView",res.toString())
    }
}


@Root(name = "svg", strict = false)
class Svg {
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