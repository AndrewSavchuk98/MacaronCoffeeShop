package com.savchukandrew.macaroncoffeeshop.data.local.datasource

import com.savchukandrew.macaroncoffeeshop.data.local.CartItemDao
import com.savchukandrew.macaroncoffeeshop.data.local.entities.RoomCartItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCartDataSourceImpl @Inject constructor(
    private val dao: CartItemDao
) : RoomCartDataSource {

    override fun read(): Flow<List<RoomCartItemEntity>> {
        return dao.getAll()
    }

    override suspend fun write(entity: RoomCartItemEntity) {
        dao.insertItem(entity)
    }
}