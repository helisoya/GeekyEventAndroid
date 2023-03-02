package com.ffc.geekyevent.model

data class Stand(
    val id: Int,
    val nom: String,
    val description: String,
    val typeStand: String,
    val presta: String
)

class Datasource {

    fun loadStand(): List<Stand> {
        return listOf(
            Stand(300, "stand 300", "voici une descirption", "boutique", "presta1"),
            Stand(310, "stand 310", "voici une descirption", "temporaire", "presta1"),
            Stand(305, "stand 305", "voici une descirption", "normal", "presta2"),
            Stand(306, "stand 306", "voici une descirption", "normal", "presta3"),
            Stand(307, "stand test de tournoi", "fuiekhjkgrtgjrtlgjr", "tournoi", "56"),
            Stand(320, "stand 300", "voici une descirption", "boutique", "presta1"),
            Stand(320, "stand 310", "voici une descirption", "temporaire", "presta1"),
            Stand(325, "stand 305", "voici une descirption", "normal", "presta2"),
            Stand(326, "stand 306", "voici une descirption", "normal", "presta3"),
            Stand(327, "stand test de tournoi", "fuiekhjkgrtgjrtlgjr", "tournoi", "56")
        )
    }
}
