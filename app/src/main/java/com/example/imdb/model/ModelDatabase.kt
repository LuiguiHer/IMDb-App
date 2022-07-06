package com.example.imdb.model

import android.content.Context
import androidx.room.Room
import com.example.imdb.database.UserDao
import com.example.imdb.database.UserDatabase

class ModelDatabase {
    companion object{
        var username = ""
        fun db(applicationContext: Context): UserDao {
            val db = Room.databaseBuilder(
                applicationContext, UserDatabase::class.java,
                "data_user"
            ).build()
            return db.userDao()
        }

    }
}