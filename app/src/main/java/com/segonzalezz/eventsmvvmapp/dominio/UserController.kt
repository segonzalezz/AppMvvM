package com.segonzalezz.eventsmvvmapp.dominio

import android.content.Context
import com.segonzalezz.eventsmvvmapp.data.SharedPreferencesManager
import com.segonzalezz.eventsmvvmapp.model.dto.UserDTO

class UserController(private val context: Context) {

    fun registerUser(context: Context, user: UserDTO): String {
        val missingFields = mutableListOf<String>()
        if (user.id.isEmpty()) missingFields.add("ID")
        if (user.nombre.isEmpty()) missingFields.add("Nombre")
        if (user.correo.isEmpty()) missingFields.add("Email")
        if (user.usuario.isEmpty()) missingFields.add("Usuario")
        if (user.contraseña.isEmpty()) missingFields.add("Contraseña")
        if (user.numeroTelefono.isEmpty()) missingFields.add("Número de Teléfono")
        if (user.direccion.isEmpty()) missingFields.add("Dirección")
        if (user.fechaNacimiento == -1) missingFields.add("Fecha de Nacimiento")
        if (missingFields.isNotEmpty()) {
            return "Faltan los siguientes campos: ${missingFields.joinToString(", ")}"
        }
        val existingUsers = SharedPreferencesManager.getAllUsers(context)
        if (existingUsers.any { it.usuario == user.usuario || it.correo == user.correo }) {
            return "El usuario ya existe con el mismo nombre de usuario o email"
        }
        SharedPreferencesManager.addUserToList(context, user)
        return "Registro exitoso"
    }
}