package com.github.kota.premierNavi.ui.screens.initial

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.ui.theme.IMAGE_PADDING
import com.github.kota.premierNavi.ui.theme.LARGE_PADDING
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN
import com.github.kota.premierNavi.utils.RequestState

@Composable
fun InitialScreen(
	navController: NavController,
	rank: ApiResult<RankDomainModel>,
	addTeamId: suspend (Int) -> Unit,
	teamId: RequestState<List<TeamId>>
) {
	if (teamId is RequestState.Failure.Error) {
		Column(
			modifier = Modifier.fillMaxSize()
		) {
			Text(
				text = stringResource(id = R.string.initial_text),
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = IMAGE_PADDING)
					.padding(LARGE_PADDING),
				fontSize = MaterialTheme.typography.h4.fontSize,
				textAlign = TextAlign.Center
			)
			Box(
				modifier = Modifier.fillMaxWidth()
			) {
				if (rank is ApiResult.ApiSuccess)
					InitialContent(
						navController = navController,
						rank = rank.data,
						addTeamId = addTeamId,
					)
			}
		}
	} else if (teamId is RequestState.Success) {
		navController.navigate(HOME_SCREEN)
	}
}