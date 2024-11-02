package com.segonzalezz.eventsmvvmapp.model

 data class User(
    var id: String = "",
    val name: String = "",
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val birthDate: String = "",
    val role: Role = Role.USER
)