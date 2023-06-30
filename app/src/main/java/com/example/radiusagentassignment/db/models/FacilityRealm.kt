package com.example.radiusagentassignment.db.models

import io.realm.RealmList
import io.realm.RealmObject

open class FacilityRealm(
    val facility_id: String,
    val name: String,
    val options: RealmList<FacilityOptionRealm> = RealmList()
): RealmObject()