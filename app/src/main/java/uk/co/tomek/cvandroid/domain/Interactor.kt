package uk.co.tomek.cvandroid.domain

/**
 * Interactor/Use case abstraction used to interact between data and presentation layers
 */
interface Interactor<T> {
    suspend fun fetchData(): T
}