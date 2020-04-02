package se.futurememories.flickery.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val farm: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String
): Parcelable {
    fun getPhotoThumbnail(): String = "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}_m.jpg"
    fun getPhotoURL(): String = "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}.jpg"
}