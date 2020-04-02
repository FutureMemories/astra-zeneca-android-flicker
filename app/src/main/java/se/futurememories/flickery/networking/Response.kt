package se.futurememories.flickery.networking

sealed class Response<out T> {
    data class Success<T>(val obj: T) : Response<T>()
    data class Error(val errorCode: Int) : Response<Nothing>()
}