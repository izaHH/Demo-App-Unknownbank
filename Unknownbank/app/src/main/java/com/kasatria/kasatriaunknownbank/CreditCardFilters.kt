package com.kasatria.kasatriaunknownbank

data class CreditCardFilters(
    val searchText: String = "",
    val tier: String = "All",
    val interest: String = "All"
)