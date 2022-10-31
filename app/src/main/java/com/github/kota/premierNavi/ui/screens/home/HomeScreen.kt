package com.github.kota.premierNavi.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel

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
			Column() {
				HomeLatestGame(match = latestGame)
				HomeNextGame(
					match = nextGame)
			}
		},
		topBar = {TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}