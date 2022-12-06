package com.github.kota.premierNavi.ui.screens.animation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.github.kota.premierNavi.R

@Composable
fun SplashAnimationView(
	getApiData:(Int) -> Unit
){
	val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
	LottieAnimation(
		composition =  composition,
		modifier = Modifier.fillMaxSize(),
		iterations = LottieConstants.IterateForever
	)
}