package com.example.radiusagentassignment.network

import com.example.radiusagentassignment.network.interceptor.NetworkStateChecker
import com.example.radiusagentassignment.network.interceptor.NetworkStateCheckerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkBindingModule {
    @Binds
    fun bindNetworkStateChecker(networkStateCheckerImpl: NetworkStateCheckerImpl): NetworkStateChecker
}