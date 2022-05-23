package com.example.imdb

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.room.Room
import com.example.imdb.database.UserDatabase
import com.example.imdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtSignUp.setOnClickListener {
            val start = Intent(this, SignUpActivity::class.java)
            startActivity(start)
        }

        binding.Layout.setOnClickListener { clearKeyboard() }

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
            val key = exitUserPass()
            binding.textErrorp.text = null
            if (binding.inputUser.text.isEmpty()) {
                binding.txtError.text = "Usuario requerido"
            } else if (binding.inputPass.text.isEmpty()) {
                binding.textErrorp.text = "Contraseña requerida"
            } else
                access(key)

        }


    }

    private fun clearInputs() {
        binding.inputUser.setText("")
        binding.inputPass.setText("")
    }

    private fun clearKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun access(valid: Int) {
        if (valid == 1) {
            val start = Intent(this, AfterLogin::class.java)
            startActivity(start)
            Toast.makeText(applicationContext, "Ingreso exitoso", Toast.LENGTH_SHORT).show()
            clearInputs()
        } else{
            clearInputs()
            clearKeyboard()
            binding.Layout.clearFocus()
            "Usuario o Contraseña invalido".also { binding.txtError.text = it }
        }
    }

    private fun exitUserPass(): Int {
        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,
            "data_uses"
        ).allowMainThreadQueries().build()
        val userDao = db.userDao()
        val user = binding.inputUser.text.toString()
        val pass = binding.inputPass.text.toString()
        var key = 0
        val users = userDao.readAllData()
        for (item in users) key = if (item.email != user || item.password != pass) {
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