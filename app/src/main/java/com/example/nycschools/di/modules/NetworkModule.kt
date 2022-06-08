package com.example.nycschools.di.modules

import android.content.Context
import android.net.ConnectivityManager
import com.example.nycschools.R
import com.example.nycschools.network.SchoolsService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideHighSchoolService(client: OkHttpClient) : SchoolsService{
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(SchoolsService::class.java)
    }

    @Provides
    fun provideClient(context: Context,connectivityManager: ConnectivityManager): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            )
            .addInterceptor(Interceptor { chain ->
                if (connectivityManager.activeNetwork==null)
                    throw IOException( context.resources.getString(R.string.no_internet))
                chain.proceed(chain.request().newBuilder().build())
            })
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    companion object {
        const val  BASE_URL = "https://data.cityofnewyork.us/resource/"
    }
}