package com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.components

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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.model.Event
import com.segonzalezz.eventsmvvmapp.model.EventType
import com.segonzalezz.eventsmvvmapp.model.Location
import com.segonzalezz.eventsmvvmapp.model.Seat
import com.segonzalezz.eventsmvvmapp.presentation.components.LocationInputRow
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.EventsViewModel
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens
import kotlinx.coroutines.launch


@Composable
fun RegisterEventsContent(navController: NavHostController, viewModel: EventsViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm(navController, viewModel)
    }
}

@Composable
fun BoxHeader() {
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp))
    ) {
        Image(
            painterResource(id = R.drawable.scott),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun CardForm(navController: NavHostController, viewModel: EventsViewModel) {
    val context = LocalContext.current
    val categoriesList = EventType.values().toList()
    var selectedCategoryIndex by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .offset(y = (-150).dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Crear Evento",
                modifier = Modifier.padding(top = 20.dp, bottom = 0.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(text = "Por favor ingresar campos:", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            // Campo: Nombre del Evento
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.title.value,
                onValueChange = {
                    viewModel.title.value = it
                    viewModel.validateTitle()
                },
                label = "Nombre del Evento",
                icon = Icons.Default.Info,
                errorMsg = viewModel.titleErrorMsg.value
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Campo: Descripción del Evento
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.description.value,
                onValueChange = {
                    viewModel.description.value = it
                    viewModel.validateDescription()
                },
                label = "Descripción del Evento",
                icon = Icons.Default.Info,
                errorMsg = viewModel.descriptionErrorMsg.value
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Campo: Fecha de Inicio del Evento
            Text("Fecha de Inicio del Evento:", color = Color.White)
            DefaultDatePickerDocked(
                initialDate = viewModel.date.value,
                onDateSelected = {
                    viewModel.date.value = it
                    viewModel.validateDate()
                },
                errorMsg = viewModel.dateErrorMsg.value
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Campo: Ubicación del Evento
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.address.value,
                onValueChange = {
                    viewModel.address.value = it
                    viewModel.validateLocations()
                },
                label = "Ubicación",
                icon = Icons.Default.LocationOn,
                errorMsg = viewModel.addressErrorMsg.value
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Campo: Ciudad del Evento
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.city.value,
                onValueChange = {
                    viewModel.city.value = it
                    viewModel.validateCity()
                },
                label = "Ciudad",
                icon = Icons.Default.LocationOn,
                errorMsg = viewModel.cityErrorMsg.value
            )
            Spacer(modifier = Modifier.height(5.dp))

            // Ubicaciones del Evento con Asientos
            Text("Detalles del Evento:", color = Color.White)
            viewModel.locations.value.forEachIndexed { index, location ->
                LocationWithSeats(
                    location = location,
                    onUpdate = { updatedLocation ->
                        viewModel.updateLocation(index, updatedLocation)
                    },
                    onRemove = {
                        viewModel.removeLocation(index)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Botón para agregar Ubicación
            Button(
                onClick = { viewModel.addLocation() },
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 55.dp).height(40.dp)
            ) {
                Text(text = "Agregar Ubicación")
            }

            // Campo: Categoría del Evento
            Text("Categoría del Evento:", color = Color.White)
            DefaultDropdownMenu(
                options = categoriesList.map { it.name },
                selectedIndex = selectedCategoryIndex,
                onOptionSelected = { selectedIndex ->
                    selectedCategoryIndex = selectedIndex
                    viewModel.type.value = categoriesList[selectedIndex]
                },
                icon = Icons.Default.Info // Icono requerido
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Botón para Crear Evento
            DefaultButton(
                text = "Crear",
                enabled =  viewModel.isEnabledCreateEventButton.value, // Siempre habilitado para permitir la validación al hacer clic
                onClick = {
                    if (viewModel.validateFields()) {
                        viewModel.createEvent(
                            Event(
                                title = viewModel.title.value,
                                description = viewModel.description.value,
                                date = viewModel.date.value,
                                address = viewModel.address.value,
                                city = viewModel.city.value,
                                locations = viewModel.locations.value,
                                type = viewModel.type.value
                            ),
                            onSuccess = {
                                Toast.makeText(context, "Evento creado exitosamente", Toast.LENGTH_LONG).show()
                                navController.navigate(AppScreens.MenuAdminScreen.route) {
                                    popUpTo(AppScreens.RegisterEventsScreen.route) { inclusive = true }
                                }
                            },
                            onError = { message ->
                                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                            }
                        )
                    } else {
                        Toast.makeText(context, "Por favor completa todos los campos obligatorios.", Toast.LENGTH_LONG).show()
                    }
                }
            )
        }
    }
}



@Composable
fun LocationWithSeats(
    location: Location,
    onUpdate: (Location) -> Unit,
    onRemove: () -> Unit,
    showErrors: Boolean = false
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        DefaultTextField(
            value = location.name,
            onValueChange = { newName ->
                onUpdate(location.copy(name = newName))
            },
            label = "Nombre de la Ubicación",
            icon = Icons.Default.Info,
            errorMsg = if (showErrors && location.name.isBlank()) "El nombre no puede estar vacío" else ""
        )
        Spacer(modifier = Modifier.height(8.dp))

        DefaultTextField(
            value = location.maxCapacity.toString(),
            onValueChange = { newCapacity ->
                val updatedCapacity = newCapacity.toIntOrNull() ?: 0
                onUpdate(location.copy(maxCapacity = updatedCapacity))
            },
            label = "Capacidad Máxima",
            icon = Icons.Default.Info,
            errorMsg = if (showErrors && location.maxCapacity <= 0) "Capacidad debe ser mayor a 0" else ""
        )
        Spacer(modifier = Modifier.height(8.dp))

        DefaultTextField(
            value = location.price.toString(),
            onValueChange = { newPrice ->
                val updatedPrice = newPrice.toFloatOrNull() ?: 0f
                onUpdate(location.copy(price = updatedPrice))
            },
            label = "Precio por Asiento",
            icon = Icons.Default.Info,
            errorMsg = if (showErrors && location.price <= 0) "Precio debe ser mayor a 0" else ""
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onRemove, modifier = Modifier.align(Alignment.End)) {
            Text("Eliminar Ubicación")
        }
    }
}




fun generateSeats(maxCapacity: Int): List<Seat> {
    val rows = (maxCapacity + 9) / 10 // Genera asientos en bloques de 10 por fila
    return (1..rows).flatMap { row ->
        (1..10).map { column -> Seat(row = row.toString(), column = column) }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMsg: String = "",
    isReadOnly: Boolean = false
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            leadingIcon = icon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            readOnly = isReadOnly,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            singleLine = true
        )
        if (errorMsg.isNotEmpty()) {
            Text(
                text = errorMsg,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

