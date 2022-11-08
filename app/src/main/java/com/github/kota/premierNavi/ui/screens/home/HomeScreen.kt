package com.github.kota.premierNavi.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.SplashAnimationView
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel
import com.github.kota.premierNavi.utils.ApiResult

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
	navController: NavController,
	mainViewModel: MainViewModel
){
	val latestGame by mainViewModel.latestGame.collectAsState()
	val nextGame by mainViewModel.nextGame.collectAsState()

	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Column {
				if (latestGame is ApiResult.ApiSuccess && nextGame is ApiResult.ApiSuccess){
					HomeLatestGame(match = (latestGame as ApiResult.ApiSuccess<Match>).data)
					HomeNextGame(match = (nextGame as ApiResult.ApiSuccess<Match>).data)
				}else{
					SplashAnimationView()
				}
			}
		},
		topBar = {TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}