package com.github.kota.premierNavi.utils

sealed class ApiResult<out T> {
	object Idle: ApiResult<Nothing>()
	object Loading: ApiResult<Nothing>()
	data class ApiSuccess<T>(val data: T) : ApiResult<T>()
	data class ApiError<T>(val code: Int, val message: String?) : ApiResult<T>()
	data class ApiException<T>(val e: Throwable) : ApiResult<T>()
}