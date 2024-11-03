package com.segonzalezz.eventsmvvmapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.segonzalezz.eventsmvvmapp.model.Location

@Composable
fun LocationInputRow(
    location: Location,
    onNameChange: (String) -> Unit,
    onMaxCapacityChange: (Int) -> Unit,
    onPriceChange: (Float) -> Unit,
    onRemoveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(12.dp)
            .width(20.dp)
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Nombre del Location
        OutlinedTextField(
            value = location.name,
            onValueChange = onNameChange,
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        // Capacidad Máxima
        OutlinedTextField(
            value = location.maxCapacity.toString(),
            onValueChange = { value ->
                value.toIntOrNull()?.let { onMaxCapacityChange(it) }
            },
            label = { Text(text = "Máximo") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        // Precio
        OutlinedTextField(
            value = location.price.toString(),
            onValueChange = { value ->
                value.toFloatOrNull()?.let { onPriceChange(it) }
            },
            label = { Text(text = "Precio") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        // Botón de eliminar
        IconButton(
            onClick = onRemoveClick,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(48.dp)
                .background(MaterialTheme.colorScheme.error, shape = MaterialTheme.shapes.small)
        ) {
            Icon(
                painter = painterResource(id = com.segonzalezz.eventsmvvmapp.R.drawable.cancel),
                contentDescription = "Eliminar",
                tint = Color.White
            )
        }
    }
}