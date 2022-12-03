package com.github.kota.premierNavi.utils

sealed class RequestState<out T>{
	object Idle: RequestState<Nothing>()
	data class Success<T>(val data: T) : RequestState<T>()

	sealed class Failure: RequestState<Nothing>(){
		data class NetworkError(val error: Throwable) : Failure()
		data class EmptyError(val error: Throwable) : Failure()
	}
}
