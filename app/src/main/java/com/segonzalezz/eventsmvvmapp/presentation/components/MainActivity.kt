package com.segonzalezz.eventsmvvmapp.presentation.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.LoginScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.register.RegisterScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.theme.EventsMVVMAppTheme
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppNavegation

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventsMVVMAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),  color = MaterialTheme.colorScheme.background) {
                    navController = rememberNavController()
                    AppNavegation(navController = navController)
                }
            }
        }
    }
}



