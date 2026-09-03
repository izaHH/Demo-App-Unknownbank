package com.kasatria.kasatriaunknownbank

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.kasatria.kasatriaunknownbank.ui.theme.LinkBlue
import com.kasatria.kasatriaunknownbank.ui.theme.PrimaryBlue
import com.kasatria.kasatriaunknownbank.ui.theme.TextPrimary
import com.kasatria.kasatriaunknownbank.ui.theme.White

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun CreditCardFilterScreen(
    filters: CreditCardFilters,
    onFiltersChange: (CreditCardFilters) -> Unit,
    onBack: () -> Unit,
    onShowResults: () -> Unit
) {

    var draftFilters by remember(filters) {
        mutableStateOf(filters)
    }

    val draftResultCount =
        CreditCardProducts.all
            .applyFilters(draftFilters)
            .size

    val hasDraftFilters =
        draftFilters != CreditCardFilters()

    val hasFilterChanges =
        draftFilters != filters

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

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
                    text = "Filters",

                    modifier =
                        Modifier.align(
                            Alignment.Center
                        ),

                    color = TextPrimary,

                    fontSize = 13.sp,

                    fontWeight =
                        FontWeight.Bold
                )


                Text(
                    text = "Clear All",

                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 20.dp)
                        .clickable(
                            enabled = hasDraftFilters
                        ) {
                            draftFilters =
                                CreditCardFilters()
                        },

                    color =
                        if (hasDraftFilters) {
                            PrimaryBlue
                        } else {
                            Color(0xFFAAAAAA)
                        },

                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            /*
             * =================================
             * SEARCH
             * =================================
             */

            FilterSectionHeader(
                title = "Search for"
            )


            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )


            SearchField(
                value = draftFilters.searchText,
                onValueChange = {
                    draftFilters =
                        draftFilters.copy(
                            searchText = it
                        )
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            )


            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )


            FilterDivider()


            /*
             * =================================
             * CREDIT CARD TIERS
             * =================================
             */

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            FilterSectionHeader(
                title = "Credit Card Tiers"
            )


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    )
                    .padding(horizontal = 20.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                listOf(
                    "All",
                    "Silver",
                    "Gold",
                    "World",
                    "Platinum"
                ).forEach { tier ->

                    FilterChip(
                        text = tier,
                        selected =
                            draftFilters.tier == tier,
                        onClick = {
                            draftFilters =
                                draftFilters.copy(
                                    tier = tier
                                )
                        }
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )


            FilterDivider()


            /*
             * =================================
             * INTEREST
             * =================================
             */

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            FilterSectionHeader(
                title = "Interest"
            )


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    )
                    .padding(horizontal = 20.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                listOf(
                    "All",
                    "Islamic",
                    "Cashback",
                    "Travel",
                    "Rewards",
                    "Petrol",
                    "Dining"
                ).forEach { interest ->

                    FilterChip(
                        text = interest,
                        selected =
                            draftFilters.interest == interest,
                        onClick = {
                            draftFilters =
                                draftFilters.copy(
                                    interest = interest
                                )
                        }
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    )
                    .padding(horizontal = 20.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                listOf(
                    "All",
                    "Silver",
                    "Gold",
                    "World",
                    "Platinum"
                ).forEach { tier ->

                    FilterChip(
                        text = tier,
                        selected =
                            draftFilters.tier == tier,
                        onClick = {
                            draftFilters =
                                draftFilters.copy(
                                    tier = tier
                                )
                        }
                    )
                }
            }
        }


        /*
         * =================================
         * BOTTOM BUTTON
         * =================================
         */

        Button(
            onClick = {
                onFiltersChange(draftFilters)
                onShowResults()
            },
            enabled = draftResultCount > 0 && hasFilterChanges,
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
                text =
                    if (draftResultCount == 1) {
                        "Show 1 Credit Card"
                    } else {
                        "Show $draftResultCount Credit Cards"
                    }
            )
        }
    }
}


@Composable
private fun FilterSectionHeader(
    title: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),

        horizontalArrangement =
            Arrangement.SpaceBetween,

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Text(
            text = title,

            color = Color(0xFF333333),

            fontSize = 15.sp,

            fontWeight =
                FontWeight.Bold
        )


        Icon(
            imageVector =
                Icons.Default.KeyboardArrowUp,

            contentDescription = null,

            tint = Color(0xFF666666),

            modifier =
                Modifier.size(20.dp)
        )
    }
}


@Composable
private fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val shape =
        RoundedCornerShape(8.dp)

    Row(
        modifier = modifier
            .height(46.dp)
            .background(
                color = White,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = Color(0xFFDDDDDD),
                shape = shape
            )
            .padding(
                horizontal = 19.dp
            ),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        BasicTextField(
            value = value,

            onValueChange =
                onValueChange,

            modifier =
                Modifier.weight(1f),

            singleLine = true,

            textStyle =
                androidx.compose.ui.text.TextStyle(
                    color = TextPrimary,
                    fontSize = 15.sp
                ),

            decorationBox = {
                    innerTextField ->

                Box {

                    if (value.isEmpty()) {

                        Text(
                            text = "Credit Card",
                            color = Color(0xFF999999),
                            fontSize = 15.sp
                        )
                    }

                    innerTextField()
                }
            }
        )


        Icon(
            imageVector =
                Icons.Default.Search,

            contentDescription =
                "Search",

            tint =
                Color(0xFF999999),

            modifier =
                Modifier.size(24.dp)
        )
    }
}


@Composable
private fun FilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val shape =
        RoundedCornerShape(8.dp)

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
                        color = Color(0xFF666666),
                        shape = shape
                    )
                }
            )
            .clickable(
                onClick = onClick
            )
            .padding(
                horizontal = 15.dp
            ),

        contentAlignment =
            Alignment.Center
    ) {

        Text(
            text = text,

            color =
                if (selected) {
                    White
                } else {
                    Color(0xFF666666)
                },

            fontSize = 15.sp
        )
    }
}


@Composable
private fun FilterDivider() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(
                Color(0xFFD9D9D9)
                    .copy(alpha = 0.30f)
            )
    )
}