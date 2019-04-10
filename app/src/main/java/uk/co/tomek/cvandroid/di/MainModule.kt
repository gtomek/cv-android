package uk.co.tomek.cvandroid.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uk.co.tomek.cvandroid.data.net.NetworkService

/**
 * KOIN modules declarations.
 */
val networkModule: Module = module {
    single { createOkHttpClient() }
    single {
        creteNetService<NetworkService>(
            get(),
            SERVER_URL
        )
    }
}

fun createOkHttpClient(): OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(logInterceptor)
        .build()
}

inline fun <reified T> creteNetService(httpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    return retrofit.create(T::class.java)
}

const val SERVER_URL = "https://gist.githubusercontent.com/gtomek/6374e18a38b1ff3dd01f04e40795a20d/raw/16936182fd0d5669825cbfe18f26b80d388771ea" //cvdata.json