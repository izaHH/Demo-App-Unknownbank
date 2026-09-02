package com.kasatria.kasatriaunknownbank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.shadow
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
fun CreditCardAlmostThereScreen(
    onBack: () -> Unit,
    onUploadNow: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
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
                        .align(
                            Alignment.CenterStart
                        )
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
                        "Applying for Bank World Mastercard",

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

                    maxLines = 1
                )
            }


            /*
             * =================================
             * INTRO
             * =================================
             */

            Text(
                text = "You are almost there.",

                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp
                ),

                color = Color(0xFF333333),

                fontSize = 15.sp,

                fontWeight =
                    FontWeight.Bold
            )


            Text(
                text =
                    "Upload your documents to complete your application",

                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 5.dp
                ),

                color = Color(0xFF44474D),

                fontSize = 14.sp,

                lineHeight = 22.sp
            )


            Spacer(
                modifier =
                    Modifier.height(35.dp)
            )


            /*
             * =================================
             * APPLICATION CARD
             * =================================
             */

            ApplicationReferenceCard(
                modifier = Modifier.padding(
                    horizontal = 20.dp
                )
            )


            Spacer(
                modifier =
                    Modifier.height(26.dp)
            )


            /*
             * =================================
             * NOTES
             * =================================
             */

            Text(
                text =
                    "*You may close this page if you have uploaded your documents.",

                modifier = Modifier.padding(
                    horizontal = 20.dp
                ),

                color = Color(0xFF44474D),

                fontSize = 14.sp,

                lineHeight = 20.sp
            )


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            Text(
                text =
                    "*If you do not have your supporting documents ready you may opt to upload them later before your application expires.",

                modifier = Modifier.padding(
                    horizontal = 20.dp
                ),

                color = Color(0xFF44474D),

                fontSize = 14.sp,

                lineHeight = 20.sp
            )
        }


        /*
         * =================================
         * UPLOAD NOW
         * =================================
         */

        Button(
            onClick = onUploadNow,

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
                RoundedCornerShape(100.dp),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        PrimaryBlue,

                    contentColor =
                        White
                )
        ) {

            Text(
                text = "Upload Now",

                fontSize = 15.sp,

                fontWeight =
                    FontWeight.Bold
            )
        }
    }
}


@Composable
private fun ApplicationReferenceCard(
    modifier: Modifier = Modifier
) {

    val shape =
        RoundedCornerShape(12.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 3.dp,
                shape = shape
            )
            .background(
                color = White,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = Color(0xFFEEEEEE),
                shape = shape
            )
            .padding(
                start = 17.dp,
                end = 17.dp,
                top = 17.dp,
                bottom = 27.dp
            )
    ) {

        /*
         * Reference Number
         */

        Row(
            modifier =
                Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "Reference Number",

                color = Color.Black,

                fontSize = 14.sp
            )


            Text(
                text = "21092484852030",

                color = Color.Black,

                fontSize = 14.sp,

                fontWeight =
                    FontWeight.Bold
            )
        }


        Spacer(
            modifier =
                Modifier.height(11.dp)
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    Color(0xFFF2F4F6)
                )
        )


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        /*
         * Selected card image
         * Figma: 280 x 175
         */

        Image(
            painter = painterResource(
                id =
                    R.drawable.application_card_world
            ),

            contentDescription =
                "Bank World Mastercard",

            modifier = Modifier
                .width(280.dp)
                .height(175.dp)
                .align(
                    Alignment.CenterHorizontally
                ),

            contentScale =
                ContentScale.Crop
        )


        Spacer(
            modifier =
                Modifier.height(15.dp)
        )


        Text(
            text =
                "Bank World Mastercard",

            modifier =
                Modifier.fillMaxWidth(),

            color =
                Color(0xFF333333),

            fontSize = 15.sp,

            textAlign =
                TextAlign.Center
        )
    }
}