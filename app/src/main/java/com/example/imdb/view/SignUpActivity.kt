package com.example.imdb.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.imdb.R
import com.example.imdb.databinding.ActivitySignUpBinding
import com.example.imdb.viewModel.SignUpActivityViewModel


class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[SignUpActivityViewModel::class.java]
        binding.model = viewModel
        binding.lifecycleOwner = this

        binding.Layout.setOnClickListener { hideKeyboard()}

        //OBSERVERS
        viewModel.liveEmailFound.observe(this){ result ->
            existUser(result)
        }

        viewModel.liveUserAdded.observe(this){ result ->
            if (result){
                Toast.makeText(applicationContext, "Usuario Registrado", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.liveUserAdded.observe(this){
            if (it == true){
                Toast.makeText(applicationContext, "Usuario Registrado", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.liveValidatedPass.observe(this){
            if (!it){
                binding.tilPassword.error = "error password"
            }
        }

        viewModel.liveNoEmpty.observe(this){
            if (!it){
                Toast.makeText(applicationContext,"Todos los campos son requeridos",Toast.LENGTH_SHORT).show()
            }
        }

        //Send data to db
        binding.btnAccept.setOnClickListener {
            viewModel.dontEmpty()
            viewModel.validatePassword()
            val validatedEmail = viewModel.liveEmailFound.value!!
            val validatedInputs = viewModel.liveNoEmpty.value!!
            val validatedPassword = viewModel.liveValidatedPass.value!!

            if (validatedPassword && validatedEmail && validatedInputs){
                viewModel.addUserDb()
                finish()
            }

        }

        //validate inputs
        binding.inputName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (binding.inputEmail.text!!.isNotEmpty()){
                    viewModel.emailFound()
                    viewModel.dontEmpty()
                }
                binding.tilEmail.error = null
                name()

            }
        }

        binding.inputEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.tilEmail.error = null
                email()
            }
        }

        binding.inputPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.tilPassword.error = null
                if (binding.inputEmail.text!!.isNotEmpty()){
                    viewModel.emailFound()
                    viewModel.dontEmpty()
                }
                binding.tilEmail.error = null
                password()

            }
        }

        //btn back
        binding.btnBack.setOnClickListener {
            val start = Intent(this, LoginActivity::class.java)
            startActivity(start)
            finish()
        }
    }

    private fun existUser(user: Boolean) {
        if (!user) {
            binding.tilEmail.error = "correo en uso"
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun name() {
        if (
            binding.inputPassword.text.toString().isNotEmpty() &&
            binding.inputEmail.text.toString().isNotEmpty()
        ) {
            binding.btnAccept.setBackgroundResource(R.drawable.button_secondary)
            binding.btnAccept.isEnabled = true
        } else
            binding.btnAccept.setBackgroundResource(R.drawable.button_principal)
    }

    private fun email() {
        if (
            binding.inputName.text.toString().isNotEmpty() &&
            binding.inputPassword.text.toString().isNotEmpty()
        ) {
            binding.btnAccept.setBackgroundResource(R.drawable.button_secondary)
            binding.btnAccept.isEnabled = true
        } else
            binding.btnAccept.setBackgroundResource(R.drawable.button_principal)
    }

    private fun password() {
        if (
            binding.inputName.text.toString().isNotEmpty() &&
            binding.inputEmail.text.toString().isNotEmpty()
        ) {
            binding.btnAccept.setBackgroundResource(R.drawable.button_secondary)
            binding.btnAccept.isEnabled = true
        } else
            binding.btnAccept.setBackgroundResource(R.drawable.button_principal)
    }


}