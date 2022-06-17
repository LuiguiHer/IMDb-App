package com.example.imdb.main

import android.content.Context
import com.example.imdb.database.User

class MainPresenter(
    private val mainView: MainContract.View,
    private val mainModel: MainContract.Model,
    private val applicationContext: Context
):MainContract.Presenter {

    override fun existUser(): User {
        val db= mainModel.dbConnect(applicationContext)
        val user = mainView.getDataAccess()
        return db.getUserByEmailPass(user.email, user.password)
    }
}