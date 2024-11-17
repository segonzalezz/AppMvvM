package com.segonzalezz.eventsmvvmapp.presentation.components.screens.editregister.components

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

// Campos editables
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Se asegura que ocupe todo el ancho disponible
                    value = name,
                    onValueChange = { name = it },
                    label = "Nombre",
                    icon = Icons.Default.Edit
                )
                Spacer(modifier = Modifier.height(10.dp))

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    icon = Icons.Default.Edit
                )
                Spacer(modifier = Modifier.height(10.dp))

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = username,
                    onValueChange = { username = it },
                    label = "Usuario",
                    icon = Icons.Default.Edit
                )
                Spacer(modifier = Modifier.height(10.dp))

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    icon = Icons.Default.Edit
                )
                Spacer(modifier = Modifier.height(10.dp))

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = "Número",
                    icon = Icons.Default.Edit
                )
                Spacer(modifier = Modifier.height(10.dp))

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = address,
                    onValueChange = { address = it },
                    label = "Dirección",
                    icon = Icons.Default.Edit
                )
                Spacer(modifier = Modifier.height(14.dp))

                Text(text = "Fecha nacimiento:")
                DefaultDatePickerDocked(initialDate = birthDate,onDateSelected = { birthDate = it })
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
                                // Mostrar Toast
                                Toast.makeText(
                                    context,
                                    "Datos actualizados correctamente",
                                    Toast.LENGTH_SHORT
                                ).show()

                                // Navegación basada en el rol
                                if (role == "ADMIN") {
                                    navController.navigate(AppScreens.MenuAdminScreen.route) {
                                        popUpTo(AppScreens.EditRegisterScreen.route) { inclusive = true }
                                    }
                                } else if (role == "USER") {
                                    navController.navigate(AppScreens.MenUserScreen.route
                                    ) {
                                        popUpTo(AppScreens.EditRegisterScreen.route) { inclusive = true }
                                    }
                                }
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
                )
            }
        }
    }
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
