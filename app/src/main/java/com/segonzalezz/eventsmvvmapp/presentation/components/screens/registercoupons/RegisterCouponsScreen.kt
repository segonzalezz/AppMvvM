package com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons.components.RegisterCouponsContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterCouponsScreen(){
    Scaffold(
        topBar = {},
        content = { RegisterCouponsContent()},
        bottomBar = {}
    )
}