package com.segonzalezz.eventsmvvmapp.presentation.navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.segonzalezz.eventsmvvmapp.data.SharedPreferencesManager
import com.segonzalezz.eventsmvvmapp.dominio.UserController
import com.segonzalezz.eventsmvvmapp.model.Role
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.editregister.EditRegisterScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.LoginScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.LoginViewModel
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuadmin.MenuAdminScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.menucupon.MenuCuponScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuser.MenUserScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.recoverpassword.RecoverPasswordScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.register.RegisterScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.RegisterEventsScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons.RegisterCouponsScreen

@Composable
fun AppNavigation(navController: NavHostController, viewModel: LoginViewModel) {
    val context = LocalContext.current
    val session = SharedPreferencesManager.getCurrentUser(context)

    var startDestination = AppScreens.LoginScreen.route
    if (session != null && session.role == Role.ADMIN) {
        AppScreens.MenuAdminScreen.route
    } else {
        AppScreens.MenUserScreen.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController, viewModel)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(route = AppScreens.RecoverPasswordScreen.route) {
            RecoverPasswordScreen()
        }
        composable(route = AppScreens.RegisterCouponsScreen.route) {
            RegisterCouponsScreen()
        }
        composable(route = AppScreens.RegisterEventsScreen.route) {
            RegisterEventsScreen()
        }

        composable(route = AppScreens.MenuAdminScreen.route) {
            MenuAdminScreen(navController)
        }
        composable(route = AppScreens.MenUserScreen.route){
            MenUserScreen(navController)
        }
        composable(route = AppScreens.EditRegisterScreen.route) {
            EditRegisterScreen()
        }
        composable(route = AppScreens.MenuCuponScreen.route) {
            MenuCuponScreen()
        }

    }
}
