package com.example.radiusagentassignment.ui

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FacilitiesModule {
        @Binds
        fun bindProductRepository(facilitiesRepository: FacilitiesRepository): FacilitiesContract.Repository
}