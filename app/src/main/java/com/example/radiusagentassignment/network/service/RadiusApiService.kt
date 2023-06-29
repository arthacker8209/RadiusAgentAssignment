package com.example.radiusagentassignment.network.service

import retrofit2.http.GET

interface RadiusApiService {
    @GET("iranjith4/ad-assignment/db")
    fun getFacilities()
}
