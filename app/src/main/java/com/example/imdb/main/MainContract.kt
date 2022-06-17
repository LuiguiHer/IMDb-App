package com.example.imdb.main

import android.content.Context
import com.example.imdb.database.User
import com.example.imdb.database.UserDao

interface MainContract {

    interface View{
        fun clearInputs()
        fun hideKeyboard()
        fun changeBtn()
        fun access(user: User?)
        fun getDataAccess():User
    }

    interface Presenter{
        fun existUser():User
    }

    interface Model{
        fun dbConnect(applicationContext: Context): UserDao
    }
}