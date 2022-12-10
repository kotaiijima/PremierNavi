package com.github.kota.premierNavi.ui.screens.initial

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.github.kota.premierNavi.component.TeamCrestCard
import com.github.kota.premierNavi.utils.showCrest
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.component.SelectTeamDialog
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.domain.model.Team
import com.github.kota.premierNavi.ui.theme.*
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN
import com.github.kota.premierNavi.utils.translationToJapanese

@Composable
fun InitialContent(
	rank: RankDomainModel,
	addTeamId: (Int) -> Unit,
	navController: NavController
) {
	var selectedTeamId by rememberSaveable { mutableStateOf(0) }
	var openDialog by remember { mutableStateOf(false) }
	var selectedTeamName by remember { mutableStateOf("") }

	SelectTeamDialog(
		title = stringResource(id = R.string.confirm_add, translationToJapanese(EngTeamName = selectedTeamName)),
		openDialog = openDialog,
		closeDialog = { openDialog = false },
		onYesClicked = {
			Log.d("teamId", selectedTeamId.toString())
			addTeamId(selectedTeamId)
//			getTeamId()
			navController.navigate(HOME_SCREEN)
		}
	)
		Column(
			modifier = Modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			TeamCardRow(
				teams = rank.teams,
				start = 0,
				selectedTeamId = {
					selectedTeamId = it.first
					selectedTeamName = it.second
					openDialog = true
				}
			)
			TeamCardRow(
				teams = rank.teams,
				start = 4,
				selectedTeamId = {
					selectedTeamId = it.first
					selectedTeamName = it.second
					openDialog = true
				}			)
			TeamCardRow(
				teams = rank.teams,
				start = 8,
				selectedTeamId = {
					selectedTeamId = it.first
					selectedTeamName = it.second
					openDialog = true
				}
			)
			TeamCardRow(
				teams = rank.teams,
				start = 12,
				selectedTeamId = {
					selectedTeamId = it.first
					selectedTeamName = it.second
					openDialog = true
				}
			)
			TeamCardRow(
				teams = rank.teams,
				start = 16,
				selectedTeamId = {
					selectedTeamId = it.first
					selectedTeamName = it.second
					openDialog = true
				}
			)
		}
}

@Composable
fun TeamCardRow(
	teams: List<Team>,
	start: Int,
	selectedTeamId: (Pair<Int, String>) -> Unit
) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		var i = 0
		while (start + i <= start + 3) {
			InitialItem(
				teamId = teams[start + i].id,
				teamName = teams[start + i].teamName,
				crest = showCrest(crest = teams[start + i].crest),
				selectedTeamId = selectedTeamId
			)
			i++
		}
	}
}

@Composable
fun InitialItem(
	teamId: Int,
	teamName: String,
	crest: Painter,
	selectedTeamId: (Pair<Int, String>) -> Unit
) {
	TeamCrestCard(
		name = null,
		modifier = Modifier
			.clickable {
				selectedTeamId(
					Pair(
						teamId,
						teamName
					)
				)
			}
			.padding(MEDIUM_PADDING)
			.width(INITIAL_SCREEN_IMAGE)
		,
		teamCrestCard = {
			Image(
				painter = crest,
				contentDescription = stringResource(id = R.string.club_crest),
				modifier = Modifier.requiredSize(INITIAL_SCREEN_IMAGE),
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
		teamName = "Arsenal",
		crest = painterResource(id = R.drawable.players),
		selectedTeamId = {}
	)
}