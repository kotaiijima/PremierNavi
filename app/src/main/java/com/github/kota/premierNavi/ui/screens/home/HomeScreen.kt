package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.SplashAnimationView
import com.github.kota.premierNavi.ui.screens.initial.InitialScreen
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState

@Composable
fun HomeScreen(
	navController: NavController,
	viewModel: MainViewModel
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
//				if (teamId is RequestState.Success) {
//					if ((teamId as RequestState.Success<List<TeamId>>).data.isEmpty()) {
//						InitialScreen(
//							navController = navController,
//							viewModel = viewModel
//						)
//					} else {
						if (latestGame is ApiResult.ApiSuccess && nextGame is ApiResult.ApiSuccess) {
							HomeLatestGame(match = (latestGame as ApiResult.ApiSuccess<Match>).data)
							HomeNextGame(match = (nextGame as ApiResult.ApiSuccess<Match>).data)
						} else {
							SplashAnimationView()
						}
//					}
//				}
			}
		},
		topBar = {TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}