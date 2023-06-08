package com.savchukandrew.macaroncoffeeshop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutinesModule {

    @Provides
    @Singleton
    fun provideDispatcherIO() : CoroutineDispatcher{
        return Dispatchers.IO
    }
}