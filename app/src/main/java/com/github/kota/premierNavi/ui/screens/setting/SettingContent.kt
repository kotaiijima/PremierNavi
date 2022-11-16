package com.github.kota.premierNavi.ui.screens.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.ui.theme.SMALL_IMAGE
import com.github.kota.premierNavi.utils.showCrest
import com.github.kota.premierNavi.R

@Composable
fun InitialContent(
	rank: Rank
){
	LazyColumn {
		items(rank.standings[0].table) {
			SettingItem(
				crest = it.team.crest,
				teamName = it.team.shortName
			)
		}
	}
}

@Composable
fun SettingItem(
	crest: String,
	teamName: String
){
	Row(
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.height(IntrinsicSize.Max)
	) {
		Image(painter = showCrest(crest = crest),
			contentDescription = stringResource(id = R.string.club_crest),
			modifier = Modifier.size(SMALL_IMAGE)
		)
		Text(
			text = teamName,
			fontSize = MaterialTheme.typography.h5.fontSize,
			textAlign = TextAlign.Center
		)
	}
}