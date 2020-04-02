package se.futurememories.flickery.networking

import android.content.Context
import se.futurememories.flickery.R

object FlickrErrors {
    const val UNKNOWN_ERROR = -1
    const val NO_NETWORK = -2
    const val ERR_NO_PARAMS = 3
    const val INTERNAL_SERVER_ERROR = 500

    private val errorToMessage = mapOf(
        UNKNOWN_ERROR to R.string.error_unknown,
        NO_NETWORK to R.string.error_no_network,
        ERR_NO_PARAMS to R.string.error_no_tags,
        INTERNAL_SERVER_ERROR to R.string.error_internal_server
    )

    fun getErrorMessage(context: Context, error: Response.Error): String {
        return context.getString(errorToMessage[error.errorCode] ?: R.string.error_unknown)
    }
}