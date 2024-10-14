package com.segonzalezz.eventsmvvmapp.presentation.components.screens.register.components

import android.util.Log
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
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
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.dominio.UserController
import com.segonzalezz.eventsmvvmapp.model.Role
import com.segonzalezz.eventsmvvmapp.model.dto.UserDTO
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDropdownMenu
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.LoginViewModel
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.register.RegisterViewModel
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun RegisterContent(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm(navController)
    }
}

    @Composable
    fun CardForm(navController: NavHostController,  viewModel: RegisterViewModel = hiltViewModel()){
        Card(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp)
                .offset(y = (-235).dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "Registro",
                    modifier = Modifier.padding(top = 20.dp, bottom = 0.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(text = "Por favor ingresar campos:")
                Spacer(modifier = Modifier.height(16.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.nombre.value,
                    onValueChange = { viewModel.nombre.value = it },
                    label = "Nombre",
                    icon = Icons.Default.Face,
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.email.value,
                    onValueChange = { viewModel.email.value = it },
                    label = "Email",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrorMsg.value,
                    validateField = {viewModel.validateEmail()}
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.nombreUsuario.value,
                    onValueChange = { viewModel.nombreUsuario.value = it },
                    label = "Usuario",
                    icon = Icons.Default.AccountBox,
                    keyboardType = KeyboardType.Text,
                    errorMsg = viewModel.nombreUsuarioErrorMsg.value,
                    validateField = {viewModel.validateNombreUsuario()}
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.password.value,
                    onValueChange = { viewModel.password.value = it },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password,
                    errorMsg = viewModel.passwordErrorMsg.value,
                    validateField = {viewModel.validatePassword()}
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.passwordd.value,
                    onValueChange = { viewModel.passwordd.value = it },
                    label = "Confirmar contraseña",
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password,
                    errorMsg = viewModel.passwordErrorMsgg.value,
                    validateField = {viewModel.validateConfirmPassword()}
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.numeroTelefono.value,
                    onValueChange = { viewModel.numeroTelefono.value = it },
                    label = "Numero",
                    icon = Icons.Default.Call,
                    keyboardType = KeyboardType.Phone,
                    errorMsg = viewModel.numeroTelefonoErrorMsg.value,
                    validateField = {viewModel.validateNumeroTelefono()}
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.direccion.value,
                    onValueChange = { viewModel.direccion.value = it },
                    label = "Dirección",
                    icon = Icons.Default.LocationOn,
                    keyboardType = KeyboardType.Text,
                    errorMsg = viewModel.direccionErrorMsg.value,
                    validateField = {viewModel.validateDireccion()}
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "Fecha nacimiento:", modifier = Modifier.align(Alignment.Start), color = Color.White)
                DefaultDatePickerDocked(
                    onDateSelected = { viewModel.fechaNacimiento.value = it },
                    errorMsg = viewModel.fechaNacimientoErrorMsg.value,
                    validateField = {viewModel.validateFechaNacimiento()}
                )
                Spacer(modifier = Modifier.height(10.dp))

                DefaultButton(text = "Registrarse", onClick = {
                }, enabled = viewModel.isEnabledRegisterButton)
            }
        }
    }

    @Composable
    fun BoxHeader(){
        Box(
            modifier = Modifier
                .height(340.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(0.dp))
        ) {
            Image(
                painterResource(id = R.drawable.trpss),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
