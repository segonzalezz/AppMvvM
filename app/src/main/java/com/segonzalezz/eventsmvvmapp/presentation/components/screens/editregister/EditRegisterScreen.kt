package com.segonzalezz.eventsmvvmapp.presentation.components.screens.editregister

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.editregister.components.EditButtonBar
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.editregister.components.EditRegisterContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditRegisterScreen(){
    Scaffold( topBar = {}, content = { EditRegisterContent() }, bottomBar = {} )
}