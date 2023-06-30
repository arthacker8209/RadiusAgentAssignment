package com.example.radiusagentassignment.db.models

import io.realm.RealmList
import io.realm.RealmObject

open class ApiResponseRealm(
    val exclusions: RealmList<RealmList<ExclusionRealm>> = RealmList(),
    val facilities: RealmList<FacilityRealm> = RealmList()
): RealmObject()