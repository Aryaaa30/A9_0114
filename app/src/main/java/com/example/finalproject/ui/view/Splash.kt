package com.example.finalproject.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.R
import com.example.finalproject.ui.navigation.DestinasiNavigasi

object DestinasiSplash : DestinasiNavigasi {
    override val route = "splash"
    override val titleRes = "splash"
}

@Composable
fun SplashScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF1F1F1) , // Putih Lembut
                        Color(0xFF2E2E2E), // Hitam Abu-abu Gelap
                        Color(0xFF393E46), // Abu-abu Elegan
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.cinema),
            contentDescription = "Logo",
            modifier = Modifier.size(180.dp)
        )

        Text(
            text = "MyCinema",
            color = Color(0xFFE94560), // Warna merah
            fontSize = 30.sp, // Ukuran font lebih besar
            fontWeight = FontWeight.Bold, // Teks tebal
            letterSpacing = 1.5.sp, // Spasi antar huruf lebih lebar
            style = TextStyle(
                fontFamily = FontFamily.Serif // Menggunakan font serif untuk kesan elegan
            ),
            modifier = Modifier
                .padding(vertical = 18.dp)
                .clickable { navController.navigate(DestinasiBeranda.route) }
        )

    }
}
