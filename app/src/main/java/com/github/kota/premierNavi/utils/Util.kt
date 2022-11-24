package com.github.kota.premierNavi.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import java.time.LocalDate
import java.time.Period

fun calcAge(birthday: String): Int {
	val today = LocalDate.now()
	return Period.between(LocalDate.parse(birthday), today).years
}

@Composable
fun showCrest(crest: String): Painter {
	return if (crest.split(".")[3] == "svg") {
		rememberImagePainter(
			data = crest,
			builder = {
				decoder(SvgDecoder(LocalContext.current))
			}
		)
	} else {
		rememberImagePainter(data = crest)
	}
}

@Composable
fun translationToJapanese(EngTeamName: String): String{
	return when(EngTeamName){
		"Arsenal" -> "アーセナル"
		"Tottenham" -> "トッテナム"
		"Man City" -> "マンチェスター・シティ"
		"Newcastle" -> "ニューカッスル"
		"Man United" -> "マンチェスター・ユナイテッド"
		"Liverpool" -> "リヴァプール"
		"Brighton Hove" -> "ブライトン"
		"Chelsea" -> "チェルシー"
		"Fulham" -> "フラム"
		"Brentford" -> "ブレントフォード"
		"Crystal Palace" -> "クリスタル・パレス"
		"Aston Villa" -> "アストンヴィラ"
		"Leicester City" -> "レスター・シティ"
		"Bournemouth" -> "ボーンマス"
		"Leeds United" -> "リーズ・ユナイテッド"
		"West Ham" -> "ウェストハム"
		"Everton" -> "エヴァートン"
		"Nottingham" -> "ノッティンガム"
		"Southampton" -> "サウサンプトン"
		"Wolverhampton" -> "ウォルヴァーハンプトン"
		else -> EngTeamName
	}
}