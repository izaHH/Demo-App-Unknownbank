package com.kasatria.kasatriaunknownbank

data class CreditCardApplicationData(

    // Screen 07 — Personal Details
    val title: String = "MS / PUAN",
    val fullName: String = "Farah Amira Ali",
    val icNumber: String = "851010145640",
    val dateOfBirth: String = "10-10-1985",
    val email: String = "farah.amira@gmail.com",
    val phoneCountryCode: String = "+60",
    val phoneNumber: String = "122500440",

    // Screen 08 — About You
    val nameOnCard: String = "Farah Amira Ali",
    val education: String = "Degree",
    val gender: String = "Female",
    val race: String = "Malay",
    val maritalStatus: String = "Married",
    val motherName: String = "Dayang",

    // Screen 09 — Job Details
    val employerName: String = "Star Trading",
    val occupation: String = "Sales Manager",
    val sector: String = "Logistic",
    val employmentType: String = "Private Employed",
    val businessClassification: String = "Private Limited",
    val yearsOfService: String = "10",
    val monthsOfService: String = "6",

    // Screen 10 — Extra Details
    val monthlyNetIncome: String = "12,000",
    val monthlyCommitments: String = "5,860",
    val retirementIncomeSource: String = "Rental Income",
    val statementDelivery: String = "Email",
    val collectionState: String =
        "Wilayah Persekutuan Kuala Lumpur",
    val collectionDistrict: String = "Kuala Lumpur",
    val collectionBranch: String = "Bangsar",

    //Screen 13 - Credit Card Details
    val nricFrontDocument: String = "farah_amira_ID_front.pdf",
    val nricBackDocument: String = "farah_amira_ID_back.pdf",
    val salaryDocument: String = "farah_amira_PaySlip.pdf",
    val additionalDocuments: List<String> = emptyList()
)