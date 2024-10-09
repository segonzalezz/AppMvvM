package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuser.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens

@Composable
fun MenUserButtonBar(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth().height(56.dp)
        ) {
            IconButton(onClick = { /* TODO: Acción del primer botón */ }) {
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

        FloatingActionButton(
            onClick = { /* TODO: Acción del FAB */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 72.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                painter = painterResource(id = R.drawable.local),
                contentDescription = "Añadir",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}



