package com.kasatria.kasatriaunknownbank

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amplitude.android.events.Identify
import com.amplitude.android.events.Revenue
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.vwo.VWO
import com.vwo.insights.VWOInsights
import com.vwo.models.user.VWOUserContext

private const val TAG = "CreditCardScreen"

@Composable
fun CreditCardScreen(userId: String?) {
    var isSubmitted by remember { mutableStateOf(false) }

    if (isSubmitted) {
        SuccessScreen(onBack = { isSubmitted = false })
    } else {
        ApplicationForm(userId = userId, onSubmitted = { isSubmitted = true })
    }
}

@Composable
fun ApplicationForm(userId: String?, onSubmitted: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var annualIncome by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Credit Card Application", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = annualIncome,
            onValueChange = { annualIncome = it },
            label = { Text("Annual Income") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                trackApplicationSuccess(userId, annualIncome.toDoubleOrNull() ?: 0.0)
                onSubmitted()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = name.isNotBlank() && annualIncome.isNotBlank()
        ) {
            Text("Submit Application")
        }
    }
}

@Composable
fun SuccessScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Application Submitted!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Thank you for choosing Unknownbank.")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBack) {
            Text("New Application")
        }
    }
}

private fun trackApplicationSuccess(userId: String?, income: Double) {
    val revenue = 50.0 // Fixed value per application for example
    Log.d(TAG, "Tracking revenue: $revenue for user: $userId")

    // Firebase Analytics
    val analytics = Firebase.analytics
    analytics.logEvent(FirebaseAnalytics.Event.PURCHASE) {
        param(FirebaseAnalytics.Param.VALUE, revenue)
        param(FirebaseAnalytics.Param.CURRENCY, "USD")
        param(FirebaseAnalytics.Param.TRANSACTION_ID, "CC_${System.currentTimeMillis()}")
    }

    // VWO Insights (Custom Event)
    VWOInsights.sendCustomEvent("credit_card_application_success", mapOf(
        "revenue" to revenue,
        "income" to income
    ))

    // VWO FME (Goal Tracking)
    val vwo = VWO.getInstance(1202089, "00ece79c37ee1218c7cc74dac6fb7971")
    val vwoContext = VWOUserContext().apply {
        id = userId ?: "guest_user"
    }
    // Assuming "revenueGoal" is set up in VWO to track revenue
    // vwo?.trackEvent("revenueGoal", vwoContext, revenue) // If API supports revenue directly
    vwo?.trackEvent("creditCardSubmit", vwoContext)

    // Amplitude
    val amplitude = UnknownbankApplication.amplitude
    // 1. Track standard event for visibility in Live Stream
    // Using 'items' array allows Amplitude to recognize Item Properties
    amplitude.track("Order Completed", mapOf(
        "revenue" to revenue,
        "items" to listOf(mapOf(
            "item_id" to "credit_card_premium",
            "item_name" to "Credit Card Premium",
            "item_category" to "Cards",
            "price" to revenue,
            "quantity" to 1
        )),
        "income" to income
    ))
    
    // 2. Revenue tracking in Amplitude using the dedicated Revenue object
    val ampRevenue = Revenue().apply {
        productId = "credit_card_premium"
        price = revenue
        quantity = 1
        this.revenue = revenue // Explicitly set total revenue for visibility
        properties = mutableMapOf<String, Any?>(
            "item_name" to "Credit Card Premium",
            "item_category" to "Cards",
            "income_bracket" to income
        )
    }
    amplitude.revenue(ampRevenue)
}
