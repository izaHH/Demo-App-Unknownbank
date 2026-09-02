package com.kasatria.kasatriaunknownbank

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.kasatria.kasatriaunknownbank.ui.theme.PrimaryBlue
import com.kasatria.kasatriaunknownbank.ui.theme.TextPrimary
import com.kasatria.kasatriaunknownbank.ui.theme.White


@Composable
fun CreditCardUploadDocumentsScreen(
    onBack: () -> Unit,
    onUploadNow: () -> Unit
) {

    val context = LocalContext.current

    var nricFront by rememberSaveable {
        mutableStateOf(
            "farah_amira_ID_front.pdf"
        )
    }

    var nricBack by rememberSaveable {
        mutableStateOf(
            "farah_amira_ID_back.pdf"
        )
    }

    var salaryDocument by rememberSaveable {
        mutableStateOf(
            "farah_amira_PaySlip.pdf"
        )
    }

    /*
     * Additional documents added
     * through "+ Add More Document".
     */
    val additionalDocuments =
        remember {
            mutableStateListOf<String>()
        }


    /*
     * 0 = NRIC Front
     * 1 = NRIC Back
     * 2 = Salary document
     * 3 = Add more document
     */
    var selectedTarget by remember {
        mutableStateOf(-1)
    }


    val documentLauncher =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.OpenDocument()
        ) { uri: Uri? ->

            uri ?: return@rememberLauncherForActivityResult

            val fileName =
                getFileName(
                    context = context,
                    uri = uri
                )

            when (selectedTarget) {

                0 -> {
                    nricFront = fileName
                }

                1 -> {
                    nricBack = fileName
                }

                2 -> {
                    salaryDocument = fileName
                }

                3 -> {
                    additionalDocuments.add(
                        fileName
                    )
                }
            }

            selectedTarget = -1
        }


    fun openDocumentPicker(
        target: Int
    ) {

        selectedTarget = target

        documentLauncher.launch(
            arrayOf(
                "application/pdf",
                "image/jpeg",
                "image/png"
            )
        )
    }


    Box(
        modifier =
            Modifier.fillMaxSize()
    ) {

        /*
         * =================================
         * BACKGROUND
         * =================================
         */

        Image(
            painter =
                androidx.compose.ui.res.painterResource(
                    id =
                        R.drawable.full_background_light
                ),

            contentDescription = null,

            modifier =
                Modifier.fillMaxSize(),

            contentScale =
                androidx.compose.ui.layout.ContentScale.Crop
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
                        .padding(
                            start = 8.dp
                        )
                        .align(
                            Alignment.CenterStart
                        )
                ) {

                    Icon(
                        imageVector =
                            Icons.AutoMirrored
                                .Filled
                                .ArrowBack,

                        contentDescription =
                            "Back",

                        tint =
                            TextPrimary
                    )
                }


                Text(
                    text =
                        "Applying for Bank World Mastercard",

                    modifier = Modifier
                        .align(
                            Alignment.Center
                        )
                        .padding(
                            start = 55.dp,
                            end = 30.dp
                        ),

                    color =
                        Color(0xFF333333),

                    fontSize = 13.sp,

                    fontWeight =
                        FontWeight.Bold,

                    maxLines = 1
                )
            }


            /*
             * =================================
             * PAGE TITLE
             * =================================
             */

            Text(
                text =
                    "Upload Your Documents",

                modifier =
                    Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 20.dp
                    ),

                color =
                    Color(0xFF333333),

                fontSize = 15.sp,

                fontWeight =
                    FontWeight.Bold
            )


            Text(
                text =
                    "Please upload your documents in PDF, JPG or PNG.",

                modifier =
                    Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 5.dp
                    ),

                color =
                    Color(0xFF44474D),

                fontSize = 14.sp
            )


            Spacer(
                modifier =
                    Modifier.height(
                        22.dp
                    )
            )


            /*
             * =================================
             * DOCUMENT LIST
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
                    ),

                verticalArrangement =
                    Arrangement.spacedBy(
                        15.dp
                    )
            ) {

                UploadDocumentCard(
                    title =
                        "NRIC Front",

                    fileName =
                        nricFront,

                    onClick = {
                        openDocumentPicker(0)
                    }
                )


                UploadDocumentCard(
                    title =
                        "NRIC Back",

                    fileName =
                        nricBack,

                    onClick = {
                        openDocumentPicker(1)
                    }
                )


                UploadDocumentCard(
                    title =
                        "Salary Slip / EA Form / EPF Statement",

                    fileName =
                        salaryDocument,

                    onClick = {
                        openDocumentPicker(2)
                    }
                )


                additionalDocuments
                    .forEachIndexed {
                            index,
                            fileName ->

                        UploadDocumentCard(
                            title =
                                "Additional Document ${index + 1}",

                            fileName =
                                fileName,

                            onClick = {
                                /*
                                 * For demo purposes,
                                 * additional documents
                                 * remain as selected.
                                 */
                            }
                        )
                    }


                /*
                 * =================================
                 * ADD MORE DOCUMENT
                 * =================================
                 */

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(
                            width = 1.dp,

                            color =
                                Color(
                                    0xFFC8C8C8
                                ),

                            shape =
                                RoundedCornerShape(
                                    8.dp
                                )
                        )
                        .clickable {
                            openDocumentPicker(3)
                        },

                    contentAlignment =
                        Alignment.Center
                ) {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.Add,

                            contentDescription =
                                null,

                            tint =
                                Color(
                                    0xFF333333
                                ),

                            modifier =
                                Modifier.size(
                                    18.dp
                                )
                        )


                        Spacer(
                            modifier =
                                Modifier.size(
                                    4.dp
                                )
                        )


                        Text(
                            text =
                                "Add More Document",

                            color =
                                Color(
                                    0xFF333333
                                ),

                            fontSize =
                                15.sp,

                            fontWeight =
                                FontWeight.Bold
                        )
                    }
                }
            }
        }


        /*
         * =================================
         * UPLOAD NOW BUTTON
         * =================================
         */

        Button(
            onClick =
                onUploadNow,

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
                RoundedCornerShape(
                    100.dp
                ),

            colors =
                ButtonDefaults
                    .buttonColors(
                        containerColor =
                            PrimaryBlue,

                        contentColor =
                            White
                    )
        ) {

            Text(
                text =
                    "Upload Now",

                fontSize =
                    15.sp,

                fontWeight =
                    FontWeight.Bold
            )
        }
    }
}


