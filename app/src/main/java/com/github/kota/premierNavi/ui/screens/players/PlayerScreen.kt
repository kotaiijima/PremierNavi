package com.github.kota.premierNavi.ui.screens.players

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel

@Composable
fun PlayerScreen(
	navController: NavController,
	mainViewModel: MainViewModel
){
	val team by mainViewModel.team.collectAsState()
	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
				  PlayerContent(team = team)
		},
		topBar = { TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}