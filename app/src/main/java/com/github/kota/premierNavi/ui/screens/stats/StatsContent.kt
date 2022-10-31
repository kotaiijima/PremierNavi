package com.github.kota.premierNavi.ui.screens.stats

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.statsModel.Match
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatsContent(
	stats: Stats?
){
	LazyColumn(
		modifier = Modifier
			.fillMaxWidth()
	){
		items(stats?.matches!!){
			StatsItem(it)
		}
	}
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun StatsItem(match: Match){
	val awayTeamCrest = rememberImagePainter(data = match.awayTeam.crest)
	val homeTeamCrest = rememberImagePainter(data = match.homeTeam.crest)

	val awayTeam = match.awayTeam.shortName
	val homeTeam = match.homeTeam.shortName

	lateinit var scoreOrTime: String
	lateinit var date: ZonedDateTime
	if (match.score.fullTime.home == null || match.score.fullTime.away == null){
		date = ZonedDateTime.parse(match.utcDate).plusHours(9)
		val dtf = DateTimeFormatter.ofPattern("HH:mm")
		scoreOrTime = date.format(dtf)
	}else{
		scoreOrTime = "${match.score.fullTime.home} - ${match.score.fullTime.away}"
	}

	Divider()
	Row (verticalAlignment = Alignment.CenterVertically){

		Row(
			modifier = Modifier
				.wrapContentWidth(Alignment.Start)
				.weight(1f)
				.padding(start = 20.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Image(
				painter = homeTeamCrest ,
				contentDescription = "home team crest",
				modifier = Modifier
					.size(40.dp)
					.padding(5.dp),
				contentScale = ContentScale.Fit
			)
			Text(
				modifier = Modifier.padding(start = 5.dp),
				text = homeTeam,
				fontWeight = FontWeight.Normal,
				fontSize = MaterialTheme.typography.subtitle2.fontSize
			)
		}
		Text(text = scoreOrTime)
		Row(
			modifier = Modifier
				.wrapContentWidth(Alignment.End)
				.weight(1f)
				.padding(end = 20.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				modifier = Modifier.padding(end = 5.dp),
				text = awayTeam,
				fontWeight = FontWeight.Normal,
				fontSize = MaterialTheme.typography.subtitle2.fontSize
			)
			Image(
				painter = awayTeamCrest ,
				contentDescription = "home team crest",
				modifier = Modifier
					.size(40.dp)
					.padding(5.dp),
				contentScale = ContentScale.Fit
			)
		}
	}
}