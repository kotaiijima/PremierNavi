package com.github.kota.premierNavi.ui.screens.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.rank.RankContent
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel

@Composable
fun StatsScreen(
	navController: NavController,
	viewModel: MainViewModel
){
	val stats by viewModel.stats.collectAsState()

	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Column() {
				StatsContent(stats = stats)
			}
		},
		topBar = { TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}