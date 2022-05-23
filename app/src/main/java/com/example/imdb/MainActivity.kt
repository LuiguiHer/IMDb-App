package com.example.imdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.imdb.database.UserDatabase
import com.example.imdb.databinding.ActivityMainBinding
import kotlin.reflect.typeOf

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

        binding.inputPass.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) {changeBtn()}}

        binding.inputUser.setOnFocusChangeListener { _, hasFocus ->if (hasFocus) {changeBtn()}}

        binding.btnLogin.setOnClickListener {
            val key = exitUserPass()
            access(key)}
    }


    private fun access(valid:Int){
        if (valid == 1){
            val start = Intent(this, AfterLogin::class.java)
            startActivity(start)
        }else
            Toast.makeText(applicationContext,"User o password incorrect", Toast.LENGTH_SHORT).show()
    }

    private fun exitUserPass():Int {
        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,
            "data_uses"
        ).allowMainThreadQueries().build()
        val userDao = db.userDao()
        val user = binding.inputUser.text.toString()
        val pass = binding.inputPass.text.toString()
        var key = 0
        val users = userDao.readAllData()
        for (item in users) key = if(item.email != user || item.password != pass){
            0
        } else
            1
        return key
    }

    private fun changeBtn() {
        if (binding.inputUser.text.toString().isNotEmpty() || binding.inputPass.text.toString()
                .isNotEmpty()
        ) {
            binding.btnLogin.setBackgroundResource(R.drawable.button_secondary)
        } else
            binding.btnLogin.setBackgroundResource(R.drawable.button_principal)
    }


}