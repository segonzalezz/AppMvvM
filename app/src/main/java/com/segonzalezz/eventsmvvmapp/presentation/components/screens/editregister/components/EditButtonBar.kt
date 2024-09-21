package com.segonzalezz.eventsmvvmapp.presentation.components.screens.editregister.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens

@Composable
fun EditButtonBar(){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Cambiar imagen?", color = Color.White
                )
        }
    }
}