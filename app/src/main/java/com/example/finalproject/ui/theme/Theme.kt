package com.example.finalproject.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

// Skema warna untuk tema abu-abu (Gray Theme)
private val GrayColorScheme = darkColorScheme(
    primary = Gray200,
    secondary = Gray100,
    tertiary = Gray300,
    background = Gray900,
    surface = Gray900,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.LightGray,
    onSurface = Color.LightGray
)

@Composable
fun FinalProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Menyesuaikan dengan pengaturan sistem
    useGrayTheme: Boolean = false, // Tambahkan opsi untuk menggunakan tema abu-abu
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        useGrayTheme -> GrayColorScheme // Gunakan tema abu-abu jika dipilih
        darkTheme -> DarkColorScheme    // Gunakan tema gelap
        else -> LightColorScheme        // Gunakan tema terang
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

