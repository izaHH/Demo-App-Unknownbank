package com.kasatria.kasatriaunknownbank

import android.util.Log

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent

import com.kasatria.kasatriaunknownbank.ui.theme.AppSpacing
import com.kasatria.kasatriaunknownbank.ui.theme.LinkBlue
import com.kasatria.kasatriaunknownbank.ui.theme.PrimaryBlue
import com.kasatria.kasatriaunknownbank.ui.theme.TextPrimary
import com.kasatria.kasatriaunknownbank.ui.theme.White

import androidx.compose.material.icons.filled.Close
import androidx.compose.foundation.layout.navigationBarsPadding


private const val TAG = "LoginScreen"


@Composable
fun LoginScreen(
    username: String,
    onLogin: (username: String, password: String) -> Unit,
    onClose: () -> Unit
) {

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }


    /*
     * =================================
     * ANALYTICS
     * =================================
     */

    LaunchedEffect(Unit) {

        val analytics = Firebase.analytics

        analytics.logEvent(
            FirebaseAnalytics.Event.SCREEN_VIEW
        ) {

            param(
                FirebaseAnalytics.Param.SCREEN_NAME,
                "Login"
            )

            param(
                FirebaseAnalytics.Param.SCREEN_CLASS,
                "LoginScreen"
            )
        }


        Log.d(
            TAG,
            "screen_view: LoginScreen"
        )


        UnknownbankApplication
            .amplitude
            .track(
                "Screen View",
                mapOf(
                    "screen_name" to "Login"
                )
            )


        UnknownbankApplication
            .amplitude
            .flush()
    }


    /*
     * =================================
     * SCREEN
     * =================================
     */

    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        /*
         * =================================
         * HEADER BACKGROUND
         * =================================
         */

        Image(
            painter = painterResource(
                id = R.drawable.login_header
            ),

            contentDescription = null,

            modifier = Modifier
                .fillMaxWidth()
                .height(273.dp)
                .align(
                    Alignment.TopCenter
                ),

            contentScale = ContentScale.Crop
        )


        /*
         * =================================
         * WHITE GRADIENT
         * Figma node 1:895
         * =================================
         */

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(94.dp)
                .align(
                    Alignment.TopCenter
                )
                .offset(
                    y = 179.dp
                )
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(

                            0f to Color.Transparent,

                            0.62f to Color.White.copy(
                                alpha = 0.8f
                            ),

                            1f to Color.White
                        )
                    )
                )
        )


        /*
         * =================================
         * CONTENT
         * =================================
         */

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(
                    horizontal = 20.dp
                ),

            horizontalAlignment = Alignment.Start
        ) {


            /*
             * =================================
             * BANK LOGO
             * =================================
             */

            Spacer(
                modifier = Modifier.height(36.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp)
            ) {

                // Close button - top left
                IconButton(
                    onClick = {
                        onClose()
                    },
                    modifier = Modifier
                        .size(34.dp)
                        .align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier.size(20.dp),
                        tint = TextPrimary
                    )
                }

                // Bank logo - centered
                Image(
                    painter = painterResource(
                        id = R.drawable.unknownbank_logo
                    ),
                    contentDescription = "Unknown Bank logo",
                    modifier = Modifier
                        .width(82.dp)
                        .height(26.dp)
                        .align(Alignment.Center)
                )
            }


            /*
             * =================================
             * AVATAR
             * =================================
             */

            Spacer(
                modifier = Modifier.height(49.dp)
            )


            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.watermelon_avatar
                    ),

                    contentDescription =
                        "Account avatar",

                    modifier = Modifier.size(
                        80.dp
                    )
                )
            }


            /*
             * =================================
             * ACCOUNT ID
             * =================================
             */

            Spacer(
                modifier = Modifier.height(
                    AppSpacing.md
                )
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.Center
            ) {

                Text(
                    text = maskUsername(username),
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextPrimary
                )
            }


            /*
             * =================================
             * ACCOUNT CHIP
             * =================================
             */

            Spacer(
                modifier = Modifier.height(
                    AppSpacing.sm
                )
            )


            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .height(36.dp)
                        .border(
                            width = 1.dp,
                            color = TextPrimary,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "watermelon",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextPrimary
                    )
                }
            }


            /*
             * =================================
             * PASSWORD SECTION
             * =================================
             */

            Spacer(
                modifier = Modifier.height(
                    36.dp
                )
            )


            Text(
                text = "Enter your password",

                style =
                    MaterialTheme.typography.titleMedium,

                color = TextPrimary
            )


            Spacer(
                modifier = Modifier.height(
                    AppSpacing.md
                )
            )


            /*
             * Password input
             */

            OutlinedTextField(

                value = password,

                onValueChange = {
                    password = it
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                singleLine = true,


                /*
                 * Show / hide password
                 */

                visualTransformation =

                    if (passwordVisible) {

                        VisualTransformation.None

                    } else {

                        PasswordVisualTransformation()
                    },


                trailingIcon = {

                    IconButton(
                        onClick = {

                            passwordVisible =
                                !passwordVisible
                        }
                    ) {

                        Icon(

                            imageVector =

                                if (passwordVisible) {

                                    Icons.Default.VisibilityOff

                                } else {

                                    Icons.Default.Visibility
                                },


                            contentDescription =

                                if (passwordVisible) {

                                    "Hide password"

                                } else {

                                    "Show password"
                                },


                            tint = TextPrimary
                        )
                    }
                },


                shape = RoundedCornerShape(
                    8.dp
                ),


                colors =
                    OutlinedTextFieldDefaults.colors(

                        focusedBorderColor =
                            PrimaryBlue,

                        unfocusedBorderColor =
                            Color(0xFFBDBDBD),

                        cursorColor =
                            PrimaryBlue,

                        focusedTextColor =
                            TextPrimary,

                        unfocusedTextColor =
                            TextPrimary
                    )
            )


            /*
             * =================================
             * FORGOT PASSWORD
             * =================================
             */

            TextButton(

                onClick = {

                    Log.d(
                        TAG,
                        "Forgot Password clicked"
                    )
                },

                contentPadding = PaddingValues(
                    horizontal = 0.dp,
                    vertical = 4.dp
                )
            ) {

                Text(
                    text = "Forgot Password",

                    style =
                        MaterialTheme.typography.labelMedium,

                    color = LinkBlue
                )
            }


            /*
             * Push login button
             * toward bottom of screen.
             */

            Spacer(
                modifier = Modifier.weight(1f)
            )

        } // Column
        Button(
            onClick = {

                UnknownbankApplication
                    .amplitude
                    .setUserId(username)

                UnknownbankApplication
                    .amplitude
                    .track("Login Button Clicked")

                UnknownbankApplication
                    .amplitude
                    .flush()

                onLogin(username,password)
            },

            enabled = password.isNotEmpty(),

            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth()
                .height(46.dp),

            shape = RoundedCornerShape(100.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue,
                contentColor = White
            )
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.labelLarge,
                color = White
            )
        }

    } // Box

} // LoginScreen

private fun maskUsername(username: String): String {
    if (username.length <= 4) {
        return username
    }

    val first = username.take(2)
    val last = username.takeLast(2)
    val maskedLength = username.length - 4

    return "$first ${"•".repeat(maskedLength)} $last"
}