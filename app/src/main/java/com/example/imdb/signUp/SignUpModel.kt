package com.example.imdb.signUp

import android.content.Context
import androidx.room.Room
import com.example.imdb.database.UserDao
import com.example.imdb.database.UserDatabase

class SignUpModel: SignUpContract.Model {

    override fun dbConnect(applicationContext: Context): UserDao {
        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,
            "data_user"
        ).allowMainThreadQueries().build()
        return db.userDao()
    }
}