package uk.co.tomek.cvandroid.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uk.co.tomek.cvandroid.data.model.CvModelData
import uk.co.tomek.cvandroid.data.net.NetworkService
import uk.co.tomek.cvandroid.data.repository.CvRepository
import uk.co.tomek.cvandroid.data.repository.Repository
import uk.co.tomek.cvandroid.domain.CvDataMapper
import uk.co.tomek.cvandroid.domain.CvInteractor
import uk.co.tomek.cvandroid.domain.Interactor
import uk.co.tomek.cvandroid.presentation.viewmodel.MainViewModel
import uk.co.tomek.cvandroid.presentation.viewstate.MainViewState

/**
 * KOIN modules declarations.
 */
val applicationModule: Module = module {
    factory<Repository<CvModelData>> {
        CvRepository(
            get()
        )
    }
    factory<Interactor<MainViewState>> {
        CvInteractor(
            get(),
            get()
        )
    }
    single { CvDataMapper() }
    viewModel { MainViewModel(get()) }
}

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

const val SERVER_URL = "https://gist.githubusercontent.com/gtomek/6374e18a38b1ff3dd01f04e40795a20d/raw/14b9fafe9de22a8ca4bdb6541b94d35389121f3f/" //cvdata.json