package com.ncl.ships.domain.data

import com.ncl.ships.domain.models.ShipModel
import io.reactivex.rxjava3.core.Single

interface ShipsService {
    fun getShipInformation(shipCode: String): Single<ShipModel>
}
