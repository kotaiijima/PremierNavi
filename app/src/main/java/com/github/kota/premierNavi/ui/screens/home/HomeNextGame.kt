package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.data.api.model.matchModel.Match

@Composable
fun HomeNextGame(match: Match?){
	val homeTeam = rememberImagePainter(data = match?.matches?.get(0)?.homeTeam?.crest)
	val awayTeam = rememberImagePainter(data = match?.matches?.get(0)?.awayTeam?.crest)
	
	Column() {
		Text(text = "次の試合:")
		Row() {
			Image(
				painter = homeTeam ,
				contentDescription = "home team icon",
				modifier = Modifier
					.size(200.dp),
				contentScale = ContentScale.Fit
			)
			Text(text = "-")
			Image(
				painter = awayTeam ,
				contentDescription = "away team icon",
				modifier = Modifier
					.size(200.dp),
				contentScale = ContentScale.Fit
			)
		}
	}
}