package com.github.kota.premierNavi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.domain.FootballDataRepository
import com.github.kota.premierNavi.domain.MatchStatus
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.MatchDomainModel
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.domain.model.TeamDomainModel
import com.github.kota.premierNavi.domain.service.MatchApiService
import com.github.kota.premierNavi.domain.service.RankApiService
import com.github.kota.premierNavi.domain.service.StatsApiService
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.Constants.TEAM_ID
import com.github.kota.premierNavi.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
	private val footballDataRepository: FootballDataRepository,
	private val rankApiService: RankApiService,
	private val statsApiService: StatsApiService,
	private val matchApiService: MatchApiService,
	private val teamApiService: TeamApiService
): ViewModel() {

	private val _latestGame = MutableStateFlow<ApiResult<MatchDomainModel>>(ApiResult.Idle)
	val latestGame: StateFlow<ApiResult<MatchDomainModel>>
		get() = _latestGame.asStateFlow()

	private val _nextGame = MutableStateFlow<ApiResult<MatchDomainModel>>(ApiResult.Idle)
	val nextGame: StateFlow<ApiResult<MatchDomainModel>>
		get() = _nextGame.asStateFlow()

	private val _team = MutableStateFlow<ApiResult<TeamDomainModel>>(ApiResult.Idle)
	val team: StateFlow<ApiResult<TeamDomainModel>>
		get() = _team.asStateFlow()

	private val _rank = MutableStateFlow<ApiResult<RankDomainModel>>(ApiResult.Idle)
	val rank: StateFlow<ApiResult<RankDomainModel>>
		get() = _rank.asStateFlow()

	private val _stats = MutableStateFlow<ApiResult<StatsDomainModel>>(ApiResult.Idle)
	val stats: StateFlow<ApiResult<StatsDomainModel>>
		get() = _stats.asStateFlow()

	private val _teamId = MutableStateFlow<RequestState<List<TeamId>>>(RequestState.Idle)
	val teamId: StateFlow<RequestState<List<TeamId>>>
		get() = _teamId.asStateFlow()

	private val _selectedApiTeam = MutableStateFlow<ApiResult<TeamDomainModel>>(ApiResult.Idle)
	val selectedApiTeam: StateFlow<ApiResult<TeamDomainModel>>
		get() = _selectedApiTeam.asStateFlow()

	init {
		viewModelScope.launch{
			val rank = rankApiService.getRank()
			if (rank is ApiResult.ApiSuccess) _rank.value = rank
			Log.d("[rank]ApiResult:", rank.toString())

			getTeamId()
		}
	}

	fun getStatsData(teamId: Int) {
		_stats.value = ApiResult.Loading

		viewModelScope.launch {
			val stats = statsApiService.getStats(TeamIdDomainObject(teamId))
			if (stats is ApiResult.ApiSuccess) _stats.value = stats
			Log.d("[stats]ApiResult:", stats.toString())
		}
	}

	fun getTeamData(teamId: Int) {
		_team.value = ApiResult.Loading

		viewModelScope.launch {
			val team = teamApiService.getTeam(TeamIdDomainObject(teamId))
			if (team is ApiResult.ApiSuccess) _team.value = team
			Log.d("[team]ApiResult:", team.toString())
		}
	}

	fun getMatchData(teamId: Int) {
		_latestGame.value = ApiResult.Loading
		_nextGame.value = ApiResult.Loading

		viewModelScope.launch {
			val latestGame = matchApiService.getMatch(TeamIdDomainObject(teamId), MatchStatus("FINISHED"))
			if (latestGame is ApiResult.ApiSuccess)  _latestGame.value = latestGame
			Log.d("[latestGame]ApiResult:", latestGame.toString())

			val nextGame = matchApiService.getMatch(TeamIdDomainObject(teamId), MatchStatus("SCHEDULED"))
			if (nextGame is ApiResult.ApiSuccess) _nextGame.value = nextGame
			Log.d("[nextGame]ApiResult:", nextGame.toString())
		}
	}

	fun getSelectedTeamData(teamId: Int) {
		viewModelScope.launch {
			val selectedTeam = teamApiService.getTeam(TeamIdDomainObject(teamId))
			if (selectedTeam is ApiResult.ApiSuccess) _selectedApiTeam.value = selectedTeam
			Log.d("[team]ApiResult:", selectedTeam.toString())
		}
	}

	private fun getTeamId() {
		viewModelScope.launch {
			try {
				footballDataRepository.getTeamId().collect {
					_teamId.value = RequestState.Success(it)
					getMatchData(it[0].teamId)
					getStatsData(it[0].teamId)
					getTeamData(it[0].teamId)
				}
			}catch(e: Exception) {
				_teamId.value = RequestState.Failure.Error(e)
			}
		}
	}

	fun addTeamId(newTeamId: Int) {
		viewModelScope.launch {
			val teamId = TeamId(id = TEAM_ID, teamId = newTeamId)
			footballDataRepository.addTeamId(teamId)
		}
	}

	fun updateTeamId(newTeamId: Int) {
		viewModelScope.launch {
			val teamId = TeamId(id = TEAM_ID, teamId = newTeamId)
			footballDataRepository.updateTeamId(teamId)
		}
	}
}