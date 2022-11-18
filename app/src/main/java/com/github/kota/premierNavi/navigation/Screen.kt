package com.github.kota.premierNavi.navigation

import androidx.navigation.NavController

class Screen(navController: NavController){
	val team: (Int) -> Unit = { teamId ->
		navController.navigate( "team/$teamId")
	}
}
