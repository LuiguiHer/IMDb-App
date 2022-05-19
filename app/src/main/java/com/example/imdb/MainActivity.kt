package com.example.imdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtSignUp.setOnClickListener {
            val start = Intent(this, SignUpActivity::class.java)
            startActivity(start)
        }

        binding.inputPass.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                changeBtn()
            }
        }

        binding.inputUser.setOnFocusChangeListener { _, hasFocus ->
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