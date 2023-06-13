package com.savchukandrew.macaroncoffeeshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.savchukandrew.macaroncoffeeshop.data.local.entities.RoomCartItemEntity

@Database(
    entities = [RoomCartItemEntity::class],
    version = 1
)
abstract class CartDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao
}