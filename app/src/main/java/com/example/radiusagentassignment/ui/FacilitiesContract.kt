package com.example.radiusagentassignment.ui

import com.example.radiusagentassignment.db.models.ApiResponseRealm
import com.example.radiusagentassignment.network.responsehandler.NetworkResource
import io.reactivex.rxjava3.core.Single

class FacilitiesContract {
    interface Repository{
        fun getFacilities(): Single<NetworkResource<ApiResponseRealm>>
    }
}