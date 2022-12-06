package com.github.kota.premierNavi.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.github.kota.premierNavi.ui.theme.LARGE_PADDING
import com.github.kota.premierNavi.ui.theme.SMALL_PADDING
import com.github.kota.premierNavi.utils.convertCompetition
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeDate(
	matchStatus: String,
	competition: String,
	section: Int,
	round: String,
	matchDay: String
) {
	lateinit var dateToString:  String

	val date: ZonedDateTime = ZonedDateTime.parse(matchDay).plusHours(9)
	val dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm")
	dateToString = date.format(dtf)
	Column {
		Text(
			modifier = Modifier
				.padding(LARGE_PADDING),
			fontWeight = FontWeight.Normal,
			fontSize = MaterialTheme.typography.h5.fontSize,
			text = matchStatus,
			fontFamily = FontFamily.SansSerif
		)
		Text(
			modifier = Modifier
				.padding(LARGE_PADDING)
				.padding(start = SMALL_PADDING),
			fontWeight = FontWeight.Bold,
			fontSize = MaterialTheme.typography.h5.fontSize ,
			text = convertCompetition(
				competition = competition,
				round = round,
				section = section
			) +
			"\n $dateToString",
			fontFamily = FontFamily.SansSerif
		)
	}
}

@Composable
@Preview
fun HomeDatePreview() {
	HomeDate(
		matchStatus = "前回の試合結果",
		competition = "PL",
		section = 1,
		round = "",
		matchDay = "2022-11-10T10:30:07Z"
	)
}