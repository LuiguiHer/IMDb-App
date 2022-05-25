package com.example.imdb.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_tab"
)
data class User(
    @PrimaryKey()
    val email: String,
    val name: String,
    val password: String
) {
}