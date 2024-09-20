package com.segonzalezz.eventsmvvmapp.screens.login.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BoxHeader()
        CardForm()
    }
}

@Composable
fun CardForm(){
    Card(
        modifier = Modifier.padding(start =40.dp, end = 40.dp).offset(y = (-60).dp),
    ) {
        Column( modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Login",
                modifier = Modifier.padding(top = 10.dp, bottom = 0.dp, start = 0.dp, end = 0.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Por favor iniciar sesión:"
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Usuario: ") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Icono usuario", tint = Color.White)
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Contraseña: ") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Icono contraseña", tint = Color.White)
                }

            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Recuperar contraseña", modifier = Modifier.align(Alignment.End), color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(text = "Iniciar Sesión")
            }
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
