package com.example.radiusagentassignment.db.models

import io.realm.RealmObject

open class FacilityOptionRealm(
    val icon: String,
    val id: String,
    val name: String
): RealmObject()