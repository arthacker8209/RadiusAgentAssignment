package com.example.radiusagentassignment.network.models

import com.example.radiusagentassignment.db.models.ExclusionRealm
import com.example.radiusagentassignment.db.models.FacilityRealm
import io.realm.RealmObject

data class Facilities(
    val exclusions: List<List<ExclusionRealm>>,
    val facilities: List<FacilityRealm>
): RealmObject()