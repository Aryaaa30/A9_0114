package com.example.finalproject.ui.view.studio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.R
import com.example.finalproject.model.Studio
import com.example.finalproject.ui.PenyediaViewModel
import com.example.finalproject.ui.costumwigdet.CostumeTopAppBar
import com.example.finalproject.ui.navigation.DestinasiNavigasi
import com.example.finalproject.ui.view.DestinasiBeranda
import com.example.finalproject.ui.view.IconMenuButton
import com.example.finalproject.ui.view.film.DestinasiHomeFilm
import com.example.finalproject.ui.view.penayangan.DestinasiHomePenayangan
import com.example.finalproject.ui.view.tiket.DestinasiHomeTiket
import com.example.finalproject.ui.viewmodel.studio.HomeUiState
import com.example.finalproject.ui.viewmodel.studio.HomeViewModelStudio

object DestinasiHomeStudio : DestinasiNavigasi {
    override val route = "home_studio"
    override val titleRes = "Studio"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewStudio(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    navigateBack: () -> Unit,
    viewModel: HomeViewModelStudio = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val navController = rememberNavController() // Tambahkan NavController jika diperlukan
    val currentDestination = DestinasiHomeStudio.route // Simulasikan halaman saat ini

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeStudio.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getStudio()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Mahasiswa")
            }
        },
        bottomBar = {
            // Menambahkan menu bawah
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFF252525)), // Warna latar belakang menu
                contentAlignment = Alignment.Center
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
    ) { innerPadding ->
        HomeStatus(
            homeUiState = viewModel.studioUIState,
            retryAction = { viewModel.getStudio() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteStudio(it.idStudio)
                viewModel.getStudio()
            }
        )
    }
}


@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Studio) -> Unit={},
    onDetailClick: (String) -> Unit
){
    when(homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.studio.isEmpty()) {
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Studio")
                }
            } else {
                StudioLayout(
                    studio = homeUiState.studio, modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idStudio)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

//Homescreen menampilkan loading message
@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.img),
        contentDescription = stringResource(R.string.loading)
    )
}

//Homescreen menampilkan error message
@Composable
fun OnError(retryAction: ()->Unit, modifier: Modifier = Modifier){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.img), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed),modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun StudioLayout(
    studio: List<Studio>,
    modifier: Modifier = Modifier,
    onDetailClick: (Studio) -> Unit,
    onDeleteClick: (Studio) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(studio){studio ->
            StudioCard(
                studio = studio,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(studio) },
                onDeleteClick = {
                    onDeleteClick(studio)
                }
            )
        }
    }
}

@Composable
fun StudioCard(
    studio: Studio,
    modifier: Modifier = Modifier,
    onDeleteClick: (Studio) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(10.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Warna latar belakang putih
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF333333)) // Background hitam
                .padding(16.dp)
        ) {
            // Bagian kiri: Ikon atau logo dengan efek bayangan
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(
                        Color(0xFF4F4F4F), // Abu-abu gelap
                        shape = CircleShape
                    )
                    .shadow(8.dp, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LiveTv, // Ganti dengan ikon yang sesuai
                    contentDescription = "Logo",
                    tint = Color.Red,
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Bagian kanan: Informasi studio
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "ID: ${studio.idStudio}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White // Warna teks putih
                )
                Text(
                    text = "Nama Studio: ${studio.namaStudio}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFFB0B0B0) // Abu-abu terang
                )
                Text(
                    text = "Kapasitas: ${studio.kapasitas}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFB0B0B0) // Abu-abu terang
                )
            }

            // Tombol delete: Desain lebih modern dengan lingkaran dan animasi
            IconButton(
                onClick = { onDeleteClick(studio) },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFB71C1C), shape = CircleShape) // Merah gelap untuk delete
                    .shadow(8.dp, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

