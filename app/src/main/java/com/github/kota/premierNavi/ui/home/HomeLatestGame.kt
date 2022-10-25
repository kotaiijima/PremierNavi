package com.github.kota.premierNavi.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.data.api.model.latestGameModel.Team

@Composable
fun HomeLatestGame(team: Team?){
	val homeTeam = rememberImagePainter(data = team?.matches?.get(0)?.homeTeam?.crest)
	val awayTeam = rememberImagePainter(data = team?.matches?.get(0)?.awayTeam?.crest)

	val section = "第${team?.matches?.get(0)?.season?.currentMatchday}節"
	val matchResult = "${team?.matches?.get(0)?.score?.fullTime?.home} - ${team?.matches?.get(0)?.score?.fullTime?.away}"
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