@Composable
private fun UploadDocumentCard(
    title: String,
    fileName: String?,
    onClick: () -> Unit
) {

    val completed =
        !fileName.isNullOrBlank()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(
                width = 1.dp,

                color =
                    Color(
                        0xFFC8C8C8
                    ),

                shape =
                    RoundedCornerShape(
                        8.dp
                    )
            )
            .clickable(
                onClick = onClick
            )
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            ),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

        /*
         * Document icon
         */

        Image(
            painter = painterResource(
                id = R.drawable.upload_document_icon
            ),
            contentDescription = null,
            modifier = Modifier.size(42.dp),
            contentScale = ContentScale.Fit
        )


        Spacer(
            modifier =
                Modifier.height(
                    10.dp
                )
        )


        Text(
            text = title,

            modifier =
                Modifier.fillMaxWidth(),

            color =
                Color.Black,

            fontSize = 14.sp,

            lineHeight = 18.sp,

            fontWeight =
                FontWeight.Bold,

            textAlign =
                TextAlign.Center
        )


        if (completed) {

            Spacer(
                modifier =
                    Modifier.height(
                        5.dp
                    )
            )


            Text(
                text =
                    fileName.orEmpty(),

                color =
                    Color(
                        0xFF666666
                    ),

                fontSize =
                    13.sp,

                maxLines = 1
            )


            Spacer(
                modifier =
                    Modifier.height(
                        4.dp
                    )
            )


            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.upload_completed_check
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp),
                    contentScale = ContentScale.Fit
                )


                Spacer(
                    modifier =
                        Modifier.size(
                            3.dp
                        )
                )


                Text(
                    text =
                        "COMPLETED",

                    color =
                        Color(
                            0xFF0EAF5F
                        ),

                    fontSize =
                        12.sp,

                    letterSpacing =
                        0.5.sp
                )
            }
        }
    }
}


private fun getFileName(
    context: Context,
    uri: Uri
): String {

    var fileName =
        "document"

    context
        .contentResolver
        .query(
            uri,
            null,
            null,
            null,
            null
        )
        ?.use { cursor ->

            val nameIndex =
                cursor.getColumnIndex(
                    OpenableColumns.DISPLAY_NAME
                )

            if (
                nameIndex >= 0 &&
                cursor.moveToFirst()
            ) {

                fileName =
                    cursor.getString(
                        nameIndex
                    )
            }
        }

    return fileName
}