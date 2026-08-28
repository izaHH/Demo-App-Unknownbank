package com.kasatria.kasatriaunknownbank

import android.app.Application
import android.util.Log
import com.amplitude.android.Amplitude
import com.amplitude.android.Configuration
import com.amplitude.core.ServerZone
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.vwo.VWO
import com.vwo.insights.VWOInsights
import com.vwo.insights.exposed.IVwoInitCallback as InsightsInitCallback
import com.vwo.insights.exposed.models.ClientConfiguration
import com.vwo.interfaces.IVwoInitCallback as VWOInitCallback
import com.vwo.models.user.VWOInitOptions

class UnknownbankApplication : Application() {
    companion object {
        lateinit var amplitude: Amplitude
    }

    override fun onCreate() {
        super.onCreate()

        // Initialize Firebase first
        FirebaseApp.initializeApp(this)

        // Get the AppCheck instance and install the debug factory
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            DebugAppCheckProviderFactory.getInstance(),
        )

        //Set VWO Account ID & SDK Key
        val vwoInitOptions = VWOInitOptions()
        vwoInitOptions.accountId = 1202089
        vwoInitOptions.sdkKey = "00ece79c37ee1218c7cc74dac6fb7971"

        VWO.init(vwoInitOptions, object : VWOInitCallback {
            override fun vwoInitSuccess(vwo: VWO, message: String) {
                Log.d("VWO", "FME SDK Initialized successfully: $message")
            }
            override fun vwoInitFailed(message: String) {
                Log.e("VWO", "FME SDK Initialization failed: $message")
            }
        })

        // Initialize VWO Insights
        val configuration = ClientConfiguration("1202089", "00ece79c37ee1218c7cc74dac6fb7971", "USER_ID")
        VWOInsights.init(this, object : InsightsInitCallback {
            override fun vwoInitSuccess(message: String) {
                Log.d("VWO", "Insights SDK Initialized successfully: $message")
                VWOInsights.startSessionRecording()
            }

            override fun vwoInitFailed(message: String) {
                Log.e("VWO", "Insights SDK NOT Initialized: $message")
            }
        }, configuration)

        // Initialize Amplitude
        val AMPLITUDE_API_KEY = "935f18228d96dec289aedd0ae96a1632"
        amplitude = Amplitude(AMPLITUDE_API_KEY, applicationContext) {
            // Increased thresholds to improve stability
            flushIntervalMillis = 10000 
            flushQueueSize = 10
            serverZone = ServerZone.US
            
            // Turning off batch mode for more precise per-event error reporting
            useBatch = false 
            
            callback = { event, code, message ->
                if (code >= 400) {
                    Log.e("Amplitude", "Event Failed: ${event.eventType}, Code: $code, Error: $message")
                    Log.e("Amplitude", "Identity Check - UserID: '${event.userId}' (len: ${event.userId?.length ?: 0}), DeviceID: '${event.deviceId}' (len: ${event.deviceId?.length ?: 0})")
                    
                    if (message.contains("id length", ignoreCase = true)) {
                        Log.e("Amplitude", "CRITICAL: Amplitude requires IDs to be at least 5 characters long. Current UserID '${event.userId}' is too short.")
                    }
                } else {
                    Log.d("Amplitude", "Event Sent: ${event.eventType}")
                }
            }
        }
        
        // Safety check: Amplitude often requires IDs to be at least 5 characters.
        // If a short ID like 'user' was persisted, we clear it to avoid Code 400 errors.
        val currentUserId = amplitude.store.userId
        if (currentUserId != null && currentUserId.length < 5) {
            Log.w("Amplitude", "UserID '$currentUserId' is too short (min 5 chars), resetting to null.")
            amplitude.setUserId(null)
        }

        Log.d("Amplitude", "Amplitude Initialized successfully. Current UserID: ${amplitude.store.userId}")
    }
}
