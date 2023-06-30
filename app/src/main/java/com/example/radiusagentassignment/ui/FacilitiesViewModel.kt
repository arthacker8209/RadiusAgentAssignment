package com.example.radiusagentassignment.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.radiusagentassignment.common.RxJavaSchedulerProvider
import com.example.radiusagentassignment.common.ViewState
import com.example.radiusagentassignment.db.models.ApiResponseRealm
import com.example.radiusagentassignment.network.responsehandler.ErrorResponse
import com.example.radiusagentassignment.network.responsehandler.ErrorStatus
import com.example.radiusagentassignment.network.responsehandler.NetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FacilitiesViewModel @Inject constructor(
    private val dispatcherProvider: RxJavaSchedulerProvider,
    private val repository: FacilitiesContract.Repository
) : ViewModel() {
    private val _facilitiesLiveData = MutableLiveData<ViewState<ApiResponseRealm>>()
    val facilitiesLiveData: LiveData<ViewState<ApiResponseRealm>> get() = _facilitiesLiveData

    @SuppressLint("CheckResult")
    fun fetchFacilitiesData() {
        _facilitiesLiveData.postValue(ViewState.Loading)
        repository.getFacilities()
            .map { networkResource ->
                when (networkResource) {
                    is NetworkResource.Success -> {
                        ViewState.Success(networkResource.data)
                    }
                    is NetworkResource.Error -> {
                        ViewState.Error(networkResource.errorResponse.exception.toString())
                    }
                }
            }
            .subscribeOn(dispatcherProvider.ioScheduler)
            .observeOn(dispatcherProvider.mainScheduler)
            .subscribe({result ->
                _facilitiesLiveData.postValue(result)
            }, { error ->
                _facilitiesLiveData.postValue(ViewState.Error(
                    ErrorResponse(
                        exception = error,
                        errorStatus = ErrorStatus.GotException
                    ).toString())
                )
            }
            )
    }
}