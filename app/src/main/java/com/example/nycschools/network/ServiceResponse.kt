package com.example.nycschools.network

sealed class ServiceResponse<out T> {
    data class Success<out T>(val data: T) : ServiceResponse<T>()
    data class Error<out T>(val error: String, val message: String = "") : ServiceResponse<T>()
}

