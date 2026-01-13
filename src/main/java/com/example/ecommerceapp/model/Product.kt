package com.example.ecommerceapp.model

data class Product(
    var id: String = "",
    val title: String = "",
    val description: String = "",
    val price: Long = 0,
    val imageUrl: String = "",
    val userEmail: String = ""
)
