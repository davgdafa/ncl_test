package com.ncl.ships.domain.models

data class ShipModel(
    val shipName: String,
    val passengerCapacity: String,
    val crew: String,
    val inauguralDate: String,
    val imagePath: String?
)
