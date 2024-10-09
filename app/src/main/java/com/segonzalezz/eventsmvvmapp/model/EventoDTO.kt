package com.segonzalezz.eventsmvvmapp.model

import java.time.LocalDate

data class EventoDTO(
    val id: Long,
    val nome: String,
    val data: LocalDate
)

