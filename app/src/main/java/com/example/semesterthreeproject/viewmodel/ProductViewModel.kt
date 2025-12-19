package com.example.semesterthreeproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semesterthreeproject.model.ProductModel
import com.example.semesterthreeproject.repository.ProductRepo

class ProductViewModel(val repo: ProductRepo) : ViewModel() {

    private val _product = MutableLiveData<ProductModel?>()
    val product: MutableLiveData<ProductModel?> get() = _product

    private val _allProducts = MutableLiveData<List<ProductModel>?>()
    val allProducts: MutableLiveData<List<ProductModel>?> get() = _allProducts

    private val _productsByCategory = MutableLiveData<List<ProductModel>?>()
    val productsByCategory: MutableLiveData<List<ProductModel>?> get() = _productsByCategory

    private val _searchResults = MutableLiveData<List<ProductModel>?>()
    val searchResults: MutableLiveData<List<ProductModel>?> get() = _searchResults

    private val _loading = MutableLiveData<Boolean>()
    val loading: MutableLiveData<Boolean> get() = _loading

    fun addProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        repo.addProduct(productId, model, callback)
    }

    fun updateProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        repo.updateProduct(productId, model, callback)
    }

    fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        repo.deleteProduct(productId, callback)
    }

    fun getProductById(productId: String) {
        _loading.postValue(true)
        repo.getProductById(productId) { success, msg, data ->
            if (success) {
                _loading.postValue(false)
                _product.postValue(data)
            } else {
                _loading.postValue(false)
                _product.postValue(null)
            }
        }
    }

    fun getAllProducts() {
        _loading.postValue(true)
        repo.getAllProducts { success, msg, data ->
            if (success) {
                _loading.postValue(false)
                _allProducts.postValue(data)
            } else {
                _loading.postValue(false)
                _allProducts.postValue(emptyList())
            }
        }
    }

    fun getProductsByCategory(category: String) {
        _loading.postValue(true)
        repo.getProductsByCategory(category) { success, msg, data ->
            if (success) {
                _loading.postValue(false)
                _productsByCategory.postValue(data)
            } else {
                _loading.postValue(false)
                _productsByCategory.postValue(emptyList())
            }
        }
    }

    fun searchProducts(query: String) {
        _loading.postValue(true)
        repo.searchProducts(query) { success, msg, data ->
            if (success) {
                _loading.postValue(false)
                _searchResults.postValue(data)
            } else {
                _loading.postValue(false)
                _searchResults.postValue(emptyList())
            }
        }
    }

    fun updateStock(
        productId: String,
        newStock: Int,
        callback: (Boolean, String) -> Unit
    ) {
        repo.updateStock(productId, newStock, callback)
    }
}