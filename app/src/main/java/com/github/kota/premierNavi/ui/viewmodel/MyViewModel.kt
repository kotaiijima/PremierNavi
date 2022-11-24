package com.github.kota.premierNavi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.domain.FootballDataRepository
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
	private val footballDataRepository: FootballDataRepository
): ViewModel() {

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

	private val _selectedTeam = MutableStateFlow<ApiResult<Team>>(ApiResult.Idle)
	val selectedTeam: StateFlow<ApiResult<Team>>
		get() = _selectedTeam.asStateFlow()

	init {
		viewModelScope.launch{
			val rank = footballDataRepository.getRank()
			if (rank is ApiResult.ApiSuccess) _rank.value = rank
			Log.d("[rank]ApiResult:", rank.toString())
			getTeamId()
		}
	}

	private suspend fun getAllData(teamId: Int) {
		val latestGame = footballDataRepository.getMatch(TeamIdDomainObject(teamId), "FINISHED")
		if (latestGame is ApiResult.ApiSuccess)  _latestGame.value = latestGame
		Log.d("[latestGame]ApiResult:", latestGame.toString())
		val nextGame = footballDataRepository.getMatch(TeamIdDomainObject(teamId), "SCHEDULED")
		if (nextGame is ApiResult.ApiSuccess) _nextGame.value = nextGame
		Log.d("[nextGame]ApiResult:", nextGame.toString())
		val stats = footballDataRepository.getStats(TeamIdDomainObject(teamId))
		if (stats is ApiResult.ApiSuccess) _stats.value = stats
		Log.d("[stats]ApiResult:", stats.toString())
		val team = footballDataRepository.getTeam(TeamIdDomainObject(teamId))
		if (team is ApiResult.ApiSuccess) _team.value = team
		Log.d("[team]ApiResult:", team.toString())
	}

	fun getTeamData(teamId: Int) {
		viewModelScope.launch {
			val selectedTeam = footballDataRepository.getTeam(TeamIdDomainObject(teamId))
			if (selectedTeam is ApiResult.ApiSuccess) _selectedTeam.value = selectedTeam
		}
	}

	private fun getTeamId() {
		viewModelScope.launch {
			footballDataRepository.getTeamId().collect {
				if (it.isNotEmpty()) {
					_teamId.value = RequestState.Success(it)
					if (it[0].teamId != 0) {
						getAllData(it[0].teamId)
					}
				} else {
					_teamId.value = RequestState.Empty
				}
			}
		}
	}

	fun addTeamId(newTeamId: Int) {
		viewModelScope.launch {
			val teamId = TeamId(id = 1, teamId = newTeamId)
			footballDataRepository.addTeamId(teamId)
		}
	}

	fun updateTeamId(newTeamId: Int) {
		viewModelScope.launch {
			val teamId = TeamId(id = 1, teamId = newTeamId)
			footballDataRepository.updateTeamId(teamId)
			getAllData(newTeamId)
		}
	}
}