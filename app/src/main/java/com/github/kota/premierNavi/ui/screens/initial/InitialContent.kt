package com.github.kota.premierNavi.ui.screens.initial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.utils.showCrest

@Composable
fun InitialContent(
	rank: Rank
){
	LazyColumn{
		items(rank.standings[0].table){
			InitialItem(teamId = it.team.id, crest = it.team.crest, teamName = it.team.shortName)
		}
	}
}

@Composable
fun InitialItem(
	teamId: Int,
	crest: String,
	teamName: String
){
	TeamCrestCard(
		crest = showCrest(crest = crest),
		name = teamName,
		modifier_column = Modifier.padding(10.dp),
		modifier_image = Modifier.requiredSize(100.dp)
	)
}