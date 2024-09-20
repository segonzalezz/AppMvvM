package com.segonzalezz.eventsmvvmapp.presentation.components.screens.register

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.register.components.RegisterContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(){
    Scaffold(
        topBar = {},
        content = {
            RegisterContent()
                  },
        bottomBar = {}
    )
}