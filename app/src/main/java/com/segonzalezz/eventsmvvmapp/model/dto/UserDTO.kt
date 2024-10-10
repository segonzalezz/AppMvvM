package com.segonzalezz.eventsmvvmapp.model.dto

import com.segonzalezz.eventsmvvmapp.model.Role

data class UserDTO(
    val id: String,
    val nombre: String,
    val correo: String,
    val usuario: String,
    val contraseña: String,
    val numeroTelefono: String,
    val direccion: String,
    val fechaNacimiento: Int,
    val role: Role
)
