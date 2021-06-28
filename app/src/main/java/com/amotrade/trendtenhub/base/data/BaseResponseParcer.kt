package com.amotrade.trendtenhub.base.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> baseApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): BaseResponse<T> {
    return withContext(dispatcher) {
        try {
            BaseResponse.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            BaseResponse.Error(ErrorData(throwable, throwable.message ?: ""))
        }
    }
}