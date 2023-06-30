package com.example.radiusagentassignment.db.realm

import com.example.radiusagentassignment.db.models.ApiResponseRealm
import com.example.radiusagentassignment.db.models.ExclusionRealm
import com.example.radiusagentassignment.db.models.FacilityRealm
import com.example.radiusagentassignment.db.models.FacilityOptionRealm
import com.example.radiusagentassignment.network.models.Facilities
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList

object RealmManager {

    private val realmConfig: RealmConfiguration by lazy {
        RealmConfiguration.Builder()
            .name("myrealm.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
    }

    fun getRealmInstance(): Realm = Realm.getInstance(realmConfig)

    // Add methods to insert, update, delete, and query data from Realm as needed

    // Example method to insert ApiResponse into Realm
    fun saveApiResponse(apiResponse: Facilities) {
        val realm = getRealmInstance()
        realm.executeTransaction { transactionRealm ->
            transactionRealm.copyToRealmOrUpdate(mapApiResponseToRealm(apiResponse))
        }
        realm.close()
    }

    // Example method to retrieve ApiResponse from Realm
    fun getApiResponse(): Facilities? {
        val realm = getRealmInstance()
        val result = realm.where(Facilities::class.java).findFirst()
        val apiResponse = result?.let { mapApiResponseRealmToApiResponse(it) }
        realm.close()
        return apiResponse
    }

    // Helper methods to map between Realm and regular data classes
    private fun mapApiResponseToRealm(apiResponse: Facilities): ApiResponseRealm {
        val facilitiesRealm = RealmList<FacilityRealm>()
        apiResponse.facilities.forEach { facility ->
            val optionsRealm = RealmList<FacilityOptionRealm>()
            facility.options.forEach { option ->
                optionsRealm.add(FacilityOptionRealm(option.name, option.icon, option.id))
            }
            facilitiesRealm.add(FacilityRealm(facility.facility_id, facility.name, optionsRealm))
        }

        val exclusionsRealm = RealmList<RealmList<ExclusionRealm>>()
        apiResponse.exclusions.forEach { exclusionList ->
            val exclusionRealmList = RealmList<ExclusionRealm>()
            exclusionList.forEach { exclusion ->
                exclusionRealmList.add(ExclusionRealm(exclusion.facility_id, exclusion.options_id))
            }
            exclusionsRealm.add(exclusionRealmList)
        }

        return ApiResponseRealm(facilitiesRealm, exclusionsRealm)
    }

    private fun mapApiResponseRealmToApiResponse(apiResponseRealm: ApiResponseRealm): Facilities {
        val facilities = mutableListOf<FacilityRealm>()
        apiResponseRealm.facilities.forEach { facilityRealm ->
            val options = mutableListOf<FacilityOptionRealm>()
            facilityRealm.options.forEach { optionRealm ->
                options.add(FacilityOptionRealm(optionRealm.name, optionRealm.icon, optionRealm.id))
            }
            facilities.add(FacilityRealm(facilityRealm.facility_id, facilityRealm.name, options))
        }
    }
}