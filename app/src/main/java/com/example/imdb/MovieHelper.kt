package com.example.imdb

class MovieHelper{
    fun movieList(): List<Movie> {
        return listOf(
            Movie(
                "Duna",
                "Duna",
                "Duna",
                "Serie de TV 2022",
                "2022",
                arrayListOf("Ficcion", "Aventura"),
                arrayListOf("Alice Braga", "Hemry Madera"),
                "5.0",
                "Arrakis, también denominado \"Dune\", se ha convertido en el planeta más importante del universo. A su alrededor comienza una gigantesca lucha por el poder que culmina en una guerra interestelar.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1TrP258UMWJi9MICqYW3o7q1dQE7wCnYNdq6hooH6LKA6-L20",
                "https://as01.epimg.net/meristation/imagenes/2021/09/21/noticias/1632198001_790832_1632198112_noticia_normal.jpg",
                null
            ),

            Movie(
                "Encanto",
                "Encanto",
                "Encanto",
                "movie",
                "2022",
                arrayListOf("Infantil", "Familia"),
                arrayListOf("Alice Braga", "Hemry Madera"),
                "4.5",
                "Una joven colombiana puede ser la última esperanza para su familia, cuando descubre que la magia que rodea a Encanto, un lugar encantado que bendice a los niños con dones únicos, se encuentra en serio peligro",
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSsifNBbCd9akddbx7-cIBMNVyZ2TXCR5ptbIx9M9_lt0KhRp6M",
                "https://static01.nyt.com/images/2022/03/16/arts/17encanto-reax-ESP-00/merlin_196958868_626089bd-510c-455b-831f-4fc2ef4738a9-articleLarge.jpg?quality=75&auto=webp&disable=upscale",
                null
            ),

            Movie(
                "Doctor Strange",
                "Doctor Strange",
                "Doctor Strange en el multiverso de la locura",
                "movie",
                "2022",
                arrayListOf("Ficcion", "Aventura"),
                arrayListOf("Elizabeth Olsen", "Benedict Cumberba.."),
                "4.9",
                "El Dr. Stephen Strange abre un portal al multiverso al utilizar un hechizo prohibido. Ahora su equipo debe enfrentarse a una amenaza enorme.",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRMcTX7_0hH2qslKSEgZ7G1guoeD0EQic8qMCAgQRO_tng1xfAP",
                "https://i.blogs.es/2e407d/doctor-strange-en-el-multiverso-de-la-locura-cartel-poster/1366_2000.jpeg",
                null
            ),

            Movie(
                "Hombre araña",
                "Spider-Man",
                "SpiderMan",
                "movie",
                "2021",
                arrayListOf("ficcion", "aventura"),
                arrayListOf("Tom Holand", "Zendaya"),
                "5.0",
                "Tras descubrirse la identidad secreta de Peter Parker como Spider-Man, la vida del joven se vuelve una locura. Peter decide pedirle ayuda al Doctor Extraño para recuperar su vida.",
                "https://mx.web.img3.acsta.net/pictures/21/11/25/18/23/3142881.jpg",
                "https://www.gamerfocus.co/wp-content/uploads/2022/05/spider-man_sin_camino_a_casa.png",
                null
            ),

            Movie(
                "Rojo",
                "Red",
                "Red",
                "Movie",
                "2022",
                arrayListOf("infantil", "drama"),
                arrayListOf("Jordan Fisher", "Rosalie Chiang"),
                "3.5",
                "Mei Lee, una niña de 13 años un poco rara pero segura de sí misma, que se debate entre seguir siendo la hija obediente que su madre quiere que sea y el caos de la adolescencia.",
                "https://static.wikia.nocookie.net/disney/images/b/be/Turning_Red_Poster.jpg/revision/latest?cb=20220117230032&path-prefix=es",
                "https://cdn.milenio.com/uploads/media/2022/03/09/turning-red-la-nueva-pelicula.jpg",
                null
            ),

            Movie(
                "Luna caida",
                "MoonFall",
                "MoonFall",
                "Movie",
                "2022",
                arrayListOf("accion", "ficcion"),
                arrayListOf("Halle Berry", "Patrick Wilson"),
                "5.0",
                "Una fuerza misteriosa golpea a la Luna fuera de su órbita y la envía en choque directo contra la Tierra a toda velocidad. Unas semanas antes del impacto con el mundo al borde de la aniquilación,",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcR-XqVXZ0iIsZhUa4OX9qdJBj11wnKMvuzC7aCeP4I2lmM7iPz5",
                "https://d9u8u3s4.rocketcdn.me/wp-content/uploads/2022/02/Moonfall-pelicula-3-1155x640.jpg",
                null
            ),

            Movie(
                "Gambito de Dama",
                "Queens Gambit",
                "the queen gambit",
                "Miniserie de TV 2020",
                "2020",
                arrayListOf("Drama"),
                arrayListOf("Anya Taylor‑Joy", "Thomas Brodie"),
                "4.5",
                "La huérfana y prodigio del ajedrez, Beth Harmon, lucha contra la adicción mientras busca convertirse en la mejor jugadora de ajedrez del mundo.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTB_LbPYzENOay4oOfpHaXM4-HU9-KRl5Amqc99wKmPPAH5FKgC",
                "https://criticasinspoilers.com/wp-content/uploads/2020/12/titulo-copia.jpg",
                17
            ),

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
            ),
            )
    }
}