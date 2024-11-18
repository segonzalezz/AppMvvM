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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.model.Event
import com.segonzalezz.eventsmvvmapp.model.EventType
import com.segonzalezz.eventsmvvmapp.presentation.components.LocationInputRow
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.EventsViewModel
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens
import kotlinx.coroutines.launch


@Composable
fun RegisterEventsContent(navController: NavHostController, viewModel: EventsViewModel = hiltViewModel()){
    val selectedEvent by remember { viewModel.selectedEvent }

    LaunchedEffect(selectedEvent) {
        selectedEvent?.let { event ->
            viewModel.title.value = event.title
            viewModel.description.value = event.description
            viewModel.date.value = event.date
            viewModel.address.value = event.address
            viewModel.city.value = event.city
            viewModel.locations.value = event.locations
            viewModel.type.value = event.type
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm(navController)
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

@Composable
fun CardForm(navController: NavHostController, viewModel: EventsViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf("") }
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

            // Nombre del Evento
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.title.value,
                onValueChange = { viewModel.title.value = it },
                label = "Nombre del Evento",
                icon = Icons.Default.Info,
                keyboardType = KeyboardType.Text,
                errorMsg = viewModel.titleErrorMsg.value,
                validateField = { viewModel.validateTitle() }
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Descripción del Evento
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.description.value,
                onValueChange = { viewModel.description.value = it },
                label = "Descripción del Evento",
                icon = Icons.Default.Info,
                keyboardType = KeyboardType.Text,
                errorMsg = viewModel.descriptionErrorMsg.value,
                validateField = { viewModel.validateDescription() }
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Fecha de Inicio del Evento
            Text(text = "Fecha de Inicio del Evento:", modifier = Modifier.align(Alignment.Start), color = Color.White)
            DefaultDatePickerDocked(
                onDateSelected = { viewModel.date.value = it },
                errorMsg = viewModel.dateErrorMsg.value,
                validateField = { viewModel.validateDate() }
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Ubicación del Evento
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.address.value,
                onValueChange = { viewModel.address.value = it },
                label = "Ubicación",
                icon = Icons.Default.LocationOn,
                keyboardType = KeyboardType.Text,
                errorMsg = viewModel.addressErrorMsg.value,
                validateField = { viewModel.validateAddress() }
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Ciudad del Evento
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.city.value,
                onValueChange = { viewModel.city.value = it },
                label = "Ciudad",
                icon = Icons.Default.Info,
                keyboardType = KeyboardType.Text,
                errorMsg = viewModel.cityErrorMsg.value,
                validateField = { viewModel.validateCity() }
            )
            Spacer(modifier = Modifier.height(5.dp))

            // Lista de Locations
            Text(text = "Ubicaciones del Evento:", color = Color.White)
            viewModel.locations.value.forEachIndexed { index, location ->
                LocationInputRow(
                    location = location,
                    onNameChange = { newName ->
                        viewModel.updateLocation(index, location.copy(name = newName))
                    },
                    onMaxCapacityChange = { newCapacity ->
                        viewModel.updateLocation(index, location.copy(maxCapacity = newCapacity))
                    },
                    onPriceChange = { newPrice ->
                        viewModel.updateLocation(index, location.copy(price = newPrice))
                    },
                    onRemoveClick = {
                        viewModel.removeLocation(index)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Botón para agregar Location
            Button(
                onClick = { viewModel.addLocation() },
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 55.dp).height(40.dp)
            ) {
                Text(text = "Agregar Ubicación")
            }

            // Categoría del Evento
            Text(text = "Categoría del Evento:", color = Color.White)
            DefaultDropdownMenu(
                options = categoriesList.map { it.name },
                selectedIndex = selectedCategoryIndex,
                onOptionSelected = { selectedCategoryIndex = it },
                icon = Icons.Default.Lock
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Botón para Crear Evento
            DefaultButton(
                text = "Crear",
                enabled = viewModel.isEnabledCreateEventButton,
                onClick = {
                    viewModel.viewModelScope.launch {
                        val newEvent = Event(
                            title = viewModel.title.value,
                            description = viewModel.description.value,
                            date = viewModel.date.value,
                            address = viewModel.address.value,
                            city = viewModel.city.value,
                            type = categoriesList[selectedCategoryIndex], // Asignar el tipo de evento seleccionado
                            locations = viewModel.locations.value, // Usar las ubicaciones ingresadas
                            imagUrl = "" // Agregar lógica para la imagen si es necesario
                        )
                        viewModel.createEvent(newEvent,
                            onSuccess = {
                                navController.navigate(AppScreens.MenuAdminScreen.route) {
                                    popUpTo(AppScreens.RegisterEventsScreen.route) { inclusive = true }
                                }
                            },
                            onError = { message ->
                                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }
            )
        }
    }
}
