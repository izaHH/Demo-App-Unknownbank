package com.kasatria.kasatriaunknownbank

import android.app.Application
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

import androidx.compose.material.icons.filled.Home

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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.sp
import fme.e
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        //Remove this line if require ga4 or set to true
        analytics.setAnalyticsCollectionEnabled(false)

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
                var userId by rememberSaveable {
                    mutableStateOf<String?>(null)
                }

                var pendingUsername by rememberSaveable {
                    mutableStateOf("")
                }

                var showPasswordScreen by rememberSaveable {
                    mutableStateOf(false)
                }

                var showLogin by rememberSaveable {
                    mutableStateOf(true)
                }


                if (showLogin) {

                    /*
                     * STEP 1
                     * User enters username
                     */
                    if (!showPasswordScreen) {

                        UsernameEntryScreen(
                            onContinue = { username ->

                                pendingUsername = username
                                showPasswordScreen = true

                                Log.d(
                                    TAG,
                                    "Username entered, moving to password screen"
                                )
                            }
                        )

                    } else {

                        /*
                         * STEP 2
                         * Password screen from Figma
                         */
                        LoginScreen(
                            username = pendingUsername,

                            onLogin = { username, _ ->

                                /*
                                 * IMPORTANT:
                                 * We deliberately ignore the password here.
                                 *
                                 * Never send passwords to:
                                 * Firebase
                                 * Amplitude
                                 * VWO
                                 * Logcat
                                 */

                                analytics.setUserId(username)

                                analytics.logEvent(
                                    FirebaseAnalytics.Event.LOGIN
                                ) {
                                    param(
                                        FirebaseAnalytics.Param.METHOD,
                                        "custom_login"
                                    )
                                }


                                /*
                                 * VWO attributes
                                 */

                                val attributes = mapOf(
                                    "user_id" to username,
                                    "login_method" to "custom_login"
                                )

                                VWOInsights.setAttribute(
                                    attributes
                                )


                                VWOInsights.sendCustomEvent(
                                    "login_success",
                                    mapOf(
                                        "method" to "custom_login"
                                    )
                                )

                                VWOInsights.sendCustomEvent(
                                    "click_login",
                                    mapOf(
                                        "method" to "custom_login"
                                    )
                                )


                                /*
                                 * VWO FME
                                 */

                                val vwo = VWO.getInstance(
                                    1202089,
                                    "00ece79c37ee1218c7cc74dac6fb7971"
                                )

                                val vwoContext =
                                    VWOUserContext()

                                vwoContext.id =
                                    username

                                vwo?.trackEvent(
                                    "clickLogin",
                                    vwoContext
                                )


                                /*
                                 * Amplitude
                                 */

                                val amplitude =
                                    UnknownbankApplication.amplitude

                                amplitude.setUserId(
                                    username
                                )

                                amplitude.track(
                                    "user_login"
                                )

                                val identify =
                                    Identify()
                                        .set(
                                            "login_method",
                                            "custom_login"
                                        )

                                amplitude.identify(
                                    identify
                                )


                                /*
                                 * Login complete
                                 */

                                userId = username

                                showLogin = false

                                Log.d(
                                    TAG,
                                    "User logged in with ID: $username"
                                )
                            },

                            onClose = {

                                /*
                                 * Return to username screen
                                 */

                                showPasswordScreen = false
                            }
                        )
                    }

                } else {

                    /*
                     * =================================
                     * LOGGED-IN APP
                     * =================================
                     */

                    UnknownbankApp(
                        userId = userId,

                        onLogout = {

                            CookieManager
                                .getInstance()
                                .removeAllCookies(null)

                            WebStorage
                                .getInstance()
                                .deleteAllData()


                            val amplitude =
                                UnknownbankApplication.amplitude

                            amplitude.setUserId(null)

                            analytics.setUserId(null)


                            /*
                             * Reset login flow
                             */

                            userId = null

                            pendingUsername = ""

                            showPasswordScreen = false

                            showLogin = true


                            Log.d(
                                TAG,
                                "User logged out and WebView data cleared."
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun UsernameEntryScreen(
    onContinue: (String) -> Unit
) {

    var username by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(
                120.dp
            )
        )

        Text(
            text = "Enter your username",
            style =
                MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(
                16.dp
            )
        )

        OutlinedTextField(
            value = username,

            onValueChange = {
                username = it
            },

            modifier =
                Modifier.fillMaxWidth(),

            singleLine = true,

            label = {
                Text("Username")
            }
        )

        Spacer(
            modifier = Modifier.height(
                16.dp
            )
        )

        Button(
            onClick = {
                onContinue(
                    username.trim()
                )
            },

            enabled =
                username.isNotBlank(),

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Continue"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnknownbankApp(userId: String?, onLogout: () -> Unit) {
    val analytics = Firebase.analytics
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    var selectedProduct by remember { mutableStateOf(CreditCardProducts.BankWorldMastercard) }
    var applicationData by remember { mutableStateOf(CreditCardApplicationData()) }
    var applicationReference by rememberSaveable { mutableStateOf("21092484852030") }
    var applicationDate by rememberSaveable { mutableStateOf("") }
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
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            /*
             * =================================
             * SCREEN CONTENT
             * =================================
             */

            when (currentDestination) {

                AppDestinations.HOME -> {
                    HomeScreen(
                        onApply = {
                            currentDestination = AppDestinations.CREDIT_CARD
                        }
                    )
                }

                AppDestinations.CREDIT_CARD -> {
                    ApplyScreen(
                        onBack = {
                            currentDestination= AppDestinations.HOME
                        },

                        onCreditCard = {
                            currentDestination = AppDestinations.CREDIT_CARD_LIST
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_LIST -> {
                    CreditCardSelectionScreen(
                        onBack =  {
                            currentDestination = AppDestinations.CREDIT_CARD
                        },
                        onCardSelected = { product ->
                            selectedProduct = product
                            applicationData = CreditCardApplicationData()

                            applicationReference =
                                (10_000_000_000_000L..
                                        99_999_999_999_999L)
                                    .random()
                                    .toString()

                            applicationDate =
                                SimpleDateFormat(
                                    "MMM d, yyyy",
                                    Locale.US
                                ).format(Date())

                            currentDestination = AppDestinations.CREDIT_CARD_DETAIL
                        },
                        onFilter = {
                            currentDestination = AppDestinations.CREDIT_CARD_FILTER
                            // Figma
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_FILTER -> {
                    CreditCardFilterScreen(
                        onBack = {
                            currentDestination = AppDestinations.CREDIT_CARD_LIST
                        },

                        onShowResults = {
                            currentDestination = AppDestinations.CREDIT_CARD_LIST
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_DETAIL -> {
                    CreditCardDetailScreen(
                        product = selectedProduct,

                        onBack = {
                            currentDestination = AppDestinations.CREDIT_CARD_LIST
                        },
                        onApplyNow = {
                            currentDestination = AppDestinations.CREDIT_CARD_REQUIREMENTS
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_REQUIREMENTS -> {
                    CreditCardRequirementsScreen(
                        productName = selectedProduct.name,
                        onBack = {
                            currentDestination = AppDestinations.CREDIT_CARD_DETAIL
                        },

                        onNext = {
                            currentDestination = AppDestinations.CREDIT_CARD_PERSONAL_DETAILS
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_PERSONAL_DETAILS -> {
                    CreditCardPersonalDetailsScreen(
                        productName = selectedProduct.name,
                        applicationData = applicationData,
                        onApplicationDataChange = {
                            applicationData = it
                        },
                        onBack = {
                            currentDestination = AppDestinations.CREDIT_CARD_REQUIREMENTS
                        },
                        onNext = {
                            currentDestination = AppDestinations.CREDIT_CARD_ABOUT_YOU
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_ABOUT_YOU -> {
                    CreditCardAboutYouScreen(
                        productName = selectedProduct.name,
                        applicationData = applicationData,
                        onApplicationDataChange = {
                            applicationData = it
                        },
                        onBack = {
                            currentDestination = AppDestinations.CREDIT_CARD_PERSONAL_DETAILS
                        },
                        onNext = {
                            currentDestination = AppDestinations.CREDIT_CARD_JOB_DETAILS
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_JOB_DETAILS -> {

                    CreditCardJobDetailsScreen(
                        productName = selectedProduct.name,
                        applicationData = applicationData,
                        onApplicationDataChange = {
                            applicationData = it
                        },
                        onBack = {
                            currentDestination =
                                AppDestinations.CREDIT_CARD_ABOUT_YOU
                        },

                        onNext = {
                            currentDestination = AppDestinations.CREDIT_CARD_EXTRA_DETAILS
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_EXTRA_DETAILS -> {

                    CreditCardExtraDetailsScreen(
                        productName = selectedProduct.name,
                        applicationData = applicationData,

                        onApplicationDataChange = {
                            applicationData = it
                        },
                        onBack = {
                            currentDestination = AppDestinations.CREDIT_CARD_JOB_DETAILS
                        },

                        onNext = {
                            currentDestination = AppDestinations.CREDIT_CARD_REVIEW
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_REVIEW -> {

                    CreditCardReviewScreen(
                        productName = selectedProduct.name,
                        applicationData = applicationData,

                        onBack = {
                            currentDestination = AppDestinations.CREDIT_CARD_EXTRA_DETAILS
                        },

                        onEditPersonal = {
                            currentDestination = AppDestinations.CREDIT_CARD_PERSONAL_DETAILS
                        },

                        onNext = {
                            currentDestination = AppDestinations.CREDIT_CARD_ALMOST_THERE
                        },
                        onEditJob = {
                            currentDestination = AppDestinations.CREDIT_CARD_JOB_DETAILS
                        },
                        onEditExtraDetails = {
                            currentDestination = AppDestinations.CREDIT_CARD_EXTRA_DETAILS
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_ALMOST_THERE -> {

                    CreditCardAlmostThereScreen(
                        product = selectedProduct,
                        applicationReference = applicationReference,

                        onBack = {
                            currentDestination = AppDestinations.CREDIT_CARD_REVIEW
                        },

                        onUploadNow = {
                            currentDestination = AppDestinations.CREDIT_CARD_UPLOAD_DOCUMENTS
                        }

                    )
                }

                AppDestinations.CREDIT_CARD_UPLOAD_DOCUMENTS -> {

                    CreditCardUploadDocumentsScreen(
                        productName = selectedProduct.name,
                        applicationData = applicationData,

                        onApplicationDataChange = {
                            applicationData = it
                        },

                        onBack = {
                            currentDestination =
                                AppDestinations.CREDIT_CARD_ALMOST_THERE
                        },

                        onUploadNow = {
                            currentDestination = AppDestinations.CREDIT_CARD_APPROVED
                        }
                    )
                }

                AppDestinations.CREDIT_CARD_APPROVED -> {

                    CreditCardApprovalScreen(
                        applicationReference = applicationReference,
                        applicationDate = applicationDate,

                        onClose = {
                            applicationData = CreditCardApplicationData()
                            selectedProduct = CreditCardProducts.BankWorldMastercard
                            currentDestination = AppDestinations.HOME
                        },

                        onBackToHome = {
                            applicationData = CreditCardApplicationData()
                            selectedProduct = CreditCardProducts.BankWorldMastercard
                            currentDestination = AppDestinations.HOME
                        },

                        onScreenShown = {
                            val eventCategory = "Credit Card - ${selectedProduct.name} - Application Success"
                            val screenName = "Credit Card | ${selectedProduct.name} | Application Success"

                            val productProperties = mutableMapOf<String, Any>(
                                "event_category" to eventCategory,
                                "event_action" to "Credit Card Application Success",
                                "event_label" to "Application Success",
                                "product_name" to selectedProduct.name,
                                "product_category" to selectedProduct.category,
                                "product_type" to selectedProduct.type,
                                "product_banking_category" to selectedProduct.bankingCategory,
                                "product_card_type" to selectedProduct.cardType
                            )

                            if (selectedProduct.benefit.isNotBlank()) {
                                productProperties["product_benefit"] =
                                    selectedProduct.benefit
                            }

                            if (selectedProduct.tier.isNotBlank()) {
                                productProperties["product_card_tiers"] =
                                    selectedProduct.tier
                            }

                            if (selectedProduct.interest.isNotBlank()) {
                                productProperties["product_card_interest"] =
                                    selectedProduct.interest
                            }

                            //firebase
                            analytics.logEvent("screen_view"){
                                param("screen_name", screenName)
                            }
                            analytics.logEvent(
                                "card_application_success"
                            ) {
                                param("event_category", eventCategory)
                                param("event_action", "Credit Card Application Success")
                                param("event_label", "Application Success")
                                param("product_name", selectedProduct.name)
                                param("product_category", selectedProduct.category)
                                param("product_type", selectedProduct.type)
                                param("product_banking_category", selectedProduct.bankingCategory)
                                param("product_card_type", selectedProduct.cardType)

                                if (selectedProduct.benefit.isNotBlank()) {
                                    param("product_benefit", selectedProduct.benefit)
                                }

                                if (selectedProduct.tier.isNotBlank()) {
                                    param("product_card_tiers", selectedProduct.tier)
                                }

                                if (selectedProduct.interest.isNotBlank()) {
                                    param("product_card_interest", selectedProduct.interest)
                                }
                            }


                            // VWO — OUTSIDE Firebase block

                            VWOInsights.sendCustomEvent(
                                "card_application_success",productProperties
                            )


                            // Amplitude — OUTSIDE Firebase block

                            UnknownbankApplication.amplitude.track(
                                "card_application_success",productProperties
                            )
                        }
                    )
                }

                AppDestinations.ACCOUNT -> {

                    NavigationPlaceholderScreen(
                        title = "Account",
                        username = userId,
                        icon = R.drawable.nav_account,
                        description =
                            "View and manage your bank accounts."
                    )
                }


                AppDestinations.SCAN -> {

                    NavigationPlaceholderScreen(
                        title = "Scan",
                        username = userId,
                        icon = R.drawable.nav_scan,
                        description =
                            "Scan a QR code to make a payment."
                    )
                }


                AppDestinations.REWARDS -> {

                    NavigationPlaceholderScreen(
                        title = "Rewards",
                        username = userId,
                        icon = R.drawable.nav_rewards,
                        description =
                            "View your rewards and available benefits."
                    )
                }


                AppDestinations.SETTING -> {

                    NavigationPlaceholderScreen(
                        title = "Setting",
                        username = userId,
                        icon = R.drawable.nav_setting,
                        description =
                            "Manage your preferences and account settings."
                    )
                }



                AppDestinations.STORE -> {
                    EcommerceScreen()
                }

                else -> {
                    Text(
                        text = "Screen unavailable",
                        modifier = Modifier.padding(20.dp)
                    )
                }
            }


            /*
             * =================================
             * FIGMA BOTTOM NAVIGATION
             * =================================
             */

            val showBottomNavigation =
                currentDestination in listOf(
                    AppDestinations.HOME,
                    AppDestinations.ACCOUNT,
                    AppDestinations.SCAN,
                    AppDestinations.REWARDS,
                    AppDestinations.SETTING
                )
            if(showBottomNavigation){
                UnknownbankBottomNavigation(
                    selectedDestination =
                        currentDestination,

                    onDestinationSelected = {
                            destination ->

                        currentDestination =
                            destination

                        analytics.logEvent(
                            "navigation_click"
                        ) {
                            param(
                                "destination",
                                destination.label
                            )
                        }

                        VWOInsights.sendCustomEvent(
                            "navigation_click",
                            mapOf(
                                "destination" to
                                        destination.label
                            )
                        )
                    },

                    modifier = Modifier
                        .align(
                            Alignment.BottomCenter
                        )
                )

            }


        }

    }
}

@Composable
fun UnknownbankBottomNavigation(
    selectedDestination: AppDestinations,
    onDestinationSelected:
        (AppDestinations) -> Unit,
    modifier: Modifier = Modifier
) {

    val items = listOf(
        Triple(
            AppDestinations.HOME,
            R.drawable.nav_home,
            "Home"
        ),
        Triple(
            AppDestinations.ACCOUNT,
            R.drawable.nav_account,
            "Account"
        ),
        Triple(
            AppDestinations.SCAN,
            R.drawable.nav_scan,
            "Scan"
        ),
        Triple(
            AppDestinations.REWARDS,
            R.drawable.nav_rewards,
            "Rewards"
        ),
        Triple(
            AppDestinations.SETTING,
            R.drawable.nav_setting,
            "Setting"
        )
    )


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        /*
         * White navigation background
         *
         * Figma:
         * y = 768
         * height = 84
         */

        Box(
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
                .fillMaxWidth()
                .height(84.dp)
                .shadow(
                    elevation = 4.dp
                )
                .background(
                    Color.White
                )
        )


        Row(
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
                .fillMaxWidth()
                .height(84.dp)
                .navigationBarsPadding(),

            horizontalArrangement =
                Arrangement.SpaceEvenly,

            verticalAlignment =
                Alignment.Top
        ) {

            items.forEach {
                    (destination, icon, label) ->

                BottomNavigationItem(
                    destination =
                        destination,

                    icon = icon,

                    label = label,

                    selected =
                        selectedDestination ==
                                destination,

                    onClick = {
                        onDestinationSelected(
                            destination
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomNavigationItem(
    destination: AppDestinations,
    icon: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val isScan =
        destination ==
                AppDestinations.SCAN

    Column(
        modifier = Modifier
            .width(64.dp)
            .clickable(
                onClick = onClick
            ),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        if (isScan) {

            /*
             * Raised middle Scan button
             */

            Box(
                modifier = Modifier
                    .offset(
                        y = (-5).dp
                    )
                    .size(57.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = CircleShape,
                        ambientColor = Color(0xFF0DCBFF),
                        spotColor = Color(0xFF0DCBFF)
                    )
                    .background(
                        color =
                            Color.White,

                        shape =
                            CircleShape
                    ),

                contentAlignment =
                    Alignment.Center
            ) {

                Image(
                    painter =
                        painterResource(
                            id = icon
                        ),

                    contentDescription =
                        label,

                    modifier =
                        Modifier.size(
                            57.dp
                        ),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = label,

                fontSize = 11.sp,

                color =
                    Color(0xFFA3A3A3),

                modifier =
                    Modifier.offset(
                        y = (-10).dp
                    )
            )

        } else {

            Spacer(
                modifier =
                    Modifier.height(
                        11.dp
                    )
            )

            Image(
                painter =
                    painterResource(
                        id = icon
                    ),

                contentDescription =
                    label,

                modifier =
                    Modifier.size(
                        30.dp
                    ),

                contentScale =
                    ContentScale.Fit
            )


            Spacer(
                modifier =
                    Modifier.height(
                        2.dp
                    )
            )


            Text(
                text = label,

                fontSize = 11.sp,

                color =
                    if (selected) {
                        Color(0xFF666666)
                    } else {
                        Color(0xFFA3A3A3)
                    }
            )
        }
    }
}

enum class AppDestinations(
    val label: String
) {

    HOME("Home"),
    ACCOUNT("Account"),
    SCAN("Scan"),
    REWARDS("Rewards"),
    SETTING("Setting"),


    // Not shown in bottom navigation.
    // We'll navigate to these from buttons.
    CREDIT_CARD("Credit Card"),
    CREDIT_CARD_LIST("Credit Card List"),
    CREDIT_CARD_FILTER("Credit Card Filter"),
    CREDIT_CARD_DETAIL("Credit Card Detail"),
    CREDIT_CARD_REQUIREMENTS("Credit Card Requirements"),
    CREDIT_CARD_PERSONAL_DETAILS("Credit Card Personal Details"),
    CREDIT_CARD_ABOUT_YOU("Credit Card About You"),
    CREDIT_CARD_JOB_DETAILS("Credit Card Job Details"),
    CREDIT_CARD_EXTRA_DETAILS("Credit Card Extra Details"),
    CREDIT_CARD_REVIEW("Credit Card Review"),
    CREDIT_CARD_ALMOST_THERE("Credit Card Almost There"),
    CREDIT_CARD_UPLOAD_DOCUMENTS("Credit Card Upload Documents"),
    CREDIT_CARD_APPROVED("Credit Card Approved"),

    STORE("Store")
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
