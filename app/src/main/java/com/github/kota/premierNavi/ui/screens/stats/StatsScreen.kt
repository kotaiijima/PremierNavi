package com.github.kota.premierNavi.ui.screens.stats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kota.premierNavi.data.api.model.statsModel.ApiStats
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.utils.ApiResult

@Composable
fun StatsScreen(
	navController: NavController,
	navigateToTeamDetail:(Int) -> Unit,
	apiStats: ApiResult<ApiStats>
){
	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Box(
				modifier = Modifier.padding(bottom = bottomNavigationHeight
				)
			){
				if (apiStats is ApiResult.ApiSuccess)
					StatsContent(
						apiStats = apiStats.data,
						navigateToTeamDetail = navigateToTeamDetail
					)
				else
					LoadingAnimationView()
			}
		},
		topBar = { TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController)}
	)
}