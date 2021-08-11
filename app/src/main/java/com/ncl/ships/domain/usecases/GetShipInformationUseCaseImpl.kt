package com.ncl.ships.domain.usecases

import com.ncl.ships.domain.data.ShipsRepository
import com.ncl.ships.domain.models.ShipModel
import io.reactivex.rxjava3.core.Single

internal class GetShipInformationUseCaseImpl(private val repository: ShipsRepository): GetShipInformationUseCase {
    override fun invoke(shipCode: String): Single<ShipModel> = repository.getShipInformation(shipCode)
}
