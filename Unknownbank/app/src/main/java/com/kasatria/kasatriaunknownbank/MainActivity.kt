package com.kasatria.kasatriaunknownbank

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebStorage
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.kasatria.kasatriaunknownbank.ui.theme.UnknownbankTheme
import com.vwo.VWO
import com.vwo.interfaces.IVwoListener
import com.vwo.models.user.GetFlag
import com.vwo.models.user.VWOUserContext
import com.vwo.insights.VWOInsights
import kotlinx.coroutines.tasks.await
import android.webkit.WebView as AndroidWebView
import com.google.firebase.analytics.FirebaseAnalytics.Event
import com.amplitude.android.Amplitude
import com.amplitude.android.events.Identify
import com.microsoft.clarity.Clarity
import com.microsoft.clarity.ClarityConfig
import com.vwo.insights.surveys.utility.Util


private const val TAG = "MainActivity"

class MyCustomWebViewClient(
    private val onSslErrorCallback: (handler: SslErrorHandler?, error: SslError?) -> Unit
) : AccompanistWebViewClient() {
    private val webViewClientTag = "MyCustomWebViewClient"

    override fun onPageStarted(view: AndroidWebView, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Log.d(webViewClientTag, "Page loading started: $url")
    }

    override fun onReceivedError(
        view: AndroidWebView,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        if (error != null && request?.isForMainFrame == true) {
            Log.e(
                webViewClientTag,
                "onReceivedError: Error Code [${error.errorCode}] ${error.description} for ${request.url}"
            )
        }
    }

    override fun onReceivedSslError(
        view: AndroidWebView,
        handler: SslErrorHandler?,
        error: SslError?
    ) {
        Log.e(webViewClientTag, "onReceivedSslError: SSL Error caught. Passing to UI.")
        onSslErrorCallback(handler, error)
    }
}

class MainActivity : ComponentActivity() {
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics = Firebase.analytics

        val campaignParams = Bundle().apply {
            putString(FirebaseAnalytics.Param.CAMPAIGN, "summer_promo_2026")
            putString(FirebaseAnalytics.Param.SOURCE, "social_media")
            putString(FirebaseAnalytics.Param.MEDIUM, "test_ad")
            putString(FirebaseAnalytics.Param.CONTENT, "test_content")
        }

        analytics.logEvent(Event.CAMPAIGN_DETAILS, campaignParams)

        val config = ClarityConfig(
            projectId = "y0gh5frzg2",
            //logLevel = LogLevel.None // Note: Use "LogLevel.Verbose" value while testing to debug initialization issues.
        )
        Clarity.initialize(applicationContext, config)

        //analytics.setDefaultEventParameters(campaignParams)
        // Enable remote debugging of the WebView
        AndroidWebView.setWebContentsDebuggingEnabled(true)

