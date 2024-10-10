package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menucupon.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField

@Composable
fun MenuCuponContent(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm()
    }
}

@Composable
fun CardForm(){
    var codigo by remember { mutableStateOf("") }

    val coupons = listOf(
        Coupon("Cupón 1", "Descripción del cupón 1"),
        Coupon("Cupón 2", "Descripción del cupón 2"),
        Coupon("Cupón 3", "Descripción del cupón 3")
    )

    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .offset(y = (-45).dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column( modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Redimir cupon:",
                modifier = Modifier.padding(top = 20.dp, bottom = 0.dp, start = 0.dp, end = 0.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Por favor ingresar campos:"
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultTextField(modifier = Modifier
                .padding()
                .fillMaxWidth(), value = codigo, onValueChange = {codigo = it}, label = "Codigo", icon = Icons.Default.Create, keyboardType = KeyboardType.Email)
            DefaultButton(text = "Redimir Cupon", onClick = {} )
            Text(
                text = "Cupones Activos:",
                modifier = Modifier.padding(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column {
                coupons.forEach { coupon ->
                    CouponCard(coupon)
                    Spacer(modifier = Modifier.height(8.dp)) // Separación entre tarjetas
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

    }
}

@Composable
fun BoxHeader(){
    Box(
        modifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp))
    ) {
        Image(
            painterResource(id = R.drawable.trplcs),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun CouponCard(coupon: Coupon) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))

            // Información del cupón
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = coupon.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = coupon.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// Modelo de datos para el cupón
data class Coupon(val name: String, val description: String)