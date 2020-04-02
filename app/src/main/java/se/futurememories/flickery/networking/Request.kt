package se.futurememories.flickery.networking

import android.net.Uri
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class Request<out T>(
    val uri: Uri,
    var method: String = "GET"
) {

    inline fun <reified T : Any> fetch(): Response<T> {
        val connection = getConnection()
        val responseCode = connection.responseCode
        when {
            responseCode == 200 -> {
                val success = handleSuccess<T>(connection)
                return success
            }
            else -> {
                return Response.Error(FlickrErrors.INTERNAL_SERVER_ERROR)
            }
        }
    }

    fun getConnection(): HttpsURLConnection {
        val connection = URL(uri.toString()).openConnection() as HttpsURLConnection

        connection.apply {
            requestMethod = method
        }
        return connection
    }

    inline fun <reified T : Any> handleSuccess(
        connection: HttpsURLConnection
    ): Response.Success<T> {
        return connection.inputStream.use {
            val br = it.bufferedReader()
            val type = object : TypeToken<T>() {}.type

            val gson = GsonBuilder()
                .create()

                return@use Response.Success<T>(
                    gson.fromJson(br, type)
                )

        }
    }

}