package com.kasatria.kasatriaunknownbank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun ApplyScreen(
    onBack: () -> Unit,
    onCreditCard: () -> Unit = {}
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        /*
         * Decorative full background
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
                    text = "Apply",

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
             * SELECT PRODUCT
             * =================================
             */

            Text(
                text = "Select product",

                modifier = Modifier.padding(
                    start = 20.dp,
                    top = 13.dp,
                    bottom = 20.dp
                ),

                color = TextPrimary,

                fontSize = 15.sp,

                fontWeight =
                    FontWeight.Bold
            )


            /*
             * =================================
             * PRODUCT CARDS
             * =================================
             */

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    ),

                verticalArrangement =
                    Arrangement.spacedBy(15.dp)
            ) {

                ProductSelectionCard(
                    icon =
                        R.drawable.apply_credit_card,

                    title =
                        "Credit Card",

                    description =
                        "Enjoy cashbacks, rewards and exclusive privileges.",

                    onClick =
                        onCreditCard
                )


                ProductSelectionCard(
                    icon =
                        R.drawable.apply_loan,

                    title =
                        "Loan",

                    description =
                        "Get the financing that you need to provide financial comfort for those you value the most."
                )


                ProductSelectionCard(
                    icon =
                        R.drawable.apply_investment,

                    title =
                        "Investment",

                    description =
                        "Transact globally with no conversion fees."
                )


                ProductSelectionCard(
                    icon =
                        R.drawable.apply_insurance,

                    title =
                        "Insurance",

                    description =
                        "Drive your dream car with fast approvals, flexible terms and easy payments."
                )


                ProductSelectionCard(
                    icon =
                        R.drawable.apply_deposit,

                    title =
                        "Deposit Account",

                    description =
                        "Start your financial journey to save, invest and grow.",

                    enabled = false
                )
            }
        }
    }
}


@Composable
private fun ProductSelectionCard(
    icon: Int,
    title: String,
    description: String,
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
            ),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        /*
         * Icon area
         *
         * Figma text begins x=158.
         */

        Box(
            modifier = Modifier
                .width(137.dp)
                .height(130.dp),

            contentAlignment =
                Alignment.Center
        ) {

            Image(
                painter =
                    painterResource(
                        id = icon
                    ),

                contentDescription = null,

                modifier = Modifier
                    .size(80.dp)
                    .then(
                        if (!enabled) {
                            Modifier.alpha(0.18f)
                        } else {
                            Modifier
                        }
                    ),

                contentScale =
                    ContentScale.Fit
            )
        }


        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    end = 8.dp
                )
                .then(
                    if (!enabled) {
                        Modifier.alpha(0.18f)
                    } else {
                        Modifier
                    }
                )
        ) {

            Text(
                text = title,
                color = Color(0xFF333333),
                fontSize = 15.sp,
                fontWeight =
                    FontWeight.Bold
            )


            Text(
                text = description,

                modifier =
                    Modifier.padding(
                        top = 6.dp
                    ),

                color =
                    Color(0xFF878787),

                fontSize = 12.sp,

                lineHeight = 14.sp
            )
        }
    }
}