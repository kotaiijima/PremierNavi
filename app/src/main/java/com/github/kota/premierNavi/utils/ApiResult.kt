package com.github.kota.premierNavi.utils

sealed class ApiResult<out T> {
	object Idle: ApiResult<Nothing>()
	object Loading: ApiResult<Nothing>()
	data class ApiSuccess<T>(val data: T) : ApiResult<T>()
	sealed class Failure: ApiResult<Nothing>() {
		data class ApiError(val code: Int, val message: String?) : Failure()
		data class ApiException(val e: Throwable) : Failure()
	}
}