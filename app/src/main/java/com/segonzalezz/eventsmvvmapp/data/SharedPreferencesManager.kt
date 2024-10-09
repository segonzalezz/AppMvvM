package com.segonzalezz.eventsmvvmapp.data

import android.content.Context
import com.segonzalezz.eventsmvvmapp.model.Role
import com.segonzalezz.eventsmvvmapp.model.dto.UserDTO

object SharedPreferencesManager {

    fun savePreferences(context: Context, idUser:String, rol: Role){
        val sharedPreferences = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("idUser", idUser)
        editor.putString("rol", rol.name)
        editor.apply()
    }

    fun clearPreferences(context: Context){
        val sharedPreferences = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun getCurrentUser(context: Context): UserDTO?{
        val sharedPreferences = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getString("idUser", "")
        val rol = sharedPreferences.getString("rol", "")
        return if(idUser.isNullOrEmpty() || rol.isNullOrEmpty()){
             null
        }else{
             UserDTO(idUser, Role.valueOf(rol))
        }
    }

}

