package se.futurememories.flickery

import android.app.Activity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.activity_photo.*
import se.futurememories.flickery.models.Photo
import se.futurememories.flickery.util.FitWidthTransform
import se.futurememories.flickery.util.Screen

class PhotoActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val photo = intent.extras!!.getParcelable<Photo>(INTENT_KEY_PHOTO)!!
        initLayout(photo)
    }

    private fun initLayout(photo: Photo) {
        val screen = Screen(this)
        Glide.with(this)
            .load(photo.getPhotoURL())
            .transform(FitWidthTransform())
            .override(screen.x, screen.y)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(photoActivityImageView)
    }

    companion object {
        const val INTENT_KEY_PHOTO = "flickery::photo"
    }
}