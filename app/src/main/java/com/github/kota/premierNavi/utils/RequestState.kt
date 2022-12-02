package com.github.kota.premierNavi.utils

sealed class RequestState<out T>{
	object Idle: RequestState<Nothing>()
	object Empty: RequestState<Nothing>()
	data class Success<T>(val data: T) : RequestState<T>()
}