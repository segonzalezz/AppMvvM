package com.segonzalezz.eventsmvvmapp.presentation.components.screens.login

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    var email : MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false);
    var emailErrorMsg: MutableState<String> = mutableStateOf("");

    var password : MutableState<String> = mutableStateOf("")
    var isPasswrodValid: MutableState<Boolean> = mutableStateOf(false);
    var passwordErrorMsg: MutableState<String> = mutableStateOf("");

    var isEnabledLoginButton = false

    fun enabledLoginButton(){
        isEnabledLoginButton = isEmailValid.value && isPasswrodValid.value
    }

    fun validateEmail(){
        if(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true;
            emailErrorMsg.value = "";
        }else{
            isEmailValid.value = true;
            emailErrorMsg.value = "Email no valido";
        }
        enabledLoginButton()
    }

    fun validatePassword(){
        if(password.value.length >= 6){
            isPasswrodValid.value = true;
            passwordErrorMsg.value = "";
        }else{
            isPasswrodValid.value = false;
            passwordErrorMsg.value = "Password debe tener al menos 6 caracteres";
        }
        enabledLoginButton()
    }

}