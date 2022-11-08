package com.github.kota.premierNavi.ui.screens.stats

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.screens.players.PlayerContent
import com.github.kota.premierNavi.ui.screens.rank.RankContent
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel
import com.github.kota.premierNavi.utils.ApiResult

@RequiresApi(Build.VERSION_CODES.O)
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
			if (stats is ApiResult.ApiSuccess)
				StatsContent(stats = (stats as ApiResult.ApiSuccess<Stats>).data)
			else
				LoadingAnimationView()
		},
		topBar = { TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}