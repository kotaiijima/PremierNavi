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

@Composable
fun HomeNextGame(match: Match){
	val homeTeamCrest = rememberImagePainter(data = match.matches[0].homeTeam.crest)
	val awayTeamCrest = rememberImagePainter(data = match.matches[0].awayTeam.crest)

	val homeTeam = match.matches[0].homeTeam.shortName
	val awayTeam = match.matches[0].awayTeam.shortName

	lateinit var date:  ZonedDateTime
	lateinit var dateToString:  String

	val utcDate = match.matches[0].utcDate
	date = ZonedDateTime.parse(utcDate).plusHours(9)
	val dtf = DateTimeFormatter.ofPattern("MM/dd\nHH:mm")
	dateToString = date.format(dtf)

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(MaterialTheme.colors.background)
			.padding(top = 30.dp)
	) {
		Text(
			modifier = Modifier.padding(10.dp),
			fontSize = MaterialTheme.typography.h5.fontSize,
			fontWeight = FontWeight.Bold,
			text = "次の試合")
		Row(
			modifier = Modifier.padding(10.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			TeamCrestCard(
				crest = homeTeamCrest,
				name = homeTeam,
				modifier_column = Modifier
					.wrapContentWidth(Alignment.End)
					.weight(1f)
					.padding(end = 30.dp),
				modifier_image = Modifier
					.requiredSize(100.dp)
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
				modifier_column = Modifier
					.wrapContentWidth(Alignment.End)
					.weight(1f)
					.padding(end = 30.dp),
				modifier_image = Modifier
					.requiredSize(100.dp)
				)
		}
	}
}