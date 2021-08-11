package com.ncl.ships.domain.data

import com.google.gson.annotations.SerializedName

data class ShipFactsData(
    @SerializedName("passengerCapacity") var passengerCapacity: String? = null,
    @SerializedName("crew") var crew: String? = null,
    @SerializedName("inauguralDate") var inauguralDate: String? = null
)
