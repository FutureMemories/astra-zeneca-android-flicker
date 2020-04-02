package se.futurememories.flickery.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import se.futurememories.flickery.models.FlickerResponse
import se.futurememories.flickery.networking.API
import se.futurememories.flickery.networking.FlickrErrors
import se.futurememories.flickery.networking.NetworkHelper
import se.futurememories.flickery.networking.Response
import kotlin.coroutines.CoroutineContext

class FlickerViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default


    val photosMLD: MutableLiveData<Response<FlickerResponse>> by lazy {
        MutableLiveData<Response<FlickerResponse>>()
    }

    fun fetchPhotos(tags: List<String>, page: Int) = launch(
        CoroutineExceptionHandler { _, err ->
            if (!NetworkHelper.isNetworkAvailable(getApplication())) {
                photosMLD.postValue(Response.Error(FlickrErrors.NO_NETWORK))
            } else {
                Log.e("FVM","fetch auth exception: $err")
                photosMLD.postValue(Response.Error(FlickrErrors.UNKNOWN_ERROR))
            }
        }
    ) {
        //TODO: cached response
        photosMLD.postValue(API.search(tags, page))
    }
}