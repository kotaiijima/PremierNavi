package com.github.kota.premierNavi.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.kota.premierNavi.ui.screens.home.HomeScreen
import com.github.kota.premierNavi.ui.screens.players.PlayerScreen
import com.github.kota.premierNavi.ui.screens.rank.RankScreen
import com.github.kota.premierNavi.ui.screens.setting.SettingScreen
import com.github.kota.premierNavi.ui.screens.stats.StatsScreen
import com.github.kota.premierNavi.ui.viewmodel.ViewModel
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN
import com.github.kota.premierNavi.utils.Constants.PLAYER_SCREEN
import com.github.kota.premierNavi.utils.Constants.RANK_SCREEN
import com.github.kota.premierNavi.utils.Constants.SETTING_SCREEN
import com.github.kota.premierNavi.utils.Constants.STATS_SCREEN

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavigation(
	navController: NavHostController,
	viewModel: ViewModel
) {
		NavHost(
			navController = navController,
			startDestination = HOME_SCREEN
		) {
			composable(HOME_SCREEN){ HomeScreen(
				navController = navController,
				viewModel = viewModel
			) }
			composable(PLAYER_SCREEN){ PlayerScreen(
				navController = navController,
				viewModel = viewModel
			) }
			composable(RANK_SCREEN){ RankScreen(
				navController = navController,
				viewModel = viewModel)}

			composable(STATS_SCREEN){ StatsScreen(
				navController = navController,
				viewModel = viewModel)}

			composable(SETTING_SCREEN){ SettingScreen(
				navController = navController,
				viewModel = viewModel)}
		}
}