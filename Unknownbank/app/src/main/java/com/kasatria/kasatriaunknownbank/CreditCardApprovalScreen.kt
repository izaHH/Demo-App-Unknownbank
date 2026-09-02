package com.kasatria.kasatriaunknownbank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.kasatria.kasatriaunknownbank.ui.theme.PrimaryBlue
import com.kasatria.kasatriaunknownbank.ui.theme.White


@Composable
fun CreditCardApprovalScreen(
    onClose: () -> Unit,
    onBackToHome: () -> Unit,
    onScreenShown: () -> Unit = {}
) {

    /*
     * Fire success tracking once when
     * this screen is first displayed.
     */
    LaunchedEffect(Unit) {
        onScreenShown()
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        /*
         * =================================
         * BACKGROUND
         * =================================
         */

        Image(
            painter = painterResource(
                id = R.drawable.full_background_light
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(
                    horizontal = 29.dp
                )
        ) {

            /*
             * =================================
             * CLOSE
             * =================================
             */

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {

                IconButton(
                    onClick = onClose,
                    modifier = Modifier
                        .align(
                            Alignment.CenterStart
                        )
                        .size(40.dp)
                ) {

                    Image(
                        painter = painterResource(
                            id = R.drawable.approval_close
                        ),
                        contentDescription = "Close",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }


            /*
             * =================================
             * SUCCESS ICON
             * =================================
             */

            Image(
                painter = painterResource(
                    id = R.drawable.approval_success
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .align(
                        Alignment.CenterHorizontally
                    ),
                contentScale = ContentScale.Fit
            )


            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )


            /*
             * =================================
             * TITLE
             * =================================
             */

            Text(
                text =
                    "Your application is approved\nin principle",

                modifier =
                    Modifier.fillMaxWidth(),

                color =
                    Color.Black,

                fontSize =
                    20.sp,

                lineHeight =
                    28.sp,

                fontWeight =
                    FontWeight.Bold,

                textAlign =
                    TextAlign.Center
            )


            Spacer(
                modifier =
                    Modifier.height(13.dp)
            )


            Text(
                text =
                    "You will be notified of the status of your application\nvia SMS and/ or Email",

                modifier =
                    Modifier.fillMaxWidth(),

                color =
                    Color(0xFF555555),

                fontSize =
                    14.sp,

                lineHeight =
                    24.sp,

                textAlign =
                    TextAlign.Center
            )


            Spacer(
                modifier =
                    Modifier.height(31.dp)
            )


            /*
             * =================================
             * REFERENCE CARD
             * =================================
             */

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(108.dp)
                    .background(
                        color =
                            Color(0xFFF7F8FA),

                        shape =
                            RoundedCornerShape(11.dp)
                    )
                    .padding(
                        horizontal = 17.dp,
                        vertical = 15.dp
                    )
            ) {

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text =
                            "REFERENCE NO.",

                        color =
                            Color(0xFF555555),

                        fontSize =
                            12.sp
                    )


                    Text(
                        text =
                            "21092484852030",

                        color =
                            Color(0xFF191C1E),

                        fontSize =
                            18.sp
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(13.dp)
                )


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            Color(0xFFDDDDDD)
                        )
                )


                Spacer(
                    modifier =
                        Modifier.height(13.dp)
                )


                Row(
                    modifier =
                        Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "DATE",

                        color =
                            Color(0xFF555555),

                        fontSize =
                            12.sp
                    )


                    Text(
                        text =
                            "Aug 24, 2026",

                        color =
                            Color(0xFF333333),

                        fontSize =
                            14.sp
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )


            /*
             * =================================
             * NEXT STEPS
             * =================================
             */

            Text(
                text =
                    "Next Steps",

                color =
                    Color.Black,

                fontSize =
                    16.sp,

                fontWeight =
                    FontWeight.Bold
            )


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            NextStepItem(
                icon = {
                    Image(
                        painter = painterResource(
                            id = R.drawable.approval_email
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },

                title =
                    "Check your email",

                description =
                    "We've sent a confirmation with details."
            )


            Spacer(
                modifier =
                    Modifier.height(27.dp)
            )


            NextStepItem(
                icon = {
                    Image(
                        painter = painterResource(
                            id = R.drawable.approval_under_review
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },

                title =
                    "Under Review",

                description =
                    "Our team is verifying your information."
            )
        }


        /*
         * =================================
         * BACK TO HOME
         * =================================
         */

        Button(
            onClick =
                onBackToHome,

            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
                .navigationBarsPadding()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth()
                .height(46.dp),

            shape =
                RoundedCornerShape(
                    100.dp
                ),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        PrimaryBlue,

                    contentColor =
                        White
                )
        ) {

            Text(
                text =
                    "Back To Home",

                fontSize =
                    15.sp,

                fontWeight =
                    FontWeight.Bold
            )
        }
    }
}


@Composable
private fun NextStepItem(
    icon: @Composable () -> Unit,
    title: String,
    description: String
) {

    Row(
        verticalAlignment =
            Alignment.Top
    ) {

        Box(
            modifier =
                Modifier.size(20.dp),

            contentAlignment =
                Alignment.Center
        ) {

            icon()
        }


        Spacer(
            modifier =
                Modifier.size(12.dp)
        )


        Column {

            Text(
                text = title,

                color =
                    Color.Black,

                fontSize =
                    15.sp,

                fontWeight =
                    FontWeight.Bold
            )


            Spacer(
                modifier =
                    Modifier.height(2.dp)
            )


            Text(
                text =
                    description,

                color =
                    Color(0xFF666666),

                fontSize =
                    14.sp,

                lineHeight =
                    19.sp
            )
        }
    }
}