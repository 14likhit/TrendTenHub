package com.amotrade.trendtenhub.base.data

open class BaseResponse<out T> {

    data class Success<out T>(val data: T) : BaseResponse<T>()
    data class Error(val errorData: ErrorData) : BaseResponse<Nothing>()

}