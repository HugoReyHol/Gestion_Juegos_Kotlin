package com.example.gestion_juegos_kotlin

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
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
                binding.visibilityBtn.setImageResource(R.drawable.ic_launcher_foreground) // TODO cambiar por ojo tachado cuando deje descargarlo
                binding.passwordEditText.transformationMethod = null

            } else {
                binding.visibilityBtn.setImageResource(R.drawable.baseline_visibility_24)
                binding.passwordEditText.transformationMethod = android.text.method.PasswordTransformationMethod.getInstance()

            }

            binding.passwordEditText.setSelection(selectStart, selectEnd)
        }
    }
}