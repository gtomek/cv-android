package uk.co.tomek.cvandroid.data.repository

import uk.co.tomek.cvandroid.data.model.CvModelData
import uk.co.tomek.cvandroid.data.net.NetworkService

/**
 * Repository implementation for Cv Data.
 */
class CvRepository(private val networkService: NetworkService) : Repository<CvModelData> {
    override suspend fun fetchData(): CvModelData =
        networkService.getCvDataAsync().await()
}