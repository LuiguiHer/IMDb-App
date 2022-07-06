package com.example.imdb.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.model.ModelDatabase.Companion.username


class HomeActivityViewModel: ViewModel() {

    val getUserName = MutableLiveData<String>()

    fun sendDataToStore(){
        username = getUserName.value.toString()
    }
}