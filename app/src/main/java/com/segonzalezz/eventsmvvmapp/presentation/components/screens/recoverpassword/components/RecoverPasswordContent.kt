package com.segonzalezz.eventsmvvmapp.presentation.components.screens.recoverpassword.components

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextFieldPassword
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.LoginViewModel

@Composable
fun RecoverPasswordContent(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm(viewModel = viewModel, navController = navController)
    }
}

@Composable
fun CardForm(viewModel: LoginViewModel, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf("") }
    var errorCurrentPassword by remember { mutableStateOf("") }
    var errorNewPassword by remember { mutableStateOf("") }
    var errorPasswordConfirm by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Verificar si todos los campos son válidos
    val isFormValid = remember(email, currentPassword, newPassword, passwordConfirm, errorEmail, errorCurrentPassword, errorNewPassword, errorPasswordConfirm) {
        email.isNotBlank() &&
                errorEmail.isEmpty() &&
                currentPassword.isNotBlank() &&
                errorCurrentPassword.isEmpty() &&
                newPassword.isNotBlank() &&
                errorNewPassword.isEmpty() &&
                passwordConfirm.isNotBlank() &&
                errorPasswordConfirm.isEmpty()
    }

    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .offset(y = (-140).dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Recuperar contraseña: ",
                modifier = Modifier.padding(top = 20.dp, bottom = 0.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(text = "Por favor ingresar campos:")
            Spacer(modifier = Modifier.height(16.dp))

            // Campo Email
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                    errorEmail = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "El correo no es válido" else ""
                },
                label = "Email",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                errorMsg = errorEmail
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Campo Contraseña Actual
            DefaultTextFieldPassword(
                modifier = Modifier.fillMaxWidth(),
                value = currentPassword,
                onValueChange = {
                    currentPassword = it
                    errorCurrentPassword = if (currentPassword.isEmpty()) "La contraseña actual es obligatoria" else ""
                },
                label = "Contraseña Actual",
                icon = Icons.Default.Lock,
                errorMsg = errorCurrentPassword
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Campo Nueva Contraseña
            DefaultTextFieldPassword(
                modifier = Modifier.fillMaxWidth(),
                value = newPassword,
                onValueChange = {
                    newPassword = it
                    errorNewPassword = if (newPassword.length < 6) "La nueva contraseña debe tener al menos 6 caracteres" else ""
                },
                label = "Nueva Contraseña",
                icon = Icons.Default.Lock,
                errorMsg = errorNewPassword
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Campo Confirmar Nueva Contraseña
            DefaultTextFieldPassword(
                modifier = Modifier.fillMaxWidth(),
                value = passwordConfirm,
                onValueChange = {
                    passwordConfirm = it
                    errorPasswordConfirm = if (passwordConfirm != newPassword) "Las contraseñas no coinciden" else ""
                },
                label = "Confirmar Contraseña",
                icon = Icons.Default.Lock,
                errorMsg = errorPasswordConfirm
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Botón Cambiar Contraseña
            DefaultButton(
                text = "Cambiar contraseña",
                onClick = {
                    if (isFormValid) {
                        viewModel.recoverPassword(
                            email = email,
                            currentPassword = currentPassword,
                            newPassword = newPassword,
                            onSuccess = {
                                Toast.makeText(
                                    context,
                                    "Contraseña actualizada correctamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack()
                            },
                            onError = { errorMessage ->
                                Toast.makeText(
                                    context,
                                    errorMessage,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                },
                enabled = isFormValid // Solo se habilita si el formulario es válido
            )
        }
    }
}


@Composable
fun BoxHeader() {
    Box(
        modifier = Modifier
            .height(340.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp))
    ) {
        Image(
            painterResource(id = R.drawable.trpss),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

