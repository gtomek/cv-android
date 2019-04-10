package uk.co.tomek.cvandroid.domain

import uk.co.tomek.cvandroid.data.model.CvModelData
import uk.co.tomek.cvandroid.data.repository.Repository
import uk.co.tomek.cvandroid.presentation.viewstate.MainViewState

/**
 * Main interactor implementation.
 */
class CvInteractor(
    private val repository: Repository<CvModelData>,
    private val mapper: CvDataMapper
) : Interactor<MainViewState> {

    override suspend fun fetchData(): MainViewState {
        return try {
            val receivedCvModelData = repository.fetchData()
            MainViewState.Data(mapper.mapCvDataToPresentation(receivedCvModelData))
        } catch (exception: Exception) {
            MainViewState.Error(exception)
        }

    }

}