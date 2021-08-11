package com.ncl.ships.domain.data

import com.google.gson.annotations.SerializedName

data class ShipResponseData(
    @SerializedName("shipName") var shipName: String? = null,
    @SerializedName("shipFacts") var shipFacts: ShipFactsData? = null,
    @SerializedName("bgeImagePath") var imagePath: String? = null,
)
