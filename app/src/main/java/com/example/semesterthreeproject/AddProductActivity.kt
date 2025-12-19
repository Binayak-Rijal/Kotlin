package com.example.semesterthreeproject

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.semesterthreeproject.model.ProductModel
import com.example.semesterthreeproject.repository.ProductRepoImpl
import com.example.semesterthreeproject.viewmodel.ProductViewModel

class AddProductActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModel with Repository
        val repo = ProductRepoImpl()
        viewModel = ProductViewModel(repo)

        // Create main layout
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(60, 60, 60, 60)
            gravity = Gravity.CENTER
            setBackgroundColor(Color.WHITE)
        }

        // Title
        val title = TextView(this).apply {
            text = "Add New Product"
            textSize = 24f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 60)
        }

        // Product Name EditText
        val etProductName = EditText(this).apply {
            hint = "Product Name"
            setPadding(30, 30, 30, 30)
            textSize = 16f
        }

        // Product ID EditText
        val etProductId = EditText(this).apply {
            hint = "Product ID"
            setPadding(30, 30, 30, 30)
            textSize = 16f
        }

        // Product Location EditText
        val etProductLocation = EditText(this).apply {
            hint = "Product Location"
            setPadding(30, 30, 30, 30)
            textSize = 16f
        }

        // Add Product Button
        val btnAddProduct = Button(this).apply {
            text = "Add Product"
            textSize = 16f
            setPadding(30, 30, 30, 30)
        }

        // Set layout params with margins
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 0, 0, 40)
        }

        // Add views to layout
        mainLayout.addView(title)
        mainLayout.addView(etProductName, layoutParams)
        mainLayout.addView(etProductId, layoutParams)
        mainLayout.addView(etProductLocation, layoutParams)
        mainLayout.addView(btnAddProduct, layoutParams)

        setContentView(mainLayout)

        // Button click listener
        btnAddProduct.setOnClickListener {
            val productName = etProductName.text.toString().trim()
            val productId = etProductId.text.toString().trim()
            val productLocation = etProductLocation.text.toString().trim()

            // Validation
            if (productName.isEmpty()) {
                etProductName.error = "Product name is required"
                etProductName.requestFocus()
                return@setOnClickListener
            }

            if (productId.isEmpty()) {
                etProductId.error = "Product ID is required"
                etProductId.requestFocus()
                return@setOnClickListener
            }

            if (productLocation.isEmpty()) {
                etProductLocation.error = "Product location is required"
                etProductLocation.requestFocus()
                return@setOnClickListener
            }

            // Create ProductModel matching your model structure
            val product = ProductModel(
                productId = productId,
                name = productName,
                description = productLocation,
                price = 0.0,
                category = "",
                imageUrl = "",
                stock = 0
            )

            // Disable button while processing
            btnAddProduct.isEnabled = false

            // Add product to Firebase using ViewModel
            viewModel.addProduct(productId, product) { success, message ->
                runOnUiThread {
                    btnAddProduct.isEnabled = true

                    if (success) {
                        Toast.makeText(
                            this@AddProductActivity,
                            "Product added successfully!",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Clear fields after successful addition
                        etProductName.text.clear()
                        etProductId.text.clear()
                        etProductLocation.text.clear()
                    } else {
                        Toast.makeText(
                            this@AddProductActivity,
                            "Error: $message",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}