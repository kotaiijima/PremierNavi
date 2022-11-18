package com.github.kota.premierNavi.ui.screens.players

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.ui.viewmodel.MyViewModel
import com.github.kota.premierNavi.utils.ApiResult

@Composable
fun PlayerScreen(
	selectedTeam: ApiResult<Team>,
	navController: NavController,
	myViewModel: MyViewModel
){
	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
				Box(modifier = Modifier.padding(bottom = bottomNavigationHeight)){
					if (selectedTeam is ApiResult.ApiSuccess){
						Column {
							TeamInformation(
								crest = (selectedTeam as ApiResult.ApiSuccess<Team>).data.crest,
								stadium = (selectedTeam as ApiResult.ApiSuccess<Team>).data.venue,
								teamName = (selectedTeam as ApiResult.ApiSuccess<Team>).data.name
							)
							PlayerContent(team = (selectedTeam as ApiResult.ApiSuccess<Team>).data)
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