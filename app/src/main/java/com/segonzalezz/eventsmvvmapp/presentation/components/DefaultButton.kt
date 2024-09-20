package com.segonzalezz.eventsmvvmapp.presentation.components
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    text:String,
    onClick:() -> Unit,
    color : Color = Color.Cyan,
){

    Button(onClick = { onClick() }, modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = 20.dp, start = 50.dp, end = 50.dp),
        colors = ButtonDefaults.buttonColors(color)) {
        Text(text = text)
        }
}