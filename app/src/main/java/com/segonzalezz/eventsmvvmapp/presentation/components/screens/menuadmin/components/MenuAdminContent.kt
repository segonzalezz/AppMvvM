package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuadmin.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segonzalezz.eventsmvvmapp.R
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.EventsViewModel
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons.CouponsViewModel
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens

@Composable
fun MenuAdminContent(
    viewModel: EventsViewModel = hiltViewModel(),
    viewModell: CouponsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val events by viewModel.events.collectAsState()
    val coupons by viewModell.coupons.collectAsState()
    var selectedItem by remember { mutableStateOf<Pair<String, String>?>(null) } // Elemento seleccionado para mostrar en el diálogo

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .offset(y = (110).dp)
        ) {
            Text(
                text = "Desliza para ver más eventos",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(bottom = 20.dp),
            ) {
                events.forEach { event ->
                    CardItem(
                        title = event.title,
                        description = event.description,
                        vigencia = "Vigencia: " + event.date,
                        imageResId = R.drawable.scott,
                        itemType = ItemType.EVENT,
                        onEditClick = {
                            Log.d("RRRRR", "Seleccionado evento: ")
                            viewModel.setSelectedEvent(event)
                            navController.currentBackStackEntry?.savedStateHandle?.set("selectedEvent", event)
                            navController.navigate(AppScreens.EditEventScreen.route)
                        },
                        onDeleteClick = {
                            viewModel.deleteEvent(event)
                        }
                    )
                }
            }

            Text(
                text = "Desliza para ver más cupones",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                coupons.forEach { coupon ->
                    CardItem(
                        title = coupon.name,
                        description = "Descuento: " + coupon.salePrice.toString(),
                        vigencia = "Vigencia: " + coupon.startDate,
                        imageResId = R.drawable.jett,
                        itemType = ItemType.COUPON,
                        onEditClick = {
                            Log.d("rRRRRR", "Seleccionado cupón: ${coupon.name}")
                            navController.currentBackStackEntry?.savedStateHandle?.set("selectedCoupon", coupon) // Guardar cupón en el estado
                            navController.navigate(AppScreens.EditCouponScreen.route)
                        },
                        onDeleteClick = {
                            viewModell.deleteCoupon(coupon)
                        }
                    )
                }
            }
        }
    }
}

enum class ItemType {
    EVENT,
    COUPON
}

@Composable
fun CardItem(
    title: String,
    description: String,
    vigencia: String,
    imageResId: Int = R.drawable.scott,
    itemType: ItemType,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .width(180.dp)
            .height(280.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { showDialog = true },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 4.dp).padding(horizontal = 4.dp)
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 5.dp)
            )

            Text(
                text = vigencia,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp).padding(horizontal = 8.dp)
            )
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(410.dp)
                    .offset(y = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Detalles de $title",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DefaultButton(
                        text = "Editar",
                        onClick = {
                            onEditClick()
                            showDialog = false
                        },
                        icon = R.drawable.edit
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DefaultButton(
                        text = "Eliminar",
                        onClick = {
                            onDeleteClick()
                            showDialog = false
                        },
                        icon = R.drawable.delete
                    )
                }
            }
        }
    }
}

@Composable
fun DefaultButton(text: String, onClick: () -> Unit, icon: Int? = null) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text = text, style = MaterialTheme.typography.labelLarge, color = Color.White)
    }
}


