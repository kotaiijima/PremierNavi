package com.github.kota.premierNavi.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.kota.premierNavi.ui.screens.home.HomeScreen
import com.github.kota.premierNavi.ui.screens.players.PlayerScreen
import com.github.kota.premierNavi.ui.screens.rank.RankScreen
import com.github.kota.premierNavi.ui.screens.stats.StatsScreen
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SetupNavigation(
	navController: NavHostController,
	viewModel: MainViewModel
) {
		NavHost(navController = navController, startDestination = HOME_SCREEN) {
			composable(HOME_SCREEN){ HomeScreen(
				navController = navController,
				mainViewModel = viewModel
			) }
			composable("players"){ PlayerScreen(
				navController = navController,
				mainViewModel = viewModel
			) }
			composable("rank"){ RankScreen(
				navController = navController,
				viewModel = viewModel)}

			composable("stats"){ StatsScreen(
				navController = navController,
				viewModel = viewModel)}
	}
}