package com.segonzalezz.eventsmvvmapp.presentation.navegation
sealed class AppScreens (val route: String){
    object LoginScreen: AppScreens("LoginScreen")
    object RegisterScreen : AppScreens("RegisterScreen")
    object RecoverPasswordScreen : AppScreens("RecoverPasswordScreen")
    object RegisterCouponsScreen : AppScreens("RegisterCouponsScreen")

}