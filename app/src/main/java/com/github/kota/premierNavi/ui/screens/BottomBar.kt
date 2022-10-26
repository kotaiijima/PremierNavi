package com.github.kota.premierNavi.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.kota.premierNavi.R

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BottomBar(){
	val bottomMenuItemList = prepareBottomMenu()

	val selectedItem by remember {
		mutableStateOf("Home")
	}

	BottomNavigation {
		bottomMenuItemList.forEach { menuItem ->

			BottomNavigationItem(
				selected = (selectedItem == menuItem.label),
				onClick = {

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

	bottomMenuItemsList.add(BottomMenuItem(label = "Home", icon = painterResource(id = R.drawable.home)))
	bottomMenuItemsList.add(BottomMenuItem(label = "Results", icon = painterResource(id = R.drawable.sports_soccer)))
	bottomMenuItemsList.add(BottomMenuItem(label = "Stats", icon = painterResource(id = R.drawable.rank)))
	bottomMenuItemsList.add(BottomMenuItem(label = "Players", icon = painterResource(id = R.drawable.players)))

	return bottomMenuItemsList
}

data class BottomMenuItem(val label: String, val icon: Painter)

@RequiresApi(Build.VERSION_CODES.N)
@Composable
@Preview
fun BottomBarPreview(){
	BottomBar()
}