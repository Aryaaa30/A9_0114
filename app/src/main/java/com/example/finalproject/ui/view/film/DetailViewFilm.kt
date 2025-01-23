package com.example.finalproject.ui.view.film

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.model.Film
import com.example.finalproject.ui.PenyediaViewModel
import com.example.finalproject.ui.costumwigdet.CostumeTopAppBar
import com.example.finalproject.ui.navigation.DestinasiNavigasi
import com.example.finalproject.ui.viewmodel.film.DetailUiState
import com.example.finalproject.ui.viewmodel.film.DetailViewModelFilm

object DestinasiDetail: DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail Mahasiswa"
    const val FILM = "idFilm"
    val routesWithArg = "$route/{$FILM}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailViewFilm(
    navigateBack: () -> Unit,
    navigateToItemUpdate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModelFilm = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getFilmById()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemUpdate,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Kontak"
                )
            }
        }
    ) { innerPadding ->
        DetailStatus(
            modifier = Modifier.padding(innerPadding),
            detailUiState = viewModel.filmDetailState,
            retryAction = { viewModel.getFilmById() },
            onDeleteClick = {
                viewModel.deleteFilm(viewModel.filmDetailState.let { state ->
                    if (state is DetailUiState.Success) state.film.idFilm else ""
                })
                navigateBack()
            }
        )
    }
}


@Composable
fun DetailStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,
    onDeleteClick: () -> Unit
) {
    when (detailUiState) {
        is DetailUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is DetailUiState.Success -> {
            if (detailUiState.film.idFilm.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) { Text("Data tidak ditemukan") }
            } else {
                ItemDetailMhs(
                    film = detailUiState.film,
                    modifier = modifier.fillMaxWidth(),
                    onDeleteClick = onDeleteClick
                )
            }
        }
        is DetailUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}


@Composable
fun ItemDetailMhs(
    modifier: Modifier = Modifier,
    film: Film,
    onDeleteClick: () -> Unit
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMhs(judul = "ID Film", isinya = film.idFilm)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailMhs(judul = "Judul Film", isinya = film.judulFilm)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailMhs(judul = "Durasi", isinya = film.durasi)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailMhs(judul = "Deskripsi", isinya = film.deskripsi)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailMhs(judul = "Genre", isinya = film.genre)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailMhs(judul = "Rating Usia", isinya = film.ratingUsia)

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    deleteConfirmationRequired = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Delete")
            }

            if (deleteConfirmationRequired) {
                DeleteConfirmationDialog(
                    onDeleteConfirm = {
                        deleteConfirmationRequired = false
                        onDeleteClick()
                    },
                    onDeleteCancel = { deleteConfirmationRequired = false },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


@Composable
fun ComponentDetailMhs(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = judul,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = isinya,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = {},
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        })
}