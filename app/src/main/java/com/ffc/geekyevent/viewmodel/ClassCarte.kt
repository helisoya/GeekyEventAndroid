package com.ffc.geekyevent.viewmodel

import android.app.Application
import android.graphics.Paint
import android.graphics.Rect
import com.ffc.geekyevent.R
import org.simpleframework.xml.*
import org.simpleframework.xml.core.Persister
import java.io.InputStreamReader

data class RectStand(val x : Int,val y: Int, val width:Int,val height : Int,val id:String) {
    val rect = Rect(x,y,width,height)
    var color = Paint()
    var exist = false
    init{
        color.setARGB(255,0,0,0)
    }
    override fun toString(): String {
        return "RectStand(x=$x, y=$y, width=$width, height=$height, id='$id', rect=$rect, color=$color)"
    }

}


class LoadCarte(val application: Application){
    fun loadSVG(): Svg {
        val xmlToParse = InputStreamReader(application.resources.openRawResource(R.raw.carte)).readText()
        val serializer: Serializer = Persister()
        val res= serializer.read(Svg::class.java, xmlToParse)
        return res
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
}