package com.segonzalezz.eventsmvvmapp.presentation.navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.segonzalezz.eventsmvvmapp.data.SharedPreferencesManager
import com.segonzalezz.eventsmvvmapp.model.Role
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.editcoupon.EditCouponScreen
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
fun AppNavigation(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.LoginScreen.route
    ) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(route = AppScreens.RecoverPasswordScreen.route) {
            RecoverPasswordScreen(navController)
        }
        composable(route = AppScreens.RegisterCouponsScreen.route) {
            RegisterCouponsScreen(navController)
        }
        composable(route = AppScreens.RegisterEventsScreen.route) {
            RegisterEventsScreen(navController)
        }

        composable(route = AppScreens.MenuAdminScreen.route) {
            MenuAdminScreen(navController)
        }
        composable(route = AppScreens.MenUserScreen.route){
            MenUserScreen(navController)
        }
        composable(route = AppScreens.EditRegisterScreen.route) {
            EditRegisterScreen(navController)
        }
        composable(route = AppScreens.EditCouponScreen.route) {
            EditCouponScreen(navController)
        }
        composable(route = AppScreens.MenuCuponScreen.route) {
            MenuCuponScreen()
        }

    }
}
