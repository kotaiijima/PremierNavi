package com.github.kota.premierNavi.data

import androidx.room.*
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.utils.RequestState
import kotlinx.coroutines.flow.Flow

@Dao
interface PreNaviDao {
	@Query("SELECT * FROM `PreNavi-table`")
	fun getTeamId(): Flow<List<TeamId>>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun addTeamId(teamId: TeamId)

	@Update
	suspend fun updateTeamId(teamId: TeamId)
}