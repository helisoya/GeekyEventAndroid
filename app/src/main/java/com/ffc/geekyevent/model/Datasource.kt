package com.ffc.geekyevent.model

class Datasource {

    fun loadStand(): List<Stand> {
        return listOf(
            Stand(300, "stand 300", "voici une descirption", "boutique", 1),
            Stand(310, "stand 310", "voici une descirption", "temporaire", 1),
            Stand(305, "stand 305", "voici une descirption", "normal", 2),
            Stand(306, "stand 306", "voici une descirption", "normal", 3),
            Stand(307, "stand test de tournoi", "fuiekhjkgrtgjrtlgjr", "tournoi", 3),
            Stand(320, "stand 300", "voici une descirption", "boutique", 1),
            Stand(320, "stand 310", "voici une descirption", "temporaire", 1),
            Stand(325, "stand 305", "voici une descirption", "normal", 2),
            Stand(326, "stand 306", "voici une descirption", "normal", 3),
            Stand(327, "stand test de tournoi", "fuiekhjkgrtgjrtlgjr", "tournoi", 2)
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