package com.github.kota.premierNavi.ui.screens.stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.data.api.model.statsModel.Matche
import com.github.kota.premierNavi.data.api.model.statsModel.Stats

@Composable
fun StatsContent(
	stats: Stats?
){
	LazyColumn(){
		items(stats?.matches!!){
			StatsItem(it)
		}
	}
}

@Composable
private fun StatsItem(matche: Matche){
	val awayTeamCrest = rememberImagePainter(data = matche.awayTeam.crest)
	val homeTeamCrest = rememberImagePainter(data = matche.homeTeam.crest)

	Row() {
		Image(
			painter = homeTeamCrest ,
			contentDescription = "home team crest",
			modifier = Modifier
				.size(40.dp),
			contentScale = ContentScale.Fit
		)
		 Text(text = "${matche.score.fullTime.home} - ${matche.score.fullTime.away}")
		Image(
			painter = awayTeamCrest ,
			contentDescription = "away team crest",
			modifier = Modifier
				.size(40.dp),
			contentScale = ContentScale.Fit
		)
	}
}