package com.historical.streetlight.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.historical.streetlight.screens.game.GameScreen
import com.historical.streetlight.screens.menu.MenuScreen
import com.historical.streetlight.screens.score.ScoreScreen
import com.historical.streetlight.screens.settings.SettingsScreen
import com.historical.streetlight.utils.Screen

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Menu.route,
    onVibrate: () -> Unit,
    onSound: (Boolean) -> Unit
) {
    NavHost(
        navController,
        startDestination = startDestination,
    ) {
        composable(Screen.Menu.route) {
            MenuScreen(
                onGameStart = {
                    onVibrate()
                    navController.navigate(Screen.Game.route)
                },
                onSettingsClick = {
                    onVibrate()
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onBackPressed = {
                    navController.popBackStack()
                },
                onVibrate = onVibrate,
                onSound = onSound
            )
        }

        composable(Screen.Game.route) {
            GameScreen(
                onBack = {
                    onVibrate()
                    navController.popBackStack()
                },
                onVibrate = onVibrate
            )
        }
    }
}