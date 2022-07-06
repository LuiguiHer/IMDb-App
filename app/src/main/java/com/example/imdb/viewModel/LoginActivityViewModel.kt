package com.example.imdb.viewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdb.database.User
import com.example.imdb.model.ModelDatabase.Companion.db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivityViewModel (application: Application): AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    private val connect = db(context)

    val liveUser = MutableLiveData<String>()
    val livePassword = MutableLiveData<String>()
    val liveErrorPassword = MutableLiveData<String>()
    val liveErrorUser = MutableLiveData<String>()
    val liveUserValidated = MutableLiveData<User>()
    val liveUserName= MutableLiveData<String>()



    fun existUser(){
        val user = validateUser()
        viewModelScope.launch(Dispatchers.IO) {
            val userFound: User? = connect.getUserByEmailPass(user.email, user.password)
            liveUserValidated.postValue(userFound)
        }
    }

    private fun validateUser(): User {
        val user = liveUser.value
        val pass = livePassword.value
        return user?.let { pass?.let { it1 -> User(it,"", it1) } } ?: User("","","")
    }


}