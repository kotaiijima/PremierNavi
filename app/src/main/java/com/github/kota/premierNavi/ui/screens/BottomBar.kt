package com.github.kota.premierNavi.ui.screens

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN
import com.github.kota.premierNavi.utils.Constants.PLAYER_SCREEN
import com.github.kota.premierNavi.utils.Constants.RANK_SCREEN
import com.github.kota.premierNavi.utils.Constants.STATS_SCREEN

@Composable
fun BottomBar(
	navController: NavController
){
	val bottomMenuItemList = prepareBottomMenu()

	val selectedItem by remember {
		mutableStateOf(HOME_SCREEN)
	}

	BottomNavigation (){
		bottomMenuItemList.forEach { menuItem ->

			BottomNavigationItem(
				selected = (selectedItem == menuItem.label),
				onClick = {
						  navController.navigate(menuItem.label)
				},
				icon = {
					Icon(
						painter = menuItem.icon,
						contentDescription = menuItem.label)
				})
		}
	}
}

@Composable
private fun prepareBottomMenu(): List<BottomMenuItem> {
	val bottomMenuItemsList = arrayListOf<BottomMenuItem>()

	bottomMenuItemsList.add(BottomMenuItem(label = HOME_SCREEN, icon = painterResource(id = R.drawable.home)))
	bottomMenuItemsList.add(BottomMenuItem(label = STATS_SCREEN, icon = painterResource(id = R.drawable.sports_soccer)))
	bottomMenuItemsList.add(BottomMenuItem(label = RANK_SCREEN, icon = painterResource(id = R.drawable.rank)))
	bottomMenuItemsList.add(BottomMenuItem(label = PLAYER_SCREEN, icon = painterResource(id = R.drawable.players)))

	return bottomMenuItemsList
}

data class BottomMenuItem(val label: String, val icon: Painter)