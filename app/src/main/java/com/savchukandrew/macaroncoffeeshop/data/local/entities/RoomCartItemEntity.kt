package com.savchukandrew.macaroncoffeeshop.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cart_item")
data class RoomCartItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String,
    val productName: String,
    val description: String,
    val price: Int,
    val quantity: Int
)