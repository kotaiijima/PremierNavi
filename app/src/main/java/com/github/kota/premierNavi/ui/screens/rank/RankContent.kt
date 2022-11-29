package com.github.kota.premierNavi.ui.screens.rank

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kota.premierNavi.ui.theme.MEDIUM_PADDING
import com.github.kota.premierNavi.ui.theme.SMALL_IMAGE
import com.github.kota.premierNavi.utils.showCrest
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.utils.translationToJapanese

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RankContent(
	rank: RankDomainModel,
	navigateToTeamDetail:(Int) -> Unit
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
		items(rank.teams){
			RankItem(
				position = it.position,
				crest = it.crest,
				teamName = it.teamName,
				points = it.points,
				playedGame = it.playedGame,
				won = it.won,
				draw = it.draw,
				lost = it.lost,
				goalDifference = it.goalDifference,
				modifier = Modifier
					.height(IntrinsicSize.Min)
					.clickable {
						navigateToTeamDetail(it.id)
					}
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
				.requiredSize(SMALL_IMAGE)
				.padding(MEDIUM_PADDING)
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
		text = translationToJapanese(EngTeamName = text))
}

@Composable
fun RankItemImage(
	image: String,
	modifier: Modifier
){
	Divider(
		modifier = Modifier
			.fillMaxHeight()
			.width(1.dp)
	)
	Image(
		painter = showCrest(crest = image) ,
		contentDescription = stringResource(id = R.string.club_crest),
		modifier = modifier,
		contentScale = ContentScale.Fit
	)
}

@Composable
@Preview
fun RankItemPreview(){
	RankItem(
		modifier = Modifier
	)
}