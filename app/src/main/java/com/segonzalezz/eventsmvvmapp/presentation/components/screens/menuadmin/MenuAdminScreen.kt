package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuadmin

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuadmin.components.MenuAdminButtonBar
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuadmin.components.MenuAdminContent
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuadmin.components.MenuTopBarMenu


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuAdminScreen(navController: NavHostController){
    Scaffold(
        topBar = {MenuTopBarMenu(navController)},
        content = {MenuAdminContent(navController = navController)},
        bottomBar = {MenuAdminButtonBar(navController)}
    )
}