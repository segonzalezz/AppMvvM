package com.segonzalezz.eventsmvvmapp.presentation.components.screens.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.segonzalezz.eventsmvvmapp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

    val db = Firebase.firestore
    val auth = Firebase.auth

    private val _users = MutableStateFlow( emptyList<User>())
    val users : StateFlow<List<User>> = _users.asStateFlow()

    init{
        loadUsers()
    }

    //Todo esto son validaciones

    var nombre: MutableState<String> = mutableStateOf("")

    var email : MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false);
    var emailErrorMsg: MutableState<String> = mutableStateOf("");

    var nombreUsuario: MutableState<String> = mutableStateOf("")
    var isnombreUsuarioValid: MutableState<Boolean> = mutableStateOf(false)
    var nombreUsuarioErrorMsg: MutableState<String> =  mutableStateOf("")

    var password : MutableState<String> = mutableStateOf("")
    var isPasswrodValid: MutableState<Boolean> = mutableStateOf(false);
    var passwordErrorMsg: MutableState<String> = mutableStateOf("");

    var passwordd : MutableState<String> = mutableStateOf("")
    var isPasswrodValidd: MutableState<Boolean> = mutableStateOf(false);
    var passwordErrorMsgg: MutableState<String> = mutableStateOf("");

    var numeroTelefono : MutableState<String> = mutableStateOf("")
    var isNumeroTelefonoValid: MutableState<Boolean> = mutableStateOf(false);
    var numeroTelefonoErrorMsg: MutableState<String> = mutableStateOf("");

    var direccion :  MutableState<String> = mutableStateOf("")
    var isDireccionValid: MutableState<Boolean> = mutableStateOf(false);
    var direccionErrorMsg: MutableState<String> = mutableStateOf("");

    var fechaNacimiento : MutableState<String> = mutableStateOf("")
    var isFechaNacimientoValid: MutableState<Boolean> = mutableStateOf(false);
    var fechaNacimientoErrorMsg: MutableState<String> = mutableStateOf("");

    var isEnabledRegisterButton = false

    fun enabledLoginButton(){
        isEnabledRegisterButton = isEmailValid.value && isPasswrodValid.value
                &&  isnombreUsuarioValid.value && isPasswrodValidd.value
                && isFechaNacimientoValid.value && isDireccionValid.value
                && isNumeroTelefonoValid.value
    }

    fun validateConfirmPassword(){
        if(password.value == passwordd.value){
            isPasswrodValidd.value = true
            passwordErrorMsgg.value = ""
        }else{
            isPasswrodValidd.value = false
            passwordErrorMsgg.value = "Las contraseñas no coinciden"
        }
        enabledLoginButton()
    }
    fun validateEmail(){
        if(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true;
            emailErrorMsg.value = "";
        }else{
            isEmailValid.value = true;
            emailErrorMsg.value = "Email no valido";
        }
        isEnabledRegisterButton
    }

    fun validateNombreUsuario(){
        if(nombreUsuario.value.length >= 5){
            isnombreUsuarioValid.value = true;
            nombreUsuarioErrorMsg.value = "";
        }else{
            isnombreUsuarioValid.value = false;
            nombreUsuarioErrorMsg.value = "Nombre de usuario no valido";
        }
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


    fun validateNumeroTelefono(){
        val regex = Regex("^[0-9]{7,10}\$")
        if(regex.matches(numeroTelefono.value)){
            isNumeroTelefonoValid.value = true;
            numeroTelefonoErrorMsg.value = "";
        }else{
            isNumeroTelefonoValid.value = false;
            numeroTelefonoErrorMsg.value = "Numero de telefono no valido";
        }
    }

    fun validateDireccion(){
        val regex = Regex("^[a-zA-Z0-9\\s#-]+$")
        if(direccion.value.contains("-") && direccion.value.contains("#") && regex.matches(direccion.value)){
            isDireccionValid.value = true;
            direccionErrorMsg.value = "";
        }else{
            isDireccionValid.value = false;
            direccionErrorMsg.value = "Direccion no valida";
        }
    }

    fun validateFechaNacimiento(){
        val anioNacimiento = fechaNacimiento.value.substringAfterLast("/").toInt()
        Log.d("anioNacimiento", anioNacimiento.toString())
        val anioActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        val edad = anioActual - anioNacimiento
        Log.d("edad", edad.toString())
        if(edad >= 18){
            isFechaNacimientoValid.value = true;
            fechaNacimientoErrorMsg.value = "";
        }else{
            isFechaNacimientoValid.value = false;
            fechaNacimientoErrorMsg.value = "Debes tener al menos 18 años";
        }
    }

    //Logica de negocio

    fun loadUsers() {
        viewModelScope.launch {
            _users.value = getUsersList()
        }
    }
    private suspend fun getUsersList(): List<User> {
        val snapshot = db.collection("users")
            .get().await()
        return snapshot.documents.mapNotNull {
            val user = it.toObject(User::class.java)
            requireNotNull(user)
            user.id = it.id
            user
        }
    }

    suspend fun createUser(user: User, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val result = auth.createUserWithEmailAndPassword(user.email, user.password).await()
        val userId = result.user?.uid
        if (userId != null) {
            user.id = userId
            db.collection("users").document(userId).set(user).await()
            loadUsers()
            onSuccess()
        } else {
            onError("Error al crear usuario: ID de usuario nulo.")
        }
    }
}