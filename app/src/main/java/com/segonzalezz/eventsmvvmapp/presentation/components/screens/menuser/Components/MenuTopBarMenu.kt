package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuser.Components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.segonzalezz.eventsmvvmapp.data.SharedPreferencesManager
//import com.segonzalezz.eventsmvvmapp.data.SharedPreferencesManager.getCurrentUser
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopBarMenu(navController: NavHostController) {
    var username by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
       // val currentUser = SharedPreferencesManager.getCurrentUser(context)
        //username = currentUser?.usuario
    }

    CenterAlignedTopAppBar(
        title = { Text(text = "Menú ${username ?:""}") },
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
        modifier = Modifier.padding(0.dp)
    )
}