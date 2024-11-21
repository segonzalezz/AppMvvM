package com.segonzalezz.eventsmvvmapp.model

import java.io.Serializable

data class Event(
    var id: String = "",
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val address: String = "",
    val city: String = "",
    val locations: List<Location> = listOf(),
    val type: EventType = EventType.DEFAULT,
    val imagUrl: String = "",
): Serializable