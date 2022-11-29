package com.github.kota.premierNavi.ui.screens.initial

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.rankingModel.ApiRank
import com.github.kota.premierNavi.ui.theme.IMAGE_PADDING
import com.github.kota.premierNavi.ui.theme.LARGE_IMAGE
import com.github.kota.premierNavi.utils.showCrest
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.domain.model.RankDomainModel

@Composable
fun InitialContent(
	rank: RankDomainModel,
	addTeamId: (Int) -> Unit,
) {
	LazyRow(
		modifier = Modifier.fillMaxSize(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		items(rank.teams){
			InitialItem(
				teamId = it.id,
				crest = showCrest(crest = it.crest),
				teamName = it.teamName,
				addTeamId = addTeamId,
			)
		}
	}
}

@Composable
fun InitialItem(
	teamId: Int,
	crest: Painter,
	teamName: String,
	addTeamId: (Int) -> Unit,
){
	TeamCrestCard(
		name = teamName,
		modifier = Modifier
			.padding(IMAGE_PADDING)
			.clickable(onClick = {
				addTeamId(teamId)
			}),
		teamCrestCard = {
			Image(
				painter = crest,
				contentDescription = stringResource(id = R.string.club_crest),
				modifier = Modifier.requiredSize(LARGE_IMAGE),
				contentScale = ContentScale.Fit
			)
		}
	)
}

@Composable
@Preview
fun InitialContentPreview() {
	InitialItem(
		teamId = 0,
		crest = painterResource(id = R.drawable.players),
		teamName = "Arsenal",
		addTeamId = {}
	)
}