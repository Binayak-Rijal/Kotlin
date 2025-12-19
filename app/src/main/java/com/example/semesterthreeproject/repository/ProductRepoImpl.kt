package com.example.semesterthreeproject.repository

import com.example.semesterthreeproject.model.ProductModel
import com.google.firebase.database.*

class ProductRepoImpl : ProductRepo {

    private val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("products")

    override fun addProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).setValue(model).addOnCompleteListener {
            callback(it.isSuccessful, it.exception?.message ?: "Success")
        }
    }

    override fun updateProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).setValue(model).addOnCompleteListener {
            callback(it.isSuccessful, it.exception?.message ?: "Success")
        }
    }

    override fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).removeValue().addOnCompleteListener {
            callback(it.isSuccessful, it.exception?.message ?: "Success")
        }
    }

    override fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    ) {
        ref.child(productId)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val product = snapshot.getValue(ProductModel::class.java)
                    callback(true, "Done", product)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false, error.message, null)
                }
            })
    }

    override fun getAllProducts(
        callback: (Boolean, String, List<ProductModel>) -> Unit
    ) {
        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(ProductModel::class.java)
                }
                callback(true, "Done", list)
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
        ref.orderByChild("category")
            .equalTo(category)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = snapshot.children.mapNotNull {
                        it.getValue(ProductModel::class.java)
                    }
                    callback(true, "Done", list)
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
        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val result = snapshot.children.mapNotNull {
                    it.getValue(ProductModel::class.java)
                }.filter {
                    it.name.contains(query, ignoreCase = true) ||
                            it.description.contains(query, ignoreCase = true)
                }

                callback(true, "Done", result)
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
        ref.child(productId).child("stock").setValue(newStock)
            .addOnCompleteListener {
                callback(it.isSuccessful, it.exception?.message ?: "Success")
            }
    }
}
