package com.example.gestion_juegos_kotlin

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.gestion_juegos_kotlin.databinding.ActivityLoginBinding
import com.example.gestion_juegos_kotlin.models.UserRequest
import com.example.gestion_juegos_kotlin.models.User
import com.example.gestion_juegos_kotlin.services.RetrofitClient
import com.example.gestion_juegos_kotlin.util.encript
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

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

            // TODO llamar al service para registrar un usuario
        }

        binding.loginBtn.setOnClickListener {
            if (!validateFields()) return@setOnClickListener
            login()
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

    private fun login() {
        val request = UserRequest(
            username = binding.usernameEditText.text.toString(),
            password = binding.passwordEditText.text.toString().encript()
        )

        Log.i("LogIn", request.password)

        RetrofitClient.instance.loginUser(request).enqueue( object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 401) {
                    Toast.makeText(applicationContext, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }

                else if (response.code() == 404) {
                    Toast.makeText(applicationContext, "El usuario ${request.username} no existe", Toast.LENGTH_SHORT).show()
                }

                else if (response.isSuccessful) {
                    val user = response.body()

                    // TODO lógica para iniciar sesión
                    Log.i("LogIn", "${user?.idUser}")
                    Log.i("LogIn", "${user?.username}")
                    Log.i("LogIn", "${user?.password}")
                    Log.i("LogIn", "${user?.token}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("LoginError", t.message.toString())
                Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}