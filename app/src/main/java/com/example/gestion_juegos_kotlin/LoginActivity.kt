package com.example.gestion_juegos_kotlin

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.gestion_juegos_kotlin.databinding.ActivityLoginBinding
import com.example.gestion_juegos_kotlin.models.UserRequest
import com.example.gestion_juegos_kotlin.services.UserService
import com.example.gestion_juegos_kotlin.util.encript

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var passIsVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeComponents()
    }

    private fun initializeComponents() {
        binding.visibilityBtn.setOnClickListener {
            val selectStart = binding.passwordEditText.selectionStart
            val selectEnd = binding.passwordEditText.selectionEnd
            passIsVisible = !passIsVisible;

            if (passIsVisible) {
                binding.visibilityBtn.setImageResource(R.drawable.visibility_off)
                binding.passwordEditText.transformationMethod = null

            } else {
                binding.visibilityBtn.setImageResource(R.drawable.visibility)
                binding.passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()

            }

            binding.passwordEditText.setSelection(selectStart, selectEnd)
        }

        binding.registerBtn.setOnClickListener {
            if (!validateFields()) return@setOnClickListener
            UserService.register(
                UserRequest(
                    username = binding.usernameEditText.text.toString(),
                    password = binding.passwordEditText.text.toString().encript()
                ),
                this
            )
        }

        binding.loginBtn.setOnClickListener {
            if (!validateFields()) return@setOnClickListener
            UserService.login(
                UserRequest(
                    username = binding.usernameEditText.text.toString(),
                    password = binding.passwordEditText.text.toString().encript()
                ),
                this
            )
        }
    }

    private fun validateFields(): Boolean {
        if (binding.usernameEditText.text.isBlank()) {
            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.passwordEditText.text.isBlank()) {
            Toast.makeText(this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.passwordEditText.text.length < 8) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}