package com.github.kota.premierNavi.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.data.api.model.latestGameModel.Team

@Composable
fun HomeScreen(){
	val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
	val latestGame by homeViewModel.latestGame.collectAsState()
//	val nextGame by homeViewModel.latestGame.collectAsState()

	Row() {
		HomeLatestGame(team = latestGame)
//		HomeNextGame(team = nextGame)
	}
}



@Composable
fun FootballTeamImageCard(team: Team?){
	val imagePainter = rememberImagePainter(data = team?.matches?.get(0)?.awayTeam?.crest)
	
	Card(
		shape = MaterialTheme.shapes.medium,
		modifier = Modifier.padding(16.dp)
	) {
		Box{
			Image(
				painter = imagePainter ,
				contentDescription = null,
				modifier = Modifier
					.fillMaxWidth()
					.height(200.dp),
				contentScale = ContentScale.FillBounds
			)

				Surface(
				color = MaterialTheme.colors.surface.copy(alpha = .3f),
				modifier = Modifier.align(Alignment.BottomCenter),
				contentColor = MaterialTheme.colors.surface
			) {
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.padding(4.dp)
				) {
					Text(text = "Team : ${team?.matches?.get(0)?.awayTeam?.name}")
				}
			}
		}
	}
}