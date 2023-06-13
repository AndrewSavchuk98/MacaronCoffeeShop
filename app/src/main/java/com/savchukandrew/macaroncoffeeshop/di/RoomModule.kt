package com.savchukandrew.macaroncoffeeshop.di

import android.content.Context
import androidx.room.Room
import com.savchukandrew.macaroncoffeeshop.data.local.CartDatabase
import com.savchukandrew.macaroncoffeeshop.data.local.CartItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): CartDatabase {
        return Room.databaseBuilder(
            context,
            CartDatabase::class.java, "database"
        ).build()

    }

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: CartDatabase): CartItemDao {
        return appDatabase.cartItemDao()
    }
}