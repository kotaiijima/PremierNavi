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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.github.kota.premierNavi.ui.theme.MEDIUM_PADDING
import com.github.kota.premierNavi.ui.theme.SMALL_PADDING
import com.github.kota.premierNavi.utils.showCrest
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.domain.model.MatchInformation
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.ui.theme.MEDIUM_IMAGE
import com.github.kota.premierNavi.ui.theme.SMALL_IMAGE
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
				navigateToTeamDetail =	navigateToTeamDetail
			)
		}
	}
}

@Composable
private fun StatsItem(
	match: MatchInformation,
	navigateToTeamDetail: (Int) -> Unit
){
	val awayTeam = match.awayTeam.name
	val homeTeam = match.homeTeam.name

	val jstDate = ZonedDateTime.parse(match.matchDay).plusHours(9)
	val dateFormat = DateTimeFormatter.ofPattern("MM/dd")
	val dateToJapan = jstDate.format(dateFormat)
	val matchday = when (match.competition) {
		"PL" ->	"プレミアリーグ 第${match.section}節"
		"CL" ->
			when (match.competitionRound){
				"LAST_16" -> "チャンピオンズリーグ ラウンド１６"
				"LAST_8" -> "チャンピオンズリーグ 準々決勝"
				"LAST_4" -> "チャンピオンズリーグ 準決勝"
				"LAST_2" -> "チャンピオンズリーグ 決勝"
				else -> "チャンピオンズリーグ 第${match.section}節"
			}
		else -> ""
	}



	val scoreOrTime = if (match.score.homeScore == null || match.score.awayScore == null){
		val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
		jstDate.format(timeFormat)
	}else{
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
		){
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
					painter = showCrest(crest = match.homeTeam.crest),
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
					text = translationToJapanese(EngTeamName = homeTeam),
					fontWeight = FontWeight.Normal,
					fontSize = MaterialTheme.typography.subtitle2.fontSize,
					maxLines = 2,
					overflow = TextOverflow.Ellipsis
				)
			}
			Text(text = scoreOrTime)
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
					text = translationToJapanese(EngTeamName = awayTeam),
					fontWeight = FontWeight.Normal,
					fontSize = MaterialTheme.typography.subtitle2.fontSize,
					maxLines = 2,
					overflow = TextOverflow.Ellipsis
				)
				Image(
					painter = showCrest(crest = match.awayTeam.crest),
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