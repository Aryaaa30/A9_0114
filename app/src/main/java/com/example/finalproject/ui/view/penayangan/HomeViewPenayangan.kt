package com.example.finalproject.ui.view.penayangan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finalproject.R
import com.example.finalproject.model.Penayangan
import com.example.finalproject.ui.PenyediaViewModel
import com.example.finalproject.ui.costumwigdet.CostumeTopAppBar
import com.example.finalproject.ui.navigation.DestinasiNavigasi
import com.example.finalproject.ui.view.DestinasiBeranda
import com.example.finalproject.ui.view.IconMenuButton
import com.example.finalproject.ui.view.film.DestinasiHomeFilm
import com.example.finalproject.ui.view.studio.DestinasiHomeStudio
import com.example.finalproject.ui.view.tiket.DestinasiHomeTiket
import com.example.finalproject.ui.viewmodel.penayangan.HomeUiState
import com.example.finalproject.ui.viewmodel.penayangan.HomeViewModelPenayangan

object DestinasiHomePenayangan : DestinasiNavigasi {
    override val route = "home_penayangan"
    override val titleRes = "Penayangan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewPenayangan(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    navigateBack: () -> Unit,
    viewModel: HomeViewModelPenayangan = viewModel(factory = PenyediaViewModel.Factory),
    navController: NavController, // Tambahkan NavController untuk navigasi
    currentDestination: String // Tambahkan parameter untuk destinasi saat ini
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomePenayangan.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPenayangan()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Penayangan")
            }
        },
        bottomBar = { // Tambahkan bar navigasi bawah
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
            homeUiState = viewModel.penayanganUIState,
            retryAction = { viewModel.getPenayangan() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deletePenayangan(it.idPenayangan)
                viewModel.getPenayangan()
            }
        )
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Penayangan) -> Unit={},
    onDetailClick: (String) -> Unit
){
    when(homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.penayangan.isEmpty()) {
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Penayangan")
                }
            } else {
                PenayanganLayout(
                    penayangan = homeUiState.penayangan, modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idPenayangan)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

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
fun PenayanganLayout(
    penayangan: List<Penayangan>,
    modifier: Modifier = Modifier,
    onDetailClick: (Penayangan) -> Unit,
    onDeleteClick: (Penayangan) -> Unit = {}
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Menggunakan grid dengan 2 kolom
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(penayangan){penayangan ->
            PenayanganCard(
                penayangan = penayangan,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(penayangan) },
                onDeleteClick = {
                    onDeleteClick(penayangan)
                }
            )
        }
    }
}

@Composable
fun PenayanganCard(
    penayangan: Penayangan,
    modifier: Modifier = Modifier,
    onDeleteClick: (Penayangan) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Harga tiket di atas card dengan ikon uang
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.TopStart) // Menempatkan di bagian atas kiri
                .padding(horizontal = 12.dp, vertical = 4.dp) // Padding untuk teks
        ) {
            Icon(
                imageVector = Icons.Default.Money, // Ikon untuk harga tiket
                contentDescription = "Harga Tiket",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Rp ${penayangan.hargaTiket}",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White, // Warna teks putih
            )
        }

        // Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp) // Beri jarak agar teks tidak menumpuk dengan card
                .shadow(8.dp, shape = RoundedCornerShape(12.dp))
                .border(2.dp, Color.White, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black // Warna latar belakang hitam
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Tanggal Penayangan dengan ikon kalender
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday, // Ikon untuk tanggal
                        contentDescription = "Tanggal Penayangan",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = penayangan.tanggalPenayangan,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                // Divider ketiga
                Divider(color = Color.Gray, thickness = 1.dp)

                // ID Penayangan dengan ikon
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DateRange, // Ikon untuk penayangan
                        contentDescription = "Penayangan ID",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = penayangan.idPenayangan,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Green // Warna teks putih
                    )
                }

                // ID Film dengan ikon
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Movie, // Ikon untuk film
                        contentDescription = "Film ID",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ID Film: ${penayangan.idFilm}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White // Warna teks putih
                    )
                }

                // ID Studio dengan ikon
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LiveTv, // Ikon untuk studio
                        contentDescription = "Studio ID",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ID Studio: ${penayangan.idStudio}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White // Warna teks putih
                    )
                }
            }

            // Tombol delete
            IconButton(
                onClick = { onDeleteClick(penayangan) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red // Warna ikon delete tetap merah
                )
            }
        }
    }
}




