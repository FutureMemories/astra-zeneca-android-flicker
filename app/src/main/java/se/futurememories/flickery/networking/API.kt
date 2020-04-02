package se.futurememories.flickery.networking

import android.net.Uri
import se.futurememories.flickery.BuildConfig
import se.futurememories.flickery.models.FlickerResponse

object API {
    private const val SCHEME = "https"
    private const val API_URL = "api.flickr.com/services/rest"

    private const val QUERY_KEY_API_KEY = "api_key"
    private const val QUERY_KEY_TAGS = "tags"
    private const val QUERY_KEY_PAGE = "page"
    private val QUERY_METHOD = "method" to "flickr.photos.search"
    private val QUERY_FORMAT = "format" to "json"
    private val QUERY_NO_JSON_CALLBACK = "nojsoncallback" to "1"

    fun search(tags: List<String>, page: Int): Response<FlickerResponse> {
        val params = mapOf(
            QUERY_KEY_API_KEY to BuildConfig.FLICKR_API_KEY,
            QUERY_METHOD,
            QUERY_FORMAT,
            QUERY_NO_JSON_CALLBACK,
            QUERY_KEY_PAGE to page.toString(),
            QUERY_KEY_TAGS to tags.joinToString(",")
        )

        val uri  = Uri.Builder().apply {
                scheme(SCHEME)
                encodedAuthority(API_URL)
                for ((k, v) in params) {
                    appendQueryParameter(k, v)
                }
            }.build()

        val request = Request<FlickerResponse>(uri)
        val response = request.fetch<FlickerResponse>()
        when (response) {
            is Response.Success -> {
                if (response.obj.isSuccess()) {
                    return response
                } else {
                    return Response.Error(response.obj.code ?: FlickrErrors.UNKNOWN_ERROR)
                }
            }
            is Response.Error -> return response
        }

    }
}