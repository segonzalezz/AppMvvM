package com.segonzalezz.eventsmvvmapp.presentation.components.screens.editregister.components

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.model.User
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultCheckBox
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDropdownMenu
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultImagePicker
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.LoginViewModel
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditRegisterContent(navController: NavController, viewModel: LoginViewModel = hiltViewModel()){
    LaunchedEffect(Unit) {
        viewModel.fetchUserData() // Asegúrate de cargar los datos del usuario
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm(navController = navController)
    }
}

@Composable
fun CardForm(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {
    val user = viewModel.userData.value
    val context = LocalContext.current
    if (user != null) {
        var name by remember { mutableStateOf(user.name) }
        var email by remember { mutableStateOf(user.email) }
        var username by remember { mutableStateOf(user.username) }
        var password by remember { mutableStateOf(user.password) }
        var phoneNumber by remember { mutableStateOf(user.phoneNumber) }
        var address by remember { mutableStateOf(user.address) }
        var birthDate by remember { mutableStateOf(user.birthDate) }

        // Detectar cambios en los datos
        val isButtonEnabled = remember(name, email, username, password, phoneNumber, address, birthDate) {
            name != user.name ||
                    email != user.email ||
                    username != user.username ||
                    password != user.password ||
                    phoneNumber != user.phoneNumber ||
                    address != user.address ||
                    birthDate != user.birthDate
        }

        // Variables para los mensajes de error
        var errorName by remember { mutableStateOf("") }
        var errorEmail by remember { mutableStateOf("") }
        var errorUsername by remember { mutableStateOf("") }
        var errorPassword by remember { mutableStateOf("") }
        var errorPhoneNumber by remember { mutableStateOf("") }
        var errorAddress by remember { mutableStateOf("") }
        var errorBirthDate by remember { mutableStateOf("") }

        Card(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp)
                .offset(y = (-90).dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "Editar cuenta:",
                    modifier = Modifier.padding(top = 20.dp, bottom = 0.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(text = "Por favor ingresar campos:")
                Spacer(modifier = Modifier.height(16.dp))

                // Campo Nombre
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                        errorName = if (name.isEmpty()) "El nombre no puede estar vacío" else ""
                    },
                    label = "Nombre",
                    icon = Icons.Default.Edit,
                    errorMsg = errorName
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Campo Email
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = {
                        email = it
                        errorEmail = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "El correo no es válido" else ""
                    },
                    label = "Email",
                    icon = Icons.Default.Edit,
                    errorMsg = errorEmail,
                    readOnly = true
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Campo Usuario
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = username,
                    onValueChange = {
                        username = it
                        errorUsername = if (username.length <= 5) "El usuario debe tener más de 5 caracteres" else ""
                    },
                    label = "Usuario",
                    icon = Icons.Default.Edit,
                    errorMsg = errorUsername
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Campo Contraseña
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    icon = Icons.Default.Edit,
                    errorMsg = errorPassword,
                    readOnly = true // Hacer la contraseña no editable
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Campo Número
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = phoneNumber,
                    onValueChange = {
                        phoneNumber = it
                        errorPhoneNumber = if (phoneNumber.isEmpty()) "El número no puede estar vacío" else ""
                    },
                    label = "Número",
                    icon = Icons.Default.Edit,
                    errorMsg = errorPhoneNumber
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Campo Dirección
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = address,
                    onValueChange = {
                        address = it
                        errorAddress = if (address.isEmpty()) "La dirección no puede estar vacía" else ""
                    },
                    label = "Dirección",
                    icon = Icons.Default.Edit,
                    errorMsg = errorAddress
                )
                Spacer(modifier = Modifier.height(14.dp))

                // Campo Fecha de nacimiento
                Text(text = "Fecha nacimiento:")
                DefaultDatePickerDocked(
                    initialDate = birthDate,
                    onDateSelected = { newDate ->
                        birthDate = newDate
                        errorBirthDate = if (!isAdult(newDate)) "Debes ser mayor de 18 años" else ""
                    },
                    errorMsg = errorBirthDate
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Botón para guardar los cambios
                DefaultButton(
                    text = "Guardar cambios",
                    onClick = {
                        viewModel.updateUserData(
                            updatedUser = User(
                                id = user.id,
                                name = name,
                                email = email,
                                username = username,
                                password = password,
                                phoneNumber = phoneNumber,
                                address = address,
                                birthDate = birthDate,
                                role = user.role
                            ),
                            onSuccess = { role ->
                                Toast.makeText(
                                    context,
                                    "Datos actualizados correctamente",
                                    Toast.LENGTH_SHORT
                                ).show()

                                if (role == "ADMIN") {
                                    navController.navigate(AppScreens.MenuAdminScreen.route) {
                                        popUpTo(AppScreens.EditRegisterScreen.route) { inclusive = true }
                                    }
                                } else if (role == "USER") {
                                    navController.navigate(AppScreens.MenUserScreen.route) {
                                        popUpTo(AppScreens.EditRegisterScreen.route) { inclusive = true }
                                    }
                                }
                            },
                            onError = { errorMessage ->
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    enabled = isButtonEnabled // Habilitar o deshabilitar el botón
                )
            }
        }
    }
}


// Función para validar si un usuario es mayor de edad
fun isAdult(birthDate: String): Boolean {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val birth = formatter.parse(birthDate)
    val today = Date()
    val diff = today.time - birth.time
    val age = diff / (1000L * 60 * 60 * 24 * 365)
    return age >= 18
}


@Composable
fun BoxHeader(){
    Box(
        modifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp))
    ) {
        Image(
            painterResource(id = R.drawable.people),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}
