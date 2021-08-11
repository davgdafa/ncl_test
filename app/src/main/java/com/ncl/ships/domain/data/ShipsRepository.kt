package com.ncl.ships.domain.data

import com.ncl.ships.domain.models.ShipModel
import io.reactivex.rxjava3.core.Single

interface ShipsRepository {
    fun getShipInformation(shipCode: String): Single<ShipModel>
}
