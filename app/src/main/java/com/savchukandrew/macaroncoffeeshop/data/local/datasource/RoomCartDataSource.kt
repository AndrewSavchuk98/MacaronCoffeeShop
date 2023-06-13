package com.savchukandrew.macaroncoffeeshop.data.local.datasource

import com.savchukandrew.macaroncoffeeshop.data.local.entities.RoomCartItemEntity
import kotlinx.coroutines.flow.Flow

interface RoomCartDataSource {

    fun read(): Flow<List<RoomCartItemEntity>>

    suspend fun write(entity: RoomCartItemEntity)
}