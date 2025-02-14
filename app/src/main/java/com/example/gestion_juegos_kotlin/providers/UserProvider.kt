package com.example.gestion_juegos_kotlin.providers

import com.example.gestion_juegos_kotlin.models.User

object UserProvider {
    private var _user: User? = null

    var user: User?
        get() {
            return _user
        }
        set(value) {
            if (_user == null) _user = value
        }
}