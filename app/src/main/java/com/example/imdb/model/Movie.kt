package com.example.imdb.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

@Parcelize
data class Movie(
    val title: String,
    val subTitle: String,
    val titleOrigin: String,
    val type: String,
    val year: String,
    val genders: ArrayList<String>,
    val principal_actors: ArrayList<String>,
    val rating: String,
    val synopsis: String,
    val imageUrl: String,
    val videoUrl: String,
    val chapters: Int?
): Parcelable