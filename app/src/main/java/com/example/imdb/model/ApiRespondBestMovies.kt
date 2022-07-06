package com.example.imdb.model

data class ApiRespondBestMovies(
    val page:Int,
    val results:List<Movies>,
    val total_pages:Int,
    val total_results:Int
)