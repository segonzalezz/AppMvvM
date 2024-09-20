package com.segonzalezz.eventsmvvmapp.presentation.components.screens.register.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.components.BoxHeader


@Composable
fun RegisterContent(){
    Column(
        modifier = Modifier
            .fillMaxWidth().verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm()
    }
}

    @Composable
    fun CardForm(){
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var usuario by remember { mutableStateOf("")  }
        var password by remember { mutableStateOf("") }
        var numero by remember { mutableStateOf("") }
        var direccion by remember { mutableStateOf("") }

        Card(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp)
                .offset(y = (-230).dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column( modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "Register",
                    modifier = Modifier.padding(top = 20.dp, bottom = 0.dp, start = 0.dp, end = 0.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Por favor ingresar campos:"
                )
                Spacer(modifier = Modifier.height(16.dp))
                DefaultTextField(modifier = Modifier
                    .padding()
                    .fillMaxWidth(), value = name, onValueChange = {name = it}, label = "Nombre", icon = Icons.Default.Face, keyboardType = KeyboardType.Email)
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(modifier = Modifier
                    .padding()
                    .fillMaxWidth(), value = email, onValueChange = {email = it}, label = "Email", icon = Icons.Default.Email, keyboardType = KeyboardType.Email)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Validar email", modifier = Modifier.align(Alignment.End), color = Color.White)
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(modifier = Modifier
                    .padding()
                    .fillMaxWidth(), value = usuario, onValueChange = {usuario = it}, label = "Usuario", icon = Icons.Default.AccountBox, keyboardType = KeyboardType.Email)
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(modifier = Modifier
                    .padding()
                    .fillMaxWidth(), value = password, onValueChange = {password = it}, label = "Password", icon = Icons.Default.Lock, keyboardType = KeyboardType.Email)
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(modifier = Modifier
                    .padding()
                    .fillMaxWidth(), value = numero, onValueChange = {numero = it}, label = "Numero", icon = Icons.Default.Call, keyboardType = KeyboardType.Email)
                Spacer(modifier = Modifier.height(10.dp))
                DefaultTextField(modifier = Modifier
                    .padding()
                    .fillMaxWidth(), value = direccion, onValueChange = {direccion = it}, label = "Dirección", icon = Icons.Default.LocationOn, keyboardType = KeyboardType.Email)
                Spacer(modifier = Modifier.height(10.dp))
                DefaultButton(text = "Registrarse", onClick = {} )
            }

        }
    }

    @Composable
    fun BoxHeader(){
        Box(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
                .background(color = Color.Gray)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }
    }
