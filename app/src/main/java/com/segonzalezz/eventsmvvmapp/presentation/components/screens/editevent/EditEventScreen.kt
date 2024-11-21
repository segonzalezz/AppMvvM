package com.segonzalezz.eventsmvvmapp.presentation.components.screens.editevent

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.editevent.components.EditEventContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditEventScreen(navController: NavHostController){
    Scaffold (topBar = {}, content = { EditEventContent(navController) }, bottomBar = {} )
}