package com.github.kota.premierNavi.utils

sealed class RequestState<out T>{
	object Idle: RequestState<Nothing>()
	data class Success<T>(val data: T) : RequestState<T>()

	sealed class Failure: RequestState<Nothing>(){
		data class Error(val error: Throwable) : Failure()
	}
}
