package com.github.kota.premierNavi.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import java.time.LocalDate
import java.time.Period

fun calcAge(birthday: String): Int{
	val today = LocalDate.now()
	return Period.between(LocalDate.parse(birthday), today).years
}

@Composable
fun showCrest(crest: String): Painter {
	return if (crest.split(".")[3] == "svg"){
		rememberImagePainter(
			data = crest,
			builder = {
				decoder(SvgDecoder(LocalContext.current))
			}
		)
	} else{
		rememberImagePainter(data = crest)
	}
}