package com.savchukandrew.macaroncoffeeshop.data.remote.entities

import com.google.firebase.firestore.DocumentReference

data class PriceRemoteEntity(
    val id_doping: DocumentReference? = null,
    val id_product: DocumentReference? = null,
    val id_size: DocumentReference? = null,
    val price: Int = 0
)
