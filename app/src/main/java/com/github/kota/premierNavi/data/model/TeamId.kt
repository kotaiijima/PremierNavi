package com.github.kota.premierNavi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.kota.premierNavi.utils.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class TeamId(
	@PrimaryKey(autoGenerate = true)val id: Int,
	val teamId: Int
)
