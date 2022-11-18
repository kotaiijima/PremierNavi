package com.github.kota.premierNavi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.kota.premierNavi.navigation.SetupNavigation
import com.github.kota.premierNavi.ui.theme.PremierNaviTheme
import com.github.kota.premierNavi.ui.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	private lateinit var navController: NavHostController
	private val myViewModel: MyViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PremierNaviTheme {
				navController = rememberNavController()
				SetupNavigation(
					navController = navController,
					myViewModel = myViewModel
				)
			}
		}
	}
}