package com.github.kota.premierNavi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.data.repository.FootballDataRepository
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
	private val _latestGame = MutableStateFlow<Match?>(null)
	val latestGame: StateFlow<Match?>
		get() = _latestGame.asStateFlow()

	private val _nextGame = MutableStateFlow<Match?>(null)
	val nextGame: StateFlow<Match?>
		get() = _nextGame.asStateFlow()

	private val _team = MutableStateFlow<Team?>(null)
	val team: StateFlow<Team?>
		get() = _team.asStateFlow()

	private val _rank = MutableStateFlow<Rank?>(null)
	val rank: StateFlow<Rank?>
		get() = _rank.asStateFlow()

	private val _stats = MutableStateFlow<Stats?>(null)
	val stats: StateFlow<Stats?>
		get() = _stats.asStateFlow()

	init {
		viewModelScope.launch {
			val latestGame = footballDataRepository.getLatestGame(57, "FINISHED")
			_latestGame.value = latestGame
			val nextGame = footballDataRepository.getLatestGame(57, "SCHEDULED")
			_nextGame.value = nextGame
			val team = footballDataRepository.getTeam(57)
			_team.value = team
			val rank = footballDataRepository.getRank()
			_rank.value = rank
			val stats = footballDataRepository.getStats(57)
			_stats.value = stats
		}
	}
}