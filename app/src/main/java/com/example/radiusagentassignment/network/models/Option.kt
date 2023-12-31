package com.example.radiusagentassignment.network.models

import io.realm.RealmObject

data class Option(
    val icon: String,
    val id: String,
    val name: String
): RealmObject()