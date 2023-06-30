package com.example.radiusagentassignment.db.models

import io.realm.RealmObject

open class ExclusionRealm(
    val facility_id: String,
    val options_id: String
): RealmObject()