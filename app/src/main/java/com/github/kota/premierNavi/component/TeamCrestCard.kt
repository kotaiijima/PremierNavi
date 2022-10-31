package com.github.kota.premierNavi.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun TeamCrestCard(
	crest: Painter,
	name: String?,
	modifier: Modifier = Modifier
){
	Column(
		modifier = modifier
	) {
		Image(
			painter = crest ,
			contentDescription = "home team icon",
			modifier = Modifier
				.size(100.dp),
			contentScale = ContentScale.Fit
		)
		name?.let {
			Text(
				modifier = Modifier
					.padding(top = 5.dp),
				text = it,
				fontWeight = FontWeight.Normal,
				fontSize = MaterialTheme.typography.h6.fontSize
			)
		}
	}
}
