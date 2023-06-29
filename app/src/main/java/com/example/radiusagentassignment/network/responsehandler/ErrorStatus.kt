package com.example.radiusagentassignment.network.responsehandler

sealed class ErrorStatus {
    object HTTPError : ErrorStatus()
    object NetworkError : ErrorStatus()
    object GotException : ErrorStatus()
    object InvalidError : ErrorStatus()
    object ServerError : ErrorStatus()
}