package com.ffc.geekyevent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ffc.geekyevent.model.Datasource
import com.ffc.geekyevent.model.Stand

class ViewModel : ViewModel() {
    private var _listeStand : List<Stand>
    val listeStand :List<Stand>
        get() = _listeStand

    init{
        _listeStand = Datasource().loadStand()
    }
}