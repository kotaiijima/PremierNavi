package com.github.kota.premierNavi.ui.screens.players

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
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
}

@Composable
fun PlayerItem(
	squad: Squad
){
	Row() {
		Image(painter = painterResource(id = R.drawable.players), contentDescription = "player icon")
		Column() {
			Text(text = squad.name)
			Text(text = squad.position)
		}
	}
}