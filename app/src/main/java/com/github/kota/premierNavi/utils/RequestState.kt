package com.github.kota.premierNavi.utils

sealed interface ApiResult<T : Any> {
	object Loading : ApiResult<Nothing>
	class ApiSuccess<T : Any>(val data: T) : ApiResult<T>
	class ApiError<T : Any>(val code: Int, val message: String?) : ApiResult<T>
	class ApiException<T : Any>(val e: Throwable) : ApiResult<T>
}