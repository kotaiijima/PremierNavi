package com.github.kota.premierNavi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.domain.FootballDataRepository
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.MatchDomainModel
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.domain.model.TeamDomainModel
import com.github.kota.premierNavi.domain.service.MatchApiService
import com.github.kota.premierNavi.domain.service.RankApiService
import com.github.kota.premierNavi.domain.service.StatsApiService
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.usecase.TeamUseCase
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
	private val footballDataRepository: FootballDataRepository,
	private val rankApiService: RankApiService,
	private val statsApiService: StatsApiService,
	private val matchApiService: MatchApiService
): ViewModel() {

	private val _latestGame = MutableStateFlow<RequestState<MatchDomainModel>>(RequestState.Idle)
	val latestGame: StateFlow<RequestState<MatchDomainModel>>
		get() = _latestGame.asStateFlow()

	private val _nextGame = MutableStateFlow<RequestState<MatchDomainModel>>(RequestState.Idle)
	val nextGame: StateFlow<RequestState<MatchDomainModel>>
		get() = _nextGame.asStateFlow()

	private val _team = MutableStateFlow<RequestState<TeamDomainModel>>(RequestState.Idle)
	val team: StateFlow<RequestState<TeamDomainModel>>
		get() = _team.asStateFlow()

	private val _rank = MutableStateFlow<RequestState<RankDomainModel>>(RequestState.Idle)
	val rank: StateFlow<RequestState<RankDomainModel>>
		get() = _rank.asStateFlow()

	private val _stats = MutableStateFlow<RequestState<StatsDomainModel>>(RequestState.Idle)
	val stats: StateFlow<RequestState<StatsDomainModel>>
		get() = _stats.asStateFlow()

	private val _teamId = MutableStateFlow<RequestState<List<TeamId>>>(RequestState.Idle)
	val teamId: StateFlow<RequestState<List<TeamId>>>
		get() = _teamId.asStateFlow()

	private val _selectedApiTeam = MutableStateFlow<RequestState<TeamDomainModel>>(RequestState.Idle)
	val selectedApiTeam: StateFlow<RequestState<TeamDomainModel>>
		get() = _selectedApiTeam.asStateFlow()

	init {
		viewModelScope.launch{
			val rank = footballDataRepository.getRank()
			if (rank is ApiResult.ApiSuccess) _rank.value = rankApiService.convertRank(rank)
			Log.d("[rank]ApiResult:", rank.toString())

			getTeamId()
		}
	}

	private suspend fun getApiData(teamId: Int) {
		val latestGame = footballDataRepository.getMatch(TeamIdDomainObject(teamId), "FINISHED")
		if (latestGame is ApiResult.ApiSuccess)  _latestGame.value = matchApiService.convertMatch(latestGame)
		Log.d("[latestGame]ApiResult:", latestGame.toString())

		val nextGame = footballDataRepository.getMatch(TeamIdDomainObject(teamId), "SCHEDULED")
		if (nextGame is ApiResult.ApiSuccess) _nextGame.value = matchApiService.convertMatch(nextGame)
		Log.d("[nextGame]ApiResult:", nextGame.toString())

		val stats = footballDataRepository.getStats(TeamIdDomainObject(teamId))
		if (stats is ApiResult.ApiSuccess) _stats.value = statsApiService.convertStats(stats)
		Log.d("[stats]ApiResult:", stats.toString())

		val team = footballDataRepository.getTeam(TeamIdDomainObject(teamId))
		if (team is ApiResult.ApiSuccess) _team.value = TeamUseCase(team)
		Log.d("[team]ApiResult:", team.toString())
	}

	fun getTeamData(teamId: Int) {
		viewModelScope.launch {
			val selectedTeam = footballDataRepository.getTeam(TeamIdDomainObject(teamId))
			if (selectedTeam is ApiResult.ApiSuccess) _selectedApiTeam.value = teamApiService.convertTeam(selectedTeam)
		}
	}

	private fun getTeamId() {
		viewModelScope.launch {
			footballDataRepository.getTeamId().collect {
				if (it.isNotEmpty()) {
					_teamId.value = RequestState.Success(it)
					if (it[0].teamId != 0) {
						getApiData(it[0].teamId)
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
		}
	}
}