package com.savchukandrew.macaroncoffeeshop.data.remote.entities

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class ProductRemoteEntity(
    val id: String = "",
    @PropertyName(value = "id_category")
    val category: String = "",
    @PropertyName(value = "name")
    val name: String = "",
    @PropertyName(value = "image")
    val image: String = "",
    @PropertyName(value = "description")
    val description: String = "",
    @PropertyName(value = "date_created")
    val date_created: Timestamp = Timestamp.now(),
    val price: Int = 0,
    val size: String = "Medium"
)
