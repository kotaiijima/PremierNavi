package com.github.kota.premierNavi.navigation

import androidx.navigation.NavHostController

class Screens (navController: NavHostController){
	val home  = {
		navController.navigate( "home")
	}

	val players = {
		navController.navigate( "players")
	}
}

