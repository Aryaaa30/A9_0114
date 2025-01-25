package com.example.finalproject.ui.view

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF42A5F5),
                        Color(0xFF0D47A1),
                        Color(0xFF6A1B9A)
                    )
                )
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Konten Utama
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp)) // Space awal

            // Konten Utama dengan Judul
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Selamat Datang di MyCinema",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD32F2F), // Warna merah agar mencolok
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Menu Manajemen",
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp,
                    )
                    Text(
                        text = "Pilih menu yang ingin dikelola",
                        fontWeight = FontWeight.Light,
                    )

                    Spacer(modifier = Modifier.height(20.dp)) // Space sebelum item menu

                    // Tombol Menu
                    MenuButton(
                        text = "Manajemen Film",
                        color = Color(0xFFFFB74D),
                        onClick = { navController.navigate(DestinasiHomeFilm.route) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    MenuButton(
                        text = "Manajemen Penayangan",
                        color = Color(0xFF66BB6A),
                        onClick = { navController.navigate(DestinasiHomePenayangan.route) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    MenuButton(
                        text = "Manajemen Studio",
                        color = Color(0xFF29B6F6),
                        onClick = { navController.navigate(DestinasiHomeStudio.route) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    MenuButton(
                        text = "Manajemen Tiket",
                        color = Color(0xFFAB47BC),
                        onClick = { navController.navigate(DestinasiHomeTiket.route) }
                    )
                }
                // Footer
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Divider(
                        color = Color.Black,
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    Text(
                        text = "© 2024 MyCinema",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                    Text(
                        text = "Powered by Creativity",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
@Composable
fun MenuButton(text: String, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(8.dp))  // Shadow effect on card
            .background(color = color, shape = RoundedCornerShape(8.dp)),  // Card with background color and shape
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = color)  // Background color of Card
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}