package com.example.radiusagentassignment.network.responsehandler

import com.example.radiusagentassignment.network.responsehandler.ErrorStatus
import okhttp3.ResponseBody

data class ErrorResponse(
    val responseCode: Int? = 0,
    val retrofitErrorResponse: ResponseBody? = null,
    val errorStatus: ErrorStatus,
    val exception: Throwable? = null,
)
