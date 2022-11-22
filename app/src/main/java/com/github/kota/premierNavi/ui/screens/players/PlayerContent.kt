package com.github.kota.premierNavi.ui.screens.players


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.ui.theme.MEDIUM_PADDING
import com.github.kota.premierNavi.utils.calcAge

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerContent (team: Team){
	val listState = rememberLazyListState()
	val playersPositionGroup = team.squad.groupBy { it.position }

	LazyColumn(
		modifier = Modifier.padding(
			start = MEDIUM_PADDING,
			end = MEDIUM_PADDING,
			top = MEDIUM_PADDING),
		state = listState){
		stickyHeader {
			Text(
				text = "Coach",
				fontWeight = FontWeight.Bold,
				fontSize = MaterialTheme.typography.h6.fontSize,
				modifier = Modifier
					.background(color = Color.White)
					.fillMaxWidth()
			)
		}
		item { PlayerItem(
			name = team.coach.name,
			birthday = team.coach.dateOfBirth,
			country = team.coach.nationality)
		}
		playersPositionGroup.forEach{( position, player) ->
			stickyHeader {
				Text(
					text = position,
					fontWeight = FontWeight.Bold,
					fontSize = MaterialTheme.typography.h6.fontSize,
					modifier = Modifier
						.background(color = Color.White)
						.fillMaxWidth()
				)
			}
			items(player){
				PlayerItem(
					it.name,
					it.dateOfBirth,
					it.nationality
				)
			}
		}
	}
}

@Composable
fun PlayerItem(
	name: String,
	birthday: String,
	country: String
){
	Row (
		modifier = Modifier
			.fillMaxWidth()
			.height(IntrinsicSize.Max)
			.padding(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
			){
		Text(
			modifier = Modifier
				.padding(MEDIUM_PADDING)
				.weight(4f),
			fontSize = MaterialTheme.typography.subtitle1.fontSize,
			fontWeight = FontWeight.Normal,
			text = name,
			maxLines = 1,
			overflow = TextOverflow.Ellipsis
		)
		Divider(
			modifier = Modifier
				.fillMaxHeight()
				.width(1.dp)
		)
		Text(
			modifier = Modifier
				.padding(MEDIUM_PADDING)
				.weight(3f),
			fontSize = MaterialTheme.typography.subtitle1.fontSize,
			fontWeight = FontWeight.Normal,
			text = country,
			maxLines = 1,
			overflow = TextOverflow.Ellipsis
		)
		Divider(
			modifier = Modifier
				.fillMaxHeight()
				.width(1.dp)
		)
		Text(
			modifier = Modifier
				.padding(MEDIUM_PADDING)
				.weight(1f),
			fontSize = MaterialTheme.typography.subtitle1.fontSize,
			fontWeight = FontWeight.Normal,
			text = ("${calcAge(birthday)}")
		)
	}
}

@Composable
@Preview
fun PlayerItemPreview(){
	PlayerItem(name = "Takehiro Tomiyasu", birthday = "2001-06-22", country = "Japan")
}