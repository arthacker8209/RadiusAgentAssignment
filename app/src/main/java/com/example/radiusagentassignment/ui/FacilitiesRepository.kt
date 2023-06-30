package com.example.radiusagentassignment.ui

import com.example.radiusagentassignment.db.models.ApiResponseRealm
import com.example.radiusagentassignment.network.responsehandler.ErrorResponse
import com.example.radiusagentassignment.network.responsehandler.ErrorStatus
import com.example.radiusagentassignment.network.responsehandler.NetworkResource
import com.example.radiusagentassignment.network.service.RadiusApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FacilitiesRepository @Inject constructor(
    private val radiusApiService: RadiusApiService
    ): FacilitiesContract.Repository

 {
     override fun getFacilities(): Single<NetworkResource<ApiResponseRealm>> {
         return radiusApiService.getFacilities()
             .map { response ->
                 if (response.isSuccessful) {
                     val data = response.body()
                     if (data != null) {
                         NetworkResource.Success(data)
                     } else {
                         NetworkResource.Error(
                             ErrorResponse(
                                 responseCode = response.code(),
                                 retrofitErrorResponse = response.errorBody(),
                                 errorStatus = ErrorStatus.ServerError
                             )
                         )
                     }
                 } else {
                     NetworkResource.Error(
                         ErrorResponse(
                             responseCode = response.code(),
                             retrofitErrorResponse = response.errorBody(),
                             errorStatus = ErrorStatus.InvalidError
                         )
                     )
                 }
             }
             .onErrorReturn { throwable ->
                 NetworkResource.Error(
                     ErrorResponse(
                         exception = throwable,
                         errorStatus = ErrorStatus.GotException
                     )
                 )
             }
     }
 }
