package com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.LoginScreen
import com.segonzalezz.eventsmvvmapp.presentation.components.theme.EventsMVVMAppTheme
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens

@Composable
fun LoginButtonBar(navController: NavHostController){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "¿Quieres crear tu propia cuenta?", modifier = Modifier.clickable { navController.navigate(route = AppScreens.RegisterScreen.route) })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Creación de Cupones", modifier = Modifier.clickable { navController.navigate(route = AppScreens.RegisterCouponsScreen.route) })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Creacion de Eventos", modifier = Modifier.clickable { navController.navigate(route = AppScreens.RegisterEventsScreen.route) })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Editar Cuenta", modifier = Modifier.clickable { navController.navigate(route = AppScreens.EditRegisterScreen.route) })
        }
    }

}