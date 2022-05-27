package com.example.imdb

data class Movie(
    val title: String,
    val titleOrigin: String,
    val type: String,
    val year: Int,
    val genders: List<String>,
    val rating: Int,
    val synopsis: String,
    val imageUrl: String,
    val videoUrl: String,
    val chapters: Int?
) {
}