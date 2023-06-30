package com.example.radiusagentassignment.network

import com.example.radiusagentassignment.BuildConfig
import com.example.radiusagentassignment.network.interceptor.NetworkInterceptor
import com.example.radiusagentassignment.network.interceptor.NetworkStateChecker
import com.example.radiusagentassignment.network.service.RadiusApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(networkStateChecker: NetworkStateChecker): OkHttpClient{
        val timeOutInSeconds = 120
        val builder = OkHttpClient.Builder()
            .addInterceptor(NetworkInterceptor(networkStateChecker))
            .readTimeout(timeOutInSeconds.toLong(), TimeUnit.SECONDS)
            .connectTimeout(timeOutInSeconds.toLong(), TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRadiusApiService(retrofit: Retrofit) = retrofit.create(RadiusApiService::class.java)
}