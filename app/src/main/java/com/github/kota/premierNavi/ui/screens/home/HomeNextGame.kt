package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.ui.theme.*
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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
			.padding(top = LARGE_PADDING)
	) {
		Text(
			modifier = Modifier.padding(LARGE_PADDING),
			fontSize = MaterialTheme.typography.h5.fontSize,
			fontWeight = FontWeight.Bold,
			text = stringResource(id = R.string.nextGame_text))
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			TeamCrestCard(
				crest = homeTeamCrest,
				name = homeTeam,
				modifier_column = Modifier
					.wrapContentWidth(Alignment.Start)
					.weight(1f)
					.width(MEDIUM_IMAGE + IMAGE_PADDING + IMAGE_PADDING),
				modifier_image = Modifier
					.requiredSize(MEDIUM_IMAGE)
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
					.width(MEDIUM_IMAGE + IMAGE_PADDING + IMAGE_PADDING),
				modifier_image = Modifier
					.requiredSize(MEDIUM_IMAGE)
				)
		}
	}
}