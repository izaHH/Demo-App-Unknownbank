package com.kasatria.kasatriaunknownbank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.kasatria.kasatriaunknownbank.ui.theme.TextPrimary


@Composable
fun CreditCardSelectionScreen(
    onBack: () -> Unit,
    onCardSelected: () -> Unit = {},
    onFilter: () -> Unit = {}
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        /*
         * Background
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
                .verticalScroll(
                    rememberScrollState()
                )
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
                        .size(48.dp)
                ) {

                    Icon(
                        imageVector =
                            Icons.AutoMirrored.Filled.ArrowBack,

                        contentDescription = "Back",

                        modifier =
                            Modifier.size(24.dp),

                        tint = TextPrimary
                    )
                }


                Text(
                    text = "Credit Card",

                    modifier =
                        Modifier.align(
                            Alignment.Center
                        ),

                    color = TextPrimary,

                    fontSize = 13.sp,

                    fontWeight =
                        FontWeight.Bold
                )
            }


            /*
             * =================================
             * TITLE + FILTER
             * =================================
             */

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 13.dp,
                        bottom = 19.dp
                    ),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text =
                        "Select the right credit card for you",

                    color = TextPrimary,

                    fontSize = 15.sp,

                    fontWeight =
                        FontWeight.Bold
                )


                Image(
                    painter =
                        painterResource(
                            id =
                                R.drawable.credit_card_filter
                        ),

                    contentDescription =
                        "Filter credit cards",

                    modifier = Modifier
                        .size(36.dp)
                        .clickable {
                            onFilter()
                        },

                    contentScale =
                        ContentScale.Fit
                )
            }


            /*
             * =================================
             * CREDIT CARDS
             * =================================
             */

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    ),

                verticalArrangement =
                    Arrangement.spacedBy(
                        15.dp
                    )
            ) {

                CreditCardOption(
                    image =
                        R.drawable.card_world,

                    name =
                        "Bank World\nMastercard",

                    onClick =
                        onCardSelected
                )


                CreditCardOption(
                    image =
                        R.drawable.card_gold,

                    name =
                        "Bank Gold\nMastercard",

                    onClick =
                        onCardSelected
                )


                CreditCardOption(
                    image =
                        R.drawable.card_woman,

                    name =
                        "Bank Woman\nMastercard",

                    onClick =
                        onCardSelected
                )


                CreditCardOption(
                    image =
                        R.drawable.card_platinum_islamic,

                    name =
                        "Bank Platinum\nIslamic Mastercard",

                    onClick =
                        onCardSelected
                )


                CreditCardOption(
                    image =
                        R.drawable.card_fuel,

                    name =
                        "Bank Fuel\nMastercard",

                    enabled = false
                )


                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )
            }
        }
    }
}


@Composable
private fun CreditCardOption(
    image: Int,
    name: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {

    val shape =
        RoundedCornerShape(12.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .shadow(
                elevation = 2.dp,
                shape = shape
            )
            .background(
                color = Color.White,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = Color(0xFFEEEEEE),
                shape = shape
            )
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .then(
                if (!enabled) {
                    Modifier.alpha(0.18f)
                } else {
                    Modifier
                }
            ),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Spacer(
            modifier =
                Modifier.width(22.dp)
        )


        /*
         * Figma card image:
         * 140 x 88
         */

        Image(
            painter =
                painterResource(
                    id = image
                ),

            contentDescription = name,

            modifier = Modifier
                .width(140.dp)
                .height(88.dp),

            contentScale =
                ContentScale.Fit
        )


        Spacer(
            modifier =
                Modifier.width(18.dp)
        )


        Text(
            text = name,

            modifier =
                Modifier.weight(1f),

            color =
                Color(0xFF333333),

            fontSize = 15.sp,

            lineHeight = 17.sp,

            fontWeight =
                FontWeight.Bold
        )


        Spacer(
            modifier =
                Modifier.width(10.dp)
        )
    }
}