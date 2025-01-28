package com.example.finalproject.ui.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun MyCinema(navController: NavController,currentDestination: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1C)) // Latar belakang gelap
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween // Mengatur jarak elemen atas & bawah
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // TextField dengan desain mirip label pencarian
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .clip(RoundedCornerShape(50.dp)) // Membuat bentuk melingkar
                    .background(Color(0xFF252525)) // Latar belakang abu-abu gelap
                    .padding(horizontal = 16.dp) // Padding dalam TextField
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "My Cinema",
                        color = Color.Red,
                        style = TextStyle(fontSize = 16.sp,
                            fontWeight = FontWeight.Bold) // Gaya teks placeholder
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Spacer fleksibel untuk mendorong ikon ke kanan
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp) // Ukuran ikon pencarian
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Ikon person dan lonceng
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Handle person icon click */ }) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Person Icon",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp) // Ukuran ikon
                    )
                }
                IconButton(onClick = { /* Handle bell icon click */ }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Bell Icon",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp) // Ukuran ikon
                    )
                }
            }
        }

        // Banner Promosi
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Tinggi banner
                .clip(RoundedCornerShape(15.dp)) // Sudut melengkung
                .background(Color.Gray), // Warna default jika gambar gagal dimuat
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner), // Ganti dengan resource gambar Anda
                contentDescription = "Promotional Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Divider(color = Color.Gray, thickness = 1.dp)
        // Section "Explore Movies"
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Explore Movies",
                    color = Color.White,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                TextButton(onClick = { navController.navigate(DestinasiHomeFilm.route) }){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "More",
                            color = Color.Gray,
                            style = TextStyle(fontSize = 14.sp)
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Arrow Icon",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            // Film list
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(listOf(
                    Movie("Keajaiban Air Mata Wanita", R.drawable.wanita, "17+", 1),
                    Movie("Mufasa: The Lion King", R.drawable.mufasa, "13+", 2),
                    Movie("Dark Nuns", R.drawable.darknuns, "17+", 3)
                )) { movie ->
                    MovieCard(
                        title = movie.title,
                        imageRes = movie.imageRes,
                        rating = movie.rating,
                        badgeNumber = movie.badgeNumber
                    )
                }
            }
        }
        Divider(color = Color.Gray, thickness = 1.dp)

        Text(
            text = "Foods and Drinks",
            color = Color.White,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )

        // Banner Promosi
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp) // Tinggi banner
                .clip(RoundedCornerShape(15.dp)) // Sudut melengkung
                .background(Color.Gray), // Warna default jika gambar gagal dimuat
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.food), // Ganti dengan resource gambar Anda
                contentDescription = "Promotional Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        // Banner Promosi
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp) // Tinggi banner
                .clip(RoundedCornerShape(15.dp)) // Sudut melengkung
                .background(Color.Gray), // Warna default jika gambar gagal dimuat
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.halal), // Ganti dengan resource gambar Anda
                contentDescription = "Promotional Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Konten Utama
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Menu bawah dengan ikon
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFF252525)), // Warna latar belakang menu
                contentAlignment = Alignment.Center,

                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconMenuButton(
                        icon = Icons.Default.LocalMovies, // Ikon untuk "Film"
                        description = "Film",
                        isActive = currentDestination == DestinasiHomeFilm.route, // Tandai jika halaman saat ini adalah "Film"
                        onClick = { navController.navigate(DestinasiHomeFilm.route) },
                        text = "Film"
                    )
                    IconMenuButton(
                        icon = Icons.Default.Event, // Ikon untuk "Penayangan"
                        description = "Penayangan",
                        isActive = currentDestination == DestinasiHomePenayangan.route, // Tandai jika halaman saat ini adalah "Penayangan"
                        onClick = { navController.navigate(DestinasiHomePenayangan.route) },
                        text = "Penayangan"
                    )
                    IconMenuButton(
                        icon = Icons.Default.Home, // Ikon untuk "Home"
                        description = "Home",
                        isActive = currentDestination == DestinasiBeranda.route, // Tandai jika halaman saat ini adalah "Home"
                        onClick = { navController.navigate(DestinasiBeranda.route) },
                        text = "Home"
                    )
                    IconMenuButton(
                        icon = Icons.Default.LiveTv, // Ikon untuk "Studio"
                        description = "Studio",
                        isActive = currentDestination == DestinasiHomeStudio.route, // Tandai jika halaman saat ini adalah "Studio"
                        onClick = { navController.navigate(DestinasiHomeStudio.route) },
                        text = "Studio"
                    )
                    IconMenuButton(
                        icon = Icons.Default.ConfirmationNumber, // Ikon untuk "Tiket"
                        description = "Tiket",
                        isActive = currentDestination == DestinasiHomeTiket.route, // Tandai jika halaman saat ini adalah "Tiket"
                        onClick = { navController.navigate(DestinasiHomeTiket.route) },
                        text = "Tiket"
                    )
                }
            }
        }
    }
}

@Composable
fun IconMenuButton(
    icon: ImageVector,
    description: String,
    isActive: Boolean, // Parameter untuk menandai apakah halaman sedang aktif
    onClick: () -> Unit,
    text: String
) {
    // State untuk mendeteksi apakah ikon sedang di-hover
    var isHovered by remember { mutableStateOf(false) }

    // Animasi warna berdasarkan status aktif atau hover
    val iconColor by animateColorAsState(
        targetValue = when {
            isHovered -> Color.Yellow // Warna saat di-hover
            isActive -> Color.Red    // Warna saat aktif
            else -> Color.White      // Warna default
        },
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 300) // Durasi animasi
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) Color.Gray.copy(alpha = 0.2f) else Color.Transparent,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 300) // Durasi animasi
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Pusatkan ikon dan teks secara horizontal
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(8.dp) // Tambahkan padding
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = iconColor,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 4.dp) // Tambahkan jarak antara ikon dan teks
        )
    }
}



// Data class untuk mendeskripsikan film
data class Movie(
    val title: String,
    val imageRes: Int,
    val rating: String,
    val badgeNumber: Int
)

@Composable
fun MovieCard(title: String, imageRes: Int, rating: String, badgeNumber: Int) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp)
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Movie Image
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Movie Poster",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Badge Number (Top Left)
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
                    .background(Color.Red, shape = CircleShape)
                    .align(Alignment.TopStart),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badgeNumber.toString(),
                    color = Color.White,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                )
            }

            // Age Rating (Top Right)
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                    .align(Alignment.TopEnd)
                    .padding(horizontal = 6.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = rating,
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                )
            }

            // Title Text (Bottom Center)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black)
                        )
                    )
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}






