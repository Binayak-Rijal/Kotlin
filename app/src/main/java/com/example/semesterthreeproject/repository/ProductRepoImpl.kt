package com.example.semesterthreeproject.repository

import com.example.semesterthreeproject.model.ProductModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductRepoImpl : ProductRepo {

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    val ref: DatabaseReference = database.getReference("products")

    override fun addProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).setValue(model).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Product added successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun updateProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).setValue(model).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Product updated successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).removeValue().addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Product deleted successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    ) {
        ref.child(productId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val product = snapshot.getValue(ProductModel::class.java)
                        if (product != null) {
                            callback(true, "Product fetched successfully", product)
                        }
                    } else {
                        callback(false, "Product not found", null)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false, error.message, null)
                }
            })
    }

    override fun getAllProducts(callback: (Boolean, String, List<ProductModel>) -> Unit) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val allProducts = mutableListOf<ProductModel>()
                    for (data in snapshot.children) {
                        val product = data.getValue(ProductModel::class.java)
                        if (product != null) {
                            allProducts.add(product)
                        }
                    }
                    callback(true, "Products fetched successfully", allProducts)
                } else {
                    callback(false, "No products found", emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false, error.message, emptyList())
            }
        })
    }

    override fun getProductsByCategory(
        category: String,
        callback: (Boolean, String, List<ProductModel>) -> Unit
    ) {
        ref.orderByChild("category").equalTo(category)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val products = mutableListOf<ProductModel>()
                        for (data in snapshot.children) {
                            val product = data.getValue(ProductModel::class.java)
                            if (product != null) {
                                products.add(product)
                            }
                        }
                        callback(true, "Products fetched successfully", products)
                    } else {
                        callback(false, "No products found in this category", emptyList())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false, error.message, emptyList())
                }
            })
    }

    override fun searchProducts(
        query: String,
        callback: (Boolean, String, List<ProductModel>) -> Unit
    ) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val searchResults = mutableListOf<ProductModel>()
                    for (data in snapshot.children) {
                        val product = data.getValue(ProductModel::class.java)
                        if (product != null) {
                            // Search in product name and description
                            if (product.name.contains(query, ignoreCase = true) ||
                                product.description.contains(query, ignoreCase = true)
                            ) {
                                searchResults.add(product)
                            }
                        }
                    }
                    if (searchResults.isNotEmpty()) {
                        callback(true, "Search completed", searchResults)
                    } else {
                        callback(false, "No products found matching your search", emptyList())
                    }
                } else {
                    callback(false, "No products available", emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false, error.message, emptyList())
            }
        })
    }

    override fun updateStock(
        productId: String,
        newStock: Int,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).child("stock").setValue(newStock).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Stock updated successfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }
}