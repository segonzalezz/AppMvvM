package com.segonzalezz.eventsmvvmapp.presentation.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.LoginScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.recoverpassword.RecoverPasswordScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.register.RegisterScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons.RegisterCouponsScreen

@Composable
fun AppNavegation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = AppScreens.LoginScreen.route
    ){
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(route = AppScreens.RegisterScreen.route){
            RegisterScreen(navController)
        }
        composable(route = AppScreens.RecoverPasswordScreen.route){
            RecoverPasswordScreen()
        }
        composable(route = AppScreens.RegisterCouponsScreen.route){
            RegisterCouponsScreen()
        }
    }

}