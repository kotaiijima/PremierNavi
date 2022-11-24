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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.domain.TeamDomainObject
import com.github.kota.premierNavi.ui.theme.*
import com.github.kota.premierNavi.utils.showCrest
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeNextGame(
	homeTeam: TeamDomainObject,
	awayTeam: TeamDomainObject,
	navigateToTeamDetail:(Int) -> Unit
){
	Row(
		verticalAlignment = Alignment.CenterVertically
	) {
		TeamCrestCard(
			name = homeTeam.name,
			modifier = Modifier
				.wrapContentWidth(Alignment.Start)
				.weight(1f)
				.width(MEDIUM_IMAGE + IMAGE_PADDING + IMAGE_PADDING)
				.clickable { navigateToTeamDetail(homeTeam.id) }
			,
			teamCrestCard = {
				Image(
					painter = homeTeam.crest,
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
			name = awayTeam.name,
			modifier = Modifier
				.wrapContentWidth(Alignment.End)
				.weight(1f)
				.width(MEDIUM_IMAGE + IMAGE_PADDING + IMAGE_PADDING)
				.clickable { navigateToTeamDetail(awayTeam.id) }
			,
			teamCrestCard = {
				Image(
					painter = awayTeam.crest,
					contentDescription = stringResource(id = R.string.club_crest),
					modifier = Modifier.requiredSize(MEDIUM_IMAGE),
					contentScale = ContentScale.Fit
				)
			}
		)
	}
}

@Composable
@Preview
fun HomeNextGamePreview() {
	HomeNextGame(
		homeTeam = TeamDomainObject(
			name = "Arsenal",
			id = 0,
			crest = painterResource(id = R.drawable.players)
		),
		awayTeam = TeamDomainObject(
			name = "Man City",
			id = 0,
			crest = painterResource(id = R.drawable.players)
		),
		navigateToTeamDetail = {}
	)
}