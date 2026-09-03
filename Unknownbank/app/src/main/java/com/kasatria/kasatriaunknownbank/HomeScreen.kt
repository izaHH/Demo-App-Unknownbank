package com.kasatria.kasatriaunknownbank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.kasatria.kasatriaunknownbank.ui.theme.LinkBlue
import com.kasatria.kasatriaunknownbank.ui.theme.White

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.draw.clip

import androidx.compose.foundation.verticalScroll


@Composable
fun HomeScreen(
    onApply: () -> Unit
) {

    var selectedCategory by rememberSaveable {
        mutableStateOf("Accounts")
    }

    val categories = listOf(
        "Accounts",
        "Credit Card",
        "Loan",
        "Investment"
    )


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        /*
         * =================================
         * FULL BACKGROUND
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
        ){

            /*
             * =================================
             * HOME TOP HEADER
             * Figma y ≈ 52–94
             * =================================
             */

                HomeTopHeader(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )


            /*
             * =================================
             * ACCOUNT CATEGORY CHIPS
             * Figma y = 142
             * =================================
             */

            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        start = 16.dp,
                        top = 142.dp
                    )
                    .horizontalScroll(
                        rememberScrollState()
                    ),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                categories.forEach { category ->

                    HomeCategoryChip(
                        text = category,
                        selected =
                            selectedCategory == category,

                        onClick = {
                            selectedCategory = category
                        }
                    )
                }
            }

            /*
         * =================================
         * ACCOUNT CARDS
         * Figma y = 204
         * =================================
         */

            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        start = 16.dp,
                        top = 204.dp
                    )
                    .horizontalScroll(
                        rememberScrollState()
                    ),

                horizontalArrangement =
                    Arrangement.spacedBy(15.dp)
            ) {

                AccountSummaryCard()

                // Second account card visible partially
                // in the Figma design
                Box(
                    modifier = Modifier
                        .width(290.dp)
                        .height(160.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            }

            /*
             * =================================
             * QUICK ACTIONS
             * Figma y = 420
             * =================================
             */

            QuickActionsSection(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 420.dp),
                onApply = onApply
            )

            /*
             * =================================
             * HIGHLIGHTS
             * Figma y = 659
             * =================================
             */

            HighlightsSection(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        top = 659.dp,
                        bottom = 120.dp
                    )
            )
        }
    }
}


@Composable
private fun HomeCategoryChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val shape =
        RoundedCornerShape(20.dp)

    Box(
        modifier = Modifier
            .height(36.dp)
            .then(
                if (selected) {

                    Modifier.background(
                        color = LinkBlue,
                        shape = shape
                    )

                } else {

                    Modifier.border(
                        width = 1.dp,
                        color = LinkBlue,
                        shape = shape
                    )
                }
            )
            .clickable(
                onClick = onClick
            )
            .padding(
                horizontal = 16.dp
            ),

        contentAlignment =
            Alignment.Center
    ) {

        Text(
            text = text,

            style =
                MaterialTheme.typography.bodyMedium,

            color =
                if (selected) {
                    White
                } else {
                    LinkBlue
                }
        )
    }
}

@Composable
private fun AccountSummaryCard() {

    val cardShape =
        RoundedCornerShape(12.dp)

    Box(
        modifier = Modifier
            .width(340.dp)
            .height(160.dp)
            .shadow(
                elevation = 2.dp,
                shape = cardShape
            )
            .background(
                color = Color.White,
                shape = cardShape
            )
    ) {

        /*
         * Account icon
         * Figma: x36 y224
         */

        Box(
            modifier = Modifier
                .offset(
                    x = 20.dp,
                    y = 20.dp
                )
                .size(42.dp)
                .background(
                    color = Color(0xFF1D75D9),
                    shape = CircleShape
                ),

            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.unknownbank_logo_white
                ),
                contentDescription = null,
                modifier = Modifier.size(22.dp)
            )
        }


        /*
         * Account name
         */

        Text(
            text = "Bank Basic Account",
            modifier = Modifier.offset(
                x = 74.dp,
                y = 20.dp
            ),
            color = Color(0xFF333333),
            fontSize = 15.sp
        )


        /*
         * Masked account number
         */

        Row(
            modifier = Modifier.offset(
                x = 74.dp,
                y = 45.dp
            ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            repeat(6) {

                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(
                            color = Color(0xFF666666),
                            shape = CircleShape
                        )
                )

                if (it < 5) {
                    Spacer(
                        modifier =
                            Modifier.width(3.dp)
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.width(4.dp)
            )

            Text(
                text = "566-5",
                color = Color(0xFF666666),
                fontSize = 13.sp
            )
        }


        /*
         * Eye icon
         */

        IconButton(
            onClick = {
                // We'll wire balance
                // visibility later.
            },

            modifier = Modifier
                .offset(
                    x = 300.dp,
                    y = 21.dp
                )
                .size(48.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Visibility,

                contentDescription =
                    "Show account balance",

                tint =
                    Color(0xFF777777),

                modifier =
                    Modifier.size(24.dp)
            )
        }


        /*
         * Balance label
         */

        Text(
            text = "Available balance",

            modifier = Modifier.offset(
                x = 20.dp,
                y = 112.dp
            ),

            color = Color(0xFF555555),
            fontSize = 15.sp
        )


        /*
         * Balance
         */

        Text(
            text = "RM 5,000.00",

            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 20.dp,
                    bottom = 19.dp
                ),

            color = Color(0xFF333333),

            fontSize = 24.sp,

            fontWeight =
                FontWeight.Bold
        )
    }
}

