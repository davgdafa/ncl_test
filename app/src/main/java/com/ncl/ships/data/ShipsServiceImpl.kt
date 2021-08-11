package com.ncl.ships.data

import com.google.gson.GsonBuilder
import com.ncl.ships.domain.data.ShipResponseData
import com.ncl.ships.domain.data.ShipsApiInterface
import com.ncl.ships.domain.data.ShipsService
import com.ncl.ships.domain.models.ShipModel
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ShipsServiceImpl(
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(PATH_REQUEST_API)
        .client(OkHttpClient.Builder().build())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()))
        .build(),
    private val api: ShipsApiInterface = retrofit.create(ShipsApiInterface::class.java)
) : ShipsService {
    companion object {
        private const val PATH_REQUEST_API = "https://www.ncl.com/cms-service/cruise-ships/"
        private const val PATH_URL = "https://www.ncl.com/"
    }

    override fun getShipInformation(shipCode: String): Single<ShipModel> =
        api.getShipInformation(shipCode).flatMap { response ->
            if (response.isSuccessful) {
                mapShipInformation(response.body())?.let { shipModel -> Single.just(shipModel) }
                    ?: Single.error(Throwable("There was an error in the response"))
            } else {
                Single.error(Throwable("Result was unsuccessful"))
            }
        }

    // todo separate into a different mapper
    private fun mapShipInformation(data: ShipResponseData?): ShipModel? = data?.let { (shipName, shipFacts, imagePath) ->
        if (shipFacts != null && !shipName.isNullOrBlank() && !shipFacts.crew.isNullOrBlank() && !shipFacts.passengerCapacity.isNullOrBlank()
            && !shipFacts.inauguralDate.isNullOrBlank()
        ) {
            ShipModel(
                shipName = shipName,
                crew = shipFacts.crew!!,
                passengerCapacity = shipFacts.passengerCapacity!!,
                inauguralDate = shipFacts.inauguralDate!!,
                imagePath = imagePath?.let { "$PATH_URL$imagePath" }
            )
        } else {
            null
        }
    }
}
