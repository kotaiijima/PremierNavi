package com.github.kota.premierNavi.ui.screens.setting

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.ui.theme.SMALL_IMAGE
import com.github.kota.premierNavi.utils.showCrest
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.ui.theme.MEDIUM_PADDING
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN

@Composable
fun SettingContent(
	navController: NavController,
	rank: Rank,
	updateTeamId: (Int) -> Unit
){
	LazyColumn {
		items(rank.standings[0].table) {
			SettingItem(
				crest = it.team.crest,
				teamName = it.team.shortName,
				teamId = it.team.id,
				navController = navController,
				updateTeamId = updateTeamId
			)
		}
	}
}

@Composable
fun SettingItem(
	navController: NavController,
	crest: String,
	teamName: String,
	teamId : Int,
	updateTeamId:(Int) -> Unit
){
	Row(
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.height(IntrinsicSize.Max)
			.clickable(onClick = {
				updateTeamId(teamId)
				navController.navigate(HOME_SCREEN) })
	) {
		Image(painter = showCrest(crest = crest),
			contentDescription = stringResource(id = R.string.club_crest),
			modifier = Modifier
				.requiredSize(SMALL_IMAGE)
				.padding(MEDIUM_PADDING)
				.padding(start = MEDIUM_PADDING)
		)
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = teamName,
			fontSize = MaterialTheme.typography.h6.fontSize,
			textAlign = TextAlign.Center
		)
	}
	Divider()
}