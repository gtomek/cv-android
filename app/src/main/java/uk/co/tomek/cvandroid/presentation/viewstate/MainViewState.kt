package uk.co.tomek.cvandroid.presentation.viewstate

import uk.co.tomek.cvandroid.domain.model.CvModel

/**
 * Representation of the main screen view state.
 */
sealed class MainViewState {
    object Loading : MainViewState()
    data class Error(val throwable: Throwable? = null) : MainViewState()
    data class Data(val itemsResponse: CvModel) : MainViewState()
}