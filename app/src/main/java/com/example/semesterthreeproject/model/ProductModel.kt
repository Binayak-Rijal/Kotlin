package com.example.semesterthreeproject.model

data class ProductModel(
    val productId: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val category: String = "",
    val imageUrl: String = "",
    val stock: Int = 0
){
    fun toMap() : Map<String, Any>{
        return mapOf(
            "productId" to productId,
            "name" to name,
            "description" to description,
            "price" to price,
            "category" to category,
            "imageUrl" to imageUrl,
            "stock" to stock
        )
    }
}