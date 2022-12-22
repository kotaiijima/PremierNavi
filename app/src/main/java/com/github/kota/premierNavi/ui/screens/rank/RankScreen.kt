package com.github.kota.premierNavi.ui.screens.rank

import android.annotation.SuppressLint
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
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.utils.ApiResult

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RankScreen(
	navController: NavController,
	navigateToTeamDetail:(Int) -> Unit,
	rank: ApiResult<RankDomainModel>
) {
	val scaffoldState = rememberScaffoldState()
	Scaffold(
		scaffoldState = scaffoldState,
		content = {
			Column(
				modifier = Modifier.padding(bottom = bottomNavigationHeight)
				) {
				if (rank is ApiResult.ApiSuccess)
					RankContent(
						rank = (rank.data),
						navigateToTeamDetail
					)
			}
		},
		topBar = { TopBar(navController = navController) },
		bottomBar = { BottomBar(navController = navController) }
	)
}