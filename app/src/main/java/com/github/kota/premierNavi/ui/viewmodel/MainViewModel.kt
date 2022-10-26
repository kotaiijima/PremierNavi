package com.github.kota.premierNavi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kota.premierNavi.data.api.model.matchModel.Team
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
	private val _latestGame = MutableStateFlow<Team?>(null)
	val latestGame: StateFlow<Team?>
		get() = _latestGame.asStateFlow()

	private val _nextGame = MutableStateFlow<Team?>(null)
	val nextGame: StateFlow<Team?>
		get() = _nextGame.asStateFlow()

	init {
		viewModelScope.launch {
			val latestGame = footballDataRepository.getLatestGame(57, "FINISHED")
			_latestGame.value = latestGame
			val nextGame = footballDataRepository.getLatestGame(57, "SCHEDULED")
			_nextGame.value = nextGame
		}
	}
}