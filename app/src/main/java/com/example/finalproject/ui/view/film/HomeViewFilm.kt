package com.example.finalproject.ui.view.film

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.R
import com.example.finalproject.model.Film
import com.example.finalproject.ui.PenyediaViewModel
import com.example.finalproject.ui.costumwigdet.CostumeTopAppBar
import com.example.finalproject.ui.navigation.DestinasiNavigasi
import com.example.finalproject.ui.view.DestinasiBeranda
import com.example.finalproject.ui.view.IconMenuButton
import com.example.finalproject.ui.view.penayangan.DestinasiHomePenayangan
import com.example.finalproject.ui.view.studio.DestinasiHomeStudio
import com.example.finalproject.ui.view.tiket.DestinasiHomeTiket
import com.example.finalproject.ui.viewmodel.film.HomeUiState
import com.example.finalproject.ui.viewmodel.film.HomeViewModelFilm

object DestinasiHomeFilm : DestinasiNavigasi {
    override val route = "home_film"
    override val titleRes = "Film"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewFilm(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    navigateBack: () -> Unit,
    viewModel: HomeViewModelFilm = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val navController = rememberNavController()
    var currentDestination by remember { mutableStateOf(DestinasiHomeFilm.route) }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeFilm.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getFilm()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Film")
            }
        },
        bottomBar = {
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
                        onClick = {
                            navController.navigate(DestinasiHomeFilm.route)
                            currentDestination = DestinasiHomeFilm.route
                        },
                        text = "Film"
                    )
                    IconMenuButton(
                        icon = Icons.Default.Event,
                        description = "Penayangan",
                        isActive = currentDestination == DestinasiHomePenayangan.route,
                        onClick = {
                            navController.navigate(DestinasiHomePenayangan.route)
                            currentDestination = DestinasiHomePenayangan.route
                        },
                        text = "Penayangan"
                    )
                    IconMenuButton(
                        icon = Icons.Default.Home,
                        description = "Home",
                        isActive = currentDestination == DestinasiBeranda.route,
                        onClick = {
                            navController.navigate(DestinasiBeranda.route)
                            currentDestination = DestinasiBeranda.route
                        },
                        text = "Home"
                    )
                    IconMenuButton(
                        icon = Icons.Default.LiveTv,
                        description = "Studio",
                        isActive = currentDestination == DestinasiHomeStudio.route,
                        onClick = {
                            navController.navigate(DestinasiHomeStudio.route)
                            currentDestination = DestinasiHomeStudio.route
                        },
                        text = "Studio"
                    )
                    IconMenuButton(
                        icon = Icons.Default.ConfirmationNumber,
                        description = "Tiket",
                        isActive = currentDestination == DestinasiHomeTiket.route,
                        onClick = {
                            navController.navigate(DestinasiHomeTiket.route)
                            currentDestination = DestinasiHomeTiket.route
                        },
                        text = "Tiket"
                    )
                }
            }
        }
    ) { innerPadding ->
        HomeStatus(
            homeUiState = viewModel.filmUIState,
            retryAction = { viewModel.getFilm() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteFilm(it.idFilm)
                viewModel.getFilm()
            }
        )
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Film) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.film.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Film")
                }
            } else {
                FilmGrid(
                    film = homeUiState.film,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idFilm)
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
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun FilmGrid(
    film: List<Film>,
    modifier: Modifier = Modifier,
    onDetailClick: (Film) -> Unit,
    onDeleteClick: (Film) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Menggunakan grid dengan 2 kolom
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(film) { film ->
            FilmCard(
                film = film,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(film) },
                onDeleteClick = {
                    onDeleteClick(film)
                }
            )
        }
    }
}

@Composable
fun FilmCard(
    film: Film,
    modifier: Modifier = Modifier,
    onDeleteClick: (Film) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Box {
            // Bagian Gambar
            Image(
                painter = painterResource(R.drawable.darknuns), // Gambar placeholder
                contentDescription = "Poster Film",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            // ID Film di pojok kiri atas
            Card(
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors( // Menggunakan cardColors untuk warna background
                    containerColor = Color.Red
                ),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp) // Padding luar
            ) {
                Text(
                    text = "${film.idFilm}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp) // Padding dalam
                )
            }

            // Rating Usia di pojok kanan atas
            Card(
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors( // Menggunakan cardColors untuk warna background
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp) // Padding luar
            ) {
                Text(
                    text = "${film.ratingUsia}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp) // Padding dalam
                )
            }

            // Konten lain di bawah gambar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 230.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                // Bagian Judul Film dan Tombol Delete
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Judul Film
                    Text(
                        text = film.judulFilm,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        color = Color.White
                    )

                    // Tombol Delete
                    IconButton(onClick = { onDeleteClick(film) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    }
}









