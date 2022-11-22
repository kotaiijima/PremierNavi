package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.ui.theme.*
import com.github.kota.premierNavi.utils.showCrest
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeNextGame(
	match: Match,
	navigateToTeamDetail:(Int) -> Unit
){
	val homeTeam = match.matches[0].homeTeam.shortName
	val awayTeam = match.matches[0].awayTeam.shortName

	lateinit var date:  ZonedDateTime
	lateinit var dateToString:  String

	val utcDate = match.matches[0].utcDate
	date = ZonedDateTime.parse(utcDate).plusHours(9)
	val dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm")
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
			text = "${stringResource(id = R.string.nextGame_text)}: $dateToString")
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			TeamCrestCard(
				name = homeTeam,
				modifier = Modifier
					.wrapContentWidth(Alignment.Start)
					.weight(1f)
					.width(MEDIUM_IMAGE + IMAGE_PADDING + IMAGE_PADDING)
					.clickable { navigateToTeamDetail(match.matches[0].homeTeam.id) }
				,
				teamCrestCard = {
					Image(
						painter = showCrest(crest = match.matches[0].homeTeam.crest),
						contentDescription = stringResource(id = R.string.club_crest),
						modifier = Modifier.requiredSize(MEDIUM_IMAGE),
						contentScale = ContentScale.Fit
					)
				}
			)
			Text(
				fontSize = MaterialTheme.typography.h5.fontSize,
				fontWeight = FontWeight.Bold,
				textAlign = TextAlign.Center,
				text = ("-")
			)
			TeamCrestCard(
				name = awayTeam,
				modifier = Modifier
					.wrapContentWidth(Alignment.Start)
					.weight(1f)
					.width(MEDIUM_IMAGE + IMAGE_PADDING + IMAGE_PADDING)
					.clickable { navigateToTeamDetail(match.matches[0].awayTeam.id) }
				,
				teamCrestCard = {
					Image(
						painter = showCrest(crest = match.matches[0].awayTeam.crest),
						contentDescription = stringResource(id = R.string.club_crest),
						modifier = Modifier.requiredSize(MEDIUM_IMAGE),
						contentScale = ContentScale.Fit
					)
				}
			)
		}
	}
}