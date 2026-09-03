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
import androidx.compose.foundation.layout.size
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
fun CreditCardDetailScreen(
    onBack: () -> Unit,
    onApplyNow: () -> Unit,
    product: CreditCardProduct
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        /*
         * =================================
         * HERO ARTWORK
         * Figma node 1:383
         * =================================
         */

        val heroImage =
            product.heroImageRes
                ?: product.imageRes

        Image(
            painter = painterResource(
                id = heroImage
            ),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp),
            contentScale =
                if (product.heroImageRes != null) {
                    ContentScale.Crop
                } else {
                    ContentScale.Fit
                }
        )


        /*
         * =================================
         * TOP CONTENT
         * =================================
         */

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {

            /*
             * Header / Back
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
            }


            /*
             * Card name
             */

            Text(
                text = product.name,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),

                color = Color(0xFF333333),

                fontSize = 18.sp,

                fontWeight = FontWeight.Bold,

                textAlign = TextAlign.Center
            )


            /*
             * Income + Annual Fee
             */

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 30.dp,
                        end = 30.dp,
                        top = 12.dp
                    ),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Column(
                    modifier =
                        Modifier.weight(1f),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Min. Income",
                        color = Color(0xFF777777),
                        fontSize = 12.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(4.dp)
                    )

                    Text(
                        text = "RM 100,000 per annum",
                        color = Color(0xFF333333),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(31.dp)
                        .background(
                            Color(0xFFCFCFCF)
                        )
                )


                Column(
                    modifier =
                        Modifier.weight(1f),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Annual Fee",
                        color = Color(0xFF777777),
                        fontSize = 12.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(4.dp)
                    )

                    Text(
                        text = "FREE*",
                        color = Color(0xFF333333),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            /*
             * Hero image occupies until ~420dp.
             */

            Spacer(
                modifier =
                    Modifier.height(245.dp)
            )


            /*
             * =================================
             * DESCRIPTION
             * =================================
             */

            Text(
                text =
                    "Enjoy instant reward redemptions with\n" +
                            "Premier Travel Credit Card! Get up to 5x reward\n" +
                            "points, complimentary travel insurance and annual\n" +
                            "passes to selected airport lounges.",

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),

                color = Color(0xFF444444),

                fontSize = 15.sp,

                lineHeight = 22.sp,

                textAlign = TextAlign.Center
            )


            Spacer(
                modifier =
                    Modifier.height(34.dp)
            )


            /*
             * =================================
             * FEATURED BENEFITS
             * =================================
             */

            Text(
                text = "Featured Benefits",

                modifier =
                    Modifier.fillMaxWidth(),

                color = Color(0xFF333333),

                fontSize = 15.sp,

                fontWeight =
                    FontWeight.Bold,

                textAlign =
                    TextAlign.Center
            )


            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                BenefitCard(
                    modifier =
                        Modifier.weight(1f),

                    icon =
                        R.drawable.benefit_fee_waiver,

                    text =
                        "Annual Fee\nWaiver"
                )


                BenefitCard(
                    modifier =
                        Modifier.weight(1f),

                    icon =
                        R.drawable.benefit_cashback,

                    text =
                        "Up to 15%\nCashback"
                )


                BenefitCard(
                    modifier =
                        Modifier.weight(1f),

                    icon =
                        R.drawable.benefit_points,

                    text =
                        "Earn 5x\nPoints"
                )
            }
        }


        /*
         * =================================
         * APPLY NOW
         * =================================
         */

        Button(
            onClick = onApplyNow,

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
                text = "Apply Now",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
private fun BenefitCard(
    icon: Int,
    text: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .height(148.dp)
            .background(
                color = White,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFEEEEEE),
                shape = RoundedCornerShape(10.dp)
            ),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

        Image(
            painter =
                painterResource(
                    id = icon
                ),

            contentDescription = null,

            modifier =
                Modifier.size(52.dp),

            contentScale =
                ContentScale.Fit
        )


        Spacer(
            modifier =
                Modifier.height(18.dp)
        )


        Text(
            text = text,

            color =
                Color(0xFF333333),

            fontSize = 13.sp,

            lineHeight = 16.sp,

            textAlign =
                TextAlign.Center
        )
    }
}