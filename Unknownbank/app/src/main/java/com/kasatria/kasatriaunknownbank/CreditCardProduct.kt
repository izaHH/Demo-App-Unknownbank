package com.kasatria.kasatriaunknownbank

data class CreditCardProduct(
    val name: String,
    val category: String,
    val type: String,
    val bankingCategory: String,
    val benefit: String,
    val cardType: String,
    val tier: String,
    val interest: String,
    val imageRes: Int,
    val heroImageRes: Int?
)

object CreditCardProducts {

    val BankWorldMastercard = CreditCardProduct(
        name = "Bank World Mastercard",
        category = "Credit Card",
        type = "Personal",
        bankingCategory = "Conventional",
        benefit = "Annual Fee Waiver",
        cardType = "Principal",
        tier = "World",
        interest = "Travel",
        imageRes = R.drawable.card_world,
        heroImageRes = R.drawable.credit_card_world_hero
    )
    val BankGoldMastercard = CreditCardProduct(
        name = "Bank Gold Mastercard",
        category = "Credit Card",
        type = "Personal",
        bankingCategory = "Conventional",
        benefit = "",
        cardType = "Principal",
        tier = "Gold",
        interest = "",
        imageRes = R.drawable.card_gold,
        heroImageRes = null
    )

    val BankWomanMastercard = CreditCardProduct(
        name = "Bank Woman Mastercard",
        category = "Credit Card",
        type = "Personal",
        bankingCategory = "Conventional",
        benefit = "",
        cardType = "Principal",
        tier = "",
        interest = "",
        imageRes = R.drawable.card_woman,
        heroImageRes = null
    )

    val BankPlatinumIslamicMastercard = CreditCardProduct(
        name = "Bank Platinum Islamic Mastercard",
        category = "Credit Card",
        type = "Personal",
        bankingCategory = "Islamic",
        benefit = "",
        cardType = "Principal",
        tier = "Platinum",
        interest = "",
        imageRes = R.drawable.card_platinum_islamic,
        heroImageRes = null
    )
}