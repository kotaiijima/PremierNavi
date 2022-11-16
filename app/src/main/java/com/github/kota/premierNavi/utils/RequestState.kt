package com.github.kota.premierNavi.utils

sealed class RequestState<out T>{
	object Idle: RequestState<Nothing>()
	object Loading: RequestState<Nothing>()
	data class Success<T>(val data: T) : RequestState<T>()
	data class Error(val error: Throwable) : RequestState<Nothing>()
}

sealed class ApiResult<out T> {
	object Idle: ApiResult<Nothing>()
	data class ApiSuccess<T>(val data: T) : ApiResult<T>()
	data class ApiError<T>(val code: Int, val message: String?) : ApiResult<T>()
	data class ApiException<T>(val e: Throwable) : ApiResult<T>()
}