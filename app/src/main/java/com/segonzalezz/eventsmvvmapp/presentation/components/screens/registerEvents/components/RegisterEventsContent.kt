package com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.components

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
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDropdownMenu
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultSliderMinimal
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import androidx.compose.runtime.Composable


@Composable
fun RegisterEventsContent(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
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
    var birthDate by remember { mutableStateOf("") }
    var birthDatee by remember { mutableStateOf("") }
    val list = listOf("Arte y Cultura", "Música y Conciertos")
    var selectedRoleIndex by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 26.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .offset(y = (-170).dp)
    ) {
        Column( modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Crear Evento: ",
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
                .fillMaxWidth(), value = name, onValueChange = {name = it}, label = "Nombre del Evento", icon = Icons.Default.Info, keyboardType = KeyboardType.Email)
            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextField(modifier = Modifier
                .padding()
                .fillMaxWidth(), value = email, onValueChange = {email = it}, label = "Ubicación del Evento", icon = Icons.Default.LocationOn, keyboardType = KeyboardType.Email)
            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextField(modifier = Modifier
                .padding()
                .fillMaxWidth(), value = email, onValueChange = {email = it}, label = "Información del Evento", icon = Icons.Default.Info, keyboardType = KeyboardType.Email)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Seleccionar foto", color = Color.White, modifier = Modifier
                .align(Alignment.End)
                .padding(end = 2.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Fecha Inicio del Evento:", color = Color.White)
            DefaultDatePickerDocked(onDateSelected = { birthDate = it })
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Fecha Fin del Evento:", color = Color.White)
            DefaultDatePickerDocked(onDateSelected = { birthDatee = it })
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Numero de Entradas:", color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            DefaultSliderMinimal()
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Categoria del Evento:", color = Color.White)
            DefaultDropdownMenu(options = list, selectedIndex = selectedRoleIndex, onOptionSelected = {}  , icon = Icons.Default.Lock)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Precio:", color = Color.White)
            DefaultTextField(modifier = Modifier
                .padding()
                .fillMaxWidth(), value = email, onValueChange = {email = it}, label = "Precio del Evento", icon = Icons.Default.Info, keyboardType = KeyboardType.Email)
            Spacer(modifier = Modifier.height(10.dp))
            DefaultButton(text = "Crear", onClick = {} )
        }

    }
}

@Composable
fun BoxHeader(){
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp))
    ) {
        Image(
            painterResource(id = R.drawable.scott),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

    }
}