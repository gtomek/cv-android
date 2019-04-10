package uk.co.tomek.cvandroid.data.net

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import uk.co.tomek.cvandroid.data.model.CvModelData

/**
 * Main retrofit networking service.
 */
interface NetworkService {

    @GET("cvdata.json")
    fun getCvDataAsync(): Deferred<CvModelData>
}