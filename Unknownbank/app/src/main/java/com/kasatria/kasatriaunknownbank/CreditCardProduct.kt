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
    val heroImageRes: Int
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
}