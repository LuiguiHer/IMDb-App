package com.example.imdb.model

class CardsProfileHelper {
    companion object{
        val listCardItems = cardsList()

        private fun cardsList(): List<CardsProfile> {
            return listOf(
                CardsProfile("Calificaciones", "Calificar y obtener recomendaciones", 0),
                CardsProfile("Listas", "Agregar a Listas", 4),
                CardsProfile("Comentarios", "Aun sin comentarios", 0),
            )
        }
    }
}