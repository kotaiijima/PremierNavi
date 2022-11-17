package com.github.kota.premierNavi.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.ui.viewmodel.ViewModel
import com.github.kota.premierNavi.utils.Constants.SETTING_SCREEN

@Composable
fun TopBar(
	navController: NavController,
	viewModel: ViewModel
){
	TopAppBar(
		backgroundColor = colorResource(id = R.color.teal_700),
		contentColor = Color.White,
		title = {
			Text(text = "PremierNavi")
		},
		actions = {
			SettingMenu(navController = navController)
		}
	)
}

@Composable
fun SettingMenu(
	navController: NavController
){
	var expanded by remember { mutableStateOf(false) }
	IconButton(onClick = { expanded = true }) {
		Icon(imageVector = Icons.Filled.Menu, contentDescription = stringResource(id = R.string.menu_description))
		DropdownMenu(
			expanded = expanded,
			onDismissRequest = { expanded = false }
		) {
			DropdownMenuItem(onClick = {
				expanded = false
				navController.navigate(SETTING_SCREEN)
			}){
				Text(text = stringResource(id = R.string.setting_dropdown))
			}
		}
	}
}

//@Composable
//@Preview
//fun TopBarPreview(){
//	TopBar(navController = rememberNavController())
//}