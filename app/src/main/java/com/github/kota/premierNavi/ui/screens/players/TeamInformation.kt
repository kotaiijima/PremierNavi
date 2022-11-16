package com.github.kota.premierNavi.ui.screens.players

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.kota.premierNavi.utils.showCrest

@Composable
fun TeamInformation(
	crest: String,
	stadium: String,
	teamName: String
){
	Row (
		modifier = Modifier.fillMaxWidth()
			){
		Image(
			painter = showCrest(crest = crest),
			contentDescription = null,
			modifier = Modifier.size(150.dp)
		)
		Column(
			verticalArrangement = Arrangement.Center
		) {
			Text(text = teamName,
				fontSize = MaterialTheme.typography.h5.fontSize,
				fontWeight = FontWeight.Bold
			)
			Text(text = stadium,
				fontSize = MaterialTheme.typography.subtitle1.fontSize
			)
		}
	}
}