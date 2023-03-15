package com.ffc.geekyevent.viewmodel

import android.app.Application
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.ffc.geekyevent.R
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.model.Event
import com.ffc.geekyevent.model.Prestataire
import com.ffc.geekyevent.model.Stand
import org.simpleframework.xml.*
import org.simpleframework.xml.core.Persister
import java.io.InputStreamReader

class StandViewModel(application: Application) : AndroidViewModel(application) {
    private var _listeStand : List<Stand>
    val listeStand :List<Stand>
        get() = _listeStand

    private var _listePresta : List<Prestataire>
    val listePresta : List<Prestataire>
        get() = _listePresta

    private var _listeEvenement : List<Event>
    val listeEvenement : List<Event>
        get() = _listeEvenement

    var standFormate = ArrayList<RectStand>()
    var fontFomate = ArrayList<Rect>()

    val WIDTH = 1700;
    val HEIGHT = 1200;

    var isconnected =false
    var user:Prestataire? =null

    init{
        _listeStand = Datasource().loadStand()//data stand
        _listePresta = Datasource().loadPrestataires()
        _listeEvenement = Datasource().loadEvents()
        loadCarte(application,WIDTH/126,HEIGHT/88)//svg pour afficher carte
        syncCarteWithModel()
        user = _listePresta[0]
        isconnected =true
    }

    fun annalyserClick(x:Float,y:Float) : RectStand? {
        val res= standFormate.find { r-> x> r.rect.left.toFloat() &&
                                    x<r.rect.right.toFloat()  &&
                                    y>r.rect.top.toFloat()  &&
                                    y<r.rect.bottom.toFloat()  }
        res?.color?.setARGB(128,255,0,0)
        return res
    }

    fun connection(pseudo: String,password:String):Boolean{
        val res= _listePresta.find { p->
            p.username==pseudo && p.password==password
        }
        if (res!=null){
            isconnected=true
            user =res
            return true
        }else
            return false
    }


    fun syncCarteWithModel(){
        listeStand.forEach { s->
            val res = standFormate.find{r-> r.id.toInt()==s.id}
            if (res!=null){
                res.color.setARGB(128,255,0,0)
                res.exist=true
            }
        }
    }
    fun loadCarte(application: Application,propX: Int, propY: Int) {
        val standSVG = LoadCarte(application).loadSVG()
        standSVG.rect.forEach { r->
            standFormate.add(RectStand(r.x.toDouble().toInt()*propX,
                r.y.toDouble().toInt()*propY,
                (r.x.toDouble().toInt()+r.width.toDouble().toInt())*propX,
                (r.y.toDouble().toInt()+r.height.toDouble().toInt())*propY,r.id))
        }
        standSVG.g.rect.forEach { r->
            fontFomate.add(Rect(r.x.toDouble().toInt()*propX,
                r.y.toDouble().toInt()*propY,
                (r.x.toDouble().toInt()+r.width.toDouble().toInt())*propX,
                (r.y.toDouble().toInt()+r.height.toDouble().toInt())*propY))
        }
    }

}