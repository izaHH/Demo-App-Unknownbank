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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.kasatria.kasatriaunknownbank.ui.theme.TextPrimary
import com.kasatria.kasatriaunknownbank.ui.theme.White


@Composable
fun CreditCardRequirementsScreen(
    productName: String,
    onBack: () -> Unit,
    onNext: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {

        /*
         * Decorative background
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
        ) {

            /*
             * =================================
             * HEADER
             * =================================
             */

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {

                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterStart)
                        .size(48.dp)
                ) {

                    Icon(
                        imageVector =
                            Icons.AutoMirrored.Filled.ArrowBack,

                        contentDescription = "Back",

                        tint = TextPrimary
                    )
                }


                Text(
                    text =
                        "Applying for $productName",

                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            start = 55.dp,
                            end = 30.dp
                        ),

                    color = Color(0xFF333333),

                    fontSize = 13.sp,

                    fontWeight =
                        FontWeight.Bold,

                    textAlign =
                        TextAlign.Center,

                    maxLines = 1
                )
            }


            /*
             * =================================
             * INTRO
             * =================================
             */

            Text(
                text =
                    "You must be Malaysian aged 21 - 65.",

                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp
                ),

                color = Color(0xFF333333),

                fontSize = 15.sp,

                lineHeight = 22.sp,

                fontWeight =
                    FontWeight.Bold
            )


            Text(
                text =
                    "Below are the documents required for this application",

                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 4.dp
                ),

                color = Color(0xFF44474D),

                fontSize = 14.sp,

                lineHeight = 22.sp
            )


            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )


            /*
             * =================================
             * SELF EMPLOYED
             * =================================
             */

            RequirementGroup(
                title = "Self Employed",

                items = listOf(
                    "Valid Malaysia NRIC",
                    "BE Form with official Tax Receipt AND",
                    "Last 6 Months Bank Statement AND",
                    "Copy of Business Registration"
                ),

                modifier = Modifier.padding(
                    horizontal = 20.dp
                )
            )


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            /*
             * =================================
             * EMPLOYEE
             * =================================
             */

            RequirementGroup(
                title = "Employee",

                items = listOf(
                    "Valid Malaysia NRIC",
                    "Latest Salary Slip OR",
                    "EA Form OR",
                    "EPF Statement"
                ),

                modifier = Modifier.padding(
                    horizontal = 20.dp
                )
            )
        }


        /*
         * =================================
         * NEXT BUTTON
         * =================================
         */

        Button(
            onClick = onNext,

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

            shape =
                RoundedCornerShape(100.dp),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue,
                    contentColor = White
                )
        ) {

            Text(
                text = "Next",

                fontSize = 15.sp,

                fontWeight =
                    FontWeight.Bold
            )
        }
    }
}


@Composable
private fun RequirementGroup(
    title: String,
    items: List<String>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp)
    ) {

        Text(
            text = title,

            color = Color(0xFF333333),

            fontSize = 14.sp,

            lineHeight = 22.sp,

            fontWeight =
                FontWeight.Bold
        )


        Spacer(
            modifier =
                Modifier.height(10.dp)
        )


        Column(
            verticalArrangement =
                Arrangement.spacedBy(8.dp)
        ) {

            items.forEach { item ->

                RequirementItem(
                    text = item
                )
            }
        }
    }
}


@Composable
private fun RequirementItem(
    text: String
) {

    Row(
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Image(
            painter =
                painterResource(
                    id =
                        R.drawable.requirement_check
                ),

            contentDescription = null,

            modifier =
                Modifier.size(16.dp),

            contentScale =
                ContentScale.Fit
        )


        Spacer(
            modifier =
                Modifier.size(10.dp)
        )


        Text(
            text = text,

            color = Color(0xFF666666),

            fontSize = 13.sp,

            lineHeight = 22.sp
        )
    }
}