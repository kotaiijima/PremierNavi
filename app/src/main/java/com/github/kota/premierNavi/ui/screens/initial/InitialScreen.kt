package com.github.kota.premierNavi.ui.screens.initial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.ui.screens.animation.LoadingAnimationView
import com.github.kota.premierNavi.ui.screens.rank.RankContent
import com.github.kota.premierNavi.ui.theme.bottomNavigationHeight
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.R


@Composable
fun InitialScreen(
	navController: NavController,
	viewModel: MainViewModel
){
	val rank by viewModel.rank.collectAsState()

	Column(
		modifier = Modifier.fillMaxSize()
	) {
		Text(
			text = stringResource(id = R.string.initial_text),
			modifier = Modifier.fillMaxWidth(),
			fontSize = MaterialTheme.typography.h2.fontSize,
			textAlign = TextAlign.Center
		)
		Box(
			modifier = Modifier.fillMaxWidth()
		) {
			if (rank is ApiResult.ApiSuccess)
				InitialContent(rank = (rank as ApiResult.ApiSuccess<Rank>).data)
			else
				LoadingAnimationView()
		}
	}
}