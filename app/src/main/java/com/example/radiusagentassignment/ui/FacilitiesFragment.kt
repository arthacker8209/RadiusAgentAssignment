package com.example.radiusagentassignment.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.radiusagentassignment.R
import com.example.radiusagentassignment.common.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FacilitiesFragment : Fragment() {

    companion object {
        const val TAG = "FacilitiesFragment"
        fun newInstance() = FacilitiesFragment()
    }

    private lateinit var viewModel: FacilitiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_facilities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FacilitiesViewModel::class.java]
        viewModel.fetchFacilitiesData()
        setObservers()
    }

    private fun setObservers(){
        viewModel.facilitiesLiveData.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is ViewState.Loading -> {
                }
                is ViewState.Success -> {
                    val facilities = viewState.data
                   Log.d(TAG, facilities.facilities.toString())
                }

                is ViewState.Error -> {
                    val error = viewState.errorMessage
                    Log.d(TAG,error)
                }
            }
        }
    }

    private fun swipeToRefresh(){
//        swipeRefreshLayout.setOnRefreshListener {
//            fetchFacilitiesData()
//        }
    }
}