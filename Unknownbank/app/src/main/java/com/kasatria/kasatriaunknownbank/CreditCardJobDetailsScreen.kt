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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.kasatria.kasatriaunknownbank.ui.theme.PrimaryBlue
import com.kasatria.kasatriaunknownbank.ui.theme.TextPrimary
import com.kasatria.kasatriaunknownbank.ui.theme.White


@Composable
fun CreditCardJobDetailsScreen(
    productName: String,
    applicationData: CreditCardApplicationData,
    onApplicationDataChange: (CreditCardApplicationData) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit
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
                        .align(Alignment.CenterStart)
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
             * PROGRESS
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
                    text =
                        "Tell Us About Your Job",

                    color =
                        Color(0xFF333333),

                    fontSize = 15.sp,

                    fontWeight =
                        FontWeight.Bold
                )


                Text(
                    text = "60%",

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


            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
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
                        .fillMaxWidth(0.60f)
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
                    .weight(1f)
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 100.dp
                    ),

                verticalArrangement =
                    Arrangement.spacedBy(18.dp)
            ) {

                JobTextField(
                    label = "Employer Name",
                    value = applicationData.employerName,
                    onValueChange = {
                        onApplicationDataChange(
                            applicationData.copy(
                                employerName = it
                            )
                        )
                    }
                )


                JobSelectionField(
                    label = "Occupation",
                    value = applicationData.occupation,

                    options = listOf(
                        "Sales Manager",
                        "Manager",
                        "Executive",
                        "Engineer",
                        "Consultant",
                        "Others"
                    ),

                    onSelected = {
                        onApplicationDataChange(
                            applicationData.copy(
                                occupation = it
                            )
                        )
                    }
                )


                JobSelectionField(
                    label = "Sector",
                    value = applicationData.sector,

                    options = listOf(
                        "Logistic",
                        "Banking",
                        "Technology",
                        "Retail",
                        "Manufacturing",
                        "Government",
                        "Others"
                    ),

                    onSelected = {
                        onApplicationDataChange(
                            applicationData.copy(
                                sector = it
                            )
                        )
                    }
                )


                JobSelectionField(
                    label = "Employment Type",
                    value = applicationData.employmentType,

                    options = listOf(
                        "Private Employed",
                        "Government",
                        "Self Employed"
                    ),

                    onSelected = {
                        onApplicationDataChange(
                            applicationData.copy(
                                employmentType = it
                            )
                        )
                    }
                )


                JobSelectionField(
                    label = "Business Classification",
                    value = applicationData.businessClassification,

                    options = listOf(
                        "Private Limited",
                        "Public Limited",
                        "Partnership",
                        "Sole Proprietor",
                        "Government"
                    ),

                    onSelected = {
                        onApplicationDataChange(
                            applicationData.copy(
                                businessClassification = it
                            )
                        )
                    }
                )


                JobSelectionField(
                    label = "Length of Service (Year)",
                    value = applicationData.yearsOfService,

                    options =
                        (0..30).map {
                            it.toString()
                        },

                    onSelected = {
                        onApplicationDataChange(
                            applicationData.copy(
                                yearsOfService = it
                            )
                        )
                    }
                )


                JobSelectionField(
                    label = "Length of Service (Months)",
                    value = applicationData.monthsOfService,

                    options =
                        (0..11).map {
                            it.toString()
                        },

                    onSelected = {
                        onApplicationDataChange(
                            applicationData.copy(
                                monthsOfService = it
                            )
                        )
                    }
                )
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
private fun JobTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    Column {

        Text(
            text = label,
            color = Color(0xFF333333),
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
private fun JobSelectionField(
    label: String,
    value: String,
    options: List<String>,
    onSelected: (String) -> Unit
) {

    Column {

        Text(
            text = label,
            color = Color(0xFF333333),
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