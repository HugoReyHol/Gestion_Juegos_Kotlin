package com.example.gestion_juegos_kotlin.services

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.gestion_juegos_kotlin.MainActivity
import com.example.gestion_juegos_kotlin.models.UserRequest
import com.example.gestion_juegos_kotlin.providers.UserProvider

object UserService {

    fun login(request: UserRequest, context: Context) {
        Log.i("LogIn", request.password)

        val response = RetrofitClient.instance.loginUser(request)

        if (response.code() == 401) {
            Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
        }

        else if (response.code() == 404) {
            Toast.makeText(context, "El usuario ${request.username} no existe", Toast.LENGTH_SHORT).show()
        }

        else if (response.isSuccessful) {
            UserProvider.user = response.body()!!

            context.startActivity(
                Intent(context, MainActivity::class.java)
            )
        }
    }

    fun register(request: UserRequest, context: Context) {
        val response = RetrofitClient.instance.registerUser(request)

        if (response.code() == 400) {
            Toast.makeText(context, "El usuario ya existe", Toast.LENGTH_SHORT).show()
        }

        else if (response.isSuccessful) {
            UserProvider.user = response.body()!!

            context.startActivity(
                Intent(context, MainActivity::class.java)
            )
        }
    }
}