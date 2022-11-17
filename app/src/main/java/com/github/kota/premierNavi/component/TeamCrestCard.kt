package com.github.kota.premierNavi.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.ui.theme.IMAGE_PADDING
import com.github.kota.premierNavi.ui.theme.MEDIUM_IMAGE
import com.github.kota.premierNavi.ui.theme.SMALL_IMAGE


@Composable
fun TeamCrestCard(
	crest: Painter,
	name: String?,
	modifier_column: Modifier,
	modifier_image: Modifier
){
	Column(
		modifier = modifier_column,
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			painter = crest ,
			contentDescription = "home team icon",
			modifier = modifier_image,
			contentScale = ContentScale.Fit
		)
		name?.let {
			Text(
				modifier = Modifier
					.padding(top = 5.dp)
					.fillMaxWidth(),
				text = it,
				fontWeight = FontWeight.Normal,
				fontSize = MaterialTheme.typography.h6.fontSize,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				textAlign = TextAlign.Center
			)
		}
	}
}

@Composable
@Preview
fun TeamCrestCardPreview(){
	TeamCrestCard(crest = painterResource(id = R.drawable.players),
		name = "Arsenal",
		modifier_column = Modifier
			.wrapContentWidth(Alignment.Start)
			.width(SMALL_IMAGE + IMAGE_PADDING + IMAGE_PADDING),
		modifier_image = Modifier
			.requiredSize(MEDIUM_IMAGE))
}
