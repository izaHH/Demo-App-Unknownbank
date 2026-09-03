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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
fun CreditCardReviewScreen(
    productName: String,
    applicationData: CreditCardApplicationData,
    onBack: () -> Unit,
    onEditPersonal: () -> Unit,
    onNext: () -> Unit,
    onEditJob: () -> Unit,
    onEditExtraDetails: () -> Unit
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
                    text = "Review Details",

                    color = Color(0xFF333333),

                    fontSize = 15.sp,

                    fontWeight =
                        FontWeight.Bold
                )


                Text(
                    text = "100%",

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
             * Full progress bar
             */

            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(
                        color =
                            Color(0xFF0EAF5F),

                        shape =
                            RoundedCornerShape(100.dp)
                    )
            )


            Spacer(
                modifier =
                    Modifier.height(26.dp)
            )


            /*
             * =================================
             * REVIEW CONTENT
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
                        bottom = 110.dp
                    )
            ) {

                PersonalDetailsReviewCard(
                    applicationData = applicationData,
                    onEdit = onEditPersonal,

                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                JobDetailsReviewCard(
                    applicationData = applicationData,
                    onEdit = onEditJob
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                ExtraDetailsReviewCard(
                    applicationData = applicationData,
                    onEdit = onEditExtraDetails
                )
            }
        }


        /*
         * =================================
         * NEXT BUTTON
         * =================================
         */

        Button(
            onClick = onNext,

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
                text = "Next",

                fontSize = 15.sp,

                fontWeight =
                    FontWeight.Bold
            )
        }
    }
}


@Composable
private fun PersonalDetailsReviewCard(
    applicationData: CreditCardApplicationData,
    onEdit: () -> Unit
) {

    val shape =
        RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
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
            .padding(17.dp)
    ) {

        /*
         * Card header
         */

        Row(
            modifier =
                Modifier.fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically,

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.review_person
                    ),

                    contentDescription = null,

                    modifier =
                        Modifier.size(16.dp)
                )


                Spacer(
                    modifier =
                        Modifier.size(8.dp)
                )


                Text(
                    text = "Personal Details",

                    color = Color.Black,

                    fontSize = 14.sp,

                    fontWeight =
                        FontWeight.Bold
                )
            }


            Row(
                modifier =
                    Modifier.clickable {
                        onEdit()
                    },

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = "EDIT",

                    color = PrimaryBlue,

                    fontSize = 12.sp,

                    letterSpacing = 0.6.sp
                )


                Spacer(
                    modifier =
                        Modifier.size(4.dp)
                )


                Image(
                    painter = painterResource(
                        id = R.drawable.review_edit
                    ),

                    contentDescription =
                        "Edit personal details",

                    modifier =
                        Modifier.size(12.dp)
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(9.dp)
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
                Modifier.height(8.dp)
        )


        /*
         * Details
         */

        ReviewDetail(
            label = "Title",
            value = applicationData.title
        )

        ReviewDetail(
            label = "Name",
            value = applicationData.fullName
        )

        ReviewDetail(
            label = "IC No.",
            value = applicationData.icNumber
        )

        ReviewDetail(
            label = "Date of Birth",
            value = applicationData.dateOfBirth
        )

        ReviewDetail(
            label = "Email",
            value = applicationData.email
        )

        ReviewDetail(
            label = "Mobile No.",
            value =
                "${applicationData.phoneCountryCode} ${applicationData.phoneNumber}"
        )

        ReviewDetail(
            label = "Name on Card",
            value = applicationData.nameOnCard
        )

        ReviewDetail(
            label = "Education",
            value = applicationData.education
        )

        ReviewDetail(
            label = "Gender",
            value = applicationData.gender
        )

        ReviewDetail(
            label = "Race",
            value = applicationData.race
        )

        ReviewDetail(
            label = "Marital Status",
            value = applicationData.maritalStatus,
            addBottomSpacing = false
        )
    }
}

@Composable
private fun JobDetailsReviewCard(
    applicationData: CreditCardApplicationData,
    onEdit: () -> Unit
) {

    val shape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
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
            .padding(17.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "Job Details",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "EDIT",
                modifier = Modifier.clickable {
                    onEdit()
                },
                color = PrimaryBlue,
                fontSize = 12.sp,
                letterSpacing = 0.6.sp
            )
        }

        Spacer(
            modifier = Modifier.height(9.dp)
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
            modifier = Modifier.height(8.dp)
        )

        ReviewDetail(
            label = "Employer Name",
            value = applicationData.employerName
        )

        ReviewDetail(
            label = "Occupation",
            value = applicationData.occupation
        )

        ReviewDetail(
            label = "Sector",
            value = applicationData.sector
        )

        ReviewDetail(
            label = "Employment Type",
            value = applicationData.employmentType
        )

        ReviewDetail(
            label = "Business Classification",
            value = applicationData.businessClassification
        )

        ReviewDetail(
            label = "Length of Service",
            value =
                "${applicationData.yearsOfService} years " +
                        "${applicationData.monthsOfService} months",
            addBottomSpacing = false
        )
    }
}

@Composable
private fun ExtraDetailsReviewCard(
    applicationData: CreditCardApplicationData,
    onEdit: () -> Unit
) {

    val shape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
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
            .padding(17.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "Extra Details",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "EDIT",
                modifier = Modifier.clickable {
                    onEdit()
                },
                color = PrimaryBlue,
                fontSize = 12.sp,
                letterSpacing = 0.6.sp
            )
        }

        Spacer(
            modifier = Modifier.height(9.dp)
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
            modifier = Modifier.height(8.dp)
        )

        ReviewDetail(
            label = "Monthly Net Income",
            value = "RM ${applicationData.monthlyNetIncome}"
        )

        ReviewDetail(
            label = "Monthly Commitments",
            value = "RM ${applicationData.monthlyCommitments}"
        )

        ReviewDetail(
            label = "Retirement Income Source",
            value = applicationData.retirementIncomeSource
        )

        ReviewDetail(
            label = "Statement Delivery",
            value = applicationData.statementDelivery
        )

        ReviewDetail(
            label = "Collection State",
            value = applicationData.collectionState
        )

        ReviewDetail(
            label = "Collection District",
            value = applicationData.collectionDistrict
        )

        ReviewDetail(
            label = "Collection Branch",
            value = applicationData.collectionBranch,
            addBottomSpacing = false
        )
    }
}

@Composable
private fun ReviewDetail(
    label: String,
    value: String,
    addBottomSpacing: Boolean = true
) {

    Column(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Text(
            text = label,

            color =
                Color(0xFF666666),

            fontSize = 13.sp,

            lineHeight = 20.sp
        )


        Text(
            text = value,

            color =
                Color(0xFF191C1E),

            fontSize = 14.sp,

            lineHeight = 20.sp
        )


        if (addBottomSpacing) {

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )
        }
    }
}