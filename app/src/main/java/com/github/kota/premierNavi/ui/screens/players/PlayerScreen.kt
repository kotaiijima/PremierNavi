package com.github.kota.premierNavi.ui.screens.players

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState

@Composable
fun PlayerScreen(
	navController: NavController,
	viewModel: MainViewModel
){
	val team by viewModel.team.collectAsState()

	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
				Box(modifier = Modifier.padding(bottom = bottomNavigationHeight)){
					if (team is ApiResult.ApiSuccess){
						Column {
							TeamInformation(
								crest = (team as ApiResult.ApiSuccess<Team>).data.crest,
								stadium = (team as ApiResult.ApiSuccess<Team>).data.venue,
								teamName = (team as ApiResult.ApiSuccess<Team>).data.name
							)
							PlayerContent(team = (team as ApiResult.ApiSuccess<Team>).data)
						}
					}
					else
						LoadingAnimationView()
			}
		},
		topBar = { TopBar(navController = navController, viewModel = viewModel) },
		bottomBar = { BottomBar(navController = navController) }
	)
}