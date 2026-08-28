package com.kasatria.kasatriaunknownbank

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amplitude.android.events.Revenue

private const val TAG = "EcommerceScreen"

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val category: String
)

val mockupProducts = listOf(
    Product("p1", "Home Loan", 0.0, "Loans"),
    Product("p2", "Bank Woman Mastercard", 99.0, "Cards"),
    Product("p3", "Private Car Insurance", 49.99, "Insurance"),
    Product("p4", "Unit Trust Funds", 250.0, "Investment")
)

@Composable
fun EcommerceScreen() {
    var currentStep by remember { mutableStateOf("STORE") }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    val amplitude = UnknownbankApplication.amplitude

    when (currentStep) {
        "STORE" -> {
            StoreFront(
                onProductClick = { product ->
                    selectedProduct = product
                    currentStep = "PRODUCT_DETAIL"
                    
                    // Track Product Viewed (using items array for Item Properties)
                    amplitude.track("view_item", mapOf(
                        "items" to listOf(mapOf(
                            "item_id" to product.id,
                            "item_name" to product.name,
                            "item_category" to product.category,
                            "price" to product.price,
                            "quantity" to 1
                        ))
                    ))
                    Log.d(TAG, "Tracked: view_item ${product.name}")
                }
            )
        }
        "PRODUCT_DETAIL" -> {
            selectedProduct?.let { product ->
                ProductDetail(
                    product = product,
                    onAddToCart = {
                        currentStep = "CART"
                        
                        // Track Add to Cart (using items array for Item Properties)
                        amplitude.track("add_to_cart", mapOf(
                            "items" to listOf(mapOf(
                                "item_id" to product.id,
                                "item_name" to product.name,
                                "item_category" to product.category,
                                "price" to product.price,
                                "quantity" to 1
                            ))
                        ))
                        Log.d(TAG, "Tracked: add_to_cart ${product.name}")

                        // Track View Cart
                        // Log identity state to debug "Invalid id length" error
                        val currentUserId = amplitude.store.userId
                        val currentDeviceId = amplitude.store.deviceId
                        Log.d(TAG, "Amplitude Identity before view_cart - UserID: '$currentUserId', DeviceID: '$currentDeviceId'")

                        amplitude.track("view_cart", mapOf(
                            "items" to listOf(mapOf(
                                "item_id" to product.id,
                                "item_name" to product.name,
                                "item_category" to product.category,
                                "price" to product.price,
                                "quantity" to 1
                            ))
                        ))
                        Log.d(TAG, "Tracked: view_cart ${product.name}")

                        // Track Begin Checkout
                        amplitude.track("begin_checkout", mapOf(
                            "items" to listOf(mapOf(
                                "item_id" to product.id,
                                "item_name" to product.name,
                                "item_category" to product.category,
                                "price" to product.price,
                                "quantity" to 1
                            ))
                        ))
                        Log.d(TAG, "Tracked: begin_checkout ${product.name}")
                    },
                    onBack = { currentStep = "STORE" }
                )
            }
        }
        "CART" -> {
            selectedProduct?.let { product ->
                CartScreen(
                    product = product,
                    onCheckout = {
                        currentStep = "SUCCESS"
                        
                        // 1. Track Order Completed (Standard Amplitude E-commerce event)
                        // Using 'items' array allows Amplitude to recognize Item Properties
                        amplitude.track("Order Completed", mapOf(
                            "revenue" to product.price,
                            "items" to listOf(mapOf(
                                "item_id" to product.id,
                                "item_name" to product.name,
                                "item_category" to product.category,
                                "price" to product.price,
                                "quantity" to 1
                            ))
                        ))

                        // 2. Track Revenue object (Populates financial/revenue charts)
                        val revenue = Revenue().apply {
                            productId = product.id
                            price = product.price
                            quantity = 1
                            revenue = product.price // Explicitly set total revenue for visibility
                            properties = mutableMapOf<String, Any?>(
                                "items" to listOf(mapOf(
                                    "item_id" to product.id,
                                    "item_name" to product.name,
                                    "item_category" to product.category,
                                    "price" to product.price,
                                    "quantity" to 1
                                ))
                            )
                        }
                        amplitude.revenue(revenue)
                        
                        Log.d(TAG, "Tracked: Order Completed and Revenue ($${product.price}) for ${product.name}")
                    },
                    onBack = { currentStep = "PRODUCT_DETAIL" }
                )
            }
        }
        "SUCCESS" -> {
            PurchaseSuccess(onBackToStore = {
                selectedProduct = null
                currentStep = "STORE"
            })
        }
    }
}

@Composable
fun StoreFront(onProductClick: (Product) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Unknownbank Store", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(mockupProducts) { product ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    onClick = { onProductClick(product) }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(product.name, style = MaterialTheme.typography.titleMedium)
                            Text("$${product.price}", style = MaterialTheme.typography.bodyMedium)
                        }
                        Icon(Icons.Default.ShoppingCart, contentDescription = null)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductDetail(product: Product, onAddToCart: () -> Unit, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(product.name, style = MaterialTheme.typography.headlineMedium)
        Text(product.category, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Get the best out of your banking experience with our ${product.name}. Designed for our premium members.", 
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Price: $${product.price}", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onAddToCart, modifier = Modifier.fillMaxWidth()) {
            Text("Add to Cart")
        }
        TextButton(onClick = onBack) {
            Text("Back to Store")
        }
    }
}

@Composable
fun CartScreen(product: Product, onCheckout: () -> Unit, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Your Cart", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(16.dp)) {
                Text(product.name, modifier = Modifier.weight(1f))
                Text("$${product.price}")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text("Total: $${product.price}", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onCheckout, modifier = Modifier.fillMaxWidth()) {
            Text("Confirm Purchase")
        }
        TextButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Edit Cart")
        }
    }
}

@Composable
fun PurchaseSuccess(onBackToStore: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Purchase Successful!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Your digital product has been activated.")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBackToStore) {
            Text("Back to Store")
        }
    }
}
