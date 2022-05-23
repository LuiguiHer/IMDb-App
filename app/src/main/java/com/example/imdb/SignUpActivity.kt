package com.example.imdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.imdb.database.User
import com.example.imdb.database.UserDatabase
import com.example.imdb.databinding.ActivitySignUpBinding
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Layout.setOnClickListener { clearKeyboard() }

        //Send data to db
        binding.btnAccept.setOnClickListener {
            if (binding.inputName.text!!.isNotEmpty() && binding.inputEmail.text!!.isNotEmpty() &&
                    binding.inputPassword.text!!.isNotEmpty()){
                addUserToDatabase()
                clearInputs()
            }else
                Toast.makeText(applicationContext,"Todos los campos son requeridos", Toast.LENGTH_SHORT).show()
        }

        //validate inputs
        binding.inputName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                name()
            }
        }
        binding.inputEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                email()
            }
        }
        binding.inputPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                password()
                valideEmail()
            }
        }

        //btn back
        binding.btnBack.setOnClickListener {
            val start = Intent(this, MainActivity::class.java)
            startActivity(start)
            finish()
        }
    }

    private fun clearKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun valideEmail() {
        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,
            "data_uses"
        ).allowMainThreadQueries().build()
        val userDao = db.userDao()
        val email = binding.inputEmail.text.toString()
        val users = userDao.readAllData()
        for (i in users) {
            if (i.email == email) {
                binding
                    .tilEmail.error = getString(R.string.email_duplicate)
            } else {
                binding
                    .tilEmail.error = null
            }
        }
    }

    fun clearInputs() {
        binding.inputName.setText("")
        binding.inputEmail.setText("")
        binding.inputPassword.setText("")
    }

    fun addUserToDatabase() {
        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,
            "data_uses"
        ).allowMainThreadQueries().build()
        val userDao = db.userDao()
        val name = binding.inputName.text.toString()
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()

        lifecycleScope.launch {
            val user = User(0, name, email, password)
            userDao.addUser(user)
            Toast.makeText(applicationContext, "Usuario Registrado", Toast.LENGTH_SHORT).show()

            val users = userDao.readAllData()
            for (i in users) {
                println("${i.id} - ${i.name} - ${i.email} - ${i.password}")
            }
        }
    }

    fun name() {
        if (
            binding.inputPassword.text.toString().isNotEmpty() &&
            binding.inputEmail.text.toString().isNotEmpty()
        ) {
            binding.btnAccept.setBackgroundResource(R.drawable.button_secondary)
            binding.btnAccept.isEnabled = true
        } else
            binding.btnAccept.setBackgroundResource(R.drawable.button_principal)
    }

    fun email() {
        if (
            binding.inputName.text.toString().isNotEmpty() &&
            binding.inputPassword.text.toString().isNotEmpty()
        ) {
            binding.btnAccept.setBackgroundResource(R.drawable.button_secondary)
            binding.btnAccept.isEnabled = true
        } else
            binding.btnAccept.setBackgroundResource(R.drawable.button_principal)
    }

    fun password() {
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