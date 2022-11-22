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
	val latestGame by myViewModel.latestGame.collectAsState()
	val nextGame by myViewModel.nextGame.collectAsState()
	val rank by myViewModel.rank.collectAsState()
	val stats by myViewModel.stats.collectAsState()
	val teamId by myViewModel.teamId.collectAsState()
	val selectedTeam by myViewModel.selectedTeam.collectAsState()

	NavHost(
		navController = navController,
		startDestination = HOME_SCREEN
	) {
		composable(HOME_SCREEN) {
			HomeScreen(
				navigateToTeamDetail = screen.team,
				navController = navController,
				addTeamId = myViewModel::addTeamId,
				latestGame = latestGame,
				nextGame = nextGame,
				rank = rank,
				teamId = teamId
			)
		}
		composable(RANK_SCREEN) {
			RankScreen(
				navigateToTeamDetail = screen.team,
				navController = navController,
				rank = rank
			)
		}
		composable(STATS_SCREEN){ StatsScreen(
			navController = navController,
			navigateToTeamDetail = screen.team,
			stats = stats
		) }

		composable(SETTING_SCREEN){ SettingScreen(
			navController = navController,
			rank = rank,
			updateTeamId = myViewModel::updateTeamId
		) }
		composable(TEAM_SCREEN){ PlayerScreen(
			navController = navController,
			selectedTeam = team,
		) }
		composable(
			route = TEAM_DETAIL,
			arguments = listOf(navArgument(TEAM_DETAIL_ARGUMENT_KEY){
				type = NavType.IntType
			})
		) {
			val selectedTeamId = it.arguments!!.getInt(TEAM_DETAIL_ARGUMENT_KEY)
			myViewModel.getTeamData(selectedTeamId)

			PlayerScreen(
				selectedTeam = selectedTeam,
				navController = navController,
				)
		}
	}
}