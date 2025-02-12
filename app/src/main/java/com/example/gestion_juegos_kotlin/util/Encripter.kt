package com.example.gestion_juegos_kotlin.util

import java.security.MessageDigest

fun String.encript(): String {
    val bytes = this.toByteArray()
    val digest = MessageDigest.getInstance("SHA-256")
    val hash = digest.digest(bytes)
    return hash.joinToString("") { "%02x".format(it) }
}