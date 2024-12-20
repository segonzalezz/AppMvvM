package com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.segonzalezz.eventsmvvmapp.R
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultButton
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextField
import com.segonzalezz.eventsmvvmapp.presentation.components.DefaultTextFieldPassword
import com.segonzalezz.eventsmvvmapp.presentation.components.screens.login.LoginViewModel
import com.segonzalezz.eventsmvvmapp.presentation.navegation.AppScreens

@Composable
fun LoginContent(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BoxHeader()
        CardForm(navController)
    }

}

@Composable
fun BoxHeader(){
    Box(
        modifier = Modifier
            .height(340.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp))
    ) {
        Image(
            painterResource(id = R.drawable.trpss),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun CardForm(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()){
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(start = 44.dp, end = 40.dp)
            .offset(y = (-88).dp),
    ) {
        Column( modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = stringResource(id = R.string.login),
                modifier = Modifier.padding(top = 10.dp, bottom = 0.dp, start = 0.dp, end = 0.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Por favor iniciar sesión:"
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultTextField(modifier = Modifier
                .padding()
                .fillMaxWidth(), value = viewModel.email.value, onValueChange = {viewModel.email.value = it}, label = stringResource(
                id = R.string.usernameLabel), icon = Icons.Default.AccountBox, keyboardType = KeyboardType.Email, errorMsg = viewModel.emailErrorMsg.value, validateField = {viewModel.validateEmail()})
            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextFieldPassword(
                modifier = Modifier
                    .padding()
                    .fillMaxWidth(), value = viewModel.password.value, onValueChange = {viewModel.password.value = it}, label = stringResource(R.string.passwordLabel), icon = Icons.Default.Lock, keyboardType = KeyboardType.Password, errorMsg = viewModel.passwordErrorMsg.value, validateField = {viewModel.validatePassword()})
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Recuperar contraseña", modifier = Modifier
                .align(Alignment.End)
                .clickable { navController.navigate(route = AppScreens.RecoverPasswordScreen.route) }, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            DefaultButton(text = "Iniciar Sesión", onClick = {
                viewModel.login(
                    onSuccess = {
                        val destination = if (viewModel.startDestination.value == AppScreens.MenuAdminScreen.route) {
                            AppScreens.MenuAdminScreen.route
                        } else {
                            AppScreens.MenUserScreen.route
                        }
                        navController.navigate(destination) {
                            popUpTo(AppScreens.LoginScreen.route) { inclusive = true }
                        }
                    },
                    onError = { message ->
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    }
                )
            }, enabled = viewModel.isEnabledLoginButton )
        }
    }
}

