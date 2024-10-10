package com.segonzalezz.eventsmvvmapp.presentation.components.screens.register.components

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
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.dominio.UserController
import com.segonzalezz.eventsmvvmapp.model.Role
import com.segonzalezz.eventsmvvmapp.model.dto.UserDTO
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDropdownMenu
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun RegisterContent(navController: NavHostController){
    val contextt = LocalContext.current
    val userController = remember { UserController(contextt) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm(userController, navController)
    }
}

    @Composable
    fun CardForm(userController: UserController,navController: NavHostController){
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var usuario by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var numero by remember { mutableStateOf("") }
        var direccion by remember { mutableStateOf("") }
        var birthDate by remember { mutableStateOf("") }
        var showError by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf("") }
        val context = LocalContext.current

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
                    value = name,
                    onValueChange = { name = it },
                    label = "Nombre",
                    icon = Icons.Default.Face,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = "Usuario",
                    icon = Icons.Default.AccountBox,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = numero,
                    onValueChange = { numero = it },
                    label = "Numero",
                    icon = Icons.Default.Call,
                    keyboardType = KeyboardType.Phone
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = "Dirección",
                    icon = Icons.Default.LocationOn,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "Fecha nacimiento:", modifier = Modifier.align(Alignment.Start), color = Color.White)
                DefaultDatePickerDocked(onDateSelected = { birthDate = it })
                Spacer(modifier = Modifier.height(10.dp))

                DefaultButton(text = "Registrarse", onClick = {
                    if (birthDate.isEmpty()) {
                        showError = true
                        errorMessage = "Falta la fecha de nacimiento"
                        return@DefaultButton
                    }

                    val fechaNacimientoInt = try {
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) // Formato de entrada
                        val date = sdf.parse(birthDate)
                        val formattedDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(date) // Convertir a "yyyyMMdd"
                        formattedDate.toInt() // Convertir la cadena "yyyyMMdd" a Int
                    } catch (e: Exception) {
                        showError = true
                        errorMessage = "La fecha de nacimiento no es válida"
                        return@DefaultButton
                    }

                    val user = UserDTO(
                        id = usuario,
                        nombre = name,
                        correo = email,
                        usuario = usuario,
                        contraseña = password,
                        numeroTelefono = numero,
                        direccion = direccion,
                        fechaNacimiento = fechaNacimientoInt,
                        role = Role.USER
                    )

                    val result = userController.registerUser(context,user)

                    if (result == "Registro exitoso") {
                        Toast.makeText(context, result, Toast.LENGTH_LONG).show()
                        navController.navigate(AppScreens.LoginScreen.route)
                    } else {
                        showError = true
                        errorMessage = result
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    }
                })

                if (showError) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = errorMessage, color = Color.Red)
                }
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
