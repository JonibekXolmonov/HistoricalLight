package com.historical.streetlight.utils

sealed class Screen(
    val route: String
) {
    object Menu : Screen("menu")
    object Settings : Screen("settings")
    object Game : Screen("game")
}