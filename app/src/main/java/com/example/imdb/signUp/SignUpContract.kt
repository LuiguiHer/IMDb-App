package com.example.imdb.signUp

import android.content.Context
import com.example.imdb.database.User
import com.example.imdb.database.UserDao

interface SignUpContract {

    interface View {
        fun clearInputs()
        fun clearKeyboard()
        fun password()
        fun name()
        fun email()
        fun getUserData(): User
        fun existUser(verifyEmail: Boolean)
    }

    interface Presenter{
        suspend fun addUserToDatabase()
        fun emailFound(email:String): Boolean
        fun usersList()
    }

    interface Model {
        fun dbConnect(applicationContext: Context): UserDao
    }
}