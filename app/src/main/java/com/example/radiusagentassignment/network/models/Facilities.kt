package com.example.radiusagentassignment.network.models

data class Facilities(
    val exclusions: List<List<Exclusion>>,
    val facilities: List<Facility>
)