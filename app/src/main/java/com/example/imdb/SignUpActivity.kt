package com.example.imdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.imdb.database.User
import com.example.imdb.database.UserDatabase
import com.example.imdb.databinding.ActivitySignUpBinding
import kotlinx.coroutines.launch

@Suppress("SENSELESS_COMPARISON")
class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Layout.setOnClickListener { clearKeyboard() }

        //Send data to db
        binding.btnAccept.setOnClickListener {
            if (binding.inputPassword.text.toString().length < 8) {
                binding.tilPassword.error = "error password"
            } else {
                if (emailFound() == null) {
                    binding.tilPassword.error = null
                    if (binding.inputName.text!!.isNotEmpty() && binding.inputEmail.text!!.isNotEmpty() &&
                        binding.inputPassword.text!!.isNotEmpty()
                    ) {
                        addUserToDatabase()
                        clearInputs()
                        finish()
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
                existUser(emailFound())
                name()
            }
        }
        binding.inputEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                existUser(emailFound())
                binding.tilEmail.error = null
                email()
            }
        }
        binding.inputPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.tilEmail.error = null
                existUser(emailFound())
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

    private fun existUser(user: User?) {
        if (user != null) {
            binding.tilEmail.error = "correo en uso"
        }
    }

    private fun emailFound(): User {
        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,
            "data_user"
        ).allowMainThreadQueries().build()
        val userDao = db.userDao()
        return userDao.getUserByEmail(binding.inputEmail.text.toString())
    }

    private fun clearKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun clearInputs() {
        binding.inputName.setText("")
        binding.inputEmail.setText("")
        binding.inputPassword.setText("")
    }

    private fun addUserToDatabase() {
        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,
            "data_user"
        ).allowMainThreadQueries().build()
        val userDao = db.userDao()
        val name = binding.inputName.text.toString()
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()

        lifecycleScope.launch {
            val user = User(email, name, password)
            userDao.addUser(user)
            Toast.makeText(applicationContext, "Usuario Registrado", Toast.LENGTH_SHORT).show()

            val users = userDao.readAllData()
            for (i in users) {
                println("${i.name} - ${i.email} - ${i.password}")
            }
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