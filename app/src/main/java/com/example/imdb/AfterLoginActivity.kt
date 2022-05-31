package com.example.imdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.imdb.databinding.ActivityAfterLoginBinding

class AfterLoginActivity : AppCompatActivity() {
    lateinit var userName: String
    lateinit var binding: ActivityAfterLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAfterLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        userName = intent.getStringExtra("name_user").toString()

    }
}