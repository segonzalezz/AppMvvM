package com.segonzalezz.eventsmvvmapp.presentation.components.screens.editcoupon

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.editcoupon.components.EditCouponContent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditCouponScreen(navController: NavHostController){
    Scaffold( topBar = {}, content = { EditCouponContent(navController) }, bottomBar = {} )
}