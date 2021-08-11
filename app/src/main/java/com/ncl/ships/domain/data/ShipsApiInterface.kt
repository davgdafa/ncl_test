package com.ncl.ships.domain.data

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShipsApiInterface {
    @GET("{ship_name}")
    fun getShipInformation(@Path(value = "ship_name", encoded = true) shipCode: String): Single<Response<ShipResponseData>>
}
