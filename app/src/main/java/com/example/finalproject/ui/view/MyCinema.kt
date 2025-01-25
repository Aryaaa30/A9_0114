package com.example.finalproject.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.finalproject.R
import com.example.finalproject.ui.navigation.DestinasiNavigasi
import com.example.finalproject.ui.view.film.DestinasiHomeFilm
import com.example.finalproject.ui.view.penayangan.DestinasiHomePenayangan
import com.example.finalproject.ui.view.studio.DestinasiHomeStudio
import com.example.finalproject.ui.view.tiket.DestinasiHomeTiket

object DestinasiBeranda : DestinasiNavigasi {
    override val route = "beranda"
    override val titleRes = "Beranda"
}

@Composable
fun MyCinema(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1C)) // Latar belakang gelap
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween // Mengatur jarak elemen atas & bawah
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF252525))
                    .padding(16.dp)
            ) {
                Text(
                    text = "MyCinema",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE53935), // Merah mencolok
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // Mengurangi jarak antara header dan box gambar

            // Konten Utama
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mycinema),
                        contentDescription = "Sinema",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))

                // Tombol dengan tema bioskop
                MenuButton(
                    text = "Manajemen Film",
                    gradient = Brush.horizontalGradient(
                        listOf(Color(0xFFB71C1C), Color(0xFFFFD740)) // Merah tua ke emas
                    ),
                    onClick = { navController.navigate(DestinasiHomeFilm.route) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                MenuButton(
                    text = "Manajemen Penayangan",
                    gradient = Brush.horizontalGradient(
                        listOf(Color(0xFF212121), Color(0xFF37474F)) // Hitam ke biru tua
                    ),
                    onClick = { navController.navigate(DestinasiHomePenayangan.route) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                MenuButton(
                    text = "Manajemen Studio",
                    gradient = Brush.horizontalGradient(
                        listOf(Color(0xFF512DA8), Color(0xFF1976D2)) // Ungu tua ke biru
                    ),
                    onClick = { navController.navigate(DestinasiHomeStudio.route) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                MenuButton(
                    text = "Manajemen Tiket",
                    gradient = Brush.horizontalGradient(
                        listOf(Color(0xFFF57C00), Color(0xFFFFD740)) // Oranye ke emas
                    ),
                    onClick = { navController.navigate(DestinasiHomeTiket.route) }
                )
            }

            // Footer
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF252525))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "© 2024 MyCinema",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                    Text(
                        text = "Powered by Creativity",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun MenuButton(text: String, gradient: Brush, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(gradient)
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
