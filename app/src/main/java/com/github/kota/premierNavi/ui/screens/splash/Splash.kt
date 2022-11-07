package com.github.kota.premierNavi.component

import androidx.compose.animation.core.AnimationState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.ui.viewmodel.MainViewModel
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN

@Composable
fun SplashAnimationView(
	navController: NavController,
){
	val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
	LottieAnimation(
		composition =  composition,
		modifier = Modifier.fillMaxSize(),
		iterations = LottieConstants.IterateForever
	)
//	Thread.sleep(3000)
//	navController.navigate(HOME_SCREEN)
}