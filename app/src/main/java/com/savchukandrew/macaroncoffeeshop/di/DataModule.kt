package com.savchukandrew.macaroncoffeeshop.di

import com.savchukandrew.macaroncoffeeshop.data.local.CartRepositoryImp
import com.savchukandrew.macaroncoffeeshop.data.local.datasource.RoomCartDataSource
import com.savchukandrew.macaroncoffeeshop.data.local.datasource.RoomCartDataSourceImpl
import com.savchukandrew.macaroncoffeeshop.data.remote.FireBaseProductDataSource
import com.savchukandrew.macaroncoffeeshop.data.remote.ProductRepositoryImpl
import com.savchukandrew.macaroncoffeeshop.data.remote.RemoteProductDataSource
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartRepository
import com.savchukandrew.macaroncoffeeshop.features.detail.domain.ProductDetailRepository
import com.savchukandrew.macaroncoffeeshop.features.home.domain.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindFirebaseDataStore(fireBaseProductDataSource: FireBaseProductDataSource): RemoteProductDataSource

    @Binds
    @Singleton
    fun bindProductDetailRepository(repository: ProductRepositoryImpl): ProductDetailRepository

    @Binds
    @Singleton
    fun bindProductRepository(repository: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    fun bindRoomCartDataSource(dataSource: RoomCartDataSourceImpl): RoomCartDataSource

    @Binds
    @Singleton
    fun bindCartRepository(repository: CartRepositoryImp): CartRepository

}