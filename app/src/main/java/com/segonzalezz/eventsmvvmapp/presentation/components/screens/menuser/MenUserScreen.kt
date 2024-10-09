package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuser

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuser.Components.MenUserButtonBar
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuser.Components.MenUserContent
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuser.Components.MenuTopBarMenu


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenUserScreen(navController: NavHostController){
    Scaffold(
        topBar = {MenuTopBarMenu(navController)},
        content = {MenUserContent()},
        bottomBar = {MenUserButtonBar(navController)}
    )
}