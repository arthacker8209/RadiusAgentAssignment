package com.example.radiusagentassignment.network.models

import com.example.radiusagentassignment.db.models.FacilityOptionRealm
import io.realm.RealmObject

data class Facility(
    val facility_id: String,
    val name: String,
    val options: List<FacilityOptionRealm>
): RealmObject()