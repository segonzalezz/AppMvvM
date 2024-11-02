package com.segonzalezz.eventsmvvmapp.presentation.components.screens.menuadmin.components

import androidx.compose.foundation.Image
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

@Composable
fun MenuAdminContent() {
    Box(
        modifier = Modifier
            .fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize().fillMaxWidth()
                .offset(y = (110).dp) // Scroll vertical para toda la columna
        ) {

            Text(
                text = "Desliza para ver más eventos",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            // Row con scroll horizontal para eventos
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()) // Scroll horizontal para el Row
                    .padding(bottom = 20.dp),
            ) {
                repeat(1) {
                    CardItem(imageResId = R.drawable.scott)
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
                    .horizontalScroll(rememberScrollState()) // Otro scroll horizontal
            ) {
                repeat(1) {
                    CardItem(imageResId = R.drawable.jett)
                }
            }
        }
    }
}

@Composable
fun CardItem(imageResId: Int) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .width(180.dp) // Tamaño ajustado para que las tarjetas sean más pequeñas
            .height(280.dp)
            .clip(RoundedCornerShape(16.dp)),
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
                text = "Drake",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}