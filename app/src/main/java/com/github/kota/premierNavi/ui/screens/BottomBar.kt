package com.github.kota.premierNavi.ui.screens


import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN
import com.github.kota.premierNavi.utils.Constants.TEAM_SCREEN
import com.github.kota.premierNavi.utils.Constants.RANK_SCREEN
import com.github.kota.premierNavi.utils.Constants.STATS_SCREEN

@Composable
fun BottomBar(
	navController: NavController,

){
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination
	val bottomMenuItemList = prepareBottomMenu()

	BottomNavigation (
		backgroundColor = colorResource(id = R.color.teal_700),
		contentColor = Color.White,
			){
		bottomMenuItemList.forEach { menuItem ->
			BottomNavigationItem(
				icon = {
					Icon(
						painter = menuItem.icon,
						contentDescription = menuItem.label)
				},
				label = { Text(text = menuItem.label) },
				alwaysShowLabel = false,
				selectedContentColor = Color.White,
				unselectedContentColor = Color.White.copy(0.4f),
				selected = currentDestination?.hierarchy?.any{
					it.route == menuItem.label
				} == true,
				onClick = {
					navController.navigate(menuItem.label)
						  },
			)
		}
	}
}

@Composable
private fun prepareBottomMenu(): List<BottomMenuItem> {
	val bottomMenuItemsList = arrayListOf<BottomMenuItem>()

	bottomMenuItemsList.add(BottomMenuItem(label = HOME_SCREEN, icon = painterResource(id = R.drawable.home)))
	bottomMenuItemsList.add(BottomMenuItem(label = STATS_SCREEN, icon = painterResource(id = R.drawable.sports_soccer)))
	bottomMenuItemsList.add(BottomMenuItem(label = RANK_SCREEN, icon = painterResource(id = R.drawable.rank)))
	bottomMenuItemsList.add(BottomMenuItem(label = TEAM_SCREEN, icon = painterResource(id = R.drawable.players)))

	return bottomMenuItemsList
}

data class BottomMenuItem(val label: String, val icon: Painter)