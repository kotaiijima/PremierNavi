package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.ui.theme.LARGE_PADDING
import com.github.kota.premierNavi.ui.theme.MEDIUM_PADDING
import com.github.kota.premierNavi.ui.theme.SMALL_PADDING
import com.github.kota.premierNavi.utils.showCrest
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeLatestGame(match: Match){
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

	val visible by remember { mutableStateOf(true) }
	val density = LocalDensity.current

	Column {
		AnimatedVisibility(
			visible = visible,
			enter = slideInVertically {
				// Slide in from 40 dp from the top.
				with(density) { -40.dp.roundToPx() }
			} + expandVertically(
				// Expand from the top.
				expandFrom = Alignment.Top
			) + fadeIn(
				// Fade in with the initial alpha of 0.3f.
				initialAlpha = 0.3f
			),
			exit = slideOutVertically() + shrinkVertically() + fadeOut()
		) {
			Text(
				modifier = Modifier
					.padding(20.dp),
				fontWeight = FontWeight.Normal,
				fontSize = MaterialTheme.typography.h5.fontSize ,
				text = stringResource(id = R.string.latestGame_text)
			)
		}
		Row(modifier = Modifier
			.fillMaxWidth()
			.padding(MEDIUM_PADDING)) {
			Text(
				modifier = Modifier.padding(end = SMALL_PADDING),
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
				.padding(top = LARGE_PADDING),
			verticalAlignment = Alignment.CenterVertically
		) {
			TeamCrestCard(
				crest = showCrest(crest = match.matches[0].homeTeam.crest),
				name = homeTeam,
				modifier_column = Modifier
					.wrapContentWidth(Alignment.Start)
					.weight(1f)
					.padding(start = LARGE_PADDING),
				modifier_image = Modifier
					.requiredSize(MEDIUM_PADDING)
			)
			Text(
				modifier = Modifier.padding(top = LARGE_PADDING),
				text = matchResult,
				fontWeight = FontWeight.Bold,
				fontSize = MaterialTheme.typography.h4.fontSize,
				textAlign = TextAlign.Center
				)
			TeamCrestCard(
				crest = showCrest(crest = match.matches[0].awayTeam.crest),
				name = awayTeam,
				modifier_column = Modifier
					.wrapContentWidth(Alignment.End)
					.weight(1f)
					.padding(end = LARGE_PADDING),
				modifier_image = Modifier
					.requiredSize(MEDIUM_PADDING)
			)
		}
	}
}
