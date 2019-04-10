package uk.co.tomek.cvandroid.data.repository

/**
 * Repository abstraction.
 */
interface Repository<T> {
    suspend fun fetchData() : T
}