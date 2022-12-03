package com.github.kota.premierNavi.ui.screens.stats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState

@Composable
fun StatsScreen(
	navController: NavController,
	navigateToTeamDetail:(Int) -> Unit,
	stats: ApiResult<StatsDomainModel>
){
	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Box(
				modifier = Modifier.padding(bottom = bottomNavigationHeight
				)
			){
				if (stats is ApiResult.ApiSuccess)
					StatsContent(
						stats = stats.data,
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