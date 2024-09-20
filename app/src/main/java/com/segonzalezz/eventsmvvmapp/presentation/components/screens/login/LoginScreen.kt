package com.segonzalezz.eventsmvvmapp.presentation.components.screens.login

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.components.LoginButtonBar
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.components.LoginContent
import com.segonzalezz.eventsmvvmapp.presentation.components.theme.EventsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(){
    Scaffold(
        topBar = {},
        content = { LoginContent() },
        bottomBar = { LoginButtonBar() }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    EventsMVVMAppTheme {
        LoginScreen()
    }
}