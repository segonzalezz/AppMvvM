package com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.segonzalezz.eventsmvvmapp.model.Event
import com.segonzalezz.eventsmvvmapp.model.EventType
import com.segonzalezz.eventsmvvmapp.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor() : ViewModel() {
    private val db = Firebase.firestore

    // Eventos cargados desde Firestore
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    // Evento seleccionado
    var selectedEvent: MutableState<Event?> = mutableStateOf(null)

    // Campos del evento
    var title: MutableState<String> = mutableStateOf("")
    var description: MutableState<String> = mutableStateOf("")
    var date: MutableState<String> = mutableStateOf("")
    var address: MutableState<String> = mutableStateOf("")
    var city: MutableState<String> = mutableStateOf("")
    var type: MutableState<EventType> = mutableStateOf(EventType.DEFAULT)
    var locations: MutableState<MutableList<Location>> = mutableStateOf(mutableListOf())

    // Estados de validación y errores
    var titleErrorMsg: MutableState<String> = mutableStateOf("")
    var descriptionErrorMsg: MutableState<String> = mutableStateOf("")
    var dateErrorMsg: MutableState<String> = mutableStateOf("")
    var addressErrorMsg: MutableState<String> = mutableStateOf("")
    var cityErrorMsg: MutableState<String> = mutableStateOf("")
    var isEnabledCreateEventButton: MutableState<Boolean> = mutableStateOf(false)

    init {
        loadEvents()
    }

    // Cargar eventos desde Firestore
    fun loadEvents() {
        viewModelScope.launch {
            try {
                val snapshot = db.collection("events").get().await()
                _events.value = snapshot.documents.mapNotNull { doc ->
                    doc.toObject(Event::class.java)?.apply { id = doc.id }
                }
            } catch (e: Exception) {
                Log.e("EventsViewModel", "Error al cargar eventos: ${e.message}")
            }
        }
    }

    // Crear un nuevo evento
    fun createEvent(event: Event, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val docRef = db.collection("events").add(event).await()
                event.id = docRef.id
                loadEvents()
                onSuccess()
            } catch (e: Exception) {
                onError("Error al crear el evento: ${e.message}")
            }
        }
    }

    // Editar evento existente
    fun editEvent(event: Event, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                db.collection("events").document(event.id).set(event).await()
                loadEvents()
                onSuccess()
            } catch (e: Exception) {
                onError("Error al editar el evento: ${e.message}")
            }
        }
    }

    // Eliminar evento
    fun deleteEvent(event: Event) {
        viewModelScope.launch {
            try {
                db.collection("events").document(event.id).delete().await()
                loadEvents()
            } catch (e: Exception) {
                Log.e("EventsViewModel", "Error al eliminar evento: ${e.message}")
            }
        }
    }

    // Establecer el evento seleccionado
    fun setSelectedEvent(event: Event?) {
        selectedEvent.value = event
        event?.let {
            title.value = it.title
            description.value = it.description
            date.value = it.date
            address.value = it.address
            city.value = it.city
            type.value = it.type
            locations.value = it.locations.toMutableList()
        }
        validateFields()
    }

    // Agregar una nueva ubicación
    fun addLocation() {
        // Aseguramos que las ubicaciones se actualicen correctamente
        val updatedLocations = locations.value.toMutableList()
        updatedLocations.add(Location())
        locations.value = updatedLocations
    }


    // Actualizar una ubicación específica
    fun updateLocation(index: Int, updatedLocation: Location) {
        if (index in locations.value.indices) {
            locations.value[index] = updatedLocation
        }
    }

    // Eliminar una ubicación
    fun removeLocation(index: Int) {
        if (index in locations.value.indices) {
            val updatedLocations = locations.value.toMutableList()
            updatedLocations.removeAt(index)
            locations.value = updatedLocations
        }
    }


    fun validateLocations(): Boolean {
        return locations.value.all { location ->
            location.name.isNotBlank() &&
                    location.maxCapacity > 0 &&
                    location.price > 0
        }
    }


    // Validar campos
    fun validateFields(): Boolean {
        validateTitle()
        validateDescription()
        validateDate()
        validateAddress()
        validateCity()
        return titleErrorMsg.value.isEmpty() &&
                descriptionErrorMsg.value.isEmpty() &&
                dateErrorMsg.value.isEmpty() &&
                addressErrorMsg.value.isEmpty() &&
                cityErrorMsg.value.isEmpty() &&
                validateLocations()
    }


    fun validateTitle() {
        titleErrorMsg.value = if (title.value.isBlank() || title.value.length < 3) {
            "El título debe tener al menos 3 caracteres"
        } else {
            ""
        }
    }

    fun validateDescription() {
        descriptionErrorMsg.value = if (description.value.isBlank() || description.value.length < 10) {
            "La descripción debe tener al menos 10 caracteres"
        } else {
            ""
        }
    }

    fun validateDate() {
        dateErrorMsg.value = if (date.value.isBlank()) {
            "La fecha no puede estar vacía"
        } else {
            ""
        }
    }

    fun validateAddress() {
        addressErrorMsg.value = if (address.value.isBlank()) {
            "La dirección no puede estar vacía"
        } else {
            ""
        }
    }

    fun validateCity() {
        cityErrorMsg.value = if (city.value.isBlank()) {
            "La ciudad no puede estar vacía"
        } else {
            ""
        }
    }

    private fun updateButtonState() {
        isEnabledCreateEventButton.value = titleErrorMsg.value.isEmpty() &&
                descriptionErrorMsg.value.isEmpty() &&
                dateErrorMsg.value.isEmpty() &&
                addressErrorMsg.value.isEmpty() &&
                cityErrorMsg.value.isEmpty() &&
                locations.value.isNotEmpty()
    }
}