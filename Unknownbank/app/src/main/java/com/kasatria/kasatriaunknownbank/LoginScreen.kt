package com.kasatria.kasatriaunknownbank

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.Firebase
import com.vwo.insights.VWOInsights


private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(onLogin: (userId: String) -> Unit, onGuestLogin: () -> Unit) {
    var userId by remember { mutableStateOf("") }

    // Log the screen view event when this composable enters the composition
    LaunchedEffect(Unit) {
        val analytics = Firebase.analytics
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "Login")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "LoginScreen")
            param(FirebaseAnalytics.Param.TRANSACTION_ID, "OrderID1234")
        }
        Log.d(TAG, "screen_view: LoginScreen")

        // Amplitude Screen View
        UnknownbankApplication.amplitude.track("Screen View", mapOf("screen_name" to "Login"))
        UnknownbankApplication.amplitude.flush()

       // val screenName = "LoginScreen"
        // Track screen view in VWO
       // VWOInsights.setScreenViewed(screenName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter User ID to Login")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (userId.length >= 5) {
                    UnknownbankApplication.amplitude.setUserId(userId)
                    UnknownbankApplication.amplitude.track("Login Button Clicked")
                    UnknownbankApplication.amplitude.flush()
                    onLogin(userId)
                }
            },
            enabled = userId.length >= 5
        ) {
            Text("Login")
        }
        if (userId.isNotEmpty() && userId.length < 5) {
            Text(
                text = "User ID must be at least 5 characters",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            UnknownbankApplication.amplitude.track("Continue as Guest Clicked")
            UnknownbankApplication.amplitude.flush()
            onGuestLogin()
        }) {
            Text("Continue as Guest")
        }
    }
}
