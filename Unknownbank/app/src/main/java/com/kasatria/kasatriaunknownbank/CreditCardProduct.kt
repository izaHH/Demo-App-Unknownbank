package com.kasatria.kasatriaunknownbank

data class CreditCardBenefit(
    val iconRes: Int,
    val text: String
)
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
    val heroImageRes: Int?,

    val minIncome: String,
    val annualFee: String,
    val description: String,
    val featuredBenefits: List<CreditCardBenefit>

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
        heroImageRes = R.drawable.credit_card_world_hero,

        minIncome = "RM 100,000 per annum",
        annualFee = "RM 10,000",
        description = "Enjoy instant reward redemptions with Premier Travel Credit Card! " + "Get up to 5x reward points, complimentary travel insurance and " +
            "annual passes to selected airport lounges.",

        featuredBenefits = listOf(
            CreditCardBenefit(
                iconRes = R.drawable.benefit_fee_waiver,
                text = "Annual Fee Waiver"
            ),
            CreditCardBenefit(
                iconRes = R.drawable.benefit_cashback,
                text = "Up to 15%\nCashback"
            ),
            CreditCardBenefit(
                iconRes = R.drawable.benefit_points,
                text = "Earn 5x\nReward Points"
            )
        )
    )
    val BankGoldMastercard = CreditCardProduct(
        name = "Bank Gold Mastercard",
        category = "Credit Card",
        type = "Personal",
        bankingCategory = "Conventional",
        benefit = "",
        cardType = "Principal",
        tier = "Gold",
        interest = "Rewards",
        imageRes = R.drawable.card_gold,
        heroImageRes = null,

        minIncome = "RM 100,000 per annum",
        annualFee = "FREE*",
        description = "Credit card benefits and features.",

        featuredBenefits = listOf(
            CreditCardBenefit(
                iconRes = R.drawable.benefit_fee_waiver,
                text = "Annual Fee Waiver"
            ),
            CreditCardBenefit(
                iconRes = R.drawable.benefit_cashback,
                text = "Up to 15%\nCashback"
            ),
            CreditCardBenefit(
                iconRes = R.drawable.benefit_points,
                text = "Earn 5x\nReward Points"
            )
        )
    )

    val BankWomanMastercard = CreditCardProduct(
        name = "Bank Woman Mastercard",
        category = "Credit Card",
        type = "Personal",
        bankingCategory = "Conventional",
        benefit = "",
        cardType = "Principal",
        tier = "",
        interest = "Cashback",
        imageRes = R.drawable.card_woman,
        heroImageRes = null,

        minIncome = "RM 100,000 per annum",
        annualFee = "FREE*",
        description = "Credit card benefits and features.",

        featuredBenefits = listOf(
            CreditCardBenefit(
                iconRes = R.drawable.benefit_fee_waiver,
                text = "Annual Fee Waiver"
            ),
            CreditCardBenefit(
                iconRes = R.drawable.benefit_cashback,
                text = "Up to 15%\nCashback"
            ),
            CreditCardBenefit(
                iconRes = R.drawable.benefit_points,
                text = "Earn 5x\nReward Points"
            )
        )
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
        heroImageRes = null,

        minIncome = "RM 100,000 per annum",
        annualFee = "FREE*",
        description = "Credit card benefits and features.",

        featuredBenefits = listOf(
            CreditCardBenefit(
                iconRes = R.drawable.benefit_fee_waiver,
                text = "Annual Fee Waiver"
            ),
            CreditCardBenefit(
                iconRes = R.drawable.benefit_cashback,
                text = "Up to 15%\nCashback"
            ),
            CreditCardBenefit(
                iconRes = R.drawable.benefit_points,
                text = "Earn 5x\nReward Points"
            )
        )
    )

    val all = listOf(
        BankWorldMastercard,
        BankGoldMastercard,
        BankWomanMastercard,
        BankPlatinumIslamicMastercard
    )
}