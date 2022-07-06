package com.example.imdb.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
class CardsProfile(
    val title: String,
    val Description: String,
    val amount: Int = 0
): Parcelable