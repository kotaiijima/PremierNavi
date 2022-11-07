package com.github.kota.premierNavi.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.kota.premierNavi.component.SplashAnimationView
import com.github.kota.premierNavi.ui.screens.home.HomeScreen
import com.github.kota.premierNavi.ui.screens.players.PlayerScreen
import com.github.kota.premierNavi.ui.screens.rank.RankScreen
import com.github.kota.premierNavi.ui.screens.stats.StatsScreen
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN
import com.github.kota.premierNavi.utils.Constants.PLAYER_SCREEN
import com.github.kota.premierNavi.utils.Constants.RANK_SCREEN
import com.github.kota.premierNavi.utils.Constants.SPLASH_SCREEN
import com.github.kota.premierNavi.utils.Constants.STATS_SCREEN

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavigation(
	navController: NavHostController,
	viewModel: MainViewModel
) {
		NavHost(
			navController = navController,
			startDestination = SPLASH_SCREEN
		) {
			composable(SPLASH_SCREEN){ SplashAnimationView(
				navController = navController
			)
			}
			composable(HOME_SCREEN){ HomeScreen(
				navController = navController,
				mainViewModel = viewModel
			) }
			composable(PLAYER_SCREEN){ PlayerScreen(
				navController = navController,
				mainViewModel = viewModel
			) }
			composable(RANK_SCREEN){ RankScreen(
				navController = navController,
				viewModel = viewModel)}

			composable(STATS_SCREEN){ StatsScreen(
				navController = navController,
				viewModel = viewModel)}
	}
}