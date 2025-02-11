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
    lateinit var passTypeface: Typeface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeComponents()
    }

    private fun initializeComponents() {
        passTypeface = binding.passwordEditText.typeface

        binding.visibilityBtn.setOnClickListener {
            passIsVisible = !passIsVisible;

            if (passIsVisible) {
                binding.visibilityBtn.setImageResource(R.drawable.ic_launcher_foreground) // TODO cambiar por ojo tachado cuando deje descargarlo
                binding.passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            } else {
                binding.visibilityBtn.setImageResource(R.drawable.baseline_visibility_24)
                binding.passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            }

            binding.passwordEditText.typeface = passTypeface
            binding.passwordEditText.setSelection(binding.passwordEditText.text.length)
        }
    }
}