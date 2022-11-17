package com.github.kota.premierNavi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.data.repository.FootballDataRepository
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
	private val footballDataRepository: FootballDataRepository
): ViewModel(){

	private val _latestGame = MutableStateFlow<ApiResult<Match>>(ApiResult.Idle)
	val latestGame: StateFlow<ApiResult<Match>>
		get() = _latestGame.asStateFlow()

	private val _nextGame = MutableStateFlow<ApiResult<Match>>(ApiResult.Idle)
	val nextGame: StateFlow<ApiResult<Match>>
		get() = _nextGame.asStateFlow()

	private val _team = MutableStateFlow<ApiResult<Team>>(ApiResult.Idle)
	val team: StateFlow<ApiResult<Team>>
		get() = _team.asStateFlow()

	private val _rank = MutableStateFlow<ApiResult<Rank>>(ApiResult.Idle)
	val rank: StateFlow<ApiResult<Rank>>
		get() = _rank.asStateFlow()

	private val _stats = MutableStateFlow<ApiResult<Stats>>(ApiResult.Idle)
	val stats: StateFlow<ApiResult<Stats>>
		get() = _stats.asStateFlow()


	private val _teamId = MutableStateFlow<RequestState<List<TeamId>>>(RequestState.Idle)
	val teamId: StateFlow<RequestState<List<TeamId>>>
		get() = _teamId.asStateFlow()

	init {
		viewModelScope.launch{
			val rank = footballDataRepository.getRank()
			if (rank is ApiResult.ApiSuccess) _rank.value = rank
			getTeamId()
//			getAllData(57)
		}
	}

	private suspend fun getAllData(teamId: Int){
			val latestGame = footballDataRepository.getMatch(teamId, "FINISHED")
			if (latestGame is ApiResult.ApiSuccess)  _latestGame.value = latestGame
			val nextGame = footballDataRepository.getMatch(teamId, "SCHEDULED")
			if (nextGame is ApiResult.ApiSuccess) _nextGame.value = nextGame
			val team = footballDataRepository.getTeam(teamId)
			if (team is ApiResult.ApiSuccess) _team.value = team
			val stats = footballDataRepository.getStats(teamId)
			if (stats is ApiResult.ApiSuccess) _stats.value = stats
	}

	private fun getTeamId(){
		viewModelScope.launch {
			footballDataRepository.getTeamId().collect{
				Log.d("RequestState: " , it.toString())
				if (it.isNotEmpty()){
					_teamId.value = RequestState.Success(it)
					if (it[0].teamId != 0){
						getAllData(it[0].teamId)
					}
				}
			}
		}
	}

	fun addTeamId(newTeamId: Int){
		viewModelScope.launch(Dispatchers.IO) {
			val teamId = TeamId(id = 1, teamId = newTeamId)
			footballDataRepository.addTeamId(teamId)
		}
	}

	fun updateTeamId(newTeamId: Int){
		viewModelScope.launch(Dispatchers.IO) {
			val teamId = TeamId(id = 1, teamId = newTeamId)
			footballDataRepository.updateTeamId(teamId)
//			getTeamId()
			getAllData(newTeamId)
		}
	}
}