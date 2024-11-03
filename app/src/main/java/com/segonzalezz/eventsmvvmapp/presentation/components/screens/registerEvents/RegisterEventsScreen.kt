package com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.components.RegisterEventsContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterEventsScreen(navController: NavHostController){
    Scaffold(topBar = {},
        content = { RegisterEventsContent(navController)},
        bottomBar = {}
    )
}