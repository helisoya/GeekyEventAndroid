package com.ffc.geekyevent.model

class Datasource {

    fun loadTypeStand(): List<String> {
        return listOf("boutique", "temporaire","normal","tournoi")
    }

    fun loadStand(): List<Stand> {
        val typeStand = loadTypeStand()
        return listOf(
            Stand(300, "stand 300", "voici une description (stand avec commentaire)",
                typeStand[0], 1, mutableListOf("bien","parfait","merveilleux stand")),
            Stand(310, "stand 310", "voici une descirption (stand avec commentaire)",
                typeStand[1], 1, mutableListOf("hkgjdf","fchbekjfsc","fyusdhc","gfysdjc")),
            Stand(305, "stand 305", "voici une descirption", typeStand[2], 2, mutableListOf()),
            Stand(306, "stand 306", "voici une descirption", typeStand[2], 3, mutableListOf()),
            Stand(307, "stand test de tournoi", "fuiekhjkgrtgjrtlgjr", typeStand[3], 3, mutableListOf()),
            Stand(320, "stand 320", "voici une descirption", typeStand[0], 1, mutableListOf()),
            Stand(320, "stand 320", "voici une descirption", typeStand[1], 1, mutableListOf()),
            Stand(325, "stand 325", "voici une descirption", typeStand[2], 2, mutableListOf()),
            Stand(326, "stand 326", "voici une descirption", typeStand[2], 3, mutableListOf()),
            Stand(327, "stand test tournoi 327", "fuiekhjkgrtgjrtlgjr", typeStand[3], 2, mutableListOf())
        )
    }

    fun loadEvents(): List<Event> {
        return listOf(
            Event(1,307,"Tournoi Pierre Kart","8h","10h",18,14),
            Event(2,327,"Tournoi Survie JDR","6h","20h",5,1),
            Event(3,320,"Tournoi de regards","1h","24h",2,2)
        )
    }

    fun loadPrestataires(): List<Prestataire>{
        return  listOf(
            Prestataire(1,"Pierre","Pierre","pierre.express@fast.ru","pierre","pierre"),
            Prestataire(2,"Bach","Ludwig","bach.ludwig@world.conquest","bl","bl"),
            Prestataire(3,"Foutro","Mr.","foutro@linux.li","foutro","foutro")
        )
    }
}