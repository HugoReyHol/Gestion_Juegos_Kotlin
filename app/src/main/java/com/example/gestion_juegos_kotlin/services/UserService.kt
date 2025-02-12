package com.example.gestion_juegos_kotlin.services

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.gestion_juegos_kotlin.models.User
import com.example.gestion_juegos_kotlin.models.UserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserService {
    private var _user: User? = null

    val user: User?
        get() {
            return _user
        }


     fun login(request: UserRequest, context: Context) {
        Log.i("LogIn", request.password)

        RetrofitClient.instance.loginUser(request).enqueue( object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 401) {
                    Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }

                else if (response.code() == 404) {
                    Toast.makeText(context, "El usuario ${request.username} no existe", Toast.LENGTH_SHORT).show()
                }

                else if (response.isSuccessful) {
                    _user = response.body()!!

                    // TODO lógica para iniciar sesión
                    Log.i("APILogin", "${_user!!.idUser}")
                    Log.i("APILogin", _user!!.username)
                    Log.i("APILogin", _user!!.password)
                    Log.i("APILogin", _user!!.token)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("APILogin", t.message.toString())
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun register(request: UserRequest, context: Context) {
        RetrofitClient.instance.registerUser(request).enqueue( object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 400) {
                    Toast.makeText(context, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                }

                else if (response.isSuccessful) {
                    _user = response.body()!!

                    // TODO lógica para iniciar sesión
                    Log.i("APILogin", "${_user!!.idUser}")
                    Log.i("APILogin", _user!!.username)
                    Log.i("APILogin", _user!!.password)
                    Log.i("APILogin", _user!!.token)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("APILoginError", t.message.toString())
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}