package com.segonzalezz.eventsmvvmapp.model

data class Coupon(
    var id: String = "",
    val name: String = "",
    val stock: Int = 0,
    val startDate: String = "",
    val endDate: String = "",
    val salePrice: Int = 0,
)
