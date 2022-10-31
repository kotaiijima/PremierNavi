package com.github.kota.premierNavi.ui.screens.home

import android.icu.text.MessageFormat.format
import android.os.Build
import android.text.format.DateFormat.format
import android.text.format.DateFormat.getBestDateTimePattern
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.ui.theme.LARGE_PADDING
import java.lang.String.format
import java.text.DateFormat
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNextGame(match: Match?){
	val homeTeamCrest = rememberImagePainter(data = match?.matches?.get(0)?.homeTeam?.crest)
	val awayTeamCrest = rememberImagePainter(data = match?.matches?.get(0)?.awayTeam?.crest)

	val homeTeam = match?.matches?.get(0)?.homeTeam?.shortName
	val awayTeam = match?.matches?.get(0)?.awayTeam?.shortName

	lateinit var date:  ZonedDateTime
	lateinit var dateToString:  String

	val utcDate = match?.matches?.get(0)?.utcDate
	if (utcDate != null){
		date = ZonedDateTime.parse(utcDate).plusHours(9)
		val dtf = DateTimeFormatter.ofPattern("MM/dd\nHH:mm")
		dateToString = date.format(dtf)
	} else{
		dateToString = "null"
	}

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(MaterialTheme.colors.background)
			.padding(top = 30.dp)
	) {
		Text(
			fontSize = MaterialTheme.typography.h5.fontSize,
			fontWeight = FontWeight.Bold,
			text = "次の試合:")
		Row(
			modifier = Modifier.padding(10.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			TeamCrestCard(
				crest = homeTeamCrest,
				name = homeTeam,
				modifier = Modifier
					.wrapContentWidth(Alignment.Start)
					.weight(1f)
					.padding(start = 20.dp)
					.width(IntrinsicSize.Max),

			)
			Text(
				fontSize = MaterialTheme.typography.h5.fontSize,
				fontWeight = FontWeight.Bold,
				textAlign = TextAlign.Center,
				text = (dateToString)
			)
			TeamCrestCard(
				crest = awayTeamCrest,
				name = awayTeam,
				modifier = Modifier
					.wrapContentWidth(Alignment.End)
					.weight(1f)
					.padding(end = 20.dp)
					.width(IntrinsicSize.Max)
				)
		}
	}
}