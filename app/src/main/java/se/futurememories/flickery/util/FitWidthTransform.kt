package se.futurememories.flickery.util

import android.graphics.Bitmap
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.nio.ByteBuffer
import java.nio.IntBuffer
import java.security.MessageDigest

class FitWidthTransform(): BitmapTransformation() {
    private val ID = "se.futurememories.flickery.utils.FitWidthTransform"
    private val ID_BYTES = ID.toByteArray(charset(Key.STRING_CHARSET_NAME))
    private var mBmpWidth = 0
    private var mBmpHeight = 0

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val oldHeight = toTransform.height
        val oldWidth = toTransform.width
        val aspectRatio = oldHeight.toFloat() / oldWidth.toFloat()
        val newHeight = (outWidth.toFloat() * aspectRatio).toInt()

        mBmpHeight = newHeight
        mBmpWidth = outWidth
        return Bitmap.createScaledBitmap(toTransform, outWidth, newHeight, true)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
        messageDigest.update(ByteBuffer.allocate(Integer.SIZE).putInt(mBmpWidth))
        messageDigest.update(ByteBuffer.allocate(Integer.SIZE).putInt(mBmpHeight))
    }
}