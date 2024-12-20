package com.segonzalezz.eventsmvvmapp.presentation.navegation
sealed class AppScreens (val route: String){
    object LoginScreen: AppScreens("LoginScreen")
    object RegisterScreen : AppScreens("RegisterScreen")
    object RecoverPasswordScreen : AppScreens("RecoverPasswordScreen")
    object RegisterCouponsScreen : AppScreens("RegisterCouponsScreen")
    object RegisterEventsScreen : AppScreens("RegisterEventsScreen")
    object EditRegisterScreen : AppScreens("EditRegisterScreen")
    object MenuAdminScreen : AppScreens("MenuAdminScreen")
    object MenUserScreen : AppScreens("MenUserScreen")
    object EditCouponScreen : AppScreens("EditCouponScreen")
    object MenuCuponScreen : AppScreens("MenuCuponScreen")
    object EditEventScreen : AppScreens("EditEventScreen")


}