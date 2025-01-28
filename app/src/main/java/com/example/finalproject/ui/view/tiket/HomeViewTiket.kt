package com.example.finalproject.ui.view.tiket

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
import androidx.compose.material.icons.filled.Movie
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.R
import com.example.finalproject.model.Tiket
import com.example.finalproject.ui.PenyediaViewModel
import com.example.finalproject.ui.costumwigdet.CostumeTopAppBar
import com.example.finalproject.ui.navigation.DestinasiNavigasi
import com.example.finalproject.ui.view.DestinasiBeranda
import com.example.finalproject.ui.view.IconMenuButton
import com.example.finalproject.ui.view.film.DestinasiHomeFilm
import com.example.finalproject.ui.view.penayangan.DestinasiHomePenayangan
import com.example.finalproject.ui.view.studio.DestinasiHomeStudio
import com.example.finalproject.ui.viewmodel.tiket.HomeUiState
import com.example.finalproject.ui.viewmodel.tiket.HomeViewModelTiket

object DestinasiHomeTiket : DestinasiNavigasi {
    override val route = "home_tiket"
    override val titleRes = "Tiket"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewTiket(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    navigateBack: () -> Unit,
    viewModel: HomeViewModelTiket = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val navController = rememberNavController() // Tambahkan NavController untuk navigasi
    val currentDestination = DestinasiHomeTiket.route

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeTiket.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getTiket()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Tiket")
            }
        },
        bottomBar = {
            // Menambahkan komponen menu bawah
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0xFF252525)),
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
                            icon = Icons.Default.LocalMovies,
                            description = "Film",
                            isActive = currentDestination == DestinasiHomeFilm.route,
                            onClick = { navController.navigate(DestinasiHomeFilm.route) },
                            text = "Film"
                        )
                        IconMenuButton(
                            icon = Icons.Default.Event,
                            description = "Penayangan",
                            isActive = currentDestination == DestinasiHomePenayangan.route,
                            onClick = { navController.navigate(DestinasiHomePenayangan.route) },
                            text = "Penayangan"
                        )
                        IconMenuButton(
                            icon = Icons.Default.Home,
                            description = "Home",
                            isActive = currentDestination == DestinasiBeranda.route,
                            onClick = { navController.navigate(DestinasiBeranda.route) },
                            text = "Home"
                        )
                        IconMenuButton(
                            icon = Icons.Default.LiveTv,
                            description = "Studio",
                            isActive = currentDestination == DestinasiHomeStudio.route,
                            onClick = { navController.navigate(DestinasiHomeStudio.route) },
                            text = "Studio"
                        )
                        IconMenuButton(
                            icon = Icons.Default.ConfirmationNumber,
                            description = "Tiket",
                            isActive = currentDestination == DestinasiHomeTiket.route,
                            onClick = { navController.navigate(DestinasiHomeTiket.route) },
                            text = "Tiket"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        HomeStatus(
            homeUiState = viewModel.tiketUIState,
            retryAction = { viewModel.getTiket() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteTiket(it.idTiket)
                viewModel.getTiket()
            }
        )
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Tiket) -> Unit={},
    onDetailClick: (String) -> Unit
){
    when(homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.tiket.isEmpty()) {
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Tiket")
                }
            } else {
                TiketLayout(
                    tiket = homeUiState.tiket, modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idTiket)
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
fun TiketLayout(
    tiket: List<Tiket>,
    modifier: Modifier = Modifier,
    onDetailClick: (Tiket) -> Unit,
    onDeleteClick: (Tiket) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(tiket){tiket ->
            TiketCard(
                tiket = tiket,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(tiket) },
                onDeleteClick = {
                    onDeleteClick(tiket)
                }
            )
        }
    }
}

@Composable
fun TiketCard(
    tiket: Tiket,
    modifier: Modifier = Modifier,
    onDeleteClick: (Tiket) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(10.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black // Latar belakang kartu putih
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF212121)) // Warna latar belakang hitam gelap
                .padding(16.dp)
        ) {
            // Bagian kiri: Logo atau gambar dengan lingkaran dan bayangan
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        Color(0xFF616161), // Abu-abu medium
                        shape = CircleShape
                    )
                    .shadow(8.dp, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ConfirmationNumber, // Ganti dengan ikon yang sesuai
                    contentDescription = "Logo Tiket",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Bagian kanan: Informasi tiket
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = tiket.idTiket,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White // Teks putih
                )
                Text(
                    text = "ID Penayangan: ${tiket.idPenayangan}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFFB0B0B0) // Teks abu-abu terang
                )
                Text(
                    text = "Jumlah Tiket: ${tiket.jumlahTiket}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFB0B0B0) // Teks abu-abu terang
                )
                Text(
                    text = "Total Harga: ${tiket.totalHarga}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFB0B0B0) // Teks abu-abu terang
                )
                Text(
                    text = "Status Pembayaran: ${tiket.statusPembayaran}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFB0B0B0) // Teks abu-abu terang
                )
            }

            // Tombol delete: Desain modern dengan lingkaran dan bayangan
            IconButton(
                onClick = { onDeleteClick(tiket) },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFCF6679), shape = CircleShape) // Warna merah muda gelap
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
