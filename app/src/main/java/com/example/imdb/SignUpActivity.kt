package com.example.imdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imdb.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.inputName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){name()}
        }
        binding.inputEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){email()}
        }
        binding.inputPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){password()}
        }


        binding.btnBack.setOnClickListener {
            val start = Intent(this, MainActivity::class.java)
            startActivity(start)
            finish()
        }
    }

    fun name(){
        if(
            binding.inputPassword.text.toString().isNotEmpty() &&
            binding.inputEmail.text.toString().isNotEmpty()
        ){
            binding.btnAccept.setBackgroundResource(R.drawable.button_secondary)}
        else
            binding.btnAccept.setBackgroundResource(R.drawable.button_principal)
    }
    fun email(){
        if(
            binding.inputName.text.toString().isNotEmpty() &&
            binding.inputPassword.text.toString().isNotEmpty()
        ){
            binding.btnAccept.setBackgroundResource(R.drawable.button_secondary)}
        else
            binding.btnAccept.setBackgroundResource(R.drawable.button_principal)
    }
    fun password() {
        if (
            binding.inputName.text.toString().isNotEmpty() &&
            binding.inputEmail.text.toString().isNotEmpty()
        ) {
            binding.btnAccept.setBackgroundResource(R.drawable.button_secondary)
        } else
            binding.btnAccept.setBackgroundResource(R.drawable.button_principal)
    }



}