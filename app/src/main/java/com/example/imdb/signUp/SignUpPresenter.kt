package com.example.imdb.signUp

import android.content.Context
import com.example.imdb.database.User

class SignUpPresenter(
    private val signView:SignUpContract.View,
    private val signModel:SignUpContract.Model,
    private val applicationContext: Context
):SignUpContract.Presenter {

    override suspend fun addUserToDatabase() {
        val user = signView.getUserData()
        val db = signModel.dbConnect(applicationContext)
        db.addUser(user)
    }

    override fun emailFound(email:String): Boolean {
        val db = signModel.dbConnect(applicationContext)
        var result = false
        val user = db.getUserByEmail(email) ?: User("NA", "NA", "NA")
        if (user.email == email){
            result= true
        }
        return result
    }

    override fun usersList(){
        val db = signModel.dbConnect(applicationContext)
        val users = db.readAllData()
        for (i in users) {
            println("${i.name} - ${i.email} - ${i.password}")
        }
    }
}