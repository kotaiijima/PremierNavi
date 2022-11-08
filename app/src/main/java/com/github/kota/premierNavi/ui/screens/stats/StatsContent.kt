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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
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
	val awayTeamCrest = rememberImagePainter(data = match.awayTeam.crest, builder = {
		decoder(SvgDecoder(LocalContext.current))
	})
	val homeTeamCrest = rememberImagePainter(data = match.homeTeam.crest, builder = {
		decoder(SvgDecoder(LocalContext.current))
	})

	val awayTeam = match.awayTeam.shortName
	val homeTeam = match.homeTeam.shortName
	val utcDate = ZonedDateTime.parse(match.utcDate).plusHours(9)
	val dateFormat = DateTimeFormatter.ofPattern("MM/dd")
	val dateToJapan = utcDate.format(dateFormat)
	val matchday = "第${match.matchday}節"

	val scoreOrTime = if (match.score.fullTime.home == null || match.score.fullTime.away == null){
		val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
		utcDate.format(timeFormat)
	}else{
		"${match.score.fullTime.home} - ${match.score.fullTime.away}"
	}

	Divider()
	Column(
		modifier = Modifier.padding(10.dp)
	) {
		Row{
			Text(
				fontSize = MaterialTheme.typography.subtitle2.fontSize,
				fontWeight = FontWeight.Bold,
				text = dateToJapan
			)
			Text(
				modifier = Modifier.padding(start = 10.dp),
				fontSize = MaterialTheme.typography.subtitle2.fontSize,
				fontWeight = FontWeight.Normal,
				text = matchday)
		}
		Row (
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.height(IntrinsicSize.Max)
		){
			Row(
				modifier = Modifier
					.wrapContentWidth(Alignment.Start)
					.weight(1f)
					.padding(top = 5.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Image(
					painter = homeTeamCrest,
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
					painter = awayTeamCrest,
					contentDescription = "home team crest",
					modifier = Modifier
						.size(40.dp)
						.padding(5.dp),
					contentScale = ContentScale.Fit
				)
			}
		}
	}
}