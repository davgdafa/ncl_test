package com.ncl.ships.data

import com.ncl.ships.domain.models.ShipModel
import com.ncl.ships.domain.data.ShipsRemoteDataSource
import com.ncl.ships.domain.data.ShipsService
import io.reactivex.rxjava3.core.Single

class ShipsRemoteDataSourceImpl(private val service: ShipsService): ShipsRemoteDataSource {
    override fun getShipInformation(shipCode: String): Single<ShipModel> = service.getShipInformation(shipCode)
}
