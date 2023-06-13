package com.savchukandrew.macaroncoffeeshop.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.savchukandrew.macaroncoffeeshop.data.local.entities.RoomCartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {

    @Query("SELECT * FROM cart_item")
    fun getAll(): Flow<List<RoomCartItemEntity>>

    @Insert
    fun insertItem(data: RoomCartItemEntity)

    @Delete
    fun deleteItem(data: RoomCartItemEntity)
}