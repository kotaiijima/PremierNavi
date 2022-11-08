package com.github.kota.premierNavi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.data.repository.FootballDataRepository
import com.github.kota.premierNavi.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
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

	init {
		viewModelScope.launch {

			val latestGame = footballDataRepository.getMatch(57, "FINISHED")
			if (latestGame is ApiResult.ApiSuccess)  _latestGame.value = latestGame
			val nextGame = footballDataRepository.getMatch(57, "SCHEDULED")
			if (nextGame is ApiResult.ApiSuccess) _nextGame.value = nextGame
			val team = footballDataRepository.getTeam(57)
			if (team is ApiResult.ApiSuccess) _team.value = team
			val rank = footballDataRepository.getRank()
			if (rank is ApiResult.ApiSuccess) _rank.value = rank
			val stats = footballDataRepository.getStats(57)
			if (stats is ApiResult.ApiSuccess) _stats.value = stats
		}
	}
}