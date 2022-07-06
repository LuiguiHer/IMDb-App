package com.example.imdb.viewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdb.database.User
import com.example.imdb.model.ModelDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivityViewModel (application: Application): AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    private val connection = ModelDatabase.db(context)

    val liveName = MutableLiveData<String>()
    val liveEmail = MutableLiveData<String>()
    val livePassword = MutableLiveData<String>()
    val liveEmailFound= MutableLiveData<Boolean>()
    val liveUserAdded= MutableLiveData<Boolean>()
    val liveNoEmpty= MutableLiveData(false)
    val liveValidatedPass= MutableLiveData<Boolean>()


    fun emailFound(){
        viewModelScope.launch(Dispatchers.IO) {
            val result: User? = connection.getUserByEmail(liveEmail.value!!)
            if(result != null){
                liveEmailFound.postValue(false)
            }else{
                liveEmailFound.postValue(true)
            }
        }
    }

    fun addUserDb(){
        val user = makeUser()
        viewModelScope.launch(Dispatchers.IO) {
            connection.addUser(user)
            liveUserAdded.postValue(true)
        }
    }

    private fun makeUser(): User {
        return User(liveEmail.value!!,liveName.value!!,livePassword.value!!)
    }

    fun dontEmpty(){
        liveNoEmpty.value =liveEmail.value !=null && liveName.value!=null && livePassword.value!=null && liveName.value !=""
    }

    fun validatePassword(){
        liveValidatedPass.value = livePassword.value!!.length >= 8
    }
}