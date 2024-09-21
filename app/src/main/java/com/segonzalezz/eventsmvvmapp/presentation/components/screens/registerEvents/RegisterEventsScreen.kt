package com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.components.RegisterEventsContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterEventsScreen(){
    Scaffold(topBar = {},
        content = { RegisterEventsContent()},
        bottomBar = {}
    )
}