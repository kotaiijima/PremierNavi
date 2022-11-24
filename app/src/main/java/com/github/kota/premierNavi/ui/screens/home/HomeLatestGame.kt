package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.domain.TeamDomainObject
import com.github.kota.premierNavi.ui.theme.*

@Composable
fun HomeLatestGame(
	matchResult: String,
	homeTeam: TeamDomainObject,
	awayTeam: TeamDomainObject,
	navigateToTeamDetail:(Int) -> Unit
){
		Row(
			modifier = Modifier
				.padding(top = LARGE_PADDING),
			verticalAlignment = Alignment.CenterVertically
		) {
			TeamCrestCard(
				name = homeTeam.name,
				modifier = Modifier
					.wrapContentWidth(Alignment.Start)
					.weight(1f)
					.width(MEDIUM_IMAGE + IMAGE_PADDING + IMAGE_PADDING)
					.clickable { navigateToTeamDetail(homeTeam.id) },
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
				modifier = Modifier.padding(top = LARGE_PADDING),
				text = matchResult,
				fontWeight = FontWeight.Bold,
				fontSize = MaterialTheme.typography.h4.fontSize,
				textAlign = TextAlign.Center
				)
			TeamCrestCard(
				name = awayTeam.name,
				modifier = Modifier
					.wrapContentWidth(Alignment.End)
					.weight(1f)
					.width(MEDIUM_IMAGE + IMAGE_PADDING + IMAGE_PADDING)
					.clickable { navigateToTeamDetail(awayTeam.id) },
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
fun HomeLatestGamePreview() {
	HomeLatestGame(
		matchResult = "4 - 2",
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