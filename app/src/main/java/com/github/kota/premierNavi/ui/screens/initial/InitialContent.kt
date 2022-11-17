package com.github.kota.premierNavi.ui.screens.initial

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.ui.theme.MEDIUM_IMAGE
import com.github.kota.premierNavi.ui.theme.MEDIUM_PADDING
import com.github.kota.premierNavi.ui.viewmodel.ViewModel
import com.github.kota.premierNavi.utils.Constants
import com.github.kota.premierNavi.utils.showCrest

@Composable
fun InitialContent(
	rank: Rank,
	viewModel: ViewModel,
	navController: NavController
){
	LazyRow{
		items(rank.standings[0].table){
			InitialItem(
				teamId = it.team.id,
				crest = it.team.crest,
				teamName = it.team.shortName,
				viewModel = viewModel,
				navController = navController
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
	navController: NavController
){
	TeamCrestCard(
		crest = showCrest(crest = crest),
		name = teamName,
		modifier_column = Modifier
			.padding(MEDIUM_PADDING)
			.clickable(onClick = {
				viewModel.addTeamId(teamId)
			}
			),
		modifier_image = Modifier.requiredSize(MEDIUM_IMAGE)
	)
}

