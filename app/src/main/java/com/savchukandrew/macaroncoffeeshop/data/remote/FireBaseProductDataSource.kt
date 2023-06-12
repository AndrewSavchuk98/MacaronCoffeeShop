package com.savchukandrew.macaroncoffeeshop.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.savchukandrew.macaroncoffeeshop.data.remote.entities.ProductRemoteEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FireBaseProductDataSource @Inject constructor(
    private val db: FirebaseFirestore,
    private val dispatcher: CoroutineDispatcher
) : RemoteProductDataSource {

    override suspend fun getProducts(): List<ProductRemoteEntity> = withContext(dispatcher) {
        val snapshot = db.collection("product").get().await()
        snapshot.documents.mapNotNull {
           it.toObject(ProductRemoteEntity::class.java)?.copy(id = it.id)
        }
    }

    override suspend fun getProductById(productId: String): ProductRemoteEntity =
        withContext(dispatcher) {
            val snapshot = db.collection("product").document(productId).get().await()
            snapshot.toObject(ProductRemoteEntity::class.java)
                ?: throw IllegalArgumentException("No equals ids")
        }


}