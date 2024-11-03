package com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultDropdownMenu
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultSliderMinimal
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons.CouponsViewModel
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens
import kotlinx.coroutines.launch


@Composable
fun RegisterCouponsContent(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm(navController)
    }
}

@Composable
fun BoxHeader(){
    Box(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp))
    ) {
        Image(
            painterResource(id = R.drawable.jett),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

    }
}

@Composable
fun CardForm(navController: NavHostController, viewModel: CouponsViewModel = hiltViewModel()) {
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
                text = "Crear Cupón: ",
                modifier = Modifier.padding(top = 20.dp, bottom = 0.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(text = "Por favor ingresar campos:")
            Spacer(modifier = Modifier.height(10.dp))

            // Nombre del Cupón
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.name.value,
                onValueChange = {
                    viewModel.name.value = it
                    viewModel.validateName()
                },
                label = "Nombre",
                icon = Icons.Default.Info,
                keyboardType = KeyboardType.Text,
                errorMsg = viewModel.nameErrorMsg.value
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Stock del Cupón
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.stock.value,
                onValueChange = {
                    viewModel.stock.value = it
                    viewModel.validateStock()
                },
                label = "Stock del Cupón",
                icon = Icons.Default.Info,
                keyboardType = KeyboardType.Number,
                errorMsg = viewModel.stockErrorMsg.value
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Fecha de Inicio del Cupón
            Text(text = "Fecha de Inicio:", color = Color.White)
            DefaultDatePickerDocked(
                onDateSelected = {
                    viewModel.startDate.value = it
                    viewModel.validateDates()
                },
                errorMsg = viewModel.startDateErrorMsg.value
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Fecha de Fin del Cupón
            Text(text = "Fecha Fin:", color = Color.White)
            DefaultDatePickerDocked(
                onDateSelected = {
                    viewModel.endDate.value = it
                    viewModel.validateDates()
                },
                errorMsg = viewModel.endDateErrorMsg.value
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Precio de Descuento del Cupón
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.salePrice.value,
                onValueChange = {
                    viewModel.salePrice.value = it
                    viewModel.validateSalePrice()
                },
                label = "Precio Descuento Cupon",
                icon = Icons.Default.Info,
                keyboardType = KeyboardType.Number,
                errorMsg = viewModel.salePriceErrorMsg.value
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Botón para Crear Cupón
            DefaultButton(
                text = "Crear",
                enabled = viewModel.isEnabledCreateCouponButton,
                onClick = {
                    viewModel.viewModelScope.launch {
                        val newCoupon = Coupon(
                            name = viewModel.name.value,
                            stock = viewModel.stock.value.toInt(),
                            startDate = viewModel.startDate.value,
                            endDate = viewModel.endDate.value,
                            salePrice = viewModel.salePrice.value.toInt()
                        )
                        viewModel.createCoupon(newCoupon,
                            onSuccess = {
                                Toast.makeText(context, "Cupón creado exitosamente", Toast.LENGTH_LONG).show()
                                navController.navigate(AppScreens.MenuAdminScreen.route) {
                                    popUpTo(AppScreens.RegisterCouponsScreen.route) { inclusive = true }
                                }
                            },
                            onError = { message ->
                                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }
            )
        }
    }
}
