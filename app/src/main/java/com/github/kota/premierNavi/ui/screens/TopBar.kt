package com.github.kota.premierNavi.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.kota.premierNavi.R

@Composable
fun TopBar(
	navController: NavController
){
	TopAppBar(
		backgroundColor = colorResource(id = R.color.teal_700),
		contentColor = Color.White,
		title = {
			Text(text = "PremierNavi")
		},
		navigationIcon = {
			val icon = Icons.Filled.Menu
			val onClick: () -> Unit = {}

			icon.let {
				IconButton(onClick = onClick) {
					Icon(imageVector = it, contentDescription = null)
				}
			}
		}
	)
}

//@Composable
//@Preview
//fun TopBarPreview(){
//	TopBar(navController = rememberNavController())
//}