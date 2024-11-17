package com.segonzalezz.eventsmvvmapp.presentation.components.screens.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.segonzalezz.eventsmvvmapp.model.Role
import com.segonzalezz.eventsmvvmapp.model.User
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    val db = Firebase.firestore
    val auth = Firebase.auth
    var userData: MutableState<User?> = mutableStateOf(null)
    val startDestination = mutableStateOf(AppScreens.LoginScreen.route)

    init {
        determineStartDestination()
    }


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

    private fun determineStartDestination() {
        viewModelScope.launch {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                try {
                    val snapshot = db.collection("users")
                        .document(currentUser.uid)
                        .get()
                        .await()

                    val role = snapshot.getString("role")
                    startDestination.value = when (role) {
                        "ADMIN" -> AppScreens.MenuAdminScreen.route
                        "USER" -> AppScreens.MenUserScreen.route
                        else -> AppScreens.LoginScreen.route
                    }
                } catch (e: Exception) {
                    startDestination.value = AppScreens.LoginScreen.route
                }
            } else {
                startDestination.value = AppScreens.LoginScreen.route
            }
        }
    }

        fun login(onSuccess: () -> Unit, onError: (String) -> Unit) {
            viewModelScope.launch {
                try {
                    val result = auth.signInWithEmailAndPassword(email.value, password.value).await()
                    val user = result.user
                    if (user != null) {
                        val snapshot = db.collection("users")
                            .document(user.uid)
                            .get()
                            .await()
                        val role = snapshot.getString("role")
                        startDestination.value = when (role) {
                            "ADMIN" -> AppScreens.MenuAdminScreen.route
                            "USER" -> AppScreens.MenUserScreen.route
                            else -> AppScreens.LoginScreen.route
                        }
                        onSuccess()
                    } else {
                        onError("Error de autenticación.")
                    }
                } catch (e: Exception) {
                    onError("Error al iniciar sesión: ${e.localizedMessage}")
                }
            }
        }
    fun fetchUserData() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            viewModelScope.launch {
                try {
                    val snapshot = db.collection("users")
                        .document(currentUser.uid)
                        .get()
                        .await()

                    userData.value = snapshot.toObject(User::class.java) // Convierte el documento a un objeto User
                } catch (e: Exception) {
                    Log.e("LoginViewModel", "Error al obtener datos del usuario", e)
                }
            }
        }
    }

    fun updateUserData(
        updatedUser: User,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                db.collection("users")
                    .document(updatedUser.id)
                    .set(updatedUser)
                    .await()

                userData.value = updatedUser // Actualiza el estado local
                Log.d("LoginViewModel", "Datos actualizados correctamente")
                onSuccess(updatedUser.role.toString()) // Pasa el rol del usuario al callback de éxito
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error al actualizar los datos", e)
                onError("Error al actualizar los datos: ${e.message}")
            }
        }
    }


}