package com.ncl.ships.di

import com.ncl.ships.data.ShipsRemoteDataSourceImpl
import com.ncl.ships.data.ShipsRepositoryImpl
import com.ncl.ships.data.ShipsServiceImpl
import com.ncl.ships.domain.data.ShipsRemoteDataSource
import com.ncl.ships.domain.data.ShipsRepository
import com.ncl.ships.domain.data.ShipsService
import com.ncl.ships.domain.usecases.GetShipInformationUseCase
import com.ncl.ships.domain.usecases.GetShipInformationUseCaseImpl
import com.ncl.ships.presentation.ship.ShipDetailsViewModel
import com.ncl.ships.presentation.ships.ShipsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<ShipsRepository> { ShipsRepositoryImpl(get()) }
    factory<ShipsRemoteDataSource> { ShipsRemoteDataSourceImpl(get()) }
    factory<ShipsService> { ShipsServiceImpl() }
}

val domainModule = module {
    factory<GetShipInformationUseCase> { GetShipInformationUseCaseImpl(get()) }
}

val presentationModule = module {
    viewModel { ShipsViewModel() }
    viewModel { ShipDetailsViewModel(get()) }
}
