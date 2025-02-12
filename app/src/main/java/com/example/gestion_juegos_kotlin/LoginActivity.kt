package com.example.gestion_juegos_kotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.gestion_juegos_kotlin.databinding.ActivityLoginBinding

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
                binding.passwordEditText.transformationMethod = android.text.method.PasswordTransformationMethod.getInstance()

            }

            binding.passwordEditText.setSelection(selectStart, selectEnd)
        }

        binding.registerBtn.setOnClickListener {
            validatePass()
            // TODO llamar al service para registrar un usuario
        }

        binding.loginBtn.setOnClickListener {
            validatePass()
            // TODO llamar al service para obtener token
        }
    }

    private fun validatePass() {
        if (binding.passwordEditText.text.length < 8)
            binding.passwordEditText.error = "La contraseña debe tener al menos 8 caracteres"
    }
}