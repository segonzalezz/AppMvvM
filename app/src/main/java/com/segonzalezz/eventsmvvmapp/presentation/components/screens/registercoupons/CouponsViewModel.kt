package com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.segonzalezz.eventsmvvmapp.model.Coupon
import com.segonzalezz.eventsmvvmapp.model.Event
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
    private val _selectedCoupon = MutableStateFlow<Coupon?>(null)
    val selectedCoupon: StateFlow<Coupon?> = _selectedCoupon.asStateFlow()

    private val originalName = mutableStateOf("")
    private val originalStock = mutableStateOf("")
    private val originalStartDate = mutableStateOf("")
    private val originalEndDate = mutableStateOf("")
    private val originalSalePrice = mutableStateOf("")

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

    fun loadCoupons() {
        viewModelScope.launch {
            _coupons.value = getCouponsList()
        }
    }

    private suspend fun getCouponsList(): List<Coupon> {
        return try {
            val snapshot = db.collection("coupons").get().await()
            val couponsList = snapshot.documents.mapNotNull {
                val coupon = it.toObject(Coupon::class.java)
                if (coupon != null) {
                    Log.d("CouponsViewModel", "Coupon fetched: ${coupon.name}, ${coupon.id}")
                    coupon.id = it.id
                    coupon
                } else {
                    Log.d("CouponsViewModel", "Error fetching coupon for document: ${it.id}")
                    null
                }
            }
            Log.d("CouponsViewModel", "Coupons fetched from Firestore: ${couponsList.size}")
            couponsList
        } catch (e: Exception) {
            Log.e("CouponsViewModel", "Error fetching coupons: ${e.message}", e)
            emptyList()
        }
    }

    suspend fun createCoupon(coupon: Coupon, onSuccess: () -> Unit, onError: (String) -> Unit) {
        try {
            // Agregamos el cupón a Firestore y obtenemos la referencia del nuevo documento creado
            val newCouponRef = db.collection("coupons").add(coupon).await()
            val couponId = newCouponRef.id

            if (couponId.isNotEmpty()) {
                coupon.id = couponId
                // Actualizamos el documento con el nuevo campo `id`
                db.collection("coupons").document(couponId).set(coupon).await()

                // Recargar los cupones después de crear el nuevo cupón
                loadCoupons()

                // Invocamos la función de éxito para indicar que el proceso se completó correctamente
                onSuccess()
            } else {
                onError("Error al crear cupón: ID del cupón es nulo o vacío.")
            }
        } catch (e: Exception) {
            onError("Error al crear cupón: ${e.message}")
        }
    }

    suspend fun editCoupon(coupon: Coupon, onSuccess: () -> Unit, onError: (String) -> Unit) {
        try {
            db.collection("coupons").document(coupon.id).set(coupon).await()
            loadCoupons()
            onSuccess()
        } catch (e: Exception) {
            onError("Error al editar cupón: ${e.message}")
        }
    }

    fun deleteCoupon(coupon: Coupon) {
        viewModelScope.launch {
            try {
                db.collection("coupons").document(coupon.id).delete().await()
                loadCoupons()
            } catch (e: Exception) {
                Log.e("CouponsViewModel", "Error al eliminar cupón: ${e.message}")
            }
        }
    }

    fun setSelectedCoupon(coupon: Coupon?) {
        Log.d("DEBUG", "Estableciendo cupón seleccionado: ${coupon?.name}")
        _selectedCoupon.value = coupon
    }

    val isSaveButtonEnabled = mutableStateOf(false)

    fun initializeOriginalValues(coupon: Coupon) {
        originalName.value = coupon.name
        originalStock.value = coupon.stock.toString()
        originalStartDate.value = coupon.startDate
        originalEndDate.value = coupon.endDate
        originalSalePrice.value = coupon.salePrice.toString()
    }

    fun checkForChanges() {
        isSaveButtonEnabled.value = name.value != originalName.value ||
                stock.value != originalStock.value ||
                startDate.value != originalStartDate.value ||
                endDate.value != originalEndDate.value ||
                salePrice.value != originalSalePrice.value
    }
}
