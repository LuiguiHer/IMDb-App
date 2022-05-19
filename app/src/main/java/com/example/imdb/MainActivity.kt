package com.example.imdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.imdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputPass.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                changeBtn()
            }
        }

        binding.inputUser.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus ){
                changeBtn()
            }
        }

    }

    fun changeBtn (){
        if(binding.inputUser.text.toString().isNotEmpty() || binding.inputPass.text.toString().isNotEmpty()){
            binding.btnLogin.setBackgroundResource(R.drawable.button_secondary)}
        else
            binding.btnLogin.setBackgroundResource(R.drawable.button_principal)
    }


}