package com.example.imdb.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.imdb.AfterLoginActivity
import com.example.imdb.R
import com.example.imdb.database.User
import com.example.imdb.databinding.ActivityMainBinding
import com.example.imdb.signUp.SignUpActivity


class MainActivity : AppCompatActivity(), MainContract.View {
    lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val presenter = MainPresenter(this,MainModel(),applicationContext)

        binding.txtSignUp.setOnClickListener {
            val start = Intent(this, SignUpActivity::class.java)
            startActivity(start)
        }

        binding.Layout.setOnClickListener { hideKeyboard() }

        binding.inputPass.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                changeBtn()
                binding.txtError.text = null
                binding.textErrorp.text = null
            }
        }

        binding.inputUser.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                changeBtn()
                binding.txtError.text = null
                binding.textErrorp.text = null
            }
        }


        binding.btnLogin.setOnClickListener {
            binding.textErrorp.text = null
            if (binding.inputUser.text.isEmpty()) {
                binding.txtError.text = "Usuario requerido"
            } else if (binding.inputPass.text.isEmpty()) {
                binding.textErrorp.text = "Contraseña requerida"
            } else
                println(presenter.existUser())
                access(presenter.existUser())

        }

    }

    override fun clearInputs() {
        binding.inputUser.setText("")
        binding.inputPass.setText("")
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun access(user: User?) {
        if (user != null) {
            Toast.makeText(applicationContext, "Ingreso exitoso", Toast.LENGTH_SHORT).show()
            val start = Intent(this, AfterLoginActivity::class.java)
            start.putExtra("name_user", user.name)
            startActivity(start)
            clearInputs()
        } else {
            clearInputs()
            hideKeyboard()
            binding.Layout.clearFocus()
            "Usuario o Contraseña invalido".also { binding.txtError.text = it }
        }
    }

    override fun getDataAccess():User{
        val user = binding.inputUser.text.toString()
        val pass = binding.inputPass.text.toString()
        return User(user,"",pass)
    }

    override fun changeBtn() {
        if (binding.inputUser.text.toString().isNotEmpty() || binding.inputPass.text.toString()
                .isNotEmpty()
        ) {
            binding.btnLogin.setBackgroundResource(R.drawable.button_secondary)
        } else
            binding.btnLogin.setBackgroundResource(R.drawable.button_principal)
    }
}