package com.segonzalezz.eventsmvvmapp.presentation.components.screens.register

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultToolBar
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.register.components.RegisterContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavHostController){
    Scaffold(
        topBar = { },
        content = {
            RegisterContent(navController)
                  },
        bottomBar = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(rememberNavController())
}