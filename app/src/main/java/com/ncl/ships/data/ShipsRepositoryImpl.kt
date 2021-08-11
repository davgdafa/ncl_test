package com.ncl.ships.data

import com.ncl.ships.domain.models.ShipModel
import com.ncl.ships.domain.data.ShipsRemoteDataSource
import com.ncl.ships.domain.data.ShipsRepository
import io.reactivex.rxjava3.core.Single

class ShipsRepositoryImpl(private val remotely: ShipsRemoteDataSource): ShipsRepository {
    override fun getShipInformation(shipCode: String): Single<ShipModel> = remotely.getShipInformation(shipCode)
}
