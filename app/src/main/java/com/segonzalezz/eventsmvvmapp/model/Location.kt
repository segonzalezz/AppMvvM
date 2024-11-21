package com.segonzalezz.eventsmvvmapp.model

data class Location(
    var id: String = "",
    val name: String = "",
    val maxCapacity: Int = 0,
    val price: Float = 0.0f,
    val seats: List<Seat> = emptyList()
)