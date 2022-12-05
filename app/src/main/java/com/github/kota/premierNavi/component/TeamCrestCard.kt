package com.github.kota.premierNavi.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.ui.theme.IMAGE_PADDING
import com.github.kota.premierNavi.ui.theme.MEDIUM_IMAGE
import com.github.kota.premierNavi.ui.theme.SMALL_IMAGE
import com.github.kota.premierNavi.utils.translationToJapanese

@Composable
fun TeamCrestCard(
	name: String?,
	modifier: Modifier = Modifier,
	teamCrestCard: @Composable () -> Unit,
) {
	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		teamCrestCard()
		name?.let {
			Text(
				modifier = Modifier
					.padding(top = 5.dp)
					.fillMaxWidth(),
				text = translationToJapanese(EngTeamName = it),
				fontWeight = FontWeight.Normal,
				fontSize = MaterialTheme.typography.h6.fontSize,
				maxLines = 2,
				textAlign = TextAlign.Center
			)
		}
	}
}

@Composable
@Preview
fun TeamCrestCardPreview(){
	TeamCrestCard(
		name = "Brentford",
		modifier = Modifier
			.width(SMALL_IMAGE + IMAGE_PADDING + IMAGE_PADDING),
		teamCrestCard = {
			Image(
				painter = painterResource(id = R.drawable.players),
				contentDescription = stringResource(id = R.string.club_crest),
				modifier = Modifier.requiredSize(MEDIUM_IMAGE),
				contentScale = ContentScale.Fit
			)
		}
	)
}
