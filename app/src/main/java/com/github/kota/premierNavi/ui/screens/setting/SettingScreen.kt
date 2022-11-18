package com.github.kota.premierNavi.ui.screens.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.ui.screens.BottomBar
import com.github.kota.premierNavi.ui.screens.TopBar
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.ui.viewmodel.MyViewModel
import com.github.kota.premierNavi.utils.ApiResult

@Composable
fun SettingScreen(
	navController: NavController,
	myViewModel: MyViewModel
){
	val rank by myViewModel.rank.collectAsState()

	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Box(modifier = Modifier.padding(bottom = bottomNavigationHeight)){
				if (rank is ApiResult.ApiSuccess){
					Column {
						SettingContent(
							rank = (rank as ApiResult.ApiSuccess<Rank>).data,
							navController = navController,
							myViewModel = myViewModel
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