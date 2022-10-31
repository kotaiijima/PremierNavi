package com.github.kota.premierNavi.ui.screens.players

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.data.api.model.teamModel.Squad
import com.github.kota.premierNavi.data.api.model.teamModel.Team

@Composable
fun PlayerContent (team: Team?){
	LazyColumn(){
			items(team?.squad!!){
				PlayerItem(it)
			}
		}
	Divider()
}

@Composable
fun PlayerItem(
	squad: Squad
){
	Divider()
	Row {
		Image(
			modifier = Modifier.size(50.dp),
			painter = painterResource(id = R.drawable.players),
			contentDescription = "player icon"
		)
		Column(
			modifier = Modifier.fillMaxWidth()
		) {
			Text(
				modifier = Modifier.padding(),
				fontSize = MaterialTheme.typography.h6.fontSize,
				fontWeight = FontWeight.Bold,
				text = squad.name)
			Text(
				modifier = Modifier.padding(),
				fontSize = MaterialTheme.typography.subtitle1.fontSize,
				fontWeight = FontWeight.Normal,
				text = squad.position)
		}
	}
}