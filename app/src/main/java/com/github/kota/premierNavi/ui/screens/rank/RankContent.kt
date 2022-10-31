package com.github.kota.premierNavi.ui.screens.rank

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
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
	Divider()
}

@Composable
private fun RankItem(
	table: Table
){
	val clubCrest = rememberImagePainter(data = table.team.crest, builder = {
		decoder(SvgDecoder(LocalContext.current))
	})
	Divider()
	Row (
		modifier = Modifier.height(IntrinsicSize.Min),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
			){
		Text(
			modifier = Modifier.weight(1f),
			textAlign = TextAlign.Center,
			text = "${table.position}"
		)
		Divider(
			modifier = Modifier.fillMaxHeight().width(1.dp)
		)
		Image(
			painter = clubCrest ,
			contentDescription = "club crest",
			modifier = Modifier
				.size(40.dp)
				.padding(5.dp),
			contentScale = ContentScale.Fit
		)
		Divider(
			modifier = Modifier.fillMaxHeight().width(1.dp)
		)
		Text(
			textAlign = TextAlign.Center,
			modifier = Modifier.weight(4f),
			text = table.team.shortName)
		Divider(
			modifier = Modifier.fillMaxHeight().width(1.dp)
		)
		Text(
			textAlign = TextAlign.Center,
			modifier = Modifier.weight(1f),
			text = "${table.points}")
		Divider(
			modifier = Modifier.fillMaxHeight().width(1.dp)
		)
		Text(
			textAlign = TextAlign.Center,
			modifier = Modifier.weight(1f),
			text = "${table.playedGames}")
		Divider(
			modifier = Modifier.fillMaxHeight().width(1.dp)
		)
		Text(
			textAlign = TextAlign.Center,
			modifier = Modifier.weight(1f),
			text = "${table.won}")
		Divider(
			modifier = Modifier.fillMaxHeight().width(1.dp)
		)
		Text(
			textAlign = TextAlign.Center,
			modifier = Modifier.weight(1f),
			text = "${table.draw}")
		Divider(
			modifier = Modifier.fillMaxHeight().width(1.dp)
		)
		Text(
			textAlign = TextAlign.Center,
			modifier = Modifier.weight(1f),
			text = "${table.lost}")
		Divider(
			modifier = Modifier.fillMaxHeight().width(1.dp)
		)
		Text(
			textAlign = TextAlign.Center,
			modifier = Modifier.weight(1f),
			text = "${table.goalDifference}")
	}
}