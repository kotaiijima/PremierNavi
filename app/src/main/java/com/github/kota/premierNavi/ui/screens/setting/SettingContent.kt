package com.github.kota.premierNavi.ui.screens.setting

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.github.kota.premierNavi.ui.theme.SMALL_IMAGE
import com.github.kota.premierNavi.utils.showCrest
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.component.SelectTeamDialog
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.ui.theme.MEDIUM_PADDING
import com.github.kota.premierNavi.utils.Constants.HOME_SCREEN
import com.github.kota.premierNavi.utils.translationToJapanese
import kotlinx.coroutines.launch

@Composable
fun SettingContent(
	navController: NavController,
	rank: RankDomainModel,
	updateTeamId: suspend (Int) -> Unit
) {
	val composableScope = rememberCoroutineScope()

	var teamName by remember { mutableStateOf("") }
	var openDialog by remember { mutableStateOf(false) }
	var teamId by remember { mutableStateOf(0) }

	SelectTeamDialog(
		title = stringResource(id = R.string.confirm_upload),
		message = stringResource(id = R.string.confirm_upload_message,
			translationToJapanese(EngTeamName = teamName)
		),
		openDialog = openDialog,
		closeDialog = { openDialog = false },
		onYesClicked = {
			Log.d("teamId", teamId.toString())
			composableScope.launch {
				updateTeamId(teamId)
			}
			navController.navigate(HOME_SCREEN)
		}
	)
		val context = LocalContext.current
	LazyColumn {

		items(rank.teams) {
			SettingItem(
				crest = showCrest(crest = it.crest),
				teamName = it.teamName,
				modifier = Modifier
					.fillMaxWidth()
					.height(IntrinsicSize.Max)
					.clickable {
						if (teamId != it.id) {
							teamName = it.teamName
							teamId = it.id
							openDialog = true
						} else
							Toast
								.makeText(context, "既に設定されています.", Toast.LENGTH_SHORT)
								.show()
					}
			)
		}
	}
}

@Composable
fun SettingItem(
	crest: Painter,
	teamName: String,
	modifier: Modifier
){
	Row(
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier
	) {
		Image(painter = crest,
			contentDescription = stringResource(id = R.string.club_crest),
			modifier = Modifier
				.padding(MEDIUM_PADDING)
				.padding(start = MEDIUM_PADDING)
				.requiredSize(SMALL_IMAGE)
		)
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = translationToJapanese(EngTeamName = teamName),
			fontSize = MaterialTheme.typography.h5.fontSize,
			textAlign = TextAlign.Center
		)
	}
	Divider()
}

@Composable
@Preview
fun SettingItemPreview() {
	SettingItem(
		crest = painterResource(id = R.drawable.players),
		teamName = stringResource(id = R.string.club_name),
		modifier = Modifier
	)
}