package com.segonzalezz.eventsmvvmapp.data

import android.content.Context
import com.segonzalezz.eventsmvvmapp.model.Role
import org.json.JSONArray
import org.json.JSONObject

object SharedPreferencesManager {
    private const val USERS_KEY = "users_key"

    fun savePreferences(
        context: Context, idUser:String, rol: Role, nombre: String, correo: String, usuario: String,
        contraseña: String,
        numeroTelefono: String, direccion: String,
        fechaNacimiento: Int
    ){
        val sharedPreferences = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("idUser", idUser)
        editor.putString("rol", rol.name)
        editor.putString("nombre", nombre)
        editor.putString("correo", correo)
        editor.putString("usuario", usuario)
        editor.putString("contraseña", contraseña)
        editor.putString("numeroTelefono", numeroTelefono)
        editor.putString("direccion", direccion)
        editor.putInt("fechaNacimiento", fechaNacimiento)
        editor.apply()
    }

   /* fun addUserToList(context: Context, user: UserDTO) {
        val sharedPreferences = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val currentUsersJson = sharedPreferences.getString(USERS_KEY, "[]")
        val currentUsersArray = JSONArray(currentUsersJson)
        val userJson = JSONObject().apply {
            put("id", user.id)
            put("role", user.role.name)
            put("nombre", user.nombre)
            put("correo", user.correo)
            put("usuario", user.usuario)
            put("contraseña", user.contraseña)
            put("numeroTelefono", user.numeroTelefono)
            put("direccion", user.direccion)
            put("fechaNacimiento", user.fechaNacimiento)
        }
        currentUsersArray.put(userJson)
        editor.putString(USERS_KEY, currentUsersArray.toString())
        editor.apply()
    }

    fun getAllUsers(context: Context): MutableList<UserDTO> {
        val sharedPreferences = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val usersJson = sharedPreferences.getString(USERS_KEY, "[]")
        val usersArray = JSONArray(usersJson)

        val usersList = mutableListOf<UserDTO>()
        for (i in 0 until usersArray.length()) {
            val userJson = usersArray.getJSONObject(i)
            val user = UserDTO(
                id = userJson.getString("id"),
                role = Role.valueOf(userJson.getString("role")),
                nombre = userJson.getString("nombre"),
                correo = userJson.getString("correo"),
                usuario = userJson.getString("usuario"),
                contraseña = userJson.getString("contraseña"),
                numeroTelefono = userJson.getString("numeroTelefono"),
                direccion = userJson.getString("direccion"),
                fechaNacimiento = userJson.getInt("fechaNacimiento")
            )
            usersList.add(user)
        }
        return usersList
    }


    fun getCurrentUser(context: Context): UserDTO? {
        val sharedPreferences = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getString("idUser", null)
        val rolString = sharedPreferences.getString("rol", null)
        val nombre = sharedPreferences.getString("nombre", null)
        val correo = sharedPreferences.getString("correo", null)
        val usuario = sharedPreferences.getString("usuario", null)
        val contraseña = sharedPreferences.getString("contraseña", null)
        val numeroTelefono = sharedPreferences.getString("numeroTelefono", null)
        val direccion = sharedPreferences.getString("direccion", null)
        val fechaNacimiento = sharedPreferences.getInt("fechaNacimiento", -1)
        if (idUser.isNullOrEmpty() || rolString.isNullOrEmpty()) {
            return null
        }
        return try {
            UserDTO(
                id = idUser,
                role = Role.valueOf(rolString),
                nombre = nombre.orEmpty(),
                correo = correo.orEmpty(),
                usuario = usuario.orEmpty(),
                contraseña = contraseña.orEmpty(),
                numeroTelefono = numeroTelefono.orEmpty(),
                direccion = direccion.orEmpty(),
                fechaNacimiento = fechaNacimiento
            )
        } catch (e: IllegalArgumentException) {
            null
        }
    }*/
}