        enableEdgeToEdge()
        setContent {
            UnknownbankTheme {
                var userId by rememberSaveable { mutableStateOf<String?>(null) }
                var showLogin by rememberSaveable { mutableStateOf(true) }

                if (showLogin) {
                    LoginScreen(
                        onLogin = { id ->

                            // Add Firebase Analytics user login
                            analytics.setUserId(id)
                            analytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
                                param(FirebaseAnalytics.Param.METHOD, "custom_login")
                            }
                            
                            // Add VWO custom attributes on login
                            val attributes = mapOf(
                                "user_id" to id,
                                "login_method" to "custom_login"
                            )
                            VWOInsights.setAttribute(attributes)

                            // Send VWO custom event on login
                            VWOInsights.sendCustomEvent("login_success", mapOf("method" to "custom_login"))
                            VWOInsights.sendCustomEvent("click_login", mapOf("method" to "custom_login"))

                            // Track VWO FME Goal
                            val vwo = VWO.getInstance(1202089, "00ece79c37ee1218c7cc74dac6fb7971")
                            val vwoContext = VWOUserContext()
                            vwoContext.id = id
                            vwo?.trackEvent("clickLogin", vwoContext)

                            // Track Amplitude user login
                            val amplitude = UnknownbankApplication.amplitude
                            amplitude.setUserId(id)
                            amplitude.track("user_login")

                            val identify = Identify()
                                .set("login_method", "custom_login")
                            amplitude.identify(identify)


                            userId = id
                            showLogin = false
                            Log.d(TAG, "User logged in with ID: $id")
                        },
                        onGuestLogin = {
                            analytics.setUserId(null)
                            analytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
                                param(FirebaseAnalytics.Param.METHOD, "guest")
                            }
                            
                            // Add VWO custom attributes for guest
                            val attributes = mapOf(
                                "login_method" to "guest"
                            )
                            VWOInsights.setAttribute(attributes)

                            // Send VWO custom event for guest login
                            VWOInsights.sendCustomEvent("login_success", mapOf("method" to "guest"))
                            VWOInsights.sendCustomEvent("click_continue_as_guest", mapOf("method" to "guest"))

                            // Track VWO FME Goal
                            val vwo = VWO.getInstance(1202089, "00ece79c37ee1218c7cc74dac6fb7971")
                            val vwoContext = VWOUserContext()
                            vwoContext.id = "guest_user"
                            vwo?.trackEvent("clickContinueAsGuest", vwoContext)

                            //Track Amplitude guest login
                            val amplitude = UnknownbankApplication.amplitude
                            amplitude.setUserId(null)

                            val identify = Identify().set("login_method","guest")
                            amplitude.identify(identify)
                            amplitude.track("user_login")



                            userId = null
                            showLogin = false
                            Log.d(TAG, "User continued as guest")
                        }
                    )
                } else {
                    UnknownbankApp(
                        userId = userId,
                        onLogout = {
                            CookieManager.getInstance().removeAllCookies(null)
                            WebStorage.getInstance().deleteAllData()

                            val amplitude = UnknownbankApplication.amplitude
                            //VWOInsights.setUserId(null)

                            amplitude.setUserId(null)
                            analytics.setUserId(null)
                            userId = null
                            showLogin = true
                            Log.d(TAG, "User logged out and WebView data cleared.")
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnknownbankApp(userId: String?, onLogout: () -> Unit) {
    val analytics = Firebase.analytics
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    var webViewUrl by rememberSaveable { mutableStateOf("") }
    var showWebView by rememberSaveable { mutableStateOf(false) }

    // VWO Feature Flag and Variables state
    var isFeatureEnabled by rememberSaveable { mutableStateOf(false) }
    var vwoVariableText by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(userId) {
        val vwo = VWO.getInstance(1202089, "00ece79c37ee1218c7cc74dac6fb7971")
        val userContext = VWOUserContext()
        userContext.id = userId ?: "guest_user"

        vwo?.getFlag("testingFeatureFlag1", userContext, object : IVwoListener {
            override fun onSuccess(data: Any) {
                val featureFlag = data as? GetFlag
                isFeatureEnabled = featureFlag?.isEnabled() ?: false
                
                if (isFeatureEnabled) {
                    // Example of using Variables API for a String
                    val heroText = featureFlag?.getVariable("heroText", "VWO Feature Flag") as? String ?: ""
                    vwoVariableText = heroText

                    // To get value of a single boolean variable
                    val setThemeColor = featureFlag?.getVariable("setThemeColor", false) as? Boolean ?: false
                    
                    // To get value of all variables as a list of Maps
                    val allVariables = featureFlag?.getVariables()

                    Log.d(TAG, "VWO variable 'setThemeColor': $setThemeColor")
                    Log.d(TAG, "VWO all variables: $allVariables")
                }
                Log.d(TAG, "VWO Flag 'testingFeatureFlag1' is enabled: $isFeatureEnabled")
            }

            override fun onFailure(message: String) {
                Log.e(TAG, "VWO getFlag failed: $message")
            }
        })
    }

    if (showWebView) {
        BackHandler {
            showWebView = false
        }

        LaunchedEffect(key1 = userId) {
            try {
                val gaIDTask = analytics.appInstanceId
                val sessionIDTask = analytics.sessionId

                val gaID = gaIDTask.await()
                val sessionID: Long? = sessionIDTask.await()
                val urlBuilder = StringBuilder("https://unknownbank.kasatria.com?gaid=$gaID&sid=${sessionID?.toString()}")
                if (userId != null) {
                    urlBuilder.append("&user_id=$userId")
                }
                val urlWithQuery = urlBuilder.toString()

                webViewUrl = urlWithQuery
                Log.d(TAG, "gaid: $gaID")
                Log.d(TAG, "sid: $sessionID")
                Log.d(TAG, "url_with_query: $urlWithQuery")
            } catch (e: Exception) {
                Log.d(TAG, "gaid: failed", e)
                webViewUrl = "https://unknownbank.kasatria.com"
            }
        }
    }

    if (showWebView) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("WebView") },
                    navigationIcon = {
                        IconButton(onClick = { showWebView = false }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            if (webViewUrl.isNotEmpty()) {
                MyWebView(
                    url = webViewUrl,
                    modifier = Modifier.padding(innerPadding),
                    onGoBack = { showWebView = false }
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Loading...")
                }
            }
        }
    } else {
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                AppDestinations.entries.forEach {
                    item(
                        icon = {
                            Icon(
                                it.icon,
                                contentDescription = it.label
                            )
                        },
                        label = { Text(it.label) },
                        selected = it == currentDestination,
                        onClick = {
                            analytics.logEvent("test_event0") {
                                param("event_category", "native_event_category")
                                param("event_action", "native_event_action")
                                param("event_label", "native_event_label")
                            }
                            analytics.logEvent("link_click_1") {
                                param("event_category", "native_event_category_non_reserved")
                                param("event_action", "native_event_action_non_reserved")
                                param("event_label", "native_event_label_non_reserved")
                                param("event_trigger", "button_click")
                            }
                            analytics.logEvent("link_click") {
                                param("event_category", "native_event_category_non_reserved")
                                param("event_action", "native_event_action_non_reserved")
                                param("event_label", "native_event_label_non_reserved")
                                param("event_trigger", "button_click")
                            }
                            
                            // Send VWO custom event on navigation
                            VWOInsights.sendCustomEvent("navigation_click", mapOf("destination" to it.label))
                            
                            currentDestination = it
                        }
                    )
                }
            }
        ) {
            TrackScreenView(
                analytics = analytics,
                destination = currentDestination
            )

            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        when (currentDestination) {
                            AppDestinations.CREDIT_CARD -> {
                                CreditCardScreen(userId = userId)
                            }
                            
                            AppDestinations.STORE -> {
                                EcommerceScreen()
                            }

                            else -> {
                                Greeting(
                                    name = userId ?: "Guest",
                                )

                                if (isFeatureEnabled) {
                                    Text(
                                        text = vwoVariableText.ifEmpty { "VWO Feature is Active!" },
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                }

                                Button(
                                    onClick = {
                                        // Trigger VWO FME Track Event (Goal)
                                        val vwo =
                                            VWO.getInstance(1202089, "00ece79c37ee1218c7cc74dac6fb7971")
                                        val userContext = VWOUserContext().apply {
                                            id = userId ?: "guest_user"
                                        }
                                        vwo?.trackEvent("openWebviewClick", userContext)

                                        Log.d(TAG, "VWO Goal Triggered: open_webview_click")
                                        showWebView = true
                                    },
                                ) {
                                    Text("Open WebView")
                                }

                                Button(onClick = onLogout) {
                                    Text("Logout")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    FAVORITES("Favorites", Icons.Default.Favorite),
    STORE("Store", Icons.Default.ShoppingCart),
    CREDIT_CARD("Apply", Icons.Default.CreditCard),
    PROFILE("Profile", Icons.Default.AccountBox),
}

@Composable
fun MyWebView(url: String, modifier: Modifier = Modifier, onGoBack: () -> Unit) {
    var sslErrorState by remember { mutableStateOf<Pair<SslErrorHandler?, SslError?>?>(null) }
    val webViewState = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()
    val webViewClient = remember {
        MyCustomWebViewClient { handler, error ->
            sslErrorState = handler to error
        }
    }

    WebView(
        state = webViewState,
        modifier = modifier.fillMaxSize(),
        navigator = navigator,
        client = webViewClient,
        chromeClient = AccompanistWebChromeClient(),
        factory = { context ->
            AndroidWebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.cacheMode = WebSettings.LOAD_DEFAULT
            }
        }
    )

    if (sslErrorState != null) {
        AlertDialog(
            onDismissRequest = {
                sslErrorState?.first?.cancel()
                sslErrorState = null
                onGoBack()
            },
            title = { Text(text = "Security Warning") },
            text = { Text(text = "The security certificate for this site is not trusted. This could be risky.\n\nDo you want to proceed anyway?") },
            confirmButton = {
                Button(
                    onClick = {
                        sslErrorState?.first?.proceed()
                        sslErrorState = null
                    }
                ) {
                    Text("Proceed Anyway")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        sslErrorState?.first?.cancel()
                        sslErrorState = null
                        onGoBack()
                    }
                ) {
                    Text("Go Back")
                }
            }
        )
    }
}

@Composable
fun TrackScreenView(
    analytics: FirebaseAnalytics,
    destination: AppDestinations,
) {
    LaunchedEffect(key1 = destination) {
        val screenName = destination.label

        // Track screen view in Firebases
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
            param("event_trigger", "service_form_start")
            param("event_label", "test_event_label_rule")
        }
        
        // Track screen view in VWO
        VWOInsights.setScreenViewed(screenName)

        //Track screen view in Amplitude
        val amplitude = UnknownbankApplication.amplitude
        amplitude.track("screen_view", mapOf("screen_name" to screenName))

        Log.d(TAG, "screen_view: $screenName")
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        // Image Placeholder representing the Home Icon/Avatar
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

class UserLogoutProvider : PreviewParameterProvider<Pair<String?, () -> Unit>> {
    override val values = sequenceOf("previewUser123" to {})
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(@PreviewParameter(UserLogoutProvider::class) data: Pair<String?, () -> Unit>) {
    UnknownbankTheme {
        UnknownbankApp(userId = data.first, onLogout = data.second)
    }
}
