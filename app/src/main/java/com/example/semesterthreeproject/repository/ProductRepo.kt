package com.example.semesterthreeproject.repository

import com.example.semesterthreeproject.model.ProductModel

interface ProductRepo {

    fun addProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    )

    fun updateProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    )

    fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    )

    fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    )

    fun getAllProducts(
        callback: (Boolean, String, List<ProductModel>) -> Unit
    )

    fun getProductsByCategory(
        category: String,
        callback: (Boolean, String, List<ProductModel>) -> Unit
    )

    fun searchProducts(
        query: String,
        callback: (Boolean, String, List<ProductModel>) -> Unit
    )

    fun updateStock(
        productId: String,
        newStock: Int,
        callback: (Boolean, String) -> Unit
    )
}