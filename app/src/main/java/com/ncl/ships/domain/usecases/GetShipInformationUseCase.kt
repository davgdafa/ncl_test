package com.ncl.ships.domain.usecases

import com.ncl.ships.domain.models.ShipModel
import io.reactivex.rxjava3.core.Single

interface GetShipInformationUseCase {
    operator fun invoke(shipCode: String): Single<ShipModel>
}
