package com.segonzalezz.eventsmvvmapp.model

data class Seat (
    val row: String,
    val column: Int,
    val isBooked: Boolean = false
)