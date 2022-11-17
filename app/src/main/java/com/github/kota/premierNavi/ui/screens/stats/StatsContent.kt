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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.text.buildSpannedString
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.github.kota.premierNavi.data.api.model.statsModel.Match
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.ui.theme.MEDIUM_PADDING
import com.github.kota.premierNavi.ui.theme.SMALL_PADDING
import com.github.kota.premierNavi.utils.showCrest
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.ui.theme.LARGE_PADDING
import com.github.kota.premierNavi.ui.theme.SMALL_IMAGE
import com.github.kota.premierNavi.utils.translationToJapanese

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

@Composable
private fun StatsItem(match: Match){
	val awayTeam = match.awayTeam.shortName
	val homeTeam = match.homeTeam.shortName

	val jstDate = ZonedDateTime.parse(match.utcDate).plusHours(9)
	val dateFormat = DateTimeFormatter.ofPattern("MM/dd")
	val dateToJapan = jstDate.format(dateFormat)
	val matchday = "第${match.matchday}節"

	val scoreOrTime = if (match.score.fullTime.home == null || match.score.fullTime.away == null){
		val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
		jstDate.format(timeFormat)
	}else{
		"${match.score.fullTime.home} - ${match.score.fullTime.away}"
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
					.padding(start = SMALL_PADDING),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				Image(
					painter = showCrest(crest = match.homeTeam.crest),
					contentDescription = stringResource(id = R.string.homeTeam_crest),
					modifier = Modifier
						.size(SMALL_IMAGE)
						.padding(SMALL_PADDING),
					contentScale = ContentScale.Fit
				)
				Text(
					modifier = Modifier.padding(start = SMALL_PADDING),
					text = translationToJapanese(EngTeamName = homeTeam),
					fontWeight = FontWeight.Normal,
					fontSize = MaterialTheme.typography.subtitle2.fontSize
				)
			}
			Text(text = scoreOrTime)
			Row(
				modifier = Modifier
					.wrapContentWidth(Alignment.End)
					.weight(1f)
					.padding(end = SMALL_PADDING),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					modifier = Modifier.padding(end = SMALL_PADDING),
					text = translationToJapanese(EngTeamName = awayTeam),
					fontWeight = FontWeight.Normal,
					fontSize = MaterialTheme.typography.subtitle2.fontSize
				)
				Image(
					painter = showCrest(crest = match.awayTeam.crest),
					contentDescription = stringResource(id = R.string.awayTeam_crest),
					modifier = Modifier
						.size(SMALL_IMAGE)
						.padding(SMALL_PADDING),
					contentScale = ContentScale.Fit
				)
			}
		}
	}
}