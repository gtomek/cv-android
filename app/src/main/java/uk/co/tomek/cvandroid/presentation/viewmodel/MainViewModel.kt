package uk.co.tomek.cvandroid.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uk.co.tomek.cvandroid.domain.Interactor
import uk.co.tomek.cvandroid.presentation.viewstate.MainViewState

/**
 * ViewModel for the main app screen.
 */
class MainViewModel(private val mainInteractor: Interactor<MainViewState>) : ViewModel() {

    private val mainLiveData = MutableLiveData<MainViewState>()

    private val parentJob = Job()

    init {
        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO + parentJob).launch {
            val mainViewState = mainInteractor.fetchData()
            mainLiveData.postValue(mainViewState)
        }
    }
}