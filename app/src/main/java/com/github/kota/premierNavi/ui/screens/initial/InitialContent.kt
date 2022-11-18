package com.github.kota.premierNavi.ui.screens.initial

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.ui.theme.IMAGE_PADDING
import com.github.kota.premierNavi.ui.theme.LARGE_IMAGE
import com.github.kota.premierNavi.ui.viewmodel.ViewModel
import com.github.kota.premierNavi.utils.showCrest

@Composable
fun InitialContent(
	rank: Rank,
	viewModel: ViewModel
){
	LazyRow(
		modifier = Modifier.fillMaxSize(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	){
		items(rank.standings[0].table){
			InitialItem(
				teamId = it.team.id,
				crest = it.team.crest,
				teamName = it.team.shortName,
				viewModel = viewModel,
			)
		}
	}
}

@Composable
fun InitialItem(
	teamId: Int,
	crest: String,
	teamName: String,
	viewModel: ViewModel,
){
	TeamCrestCard(
		crest = showCrest(crest = crest),
		name = teamName,
		modifier_column = Modifier
			.padding(IMAGE_PADDING)
			.clickable(onClick = {
				viewModel.addTeamId(teamId)
			}
			),
		modifier_image = Modifier.requiredSize(LARGE_IMAGE )
	)
}

