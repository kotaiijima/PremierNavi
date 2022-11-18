package com.github.kota.premierNavi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.kota.premierNavi.ui.screens.home.HomeScreen
import com.github.kota.premierNavi.ui.screens.players.PlayerScreen
import com.github.kota.premierNavi.ui.screens.rank.RankScreen
import com.github.kota.premierNavi.ui.screens.setting.SettingScreen
import com.github.kota.premierNavi.ui.screens.stats.StatsScreen
import com.github.kota.premierNavi.ui.viewmodel.MyViewModel
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN
import com.github.kota.premierNavi.utils.Constants.RANK_SCREEN
import com.github.kota.premierNavi.utils.Constants.SETTING_SCREEN
import com.github.kota.premierNavi.utils.Constants.STATS_SCREEN
import com.github.kota.premierNavi.utils.Constants.TEAM_DETAIL
import com.github.kota.premierNavi.utils.Constants.TEAM_DETAIL_ARGUMENT_KEY
import com.github.kota.premierNavi.utils.Constants.TEAM_SCREEN

@Composable
fun SetupNavigation(
	navController: NavHostController,
	myViewModel: MyViewModel
) {
	val screen = remember(navController) {
		Screen(navController)
	}
	val team by myViewModel.team.collectAsState()

	NavHost(
		navController = navController,
		startDestination = HOME_SCREEN
	) {
		composable(HOME_SCREEN) {
			HomeScreen(
				navigateToTeamDetail = screen.team,
				navController = navController,
				addTeamId = myViewModel::addTeamId,
			)
		}
		composable(RANK_SCREEN) {
			RankScreen(
				navigateToTeamDetail = screen.team,
				navController = navController,
				myViewModel = myViewModel
			)
		}
		composable(STATS_SCREEN){ StatsScreen(
			navigateToTeamDetail = screen.team,
			navController = navController,
			myViewModel = myViewModel)}

		composable(SETTING_SCREEN){ SettingScreen(
			navController = navController,
			myViewModel = myViewModel)}
		composable(TEAM_SCREEN){ PlayerScreen(
			selectedTeam = team,
			navController = navController,
			myViewModel = myViewModel
		)}
		composable(
			route = TEAM_DETAIL,
			arguments = listOf(navArgument(TEAM_DETAIL_ARGUMENT_KEY){
				type = NavType.IntType
			})
		) {
			val teamId = it.arguments!!.getInt(TEAM_DETAIL_ARGUMENT_KEY)
			myViewModel.getTeamData(teamId)
			val selectedTeam by myViewModel.selectedTeam.collectAsState()

			PlayerScreen(
				selectedTeam = selectedTeam,
				navController = navController,
				myViewModel = myViewModel)
		}
	}
}