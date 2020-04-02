package se.futurememories.flickery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import se.futurememories.flickery.models.FlickerResponse
import se.futurememories.flickery.models.Photo
import se.futurememories.flickery.networking.FlickrErrors
import se.futurememories.flickery.networking.Response
import se.futurememories.flickery.viewmodels.FlickerViewModel

class MainActivity : AppCompatActivity() {

    private var mPhotosAdapter: PhotosRecyclerAdapter? = null
    private var mPage = 1
    private var mPhotosAreLoading = false


    private val flickerViewModel: FlickerViewModel by lazy {
        ViewModelProvider(this)[FlickerViewModel::class.java]
    }

    // Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLayout()
        flickerViewModel.photosMLD.observe(this, flickerResponseObserver)
        fetchNextPage()
    }

    private fun initLayout() {
        val layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        mPhotosAdapter = PhotosRecyclerAdapter(mutableListOf(), ::onClickPhoto)
        mainActivityRecyclerView.layoutManager = layoutManager
        mainActivityRecyclerView.adapter = mPhotosAdapter
        mainActivityRecyclerView.setHasFixedSize(true)
        mainActivityRecyclerView.addOnScrollListener(scrollListener)
    }

    private fun fetchNextPage() {
        if (mPhotosAreLoading) {
            return
        }
        mPhotosAreLoading = true
        flickerViewModel.fetchPhotos(listOf("nature", "building"), mPage++)
    }

    // Listeners

    private fun onClickPhoto(photo: Photo) {
        val intent = Intent(this, PhotoActivity::class.java)
        intent.putExtra(PhotoActivity.INTENT_KEY_PHOTO, photo)
        startActivity(intent)
    }

    private val scrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1)) {
                fetchNextPage()
            }
        }
    }

    // Observers

    private val flickerResponseObserver = Observer<Response<FlickerResponse>> { response ->
        mPhotosAreLoading = false
        when (response) {
            is Response.Success -> {
                response.obj.photos?.photo?.let {
                    mPhotosAdapter?.addPhotos(it)
                }
            }
            is Response.Error -> {
                val message = FlickrErrors.getErrorMessage(this, response)
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.error))
                    .setMessage(message)
                    .show()
            }
        }
    }
}
