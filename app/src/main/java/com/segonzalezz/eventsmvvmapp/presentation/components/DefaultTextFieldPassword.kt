package com.segonzalezz.eventsmvvmapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segonzalezz.eventsmvvmapp.R

@Composable
fun DefaultTextFieldPassword(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    validateField: () -> Unit = {},
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMsg: String = ""

){
    var passwordVisible by remember { mutableStateOf(false) }
    val icon2 =  if(passwordVisible){
        painterResource(id = R.drawable.visibility)
    }else{
        painterResource(id = R.drawable.visibility_off)
    }
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = {
                onValueChange(it)
                validateField()},
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            label = { Text(label) },
            leadingIcon = {
                Icon(imageVector = icon, contentDescription = "Icono password", tint = Color.White)
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = icon2, contentDescription = "Icono ver contraseña", tint = Color.White)
                }
            },
            visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Text(modifier = Modifier.padding(top=5.dp), text =errorMsg, fontSize = 11.sp, color = Color.Red )

    }

}
