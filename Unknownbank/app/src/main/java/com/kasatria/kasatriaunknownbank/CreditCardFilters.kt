package com.kasatria.kasatriaunknownbank

data class CreditCardFilters(
    val searchText: String = "",
    val tier: String = "All",
    val interest: String = "All"
)

fun List<CreditCardProduct>.applyFilters(
    filters: CreditCardFilters
): List<CreditCardProduct> {

    return filter { product ->

        val matchesSearch =
            filters.searchText.isBlank() ||
                    product.name.contains(
                        filters.searchText,
                        ignoreCase = true
                    )

        val matchesTier =
            filters.tier == "All" ||
                    product.tier.equals(
                        filters.tier,
                        ignoreCase = true
                    )

        val matchesInterest =
            filters.interest == "All" ||
                    when (filters.interest) {

                        "Islamic" ->
                            product.bankingCategory.equals(
                                "Islamic",
                                ignoreCase = true
                            )

                        else ->
                            product.interest.equals(
                                filters.interest,
                                ignoreCase = true
                            )
                    }

        matchesSearch &&
                matchesTier &&
                matchesInterest
    }
}