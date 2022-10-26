package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen(
	navigateToPlayerScreen:() -> Unit,
	mainViewModel: MainViewModel
){
	val latestGame by mainViewModel.latestGame.collectAsState()
	val nextGame by mainViewModel.nextGame.collectAsState()

	Column() {
		HomeLatestGame(team = latestGame)
		HomeNextGame(team = nextGame)

		Button(onClick = {
			navigateToPlayerScreen()
		}) {
			Text(text = "TAP!")
		}
	}
}