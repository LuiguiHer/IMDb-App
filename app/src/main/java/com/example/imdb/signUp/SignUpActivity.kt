package com.example.imdb.signUp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.imdb.main.MainActivity
import com.example.imdb.R
import com.example.imdb.database.User
import com.example.imdb.databinding.ActivitySignUpBinding
import kotlinx.coroutines.launch


class SignUpActivity : AppCompatActivity(), SignUpContract.View {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = SignUpPresenter(this,SignUpModel(),applicationContext)

        binding.Layout.setOnClickListener {
            clearKeyboard()
        }

        //Verify information
        binding.btnAccept.setOnClickListener {
            if (binding.inputPassword.text.toString().length < 8) {
                binding.tilPassword.error = "error password"
            } else {
                if (!presenter.emailFound(email=getUserData().email)) {
                    binding.tilPassword.error = null
                    if (binding.inputName.text!!.isNotEmpty() && binding.inputEmail.text!!.isNotEmpty() &&
                        binding.inputPassword.text!!.isNotEmpty()
                    ) {
                        lifecycleScope.launch { presenter.addUserToDatabase() }
                        Toast.makeText(applicationContext,"Usuario registrado!",Toast.LENGTH_SHORT).show()
                        clearInputs()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Todos los campos son requeridos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Correo en Uso", Toast.LENGTH_SHORT).show()
                }

            }

        }

        //validate inputs
        binding.inputName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.tilEmail.error = null
                existUser(presenter.emailFound(email=getUserData().email))
                name()
            }
        }
        binding.inputEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                existUser(presenter.emailFound(email = getUserData().email))
                binding.tilEmail.error = null
                email()
            }
        }
        binding.inputPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.tilEmail.error = null
                existUser(presenter.emailFound(email=getUserData().email))
                password()

            }
        }

        //btn back
        binding.btnBack.setOnClickListener {
            val start = Intent(this, MainActivity::class.java)
            startActivity(start)
            finish()
        }
    }

    override fun existUser(verifyEmail: Boolean) {
        if (verifyEmail) {
            binding.tilEmail.error = "correo en uso"
        }
    }

    override fun clearKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun clearInputs() {
        binding.inputName.setText("")
        binding.inputEmail.setText("")
        binding.inputPassword.setText("")
    }

    override fun getUserData() : User {
        val name = binding.inputName.text.toString()
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        return User(email,name, password)
    }

    override fun name() {
        if (
            binding.inputPassword.text.toString().isNotEmpty() &&
            binding.inputEmail.text.toString().isNotEmpty()
        ) {
            binding.btnAccept.setBackgroundResource(R.drawable.button_secondary)
            binding.btnAccept.isEnabled = true
        } else
            binding.btnAccept.setBackgroundResource(R.drawable.button_principal)
    }

    override fun email() {
        if (
            binding.inputName.text.toString().isNotEmpty() &&
            binding.inputPassword.text.toString().isNotEmpty()
        ) {
            binding.btnAccept.setBackgroundResource(R.drawable.button_secondary)
            binding.btnAccept.isEnabled = true
        } else
            binding.btnAccept.setBackgroundResource(R.drawable.button_principal)
    }

    override fun password() {
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