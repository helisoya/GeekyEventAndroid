package com.ffc.geekyevent.model

data class Event(
    val id: Int,
    val idStand: Int,
    val nom: String,
    val dateDebut: String,
    val dateFin: String,
    val nbJoueursMax: Int,
    val nbJoueursInscrit: Int
)
