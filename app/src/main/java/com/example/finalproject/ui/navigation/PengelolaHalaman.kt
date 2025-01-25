package com.example.finalproject.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalproject.ui.view.film.DestinasiDetailFilm
import com.example.finalproject.ui.view.film.DestinasiHomeFilm
import com.example.finalproject.ui.view.film.DestinasiInsertFilm
import com.example.finalproject.ui.view.film.DestinasiUpdateFilm
import com.example.finalproject.ui.view.film.DetailViewFilm
import com.example.finalproject.ui.view.film.HomeViewFilm
import com.example.finalproject.ui.view.film.InsertViewFilm
import com.example.finalproject.ui.view.film.UpdateViewFilm

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController=navController,
        startDestination = DestinasiHomeFilm.route,
        modifier = Modifier,
    ){
        composable(DestinasiHomeFilm.route){
            HomeViewFilm(
                navigateToItemEntry = {navController.navigate(DestinasiInsertFilm.route)},
                onDetailClick = { idFilm ->
                    Log.d("Navigation", "Navigating to detail with ID: $idFilm")
                    navController.navigate("${DestinasiDetailFilm.route}/$idFilm")
                }
            )
        }
        composable(DestinasiInsertFilm.route){
            InsertViewFilm(navigateBack = {
                navController.navigate(DestinasiHomeFilm.route){
                    popUpTo(DestinasiHomeFilm.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetailFilm.routesWithArg, arguments = listOf(navArgument(DestinasiDetailFilm.FILM) {
            type = NavType.StringType })
        ){
            val idFilm = it.arguments?.getString(DestinasiDetailFilm.FILM)
            Log.d("DetailPage", "Received ID: $idFilm")
            idFilm?.let { idFilm ->
                DetailViewFilm(
                    navigateToItemUpdate = { navController.navigate("${DestinasiUpdateFilm.route}/$idFilm") },
                    navigateBack = { navController.navigate(DestinasiHomeFilm.route) {
                        popUpTo(DestinasiHomeFilm.route) { inclusive = true }
                    }}
                )
            }
        }
        composable(DestinasiUpdateFilm.routesWithArg, arguments = listOf(navArgument(DestinasiDetailFilm.FILM){
            type = NavType.StringType })
        ){
            val idFilm = it.arguments?.getString(DestinasiUpdateFilm.FILM)
            idFilm?.let { idFilm ->
                UpdateViewFilm(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}