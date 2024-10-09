package com.segonzalezz.eventsmvvmapp.presentation.components.screens.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var username : MutableState<String> = mutableStateOf("")
    var password : MutableState<String> = mutableStateOf("")
}