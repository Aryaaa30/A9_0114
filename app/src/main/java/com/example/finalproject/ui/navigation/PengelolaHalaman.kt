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
import com.example.finalproject.ui.view.DestinasiBeranda
import com.example.finalproject.ui.view.MyCinema
import com.example.finalproject.ui.view.film.DestinasiDetailFilm
import com.example.finalproject.ui.view.film.DestinasiHomeFilm
import com.example.finalproject.ui.view.film.DestinasiInsertFilm
import com.example.finalproject.ui.view.film.DestinasiUpdateFilm
import com.example.finalproject.ui.view.film.DetailViewFilm
import com.example.finalproject.ui.view.film.HomeViewFilm
import com.example.finalproject.ui.view.film.InsertViewFilm
import com.example.finalproject.ui.view.film.UpdateViewFilm
import com.example.finalproject.ui.view.penayangan.DestinasiDetailPenayangan
import com.example.finalproject.ui.view.penayangan.DestinasiHomePenayangan
import com.example.finalproject.ui.view.penayangan.DestinasiInsertPenayangan
import com.example.finalproject.ui.view.penayangan.DestinasiUpdatePenayangan
import com.example.finalproject.ui.view.penayangan.DetailViewPenayangan
import com.example.finalproject.ui.view.penayangan.HomeViewPenayangan
import com.example.finalproject.ui.view.penayangan.InsertViewPenayangan
import com.example.finalproject.ui.view.penayangan.UpdateViewPenayangan
import com.example.finalproject.ui.view.studio.DestinasiDetailStudio
import com.example.finalproject.ui.view.studio.DestinasiHomeStudio
import com.example.finalproject.ui.view.studio.DestinasiInsertStudio
import com.example.finalproject.ui.view.studio.DestinasiUpdateStudio
import com.example.finalproject.ui.view.studio.DetailViewStudio
import com.example.finalproject.ui.view.studio.HomeViewStudio
import com.example.finalproject.ui.view.studio.InsertViewStudio
import com.example.finalproject.ui.view.studio.UpdateViewStudio
import com.example.finalproject.ui.view.tiket.DestinasiDetailTiket
import com.example.finalproject.ui.view.tiket.DestinasiHomeTiket
import com.example.finalproject.ui.view.tiket.DestinasiInsertTiket
import com.example.finalproject.ui.view.tiket.DestinasiUpdateTiket
import com.example.finalproject.ui.view.tiket.DetailViewTiket
import com.example.finalproject.ui.view.tiket.HomeViewTiket
import com.example.finalproject.ui.view.tiket.InsertViewTiket
import com.example.finalproject.ui.view.tiket.UpdateViewTiket

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiBeranda.route, // Ubah ke MyCinema sebagai startDestination
        modifier = Modifier,
    ) {
        composable(DestinasiBeranda.route) {
            MyCinema(navController = navController)
        }
        composable(DestinasiHomeFilm.route) {
            HomeViewFilm(
                navigateToItemEntry = { navController.navigate(DestinasiInsertFilm.route) },
                navigateBack = {
                    navController.navigate(DestinasiBeranda.route) {
                        popUpTo(DestinasiBeranda.route) { inclusive = true }
                    }
                },
                onDetailClick = { idFilm ->
                    navController.navigate("${DestinasiDetailFilm.route}/$idFilm")
                }
            )
        }
        composable(DestinasiDetailFilm.routesWithArg, arguments = listOf(navArgument(DestinasiDetailFilm.FILM) {
            type = NavType.StringType
        })) {
            val idFilm = it.arguments?.getString(DestinasiDetailFilm.FILM)
            Log.d("DetailPage", "Received ID: $idFilm")
            idFilm?.let { idFilm ->
                DetailViewFilm(
                    navigateToItemUpdate = {
                        navController.navigate("${DestinasiUpdateFilm.route}/$idFilm")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiHomeFilm.route) {
                            popUpTo(DestinasiHomeFilm.route) { inclusive = true }
                        }
                    },
                    navigateToHomePenayangan = {
                        navController.navigate(DestinasiHomePenayangan.route){
                            popUpTo(DestinasiHomePenayangan.route) { inclusive = true }
                        }
                    }
                )
            }
        }
        composable(DestinasiInsertFilm.route) {
            InsertViewFilm(navigateBack = {
                navController.navigate(DestinasiHomeFilm.route) {
                    popUpTo(DestinasiHomeFilm.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiUpdateFilm.routesWithArg, arguments = listOf(navArgument(DestinasiDetailFilm.FILM) {
            type = NavType.StringType
        })) {
            val idFilm = it.arguments?.getString(DestinasiUpdateFilm.FILM)
            idFilm?.let { idFilm ->
                UpdateViewFilm(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
        composable(DestinasiHomePenayangan.route) {
            HomeViewPenayangan(
                navigateToItemEntry = { navController.navigate(DestinasiInsertPenayangan.route) },
                navigateBack = {
                    navController.navigate(DestinasiHomeFilm.route) {
                        popUpTo(DestinasiHomeFilm.route) { inclusive = true }
                    }
                },
                onDetailClick = { idPenayangan ->
                    navController.navigate("${DestinasiDetailPenayangan.route}/$idPenayangan")
                }
            )
        }
        composable(DestinasiDetailPenayangan.routesWithArg, arguments = listOf(navArgument(DestinasiDetailPenayangan.Penayangan) {
            type = NavType.StringType
        })) {
            val idPenayangan = it.arguments?.getString(DestinasiDetailPenayangan.Penayangan)
            idPenayangan?.let { idPenayangan ->
                DetailViewPenayangan(
                    navigateToItemUpdate = {
                        navController.navigate("${DestinasiUpdatePenayangan.route}/$idPenayangan")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiHomePenayangan.route) {
                            popUpTo(DestinasiHomePenayangan.route) { inclusive = true }
                        }
                    },
                )
            }
        }
        composable(DestinasiInsertPenayangan.route) {
            InsertViewPenayangan(navigateBack = {
                navController.navigate(DestinasiHomePenayangan.route) {
                    popUpTo(DestinasiHomePenayangan.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiUpdatePenayangan.routesWithArg, arguments = listOf(navArgument(DestinasiDetailPenayangan.Penayangan) {
            type = NavType.StringType
        })) {
            val idPenayangan = it.arguments?.getString(DestinasiUpdatePenayangan.Penayangan)
            idPenayangan?.let { idPenayangan ->
                UpdateViewPenayangan(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
        composable(DestinasiHomeStudio.route) {
            HomeViewStudio(
                navigateToItemEntry = { navController.navigate(DestinasiInsertStudio.route) },
                navigateBack = {
                    navController.navigate(DestinasiBeranda.route) {
                        popUpTo(DestinasiBeranda.route) { inclusive = true }
                    }
                },
                onDetailClick = { idStudio ->
                    navController.navigate("${DestinasiDetailStudio.route}/$idStudio")
                }
            )
        }
        composable(DestinasiDetailStudio.routesWithArg, arguments = listOf(navArgument(DestinasiDetailStudio.Studio) {
            type = NavType.StringType
        })) {
            val idStudio = it.arguments?.getString(DestinasiDetailStudio.Studio)
            idStudio?.let { idStudio ->
                DetailViewStudio(
                    navigateToItemUpdate = {
                        navController.navigate("${DestinasiUpdateStudio.route}/$idStudio")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiHomeStudio.route) {
                            popUpTo(DestinasiHomeStudio.route) { inclusive = true }
                        }
                    },
                )
            }
        }
        composable(DestinasiInsertStudio.route) {
            InsertViewStudio(navigateBack = {
                navController.navigate(DestinasiHomeStudio.route) {
                    popUpTo(DestinasiHomeStudio.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiUpdateStudio.routesWithArg, arguments = listOf(navArgument(DestinasiDetailStudio.Studio) {
            type = NavType.StringType
        })) {
            val idStudio = it.arguments?.getString(DestinasiUpdateStudio.Studio)
            idStudio?.let { idStudio ->
                UpdateViewStudio(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
        composable(DestinasiHomeTiket.route) {
            HomeViewTiket(
                navigateToItemEntry = { navController.navigate(DestinasiInsertTiket.route) },
                navigateBack = {
                    navController.navigate(DestinasiBeranda.route) {
                        popUpTo(DestinasiBeranda.route) { inclusive = true }
                    }
                },
                onDetailClick = { idTiket ->
                    navController.navigate("${DestinasiDetailTiket.route}/$idTiket")
                }
            )
        }
        composable(DestinasiDetailTiket.routesWithArg, arguments = listOf(navArgument(DestinasiDetailTiket.Tiket) {
            type = NavType.StringType
        })) {
            val idTiket = it.arguments?.getString(DestinasiDetailTiket.Tiket)
            idTiket?.let { idTiket ->
                DetailViewTiket(
                    navigateToItemUpdate = {
                        navController.navigate("${DestinasiUpdateTiket.route}/$idTiket")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiHomeTiket.route) {
                            popUpTo(DestinasiHomeTiket.route) { inclusive = true }
                        }
                    },
                )
            }
        }
        composable(DestinasiInsertTiket.route) {
            InsertViewTiket(navigateBack = {
                navController.navigate(DestinasiHomeTiket.route) {
                    popUpTo(DestinasiHomeTiket.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiUpdateTiket.routesWithArg, arguments = listOf(navArgument(DestinasiDetailTiket.Tiket) {
            type = NavType.StringType
        })) {
            val idTiket = it.arguments?.getString(DestinasiUpdateTiket.Tiket)
            idTiket?.let { idTiket ->
                UpdateViewTiket(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}
