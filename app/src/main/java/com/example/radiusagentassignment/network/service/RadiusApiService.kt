package com.example.radiusagentassignment.network.service

import com.example.radiusagentassignment.db.models.ApiResponseRealm
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface RadiusApiService {
    @GET("iranjith4/ad-assignment/db")
    fun getFacilities(): Single<Response<ApiResponseRealm>>
}
