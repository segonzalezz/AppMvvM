package com.segonzalezz.eventsmvvmapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSliderMinimal(){
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        Box (
            modifier = Modifier.fillMaxWidth().border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)).padding(8.dp, end= 20.dp)
        ){
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp, vertical = 12.dp).align(Alignment.TopEnd)
            ) {
                Text(
                    text = "${(sliderPosition * 100).toInt()}",
                    color = Color.White
                )
            }
        }
    }
}