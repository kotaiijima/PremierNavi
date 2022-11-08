package com.github.kota.premierNavi.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.utils.ApiResult
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeLatestGame(match: Match){

	val homeTeamCrest = rememberImagePainter(data = match.matches[0].homeTeam.crest)
	val awayTeamCrest = rememberImagePainter(data = match.matches[0].awayTeam.crest)

	val section = "第${match.matches[0].season.currentMatchday}節"
	val matchResult = "${match.matches[0].score.fullTime?.home} - ${match.matches[0].score.fullTime?.away}"

	val homeTeam = match.matches[0].homeTeam.shortName
	val awayTeam = match.matches[0].awayTeam.shortName

	lateinit var date: ZonedDateTime
	lateinit var dateToString:  String
	val utcDate = match.matches[0].utcDate
	date = ZonedDateTime.parse(utcDate).plusHours(9)
	val dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm")
	dateToString = date.format(dtf)

	Column() {
		Text(
			modifier = Modifier
				.padding(20.dp),
			fontWeight = FontWeight.Normal,
			fontSize = MaterialTheme.typography.h5.fontSize ,
			text = "-- 直近の成績 --")
		Row(modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp)) {
			Text(
				modifier = Modifier.padding(end = 5.dp),
				fontWeight = FontWeight.Bold,
				fontSize = MaterialTheme.typography.h5.fontSize ,
				text = "$section: ")
			Text(
				fontWeight = FontWeight.Bold,
				fontSize = MaterialTheme.typography.h5.fontSize ,
				text = dateToString)
		}
		Row(
			modifier = Modifier
				.padding(top = 30.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			TeamCrestCard(
				crest = homeTeamCrest,
				name = homeTeam,
				modifier = Modifier
					.wrapContentWidth(Alignment.Start)
					.weight(1f)
					.padding(start = 30.dp)
			)
			Text(
				modifier = Modifier.padding(top = 20.dp),
				text = matchResult,
				fontWeight = FontWeight.Bold,
				fontSize = MaterialTheme.typography.h4.fontSize,
				textAlign = TextAlign.Center
				)
			TeamCrestCard(
				crest = awayTeamCrest,
				name = awayTeam,
				modifier = Modifier
					.wrapContentWidth(Alignment.End)
					.weight(1f)
					.padding(end = 30.dp)
			)
		}
	}
}
