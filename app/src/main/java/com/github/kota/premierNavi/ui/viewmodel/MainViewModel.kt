package com.github.kota.premierNavi.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.EventListener.Factory.Companion.invoke
import coil.ImageLoader.Companion.invoke
import coil.bitmap.BitmapPool.Companion.invoke
import coil.memory.MemoryCache.Key.Companion.invoke
import coil.size.SizeResolver.Companion.invoke
import coil.size.ViewSizeResolver.Companion.invoke
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.data.repository.FootballDataRepository
import com.github.kota.premierNavi.ui.screens.home.HomeScreen
import com.github.kota.premierNavi.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.Interceptor.Companion.invoke
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

			when(val latestGame = footballDataRepository.getMatch(57, "FINISHED")){
				is ApiResult.ApiSuccess -> _latestGame.value = latestGame.data
				is ApiResult.ApiError -> Log.d("getMatch", latestGame.message.toString())
			}
			when(val nextGame = footballDataRepository.getMatch(57, "SCHEDULED")){
				is ApiResult.ApiSuccess -> _nextGame.value = nextGame.data
			}
			val team = footballDataRepository.getTeam(57)
			_team.value = team
			val rank = footballDataRepository.getRank()
			_rank.value = rank
			val stats = footballDataRepository.getStats(57)
			_stats.value = stats
		}
	}
}