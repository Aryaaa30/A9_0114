package com.example.finalproject.ui.view.film

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.ui.PenyediaViewModel
import com.example.finalproject.ui.costumwigdet.CostumeTopAppBar
import com.example.finalproject.ui.navigation.DestinasiUpdate
import com.example.finalproject.ui.viewmodel.film.UpdateViewModelFilm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateViewFilm(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdateViewModelFilm = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdate.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){ padding ->
        EntryBody(
            modifier = Modifier.padding(padding),
            insertUiState = viewModel.UpdateUiState,
            onSiswaValueChange = viewModel::updateInsertFilmState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateFilm()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}