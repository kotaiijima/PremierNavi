package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.SplashAnimationView
import com.github.kota.premierNavi.ui.screens.initial.InitialScreen
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.ui.viewmodel.ViewModel
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState

@Composable
fun HomeScreen(
	navigateToTeamDetail:(Int) -> Unit,
	navController: NavController,
	viewModel: ViewModel
){
	val latestGame by viewModel.latestGame.collectAsState()
	val nextGame by viewModel.nextGame.collectAsState()
	val teamId by viewModel.teamId.collectAsState()

	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Column (
				modifier = Modifier.padding(bottom = bottomNavigationHeight)
					){
					if (teamId !is RequestState.Success) {
						InitialScreen(
							viewModel = viewModel
						)
					} else {
						if (latestGame is ApiResult.ApiSuccess && nextGame is ApiResult.ApiSuccess) {
							HomeLatestGame(
								match = (latestGame as ApiResult.ApiSuccess<Match>).data,
								navigateToTeamDetail = navigateToTeamDetail
							)
							HomeNextGame(
								match = (nextGame as ApiResult.ApiSuccess<Match>).data,
								navigateToTeamDetail = navigateToTeamDetail
							)
						} else {
							SplashAnimationView()
						}
					}
				}
		},
		topBar = {TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}