package com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.segonzalezz.eventsmvvmapp.model.Coupon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
@HiltViewModel
class CouponsViewModel @Inject constructor(): ViewModel() {
    val db = Firebase.firestore

    private val _coupons = MutableStateFlow(emptyList<Coupon>())
    val coupons: StateFlow<List<Coupon>> = _coupons.asStateFlow()

    init {
        loadCoupons()
    }

    // Validaciones para los campos del cupón
    var name: MutableState<String> = mutableStateOf("")
    var isNameValid: MutableState<Boolean> = mutableStateOf(false)
    var nameErrorMsg: MutableState<String> = mutableStateOf("")

    var stock: MutableState<String> = mutableStateOf("0")
    var isStockValid: MutableState<Boolean> = mutableStateOf(false)
    var stockErrorMsg: MutableState<String> = mutableStateOf("")

    var startDate: MutableState<String> = mutableStateOf("")
    var isStartDateValid: MutableState<Boolean> = mutableStateOf(false)
    var startDateErrorMsg: MutableState<String> = mutableStateOf("")

    var endDate: MutableState<String> = mutableStateOf("")
    var isEndDateValid: MutableState<Boolean> = mutableStateOf(false)
    var endDateErrorMsg: MutableState<String> = mutableStateOf("")

    var salePrice: MutableState<String> = mutableStateOf("0")
    var isSalePriceValid: MutableState<Boolean> = mutableStateOf(false)
    var salePriceErrorMsg: MutableState<String> = mutableStateOf("")

    var isEnabledCreateCouponButton = false

    fun enabledCreateCouponButton() {
        isEnabledCreateCouponButton = isNameValid.value && isStockValid.value
                && isStartDateValid.value && isEndDateValid.value && isSalePriceValid.value
    }

    // Funciones de validación
    fun validateName() {
        if (name.value.length >= 3) {
            isNameValid.value = true
            nameErrorMsg.value = ""
        } else {
            isNameValid.value = false
            nameErrorMsg.value = "El nombre debe tener al menos 3 caracteres"
        }
        enabledCreateCouponButton()
    }

    fun validateStock() {
        val stockValue = stock.value.toIntOrNull()
        if (stockValue != null && stockValue > 0) {
            isStockValid.value = true
            stockErrorMsg.value = ""
        } else {
            isStockValid.value = false
            stockErrorMsg.value = "Stock debe ser mayor que 0"
        }
        enabledCreateCouponButton()
    }

    fun validateDates() {
        if (startDate.value.isNotEmpty()) {
            isStartDateValid.value = true
            startDateErrorMsg.value = ""
        } else {
            isStartDateValid.value = false
            startDateErrorMsg.value = "La fecha de inicio no puede estar vacía"
        }

        if (endDate.value.isNotEmpty()) {
            isEndDateValid.value = true
            endDateErrorMsg.value = ""
        } else {
            isEndDateValid.value = false
            endDateErrorMsg.value = "La fecha de fin no puede estar vacía"
        }
        enabledCreateCouponButton()
    }

    fun validateSalePrice() {
        val priceValue = salePrice.value.toIntOrNull()
        if (priceValue != null && priceValue >= 0) {
            isSalePriceValid.value = true
            salePriceErrorMsg.value = ""
        } else {
            isSalePriceValid.value = false
            salePriceErrorMsg.value = "Precio debe ser mayor o igual a 0"
        }
        enabledCreateCouponButton()
    }

    // Lógica de negocio para los cupones
    fun loadCoupons() {
        viewModelScope.launch {
            _coupons.value = getCouponsList()
        }
    }

    private suspend fun getCouponsList(): List<Coupon> {
        val snapshot = db.collection("coupons")
            .get().await()
        return snapshot.documents.mapNotNull {
            val coupon = it.toObject(Coupon::class.java)
            requireNotNull(coupon)
            coupon.id = it.id
            coupon
        }
    }

    suspend fun createCoupon(coupon: Coupon, onSuccess: () -> Unit, onError: (String) -> Unit) {
        try {
            val newCouponRef = db.collection("coupons").add(coupon).await()
            coupon.id = newCouponRef.id
            loadCoupons()
            onSuccess()
        } catch (e: Exception) {
            onError("Error al crear cupón.")
        }
    }
}
