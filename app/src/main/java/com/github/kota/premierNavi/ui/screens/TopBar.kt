package com.github.kota.premierNavi.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun TopBar(
	navController: NavController
){
	TopAppBar(
		title = {
			Text(text = "PremierNavi")
		},
		navigationIcon = {
			val icon = Icons.Filled.Settings
			val onClick: () -> Unit = {}

			icon.let {
				IconButton(onClick = onClick) {
					Icon(imageVector = it, contentDescription = null)
				}
			}
		}
	)
}

@Composable
@Preview
fun TopBarPreview(){
	TopBar(navController = rememberNavController())
}