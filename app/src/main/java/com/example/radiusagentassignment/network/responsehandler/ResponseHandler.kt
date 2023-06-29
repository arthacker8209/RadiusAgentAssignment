package com.example.radiusagentassignment.network.responsehandler

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException


fun <T> executeRetrofitApi(call: () -> Call<T>): Single<NetworkResource<T>> {
    return Single.fromCallable<NetworkResource<T>> {
        val result = call().execute()
        when {
            result.isSuccessful -> {
                val body = result.body()
                if (body != null) {
                    NetworkResource.Success(body)
                } else {
                    NetworkResource.Error(
                        ErrorResponse(
                            responseCode = result.code(),
                            retrofitErrorResponse = result.errorBody(),
                            errorStatus = ErrorStatus.ServerError
                        )
                    )
                }
            }
            else -> {
                NetworkResource.Error(
                    ErrorResponse(
                        responseCode = result.code(),
                        retrofitErrorResponse = result.errorBody(),
                        errorStatus = ErrorStatus.InvalidError
                    )
                )
            }
        }
    }.onErrorReturn { throwable ->
        when (throwable) {
            is IOException -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.NetworkError
                    )
                )
            }
            is HttpException -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.HTTPError
                    )
                )
            }
            else -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.GotException
                    )
                )
            }
        }
    }
}