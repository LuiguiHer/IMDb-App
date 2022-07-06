package com.example.imdb.model

class MovieHelper{
    fun movieList(): List<Movie> {
        return listOf(
            Movie(
                "Stranger Things",
                "Stranger Things",
                "Stranger Things",
                "Serie de TV 2016",
                "2022",
                arrayListOf("Misterio", "Drama"),
                arrayListOf("Finn Wolfhard", "Millie Bobby Brown"),
                "5.0",
                "Cuando un niño desaparece, sus amigos, la familia y la policía se ven envueltos en una serie de eventos misteriosos al tratar de encontrarlo.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMjEzMDAxOTUyMV5BMl5BanBnXkFtZTgwNzAxMzYzOTE@._V1_.jpg",
                "https://sm.ign.com/ign_es/feature/s/stranger-t/stranger-things-season-4-exclusive-trailer-breakdown-with-th_81ku.jpg",
                32
            )
        )
    }
}