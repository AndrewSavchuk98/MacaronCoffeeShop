package com.savchukandrew.macaroncoffeeshop.data.remote

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class FireBaseProductDataSourceTest{

    @Test
    fun `check product data`() = runBlocking{
        val firestore = Firebase.firestore

        val dataSource = FireBaseProductDataSource(firestore, Dispatchers.IO)
       val result =  dataSource.getProducts()
        println("data is ${result}")


    }
}

