package com.example.imdb

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
class CardsProfile(
    val title: String,
    val Description: String,
    val values: Int = 0
): Parcelable