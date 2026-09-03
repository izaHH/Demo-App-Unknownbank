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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.kasatria.kasatriaunknownbank.ui.theme.PrimaryBlue
import com.kasatria.kasatriaunknownbank.ui.theme.TextPrimary
import com.kasatria.kasatriaunknownbank.ui.theme.White


@Composable
fun CreditCardPersonalDetailsScreen(
    productName: String,
    applicationData: CreditCardApplicationData,
    onApplicationDataChange: (CreditCardApplicationData) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
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

                    maxLines = 1
                )
            }


            /*
             * =================================
             * PERSONAL DETAILS + PROGRESS
             * =================================
             */

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 23.dp,
                        top = 20.dp
                    ),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = "Personal Details",

                    color = Color(0xFF333333),

                    fontSize = 15.sp,

                    fontWeight =
                        FontWeight.Bold
                )


                Text(
                    text = "20%",

                    color =
                        Color(0xFF0EAF5F),

                    fontSize = 14.sp,

                    fontWeight =
                        FontWeight.Medium
                )
            }


            Spacer(
                modifier =
                    Modifier.height(11.dp)
            )


            /*
             * Progress track
             */

            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp
                    )
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(
                        color =
                            Color(0xFFE6E8EA),

                        shape =
                            RoundedCornerShape(100.dp)
                    )
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.20f)
                        .height(6.dp)
                        .background(
                            color =
                                Color(0xFF0EAF5F),

                            shape =
                                RoundedCornerShape(100.dp)
                        )
                )
            }


            Spacer(
                modifier =
                    Modifier.height(23.dp)
            )


            /*
             * =================================
             * FORM
             * =================================
             */

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    ),

                verticalArrangement =
                    Arrangement.spacedBy(18.dp)
            ) {

                SelectionField(
                    label = "Title",
                    value = applicationData.title,
                    options = listOf(
                        "MS / PUAN",
                        "MR / ENCIK"
                    ),
                    onSelected = {
                       onApplicationDataChange(
                           applicationData.copy(
                               title = it
                           )
                       )
                    }
                )


                PersonalField(
                    label =
                        "Full Name (Completes as per NRIC)",

                    value = applicationData.fullName,

                    onValueChange = {
                        onApplicationDataChange(
                            applicationData.copy(
                                fullName = it
                            )
                        )
                    }
                )


                PersonalField(
                    label = "IC No.",

                    value = applicationData.icNumber,

                    onValueChange = {
                        onApplicationDataChange(
                            applicationData.copy(
                                icNumber = it
                            )
                        )
                    }
                )


                PersonalField(
                    label = "Date of birth",

                    value = applicationData.dateOfBirth,

                    onValueChange = {
                        onApplicationDataChange(
                            applicationData.copy(
                                dateOfBirth = it
                            )
                        )
                    }
                )


                PersonalField(
                    label = "Email",

                    value = applicationData.email,

                    onValueChange = {
                        onApplicationDataChange(
                            applicationData.copy(
                                email = it
                            )
                        )
                    }
                )


                /*
                 * Mobile No.
                 */

                Column {

                    Text(
                        text = "Mobile No.",

                        color =
                            Color(0xFF333333),

                        fontSize = 14.sp
                    )


                    Spacer(
                        modifier =
                            Modifier.height(7.dp)
                    )


                    Row(
                        horizontalArrangement =
                            Arrangement.spacedBy(10.dp)
                    ) {

                        SelectionBox(
                            value = applicationData.phoneCountryCode,

                            options = listOf(
                                "+60",
                                "+65",
                                "+62"
                            ),

                            onSelected = {
                                onApplicationDataChange(
                                    applicationData.copy(
                                        phoneCountryCode = it
                                    )
                                )
                            },

                            modifier =
                                Modifier.width(100.dp)
                        )


                        BasicFormField(
                            value = applicationData.phoneNumber,

                            onValueChange = {
                                onApplicationDataChange(
                                    applicationData.copy(
                                        phoneNumber = it
                                    )
                                )
                            },

                            modifier =
                                Modifier.weight(1f)
                        )
                    }
                }
            }
        }


        /*
         * =================================
         * NEXT
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
                    containerColor =
                        PrimaryBlue,

                    contentColor =
                        White
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
private fun PersonalField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    Column {

        Text(
            text = label,

            color =
                Color(0xFF333333),

            fontSize = 14.sp
        )


        Spacer(
            modifier =
                Modifier.height(7.dp)
        )


        BasicFormField(
            value = value,

            onValueChange =
                onValueChange,

            modifier =
                Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun BasicFormField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFF7F9FB)
) {

    val shape =
        RoundedCornerShape(8.dp)

    Box(
        modifier = modifier
            .height(46.dp)
            .background(
                color =
                    Color(0xFFF7F9FB),

                shape = shape
            )
            .border(
                width = 1.dp,

                color =
                    Color(0xFFDDDDDD),

                shape = shape
            )
            .padding(
                horizontal = 19.dp
            ),

        contentAlignment =
            Alignment.CenterStart
    ) {

        BasicTextField(
            value = value,

            onValueChange =
                onValueChange,

            singleLine = true,

            modifier =
                Modifier.fillMaxWidth(),

            textStyle =
                TextStyle(
                    color =
                        Color(0xFF666666),

                    fontSize = 15.sp
                )
        )
    }
}


@Composable
private fun SelectionField(
    label: String,
    value: String,
    options: List<String>,
    onSelected: (String) -> Unit
) {

    Column {

        Text(
            text = label,

            color =
                Color(0xFF333333),

            fontSize = 14.sp
        )


        Spacer(
            modifier =
                Modifier.height(7.dp)
        )


        SelectionBox(
            value = value,
            options = options,
            onSelected = onSelected,
            modifier =
                Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun SelectionBox(
    value: String,
    options: List<String>,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFF7F9FB)
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    val shape =
        RoundedCornerShape(8.dp)


    Box(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .background(
                    color =
                        Color(0xFFF7F9FB),

                    shape = shape
                )
                .border(
                    width = 1.dp,

                    color =
                        Color(0xFFDDDDDD),

                    shape = shape
                )
                .clickable {
                    expanded = true
                }
                .padding(
                    start = 19.dp,
                    end = 10.dp
                ),

            verticalAlignment =
                Alignment.CenterVertically,

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Text(
                text = value,

                color =
                    Color(0xFF666666),

                fontSize = 15.sp
            )


            Icon(
                imageVector =
                    Icons.Default.KeyboardArrowDown,

                contentDescription =
                    "Select",

                tint =
                    Color(0xFF777777),

                modifier =
                    Modifier.size(24.dp)
            )
        }


        DropdownMenu(
            expanded = expanded,

            onDismissRequest = {
                expanded = false
            }
        ) {

            options.forEach { option ->

                DropdownMenuItem(
                    text = {
                        Text(option)
                    },

                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}