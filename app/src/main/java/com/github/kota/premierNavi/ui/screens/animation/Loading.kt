package com.github.kota.premierNavi.ui.screens.animation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.github.kota.premierNavi.R
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun LoadingAnimationView(
	getApiData: suspend (Int) -> Unit,
	teamId: Int
) {

	var refreshing by remember { mutableStateOf(false) }

	LaunchedEffect(refreshing) {
		if (refreshing) run {
			getApiData(teamId)
		}
	}

	val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

	Column(
		modifier = Modifier.fillMaxSize()
	) {
		SwipeRefresh(
			state = rememberSwipeRefreshState(isRefreshing = refreshing),
			onRefresh = { refreshing = true }
		) {
			Column(
				verticalArrangement = Arrangement.Center,
				modifier = Modifier
					.fillMaxSize()
					.verticalScroll(rememberScrollState())
			) {
				LottieAnimation(
					composition =  composition,
					modifier = Modifier.fillMaxSize(),
					iterations = LottieConstants.IterateForever
				)
			}
		}
	}
}