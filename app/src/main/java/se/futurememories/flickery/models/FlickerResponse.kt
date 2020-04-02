package se.futurememories.flickery.models

data class FlickerResponse(
    val stat: String,
    val photos: Photos?,
    val code: Int?,
    val message: String?
) {
    fun isSuccess(): Boolean {
        return stat == "ok"
    }
}