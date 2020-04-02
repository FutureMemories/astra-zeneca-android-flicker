package se.futurememories.flickery

import org.junit.Test

import org.junit.Assert.*
import se.futurememories.flickery.models.FlickerResponse

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ModelTests {
    @Test
    fun response_isCorrect() {
        val response = FlickerResponse("ok", null, 123, null)
        assertTrue(response.isSuccess())
        val failedResponse = FlickerResponse("fail", null, null, null)
        assertFalse(failedResponse.isSuccess())
    }

}
