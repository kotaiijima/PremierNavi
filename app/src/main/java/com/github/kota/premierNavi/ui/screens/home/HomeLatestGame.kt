package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.data.api.model.matchModel.Match

@Composable
fun HomeLatestGame(match: Match?){
	val homeTeam = rememberImagePainter(data = match?.matches?.get(0)?.homeTeam?.crest)
	val awayTeam = rememberImagePainter(data = match?.matches?.get(0)?.awayTeam?.crest)

	val section = "第${match?.matches?.get(0)?.season?.currentMatchday}節"
	val matchResult = "${match?.matches?.get(0)?.score?.fullTime?.home} - ${match?.matches?.get(0)?.score?.fullTime?.away}"
	Column() {
		Text(text = section)
		Row() {
			Image(
				painter = homeTeam ,
				contentDescription = "home team icon",
				modifier = Modifier
					.size(200.dp),
				contentScale = ContentScale.Fit
			)
			Text(text = matchResult)
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