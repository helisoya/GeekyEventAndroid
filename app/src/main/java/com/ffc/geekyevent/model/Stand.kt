package com.ffc.geekyevent.model

data class Stand(
    val id: Int,
    val nom: String,
    val description: String,
    val typeStand: String,
    val presta: Int,
    val commentaires: MutableList<String>
)


