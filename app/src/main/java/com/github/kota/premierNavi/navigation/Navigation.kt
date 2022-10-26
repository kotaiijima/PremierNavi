package com.github.kota.premierNavi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.kota.premierNavi.ui.screens.home.HomeScreen
import com.github.kota.premierNavi.ui.screens.players.PlayerScreen
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN


@Composable
fun SetupNavigation(
	navController: NavHostController,
	viewModel: MainViewModel
) {
	val screen = remember(navController) {
		Screens(navController = navController)
	}

		NavHost(navController = navController, startDestination = HOME_SCREEN) {
			composable(HOME_SCREEN){ HomeScreen(
				navigateToPlayerScreen = screen.players,
				mainViewModel = viewModel
			) }
			composable("players"){ PlayerScreen() }
	}
}