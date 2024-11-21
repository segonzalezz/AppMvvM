package com.segonzalezz.eventsmvvmapp.presentation.components.screens.editcoupon.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.model.Coupon
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons.CouponsViewModel
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun EditCouponContent(navController: NavHostController, viewModel: CouponsViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        val coupon = navController.previousBackStackEntry?.savedStateHandle?.get<Coupon>("selectedCoupon")
        coupon?.let {
            Log.d("DEBUG", "Recibiendo cupón desde el estado: ${it.startDate}, ${it.endDate}")
            viewModel.setSelectedCoupon(it)

            // Cargar los datos del cupón en el ViewModel
            viewModel.name.value = it.name
            viewModel.stock.value = it.stock.toString()
            viewModel.startDate.value = it.startDate // Asignar la fecha de inicio
            viewModel.endDate.value = it.endDate // Asignar la fecha de fin
            viewModel.salePrice.value = it.salePrice.toString()

            // Establecer datos iniciales para verificar cambios
            viewModel.initializeOriginalValues(it)
        } ?: Log.d("DEBUG", "No se encontró cupón en savedStateHandle")
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm(navController, viewModel)
    }
}

@Composable
fun BoxHeader() {
    Box(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
    ) {
        Image(
            painterResource(id = R.drawable.jett),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun CardForm(navController: NavHostController, viewModel: CouponsViewModel) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 26.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .offset(y = (-152).dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Editar Cupón",
                modifier = Modifier.padding(top = 20.dp, bottom = 0.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Campo: Nombre del Cupón
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.name.value,
                onValueChange = {
                    viewModel.name.value = it
                    viewModel.checkForChanges()
                },
                label = "Nombre del Cupón",
                icon = Icons.Default.Info,
                keyboardType = KeyboardType.Text,
                errorMsg = viewModel.nameErrorMsg.value
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Campo: Stock del Cupón
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.stock.value,
                onValueChange = {
                    viewModel.stock.value = it
                    viewModel.checkForChanges()
                },
                label = "Stock",
                icon = Icons.Default.Info,
                keyboardType = KeyboardType.Number,
                errorMsg = viewModel.stockErrorMsg.value
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Campo: Fecha de Inicio
            Text(text = "Fecha de Inicio", color = MaterialTheme.colorScheme.onSurface)
            LaunchedEffect(viewModel.startDate.value) {
                Log.d("DEBUG", "Sincronizando fecha de inicio: ${viewModel.startDate.value}")
            }
            DefaultDatePickerDocked(
                onDateSelected = {
                    viewModel.startDate.value = it
                    viewModel.checkForChanges()
                },
                initialDate = viewModel.startDate.value,
                errorMsg = viewModel.startDateErrorMsg.value
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Campo: Fecha de Fin
            Text(text = "Fecha de Fin", color = MaterialTheme.colorScheme.onSurface)
            LaunchedEffect(viewModel.endDate.value) {
                Log.d("DEBUG", "Sincronizando fecha de fin: ${viewModel.endDate.value}")
            }
            DefaultDatePickerDocked(
                onDateSelected = {
                    viewModel.endDate.value = it
                    viewModel.checkForChanges()
                },
                initialDate = viewModel.endDate.value,
                errorMsg = viewModel.endDateErrorMsg.value
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Campo: Precio de Descuento
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.salePrice.value,
                onValueChange = {
                    viewModel.salePrice.value = it
                    viewModel.checkForChanges()
                },
                label = "Precio Descuento",
                icon = Icons.Default.Info,
                keyboardType = KeyboardType.Number,
                errorMsg = viewModel.salePriceErrorMsg.value
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botón: Guardar Cambios
            DefaultButton(
                text = "Guardar Cambios",
                enabled = viewModel.isSaveButtonEnabled.value,
                onClick = {
                    viewModel.viewModelScope.launch {
                        val updatedCoupon = Coupon(
                            id = viewModel.selectedCoupon.value?.id ?: "",
                            name = viewModel.name.value,
                            stock = viewModel.stock.value.toIntOrNull() ?: 0,
                            startDate = viewModel.startDate.value,
                            endDate = viewModel.endDate.value,
                            salePrice = viewModel.salePrice.value.toIntOrNull() ?: 0
                        )
                        viewModel.editCoupon(updatedCoupon,
                            onSuccess = {
                                Toast.makeText(context, "Cupón actualizado exitosamente", Toast.LENGTH_LONG).show()
                                navController.navigateUp() // Volver a la pantalla anterior
                            },
                            onError = { message ->
                                Toast.makeText(context, "Error al actualizar: $message", Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }
            )
        }
    }
}


@Composable
fun CustomDateField(
    initialDate: String,
    onDateSelected: (String) -> Unit,
    errorMsg: String = ""
) {
    DefaultDatePickerDocked(
        initialDate = initialDate,
        onDateSelected = onDateSelected,
        errorMsg = errorMsg
    )
}
