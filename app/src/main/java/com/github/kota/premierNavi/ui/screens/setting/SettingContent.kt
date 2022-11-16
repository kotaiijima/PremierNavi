package com.github.kota.premierNavi.ui.screens.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.utils.showCrest

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
	Row() {
		Image(painter = showCrest(crest = crest),
			contentDescription = null,
			modifier = Modifier.size(50.dp)
		)
		Text(
			text = teamName,
			fontSize = MaterialTheme.typography.h5.fontSize
		)
	}
}