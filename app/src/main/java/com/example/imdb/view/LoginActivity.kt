package com.example.imdb.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.imdb.R
import com.example.imdb.database.User
import com.example.imdb.databinding.ActivityLoginBinding
import com.example.imdb.viewModel.LoginActivityViewModel


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginActivityViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginActivityViewModel::class.java]
        binding.model = viewModel
        binding.lifecycleOwner = this


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
            viewModel.existUser()
            viewModel.liveUserValidated.observe(this){ user ->
                if (user != null){
                    Toast.makeText(applicationContext, "Ingreso exitoso", Toast.LENGTH_SHORT).show()
                    binding.txtError.text = null
                    access(user)
                }else{
                    noAccess()
                }
            }
        }

    }

    private fun clearInputs() {
        binding.inputUser.setText("")
        binding.inputPass.setText("")
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun access(user: User){
        val start = Intent(this, HomeActivity::class.java)
        start.putExtra("name_user", user.name)
        startActivity(start)
    }
    private fun noAccess(){
        clearInputs()
        binding.Layout.clearFocus()
        "Usuario o Contraseña invalido".also { binding.txtError.text = it }
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