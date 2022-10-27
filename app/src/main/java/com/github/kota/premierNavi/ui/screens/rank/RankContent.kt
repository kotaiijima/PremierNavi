package com.github.kota.premierNavi.ui.screens.rank

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.api.model.rankingModel.Table

@Composable
fun RankContent(
	rank: Rank?
){
	LazyColumn(){
		items(rank?.standings?.get(0)?.table!!){
			RankItem(it)
		}
	}
}

@Composable
private fun RankItem(
	table: Table
){
	val clubCrest = rememberImagePainter(data = table.team.crest)
	Row() {
		Text(text = "${table.position}")
		Image(
			painter = clubCrest ,
			contentDescription = "club crest",
			modifier = Modifier
				.size(40.dp),
			contentScale = ContentScale.Fit
		)
		Text(text = table.team.name)
		Text(text = "${table.points}")
		Text(text = "${table.playedGames}")
		Text(text = "${table.won} | ${table.draw} | ${table.lost} | ${table.goalDifference}")
	}
}