package com.savchukandrew.macaroncoffeeshop.di

import com.google.firebase.firestore.FirebaseFirestore
import com.savchukandrew.macaroncoffeeshop.data.remote.FireBaseProductDataSource
import com.savchukandrew.macaroncoffeeshop.data.remote.RemoteProductDataSource
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
}