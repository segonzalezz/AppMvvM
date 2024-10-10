package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuadmin.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.res.painterResource
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopBarMenu(navController: NavHostController) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Menú") },
        navigationIcon = {},
        actions = {
            IconButton(onClick = {
                navController.navigate(AppScreens.LoginScreen.route) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Salir"
                )
            }
        },
        modifier = Modifier.padding(8.dp)
    )
}