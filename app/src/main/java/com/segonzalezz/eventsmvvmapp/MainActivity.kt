package com.segonzalezz.eventsmvvmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.segonzalezz.eventsmvvmapp.screens.login.LoginScreen
import com.segonzalezz.eventsmvvmapp.ui.theme.EventsMVVMAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventsMVVMAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),  color = MaterialTheme.colorScheme.background) {
                    LoginScreen()
                }
            }
        }
    }
}

