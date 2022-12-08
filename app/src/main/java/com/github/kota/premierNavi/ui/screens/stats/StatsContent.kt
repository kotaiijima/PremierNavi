package com.github.kota.premierNavi.ui.screens.stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.github.kota.premierNavi.ui.theme.MEDIUM_PADDING
import com.github.kota.premierNavi.ui.theme.SMALL_PADDING
import com.github.kota.premierNavi.utils.showCrest
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.domain.model.MatchInformation
import com.github.kota.premierNavi.domain.model.Score
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.domain.model.TeamInformation
import com.github.kota.premierNavi.ui.theme.MEDIUM_IMAGE
import com.github.kota.premierNavi.ui.theme.SMALL_IMAGE
import com.github.kota.premierNavi.utils.convertCompetition
import com.github.kota.premierNavi.utils.translationToJapanese

@Composable
fun StatsContent(
	stats: StatsDomainModel,
	navigateToTeamDetail:(Int) -> Unit
){
	LazyColumn(
		modifier = Modifier
			.fillMaxWidth()
	){
		items(stats.matchInformation){
			StatsItem(
				match = it,
				homeCrest = showCrest(crest = it.homeTeam.crest),
				awayCrest = showCrest(crest = it.awayTeam.crest),
				navigateToTeamDetail =	navigateToTeamDetail
			)
		}
	}
}

@Composable
private fun StatsItem(
	match: MatchInformation,
	homeCrest: Painter,
	awayCrest: Painter,
	navigateToTeamDetail: (Int) -> Unit
) {
	val jstDate = ZonedDateTime.parse(match.matchDay).plusHours(9)
	val dateFormat = DateTimeFormatter.ofPattern("MM/dd")
	val dateToJapan = jstDate.format(dateFormat)
	val matchday = convertCompetition(
		competition = match.competition,
		round = match.competitionRound,
		section = match.section
	)

	val scoreOrTime = if (match.score.homeScore == null || match.score.awayScore == null) {
		val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
		jstDate.format(timeFormat)
	} else {
		"${match.score.homeScore} - ${match.score.awayScore}"
	}

	Divider()
	Column(
		modifier = Modifier.padding(top = MEDIUM_PADDING, start = MEDIUM_PADDING)
	) {
		Row{
			Text(
				fontSize = MaterialTheme.typography.subtitle2.fontSize,
				fontWeight = FontWeight.Bold,
				text = dateToJapan
			)
			Text(
				modifier = Modifier.padding(start = MEDIUM_PADDING),
				fontSize = MaterialTheme.typography.subtitle2.fontSize,
				fontWeight = FontWeight.Normal,
				text = matchday)
		}
		Row (
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.height(IntrinsicSize.Max)
				.padding(top = SMALL_PADDING)
		) {
			Row(
				modifier = Modifier
					.wrapContentWidth(Alignment.Start)
					.weight(1f)
					.clickable { navigateToTeamDetail(match.homeTeam.id) }
				,
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				Image(
					painter = homeCrest,
					contentDescription = stringResource(id = R.string.homeTeam_crest),
					modifier = Modifier
						.requiredSize(SMALL_IMAGE)
						.padding(SMALL_PADDING),
					contentScale = ContentScale.Fit
				)
				Text(
					modifier = Modifier
						.padding(start = SMALL_PADDING)
						.width(MEDIUM_IMAGE),
					text = translationToJapanese(EngTeamName = match.homeTeam.name),
					fontWeight = FontWeight.Normal,
					fontSize = MaterialTheme.typography.subtitle2.fontSize,
					maxLines = 2,
					overflow = TextOverflow.Ellipsis,
					textAlign = TextAlign.Start
				)
			}
			Text(
				text = scoreOrTime,
				fontSize = MaterialTheme.typography.h5.fontSize
			)
			Row(
				modifier = Modifier
					.wrapContentWidth(Alignment.End)
					.weight(1f)
					.clickable { navigateToTeamDetail(match.awayTeam.id) },
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					modifier = Modifier
						.padding(end = SMALL_PADDING)
						.width(MEDIUM_IMAGE),
					text = translationToJapanese(EngTeamName = match.awayTeam.name),
					fontWeight = FontWeight.Normal,
					fontSize = MaterialTheme.typography.subtitle2.fontSize,
					maxLines = 2,
					overflow = TextOverflow.Ellipsis,
					textAlign = TextAlign.End
				)
				Image(
					painter = awayCrest,
					contentDescription = stringResource(id = R.string.awayTeam_crest),
					modifier = Modifier
						.requiredSize(SMALL_IMAGE)
						.padding(SMALL_PADDING),
					contentScale = ContentScale.Fit
				)
			}
		}
	}
}

@Composable
@Preview
fun StatsItemPreview() {
	StatsItem(
		match = MatchInformation(
			matchDay = "2022-06-16T15:49:25Z",
			section = 1,
			competition = "PL",
			competitionRound = "REGULAR_SEASON",
			score = Score(
				homeScore = 1,
				awayScore = 1
			),
			homeTeam = TeamInformation(
				id = 1,
				name = stringResource(id = R.string.club_name),
				crest = ""
			),
			awayTeam = TeamInformation(
				id = 1,
				name = stringResource(id = R.string.club_name),
				crest = ""
			),
		),
		homeCrest = painterResource(id = R.drawable.players),
		awayCrest = painterResource(id = R.drawable.players),
		navigateToTeamDetail = {}
	)
}