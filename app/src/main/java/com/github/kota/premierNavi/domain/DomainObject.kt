package com.github.kota.premierNavi.domain

import androidx.compose.ui.graphics.painter.Painter

data class TeamIdDomainObject(val value: Int)
data class TeamNameDomainObject(val value: String)
data class TeamDomainObject(
	val id: Int,
	val name: String,
	val crest: Painter
)