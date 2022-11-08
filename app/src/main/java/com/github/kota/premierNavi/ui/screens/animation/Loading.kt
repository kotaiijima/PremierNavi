package com.github.kota.premierNavi.ui.screens.animation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.github.kota.premierNavi.R

@Composable
fun LoadingAnimationView(
){
	val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
	LottieAnimation(
		composition =  composition,
		modifier = Modifier.fillMaxSize(),
		iterations = LottieConstants.IterateForever
	)
}