package com.segonzalezz.eventsmvvmapp.presentation.navegation
sealed class AppScreen (val route: String){
    object LoginScreen: AppScreen("login")
    object RegisterScreen : AppScreen("register")
    
}