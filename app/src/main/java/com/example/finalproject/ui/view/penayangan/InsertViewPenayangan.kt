package com.example.finalproject.ui.view.penayangan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.ui.PenyediaViewModel
import com.example.finalproject.ui.costumwigdet.CostumeTopAppBar
import com.example.finalproject.ui.navigation.DestinasiNavigasi
import com.example.finalproject.ui.viewmodel.penayangan.InsertUiEvent
import com.example.finalproject.ui.viewmodel.penayangan.InsertUiState
import com.example.finalproject.ui.viewmodel.penayangan.InsertViewModelPenayangan
import kotlinx.coroutines.launch

object DestinasiInsertPenayangan: DestinasiNavigasi {
    override val route ="item_entry"
    override val titleRes = "Insert Penayangan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertViewPenayangan(
    navigateBack: ()-> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModelPenayangan= viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier=modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertPenayangan.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){ innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onSiswaValueChange = viewModel::updateInsertPenayanganState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertPenayangan()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    insertUiState: InsertUiState,
    onSiswaValueChange: (InsertUiEvent)->Unit,
    onSaveClick: ()->Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent)->Unit={},
    enabled: Boolean = true
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertUiEvent.idPenayangan,
            onValueChange = {onValueChange(insertUiEvent.copy(idPenayangan = it))},
            label = { Text("ID Penayangan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idFilm,
            onValueChange = {onValueChange(insertUiEvent.copy(idFilm = it))},
            label = { Text("ID Film") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idStudio,
            onValueChange = {onValueChange(insertUiEvent.copy(idStudio = it))},
            label = { Text("ID Studio") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.tanggalPenayangan,
            onValueChange = {onValueChange(insertUiEvent.copy(tanggalPenayangan = it))},
            label = { Text("Tanggal Penayangan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.hargaTiket,
            onValueChange = {onValueChange(insertUiEvent.copy(hargaTiket = it))},
            label = { Text("Harga Tiket") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled){
            Text(
                text = "Isi Semua Data",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}