package com.github.kota.premierNavi.ui.screens.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.utils.RequestState

@Composable
fun SettingScreen(
	navController: NavController,
	rank: RequestState<RankDomainModel>,
	updateTeamId:(Int) -> Unit
){

	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Box(modifier = Modifier.padding(bottom = bottomNavigationHeight)){
				if (rank is RequestState.Success){
					Column {
						SettingContent(
							navController = navController,
							rank = rank.data,
							updateTeamId = updateTeamId
						)
					}
				}
				else
					LoadingAnimationView()
			}
		},
		topBar = { TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}