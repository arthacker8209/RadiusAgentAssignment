package com.example.radiusagentassignment.network.models

import io.realm.RealmObject

data class Exclusion(
    val facility_id: String,
    val options_id: String
): RealmObject()