package com.example.imdb.view

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.imdb.R
import com.example.imdb.databinding.ActivityHomeBinding
import com.example.imdb.viewModel.HomeActivityViewModel
import com.example.imdb.viewModel.ProfileFragmentViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel : HomeActivityViewModel
    lateinit var userName: String
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[HomeActivityViewModel::class.java]
        userName = intent.getStringExtra("name_user").toString()
        viewModel.getUserName.value = userName
        viewModel.sendDataToStore()


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}