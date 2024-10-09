package com.segonzalezz.eventsmvvmapp.presentation.components.screens.login

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.components.LoginButtonBar
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.components.LoginContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel){
    Scaffold(
        topBar = {},
        content = { LoginContent(navController, viewModel) },
        bottomBar = { LoginButtonBar(navController) }
    )
}
