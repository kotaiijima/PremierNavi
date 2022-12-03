package com.github.kota.premierNavi.ui.screens.players

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kota.premierNavi.domain.model.TeamDomainModel
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import com.github.kota.premierNavi.utils.showCrest

@Composable
fun PlayerScreen(
	navController: NavController,
	team: ApiResult<TeamDomainModel>,
){
	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
				Box(modifier = Modifier.padding(bottom = bottomNavigationHeight)){
					if (team is ApiResult.ApiSuccess){
						Column {
							TeamInformation(
								crest = showCrest(crest = team.data.crest),
								stadium = team.data.stadium,
								teamName = team.data.teamName
							)
							PlayerContent(team = team.data)
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