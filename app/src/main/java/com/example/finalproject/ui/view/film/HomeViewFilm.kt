package com.example.finalproject.ui.view.film

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.R
import com.example.finalproject.model.Film
import com.example.finalproject.ui.PenyediaViewModel
import com.example.finalproject.ui.costumwigdet.CostumeTopAppBar
import com.example.finalproject.ui.navigation.DestinasiNavigasi
import com.example.finalproject.ui.viewmodel.film.HomeUiState
import com.example.finalproject.ui.viewmodel.film.HomeViewModelFilm

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Mahasiswa"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewFilm(
    navigateToItemEntry: ()-> Unit,
    modifier: Modifier=Modifier,
    onDetailClick: (String) -> Unit={},
    viewModel: HomeViewModelFilm = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHome.titleRes,
                canNavigateBack = false,
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
        }
    ){ innerPadding ->
        HomeStatus(
            homeUiState = viewModel.filmUIState,
            retryAction = { viewModel.getFilm() }, modifier = Modifier.padding(innerPadding),
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
    onDeleteClick: (Film) -> Unit={},
    onDetailClick: (String) -> Unit
){
    when(homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.film.isEmpty()) {
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Mahasiswa")
                }
            } else {
                FilmLayout(
                    film = homeUiState.film, modifier = modifier.fillMaxWidth(),
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
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.img),
        contentDescription = stringResource(R.string.loading)
    )
}

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
fun FilmLayout(
    film: List<Film>,
    modifier: Modifier = Modifier,
    onDetailClick: (Film) -> Unit,
    onDeleteClick: (Film) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(film){film ->
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
            .padding(8.dp)
            .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE0F7FA)) // Warna latar belakang biru muda
                .padding(16.dp)
        ) {
            // Bagian kiri: Logo atau gambar
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color(0xFF0097A7), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.man), // Ganti dengan logo Anda
                    contentDescription = "Logo",
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Bagian kanan: Informasi mahasiswa
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = film.judulFilm,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFF00796B) // Warna teks hijau tua
                )
                Text(
                    text = "ID Film: ${film.idFilm}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF004D40)
                )
                Text(
                    text = "Durasi: ${film.durasi}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF004D40)
                )
                Text(
                    text = "Deskripsi: ${film.deskripsi}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF004D40)
                )
                Text(
                    text = "Genre: ${film.genre}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF004D40)
                )
                Text(
                    text = "Rating Usia: ${film.ratingUsia}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF004D40)
                )
            }

            // Tombol delete
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