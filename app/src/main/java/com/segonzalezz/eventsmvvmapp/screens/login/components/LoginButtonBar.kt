package com.segonzalezz.eventsmvvmapp.screens.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.segonzalezz.eventsmvvmapp.screens.login.LoginScreen
import com.segonzalezz.eventsmvvmapp.ui.theme.EventsMVVMAppTheme

@Composable
fun LoginButtonBar(){
    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "¿Quieres crear tu propia cuenta?")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    EventsMVVMAppTheme {
        LoginButtonBar()
    }
}