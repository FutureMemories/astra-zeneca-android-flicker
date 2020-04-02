package se.futurememories.flickery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.item_photo.view.*
import se.futurememories.flickery.models.Photo

class PhotosRecyclerAdapter(
    private val photos: MutableList<Photo>,
    private val onClickPhoto: (photo: Photo) -> Unit
): RecyclerView.Adapter<PhotosRecyclerAdapter.PhotoHolder>() {

    fun addPhotos(newPhotos: List<Photo>) {
        val startPos = photos.size
        photos.addAll(newPhotos)
        notifyItemRangeInserted(startPos, newPhotos.size)
    }

    override fun getItemCount(): Int = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photos[position], onClickPhoto)
    }

    class PhotoHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(photo: Photo, onClickPhoto: (photo: Photo) -> Unit) = with(view) {
            setOnClickListener { onClickPhoto(photo) }
            Glide.with(this)
                .load(photo.getPhotoThumbnail())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(photoItemImageView)
        }
    }
}