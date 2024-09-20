package com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextFieldPassword

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
    var usuario by remember { mutableStateOf("")  }
    var password by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .padding(start = 44.dp, end = 40.dp)
            .offset(y = (-60).dp),
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
            DefaultTextField(modifier = Modifier
                .padding()
                .fillMaxWidth(), value = usuario, onValueChange = {usuario = it}, label = "Usuario", icon = Icons.Default.AccountBox, keyboardType = KeyboardType.Email)
            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextFieldPassword(
                modifier = Modifier
                    .padding()
                    .fillMaxWidth(), value = password, onValueChange = {password = it}, label = "Usuario", icon = Icons.Default.Lock)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Recuperar contraseña", modifier = Modifier.align(Alignment.End), color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            DefaultButton(text = "Iniciar Sesión", onClick = {} )
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
