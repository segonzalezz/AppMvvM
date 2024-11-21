package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuadmin.components

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.model.Coupon
import com.segonzalezz.eventsmvvmapp.model.Event
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registerEvents.EventsViewModel
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.registercoupons.CouponsViewModel
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens


@Composable
fun MenuAdminButtonBar(
    navController: NavHostController,
    eventViewModel: EventsViewModel = hiltViewModel(),
    couponViewModel: CouponsViewModel = hiltViewModel()
) {
    var showSearchBar by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val events by eventViewModel.events.collectAsState(initial = emptyList())
    val coupons by couponViewModel.coupons.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Barra de búsqueda con animación
        AnimatedVisibility(
            visible = showSearchBar,
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut()
        ) {
            SearchBar(
                searchText = searchText,
                onTextChange = { searchText = it },
                onClose = {
                    showSearchBar = false
                    searchText = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .zIndex(2f)
            )
        }

        // Mostrar resultados de búsqueda
        if (showSearchBar && searchText.isNotEmpty()) {
            val listState = rememberLazyListState()
            Column(
                modifier = Modifier
                    .fillMaxSize().offset(y = 110.dp)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                val filteredEvents = events.filter {
                    it.title.contains(searchText, ignoreCase = true) ||
                            it.description.contains(searchText, ignoreCase = true)
                }
                val filteredCoupons = coupons.filter {
                    it.name.contains(searchText, ignoreCase = true) ||
                            it.salePrice.toString().contains(searchText, ignoreCase = true)
                }

                if (filteredEvents.isNotEmpty()) {
                    Text(
                        text = "Cupones y eventos encontrados",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 174.dp)
                    ) {
                        items(filteredEvents) { event ->
                            EventCard(event, isHighlighted = searchText.isNotEmpty())
                        }
                        items(filteredCoupons) { coupon ->
                            CouponCard(coupon, isHighlighted = searchText.isNotEmpty())
                        }
                    }
                }
                if (filteredEvents.isEmpty() && filteredCoupons.isEmpty()) {
                    Text(
                        text = "No se encontraron resultados",
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }

        // Barra de navegación inferior
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            // Botón de búsqueda
            IconButton(
                onClick = {
                    showSearchBar = true
                    couponViewModel.loadCoupons()
                    eventViewModel.loadEvents()
                },
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Buscar",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { navController.navigate(AppScreens.EditRegisterScreen.route) }) {
                Icon(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "Perfil"
                )
            }
        }

        // Botones flotantes para añadir cupones y eventos
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp, bottom = 72.dp)
        ) {
            FloatingActionButton(
                onClick = { navController.navigate(AppScreens.RegisterCouponsScreen.route) },
                modifier = Modifier.size(56.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.local),
                    contentDescription = "Añadir Cupón",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            FloatingActionButton(
                onClick = { navController.navigate(AppScreens.RegisterEventsScreen.route) },
                modifier = Modifier
                    .size(40.dp)
                    .offset(y = -66.dp, x = -8.dp),
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Añadir Evento",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: String,
    onTextChange: (String) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .offset(y = 24.dp, x = 5.2.dp),
        shape = RoundedCornerShape(1.dp), // Bordes redondeados
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Buscar",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            TextField(
                value = searchText,
                onValueChange = onTextChange,
                placeholder = { Text("Buscar eventos o cupones") },
                modifier = Modifier
                    .padding(vertical = 4.dp),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = MaterialTheme.colorScheme.onSurface,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true
            )
            IconButton(onClick = onClose) {
                Icon(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "Cerrar",
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}




@Composable
fun EventCard(event: Event, isHighlighted: Boolean) {
    Card(
        modifier = Modifier
            .padding(8.dp).width(500.dp)
            .shadow(if (isHighlighted) 10.dp else 2.dp, RoundedCornerShape(10.dp)),
        elevation = if (isHighlighted) CardDefaults.cardElevation(8.dp) else CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isHighlighted) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = event.title, style = MaterialTheme.typography.titleMedium)
            Text(text = event.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha: ${event.date}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Dirección: ${event.address}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Ciudad: ${event.city}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun CouponCard(coupon: Coupon, isHighlighted: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .width(500.dp)
            .shadow(if (isHighlighted) 10.dp else 2.dp, RoundedCornerShape(10.dp)),
        elevation = if (isHighlighted) CardDefaults.cardElevation(8.dp) else CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isHighlighted) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = coupon.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Stock: ${coupon.stock}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha Inicio: ${coupon.startDate}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Fecha Fin: ${coupon.endDate}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Precio: ${coupon.salePrice}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
