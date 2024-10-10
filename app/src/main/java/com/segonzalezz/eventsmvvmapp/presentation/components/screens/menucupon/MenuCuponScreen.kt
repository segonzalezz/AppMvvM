package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menucupon

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.menucupon.components.MenuCuponContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuCuponScreen(){
    Scaffold(
        topBar = {  },
        content = { MenuCuponContent()},
        bottomBar = {  }
    )
}