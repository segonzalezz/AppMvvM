package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuadmin.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens


@Composable
fun MenuAdminButtonBar(navController: NavHostController){
    var showSearchBar by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (showSearchBar) {
            SearchBar(
                searchText = searchText,
                onTextChange = { searchText = it },
                onClose = {
                    showSearchBar = false
                    searchText = ""
                },
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )
        }


        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(52.dp)
        ) {
            IconButton(onClick = { showSearchBar = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Buscar"
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { navController.navigate(AppScreens.EditRegisterScreen.route) }) {
                Icon(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "Perfil"
                )
            }
        }

        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp, bottom = 72.dp)
        ) {
            // Botón de fondo
            FloatingActionButton(
                onClick = { navController.navigate(AppScreens.RegisterCouponsScreen.route) },
                modifier = Modifier
                    .size(56.dp), // Tamaño predeterminado del botón
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.local),
                    contentDescription = "Añadir",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            FloatingActionButton(
                onClick = { navController.navigate(AppScreens.RegisterEventsScreen.route) },
                modifier = Modifier
                    .size(40.dp).padding().offset(y = -66.dp).offset(x=-7.dp),
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Añadir",
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = -259.dp)
            .offset(x = 12.dp)// Hace que el Column ocupe todo el espacio disponible
            .padding(),
        verticalArrangement = Arrangement.Center, // Centra el contenido verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(142.dp).offset(x = -12.dp).width(200.dp),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                TextField(
                    value = searchText,
                    onValueChange = onTextChange,
                    placeholder = { Text("Buscar...") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 38.dp, horizontal = 18.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    singleLine = true
                )
                IconButton(onClick = onClose) {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "Cerrar"
                    )
                }
            }
        }
    }
}