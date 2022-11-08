package com.github.kota.premierNavi.ui.screens.rank

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RankContent(
	rank: Rank
){
	LazyColumn {
		stickyHeader {
			RankItem(
			modifier = Modifier
				.background(color = Color.White)
				.height(IntrinsicSize.Min)
			)
			Divider()
		}
		items(rank.standings[0].table){
			RankItem(
				position = it.position.toString(),
				crest = it.team.crest,
				teamName = it.team.shortName,
				points = it.points.toString(),
				playedGame = it.playedGames.toString(),
				won = it.won.toString(),
				draw = it.draw.toString(),
				lost = it.lost.toString(),
				goalDifference = it.goalDifference.toString(),
				modifier = Modifier.height(IntrinsicSize.Min)
			)
		}
	}
}

@Composable
private fun RankItem(
	position: String = "順位",
	crest: String = "",
	teamName: String = "チーム",
	points: String = "勝点",
	playedGame: String = "試合",
	won: String = "勝",
	draw: String = "引",
	lost: String = "敗",
	goalDifference: String = "得失",
	modifier: Modifier
) {
	Row (
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
			){
		RankItemText(text = position, modifier = Modifier.weight(1f))
		if (crest != ""){
			RankItemImage(image = crest, modifier = Modifier
				.requiredSize(50.dp)
				.padding(10.dp)
				.weight(2f))
			RankItemText(text = teamName, modifier = Modifier.weight(4f))
		}else{
			RankItemText(text = teamName, modifier = Modifier.weight(6f))
		}
		RankItemText(text = points, modifier = Modifier.weight(1f))
		RankItemText(text = playedGame, modifier = Modifier.weight(1f))
		RankItemText(text = won, modifier = Modifier.weight(1f))
		RankItemText(text = draw, modifier = Modifier.weight(1f))
		RankItemText(text =lost, modifier = Modifier.weight(1f))
		RankItemText(text = goalDifference, modifier = Modifier.weight(1f))
	}
}

@Composable
fun RankItemText(
	text: String,
	modifier: Modifier
){
	Divider(
		modifier = Modifier
			.fillMaxHeight()
			.width(1.dp)
	)
	Text(
		textAlign = TextAlign.Center,
		modifier = modifier,
		text = text)
}

@Composable
fun RankItemImage(
	image: String,
	modifier: Modifier
){
	val clubCrest = rememberImagePainter(data = image, builder = {
		decoder(SvgDecoder(LocalContext.current))
	})
	Divider(
		modifier = Modifier
			.fillMaxHeight()
			.width(1.dp)
	)
	Image(
		painter = clubCrest ,
		contentDescription = "club crest",
		modifier = modifier,
		contentScale = ContentScale.Fit
	)
}