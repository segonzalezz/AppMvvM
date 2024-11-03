package com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents

import android.net.Uri
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
    val db = Firebase.firestore

    private val _events = MutableStateFlow(emptyList<Event>())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    init {
        loadEvents()
    }

    // Validaciones para los campos del evento
    var title: MutableState<String> = mutableStateOf("")
    var isTitleValid: MutableState<Boolean> = mutableStateOf(false)
    var titleErrorMsg: MutableState<String> = mutableStateOf("")

    var description: MutableState<String> = mutableStateOf("")
    var isDescriptionValid: MutableState<Boolean> = mutableStateOf(false)
    var descriptionErrorMsg: MutableState<String> = mutableStateOf("")

    var date: MutableState<String> = mutableStateOf("")
    var isDateValid: MutableState<Boolean> = mutableStateOf(false)
    var dateErrorMsg: MutableState<String> = mutableStateOf("")

    var address: MutableState<String> = mutableStateOf("")
    var isAddressValid: MutableState<Boolean> = mutableStateOf(false)
    var addressErrorMsg: MutableState<String> = mutableStateOf("")

    var city: MutableState<String> = mutableStateOf("")
    var isCityValid: MutableState<Boolean> = mutableStateOf(false)
    var cityErrorMsg: MutableState<String> = mutableStateOf("")

    var locations: MutableState<List<Location>> = mutableStateOf(listOf())

    fun addLocation() {
        val newLocation = Location(name = "", maxCapacity = 0, price = 0.0f)
        locations.value = locations.value + newLocation
    }

    fun updateLocation(index: Int, newLocation: Location) {
        locations.value = locations.value.toMutableList().apply {
            set(index, newLocation)
        }
    }

    fun removeLocation(index: Int) {
        locations.value = locations.value.toMutableList().apply {
            removeAt(index)
        }
    }

    var type: MutableState<EventType> = mutableStateOf(EventType.DEFAULT)

    var isEnabledCreateEventButton = false

    fun enabledCreateEventButton() {
        isEnabledCreateEventButton = isTitleValid.value && isDescriptionValid.value
                && isDateValid.value && isAddressValid.value && isCityValid.value
    }

    // Funciones de validación
    fun validateTitle() {
        if (title.value.length >= 3) {
            isTitleValid.value = true
            titleErrorMsg.value = ""
        } else {
            isTitleValid.value = false
            titleErrorMsg.value = "El título debe tener al menos 3 caracteres"
        }
        enabledCreateEventButton()
    }

    fun validateDescription() {
        if (description.value.length >= 10) {
            isDescriptionValid.value = true
            descriptionErrorMsg.value = ""
        } else {
            isDescriptionValid.value = false
            descriptionErrorMsg.value = "La descripción debe tener al menos 10 caracteres"
        }
        enabledCreateEventButton()
    }

    fun validateDate() {
        // Aquí podrías agregar una validación para el formato de la fecha o su valor
        if (date.value.isNotEmpty()) {
            isDateValid.value = true
            dateErrorMsg.value = ""
        } else {
            isDateValid.value = false
            dateErrorMsg.value = "La fecha no puede estar vacía"
        }
        enabledCreateEventButton()
    }

    fun validateAddress() {
        val regex = Regex("^[a-zA-Z0-9\\s#-]+$")
        if (address.value.contains("-") && address.value.contains("#") && regex.matches(address.value)) {
            isAddressValid.value = true
            addressErrorMsg.value = ""
        } else {
            isAddressValid.value = false
            addressErrorMsg.value = "Dirección no válida"
        }
        enabledCreateEventButton()
    }

    fun validateCity() {
        if (city.value.isNotEmpty()) {
            isCityValid.value = true
            cityErrorMsg.value = ""
        } else {
            isCityValid.value = false
            cityErrorMsg.value = "La ciudad no puede estar vacía"
        }
        enabledCreateEventButton()
    }

    fun loadEvents() {
        viewModelScope.launch {
            _events.value = getEventsList()
        }
    }

    private suspend fun getEventsList(): List<Event> {
        val snapshot = db.collection("events")
            .get().await()
        return snapshot.documents.mapNotNull {
            val event = it.toObject(Event::class.java)
            requireNotNull(event)
            event.id = it.id
            event
        }
    }

    suspend fun createEvent(event: Event, onSuccess: () -> Unit, onError: (String) -> Unit) {
        try {
            val newEventRef = db.collection("events").add(event).await()
            event.id = newEventRef.id
            loadEvents()
            onSuccess()
        } catch (e: Exception) {
            onError("Error al crear evento.")
        }
    }

}