package com.github.kota.premierNavi.ui.screens.players

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kota.premierNavi.R
import com.github.kota.premierNavi.ui.theme.*

@Composable
fun TeamInformation(
	crest: Painter,
	stadium: String,
	teamName: String
) {
	Row (
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically
			){
		Image(
			painter = crest,
			contentDescription = stringResource(id = R.string.club_crest),
			modifier = Modifier
				.size(LARGE_IMAGE)
				.padding(LARGE_PADDING)
		)
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
			modifier = Modifier
				.fillMaxWidth()
				.height(IntrinsicSize.Max)
		) {
			Text(text = teamName,
				fontSize = MaterialTheme.typography.h4.fontSize,
				fontWeight = FontWeight.Bold,
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = IMAGE_PADDING)
				,
				textAlign = TextAlign.Center
			)
			Text(text = stadium,
				fontSize = MaterialTheme.typography.h6.fontSize,
				modifier = Modifier
					.fillMaxWidth()
					.padding(MEDIUM_PADDING),
				textAlign = TextAlign.Center
			)
		}
	}
	Divider(thickness = 2.dp)
}

@Composable
@Preview
fun TeamInformationPreview() {
	TeamInformation(crest = painterResource(id = R.drawable.players),
		stadium = "Emirate Stadium",
		teamName = "Arsenal")
}