@Composable
private fun QuickActionsSection(
    modifier: Modifier = Modifier,
    onApply: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        /*
         * Header
         */

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                ),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "Quick Actions",
                color = Color(0xFF333333),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "View All",
                color = LinkBlue,
                fontSize = 13.sp,
                modifier = Modifier.clickable {
                    // Add View All action later
                }
            )
        }


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        /*
         * First row
         */

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp
                ),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            QuickActionItem(
                icon = R.drawable.quick_egold,
                label = "e-Gold"
            )

            QuickActionItem(
                icon = R.drawable.quick_pay_bills,
                label = "Pay Bills"
            )

            QuickActionItem(
                icon = R.drawable.quick_insurance,
                label = "Insurance"
            )

            QuickActionItem(
                icon = R.drawable.quick_apply,
                label = "Apply",
                onClick = onApply
            )
        }


        Spacer(
            modifier = Modifier.height(30.dp)
        )


        /*
         * Second row
         */

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp
                ),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            QuickActionItem(
                icon = R.drawable.quick_cash_loan,
                label = "Cash Loan"
            )

            QuickActionItem(
                icon = R.drawable.quick_etrade,
                label = "e-Trade"
            )

            QuickActionItem(
                icon = R.drawable.quick_eshop,
                label = "e-Shop"
            )

            QuickActionItem(
                icon = R.drawable.quick_more,
                label = "More"
            )
        }
    }


}

@Composable
private fun QuickActionItem(
    icon: Int,
    label: String,
    onClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .width(60.dp)
            .clickable(
                onClick = onClick
            ),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(
                id = icon
            ),

            contentDescription = label,

            modifier = Modifier.size(
                40.dp
            ),

            contentScale =
                ContentScale.Fit
        )


        Spacer(
            modifier = Modifier.height(
                8.dp
            )
        )


        Text(
            text = label,

            color = Color(0xFF333333),

            fontSize = 11.sp,

            textAlign =
                TextAlign.Center,

            maxLines = 1
        )
    }
}

@Composable
private fun HighlightsSection(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        /*
         * Header
         */

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "Highlights",
                color = Color(0xFF333333),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "View All",
                color = LinkBlue,
                fontSize = 13.sp,
                modifier = Modifier.clickable {
                    // Wire later
                }
            )
        }


        Spacer(
            modifier = Modifier.height(17.dp)
        )


        /*
         * Horizontal highlight cards
         */

        Row(
            modifier = Modifier
                .horizontalScroll(
                    rememberScrollState()
                )
                .padding(start = 16.dp),

            horizontalArrangement =
                Arrangement.spacedBy(10.dp)
        ) {

            HighlightCard(
                image = R.drawable.highlight_1
            )

            HighlightCard(
                image = R.drawable.highlight_2
            )
        }
    }
}

@Composable
private fun HighlightCard(
    image: Int,
    onClick: () -> Unit = {}
) {

    Image(
        painter = painterResource(
            id = image
        ),

        contentDescription = null,

        modifier = Modifier
            .width(290.dp)
            .height(160.dp)
            .clip(
                RoundedCornerShape(8.dp)
            )
            .clickable(
                onClick = onClick
            ),

        contentScale =
            ContentScale.Crop
    )
}

@Composable
private fun HomeTopHeader(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        /*
         * Profile avatar
         * Figma:
         * x = 17
         * y = 52
         * 38 x 38
         */

        Image(
            painter = painterResource(
                id = R.drawable.header_profile
            ),

            contentDescription = "Profile",

            modifier = Modifier
                .offset(
                    x = 17.dp,
                    y = 52.dp
                )
                .size(38.dp)
                .clickable {
                    // Profile action later
                },

            contentScale = ContentScale.Fit
        )


        /*
         * Right header actions
         *
         * Figma:
         * right = 16
         * y = 60
         * each = 34 x 34
         * gap = 10
         */

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 60.dp,
                    end = 16.dp
                ),

            horizontalArrangement =
                Arrangement.spacedBy(10.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.header_support
                ),

                contentDescription =
                    "Header action",

                modifier = Modifier
                    .size(34.dp)
                    .clickable {
                        // Wire later
                    },

                contentScale =
                    ContentScale.Fit
            )


            Image(
                painter = painterResource(
                    id = R.drawable.header_notification
                ),

                contentDescription =
                    "Notifications",

                modifier = Modifier
                    .size(34.dp)
                    .clickable {
                        // Wire later
                    },

                contentScale =
                    ContentScale.Fit
            )
        }
    }
}