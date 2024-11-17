package com.segonzalezz.eventsmvvmapp.presentation.components.screens.recoverpassword

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.recoverpassword.components.RecoverPasswordContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecoverPasswordScreen(navController: NavController){
    Scaffold(
        topBar = {},
        content = { RecoverPasswordContent(navController)},
        bottomBar = {},
        )
}