package com.ncl.ships.presentation.ship

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ncl.ships.domain.models.ShipModel
import com.ncl.ships.domain.usecases.GetShipInformationUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class ShipDetailsViewModel(private val getShipInformationUseCase: GetShipInformationUseCase) : ViewModel(), LifecycleObserver {
    private val disposables = CompositeDisposable()
    var onShipInformationLoaded: MutableLiveData<ShipModel> private set
    var onShipInformationNotLoaded: MutableLiveData<Throwable> private set

    init {
        onShipInformationLoaded = MutableLiveData()
        onShipInformationNotLoaded = MutableLiveData()
    }

    fun loadShipInformation(shipCode: String) {
        getShipInformationUseCase(shipCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ shipModel ->
                onShipInformationLoaded.postValue(shipModel)
            }, { error ->
                onShipInformationNotLoaded.postValue(error)
            }).addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
