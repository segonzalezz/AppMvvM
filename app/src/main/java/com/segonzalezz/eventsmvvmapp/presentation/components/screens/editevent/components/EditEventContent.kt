package com.segonzalezz.eventsmvvmapp.presentation.components.screens.editevent.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.model.Event
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.EventsViewModel
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.components.LocationWithSeats
import kotlinx.coroutines.launch

@Composable
    fun EditEventContent(navController: NavHostController, viewModel: EventsViewModel = hiltViewModel()) {
        LaunchedEffect(Unit) {
            val event = navController.previousBackStackEntry?.savedStateHandle?.get<Event>("selectedEvent")
            event?.let {
                Log.d("1232", "Recibiendo evento desde el estado: ${it.title}")
                viewModel.setSelectedEvent(it)

                // Inicializar campos con los datos del evento seleccionado
                viewModel.title.value = it.title
                viewModel.description.value = it.description
                viewModel.date.value = it.date
                viewModel.address.value = it.address
                viewModel.city.value = it.city
                viewModel.type.value = it.type
                viewModel.locations.value = it.locations.toMutableList()
            } ?: Log.d("DEBUG", "No se encontró evento en savedStateHandle")
        }
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
            .height(280.dp)
            .fillMaxWidth()
    ) {
        Image(
            painterResource(id = R.drawable.scott), // Cambia por tu imagen
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun CardForm(navController: NavHostController, viewModel: EventsViewModel) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 26.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .offset(y = (-152).dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Editar Evento",
                modifier = Modifier.padding(top = 20.dp, bottom = 0.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(10.dp))

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

            // Campo: Fecha de Inicio
            Text(text = "Fecha del Evento", color = MaterialTheme.colorScheme.onSurface)
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
                    viewModel.validateAddress()
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
                icon = Icons.Default.Info,
                errorMsg = viewModel.cityErrorMsg.value
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Detalles de Ubicaciones con Asientos
            LaunchedEffect(viewModel.locations.value) {
                Log.d("DE464UG", "Sincronizando fecha de inicio: ${viewModel.locations.value}")
            }
            Text(text = "Ubicaciones del Evento", color = MaterialTheme.colorScheme.onSurface)
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
                Spacer(modifier = Modifier.height(10.dp))
            }

            Button(
                onClick = { viewModel.addLocation() },
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 55.dp)
                    .height(40.dp)
            ) {
                Text(text = "Agregar Ubicación")
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Botón para Guardar Cambios
            DefaultButton(
                text = "Guardar Cambios",
                enabled = viewModel.hasChanges(),
                onClick = {
                    viewModel.viewModelScope.launch {
                        val updatedEvent = Event(
                            id = viewModel.selectedEvent.value?.id ?: "",
                            title = viewModel.title.value,
                            description = viewModel.description.value,
                            date = viewModel.date.value,
                            address = viewModel.address.value,
                            city = viewModel.city.value,
                            type = viewModel.type.value,
                            locations = viewModel.locations.value
                        )
                        viewModel.editEvent(updatedEvent,
                            onSuccess = {
                                Toast.makeText(context, "Evento actualizado exitosamente", Toast.LENGTH_LONG).show()
                                navController.navigateUp()
                            },
                            onError = { message ->
                                Toast.makeText(context, "Error al actualizar: $message", Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }
            )
        }
    }
}
