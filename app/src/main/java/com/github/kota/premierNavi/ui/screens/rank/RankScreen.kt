package com.github.kota.premierNavi.ui.screens.rank

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel

@Composable
fun RankScreen(
	navController: NavController,
	viewModel: MainViewModel
){
	val rank by viewModel.rank.collectAsState()

	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Column() {
				RankContent(rank = rank)
			}
		},
		topBar = { TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}