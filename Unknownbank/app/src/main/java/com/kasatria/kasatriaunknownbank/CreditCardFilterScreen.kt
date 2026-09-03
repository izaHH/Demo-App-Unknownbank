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


@Composable
fun CreditCardFilterScreen(
    filters: CreditCardFilters,
    onFiltersChange: (CreditCardFilters) -> Unit,
    onBack: () -> Unit,
    onShowResults: () -> Unit
) {



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
                value = filters.searchText,

                onValueChange = {
                    onFiltersChange(
                        filters.copy(
                            searchText = it
                        )
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
                    .padding(horizontal = 20.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                FilterChip(
                    text = "All",
                    selected =
                        filters.tier == "All",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                tier = "All"
                            )
                        )
                    }
                )

                FilterChip(
                    text = "Silver",
                    selected =
                        filters.tier == "Silver",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                tier = "Silver"
                            )
                        )
                    }
                )

                FilterChip(
                    text = "Gold",
                    selected =
                        filters.tier == "Gold",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                tier = "Gold"
                            )
                        )
                    }
                )

                FilterChip(
                    text = "Platinum",
                    selected =
                        filters.tier == "Platinum",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                tier = "Platinum"
                            )
                        )
                    }
                )
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
                    .padding(horizontal = 20.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                FilterChip(
                    text = "All",
                    selected =
                        filters.interest == "All",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                interest = "All"
                            )
                        )
                    }
                )

                FilterChip(
                    text = "Islamic",
                    selected =
                        filters.interest == "Islamic",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                interest = "Islamic"
                            )
                        )
                    }
                )

                FilterChip(
                    text = "Cashback",
                    selected =
                        filters.interest == "Cashback",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                interest = "Cashback"
                            )
                        )
                    }
                )

                FilterChip(
                    text = "Travel",
                    selected =
                        filters.interest == "Travel",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                interest = "Travel"
                            )
                        )
                    }
                )
            }


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                FilterChip(
                    text = "Rewards",
                    selected =
                        filters.interest == "Rewards",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                interest = "Rewards"
                            )
                        )
                    }
                )

                FilterChip(
                    text = "Petrol",
                    selected =
                        filters.interest == "Petrol",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                interest = "Petrol"
                            )
                        )
                    }
                )

                FilterChip(
                    text = "Dining",
                    selected =
                        filters.interest == "Dining",
                    onClick = {
                        onFiltersChange(
                            filters.copy(
                                interest = "Dining"
                            )
                        )
                    }
                )
            }
        }


        /*
         * =================================
         * BOTTOM BUTTON
         * =================================
         */

        Button(
            onClick = onShowResults,

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
                text = "Show 18 Credit Cards",

                fontSize = 15.sp,

                fontWeight =
                    FontWeight.Bold
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