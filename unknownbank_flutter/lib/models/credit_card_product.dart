class CreditCardBenefit {
  final String image;
  final String text;

  const CreditCardBenefit({
    required this.image,
    required this.text,
  });
}

class CreditCardProduct {
  final String name;
  final String bankingCategory;
  final String tier;
  final String interest;
  final String image;
  final String? heroImage;

  final String minIncome;
  final String annualFee;
  final String description;

  final List<CreditCardBenefit> featuredBenefits;

  const CreditCardProduct({
    required this.name,
    required this.bankingCategory,
    required this.tier,
    required this.interest,
    required this.image,
    this.heroImage,
    required this.minIncome,
    required this.annualFee,
    required this.description,
    required this.featuredBenefits,
  });
}

class CreditCardProducts {
  CreditCardProducts._();

  static const bankWorldMastercard =
      CreditCardProduct(
    name: 'Bank World Mastercard',
    bankingCategory: 'Conventional',
    tier: 'World',
    interest: 'Travel',
    image: 'card_world.png',
    heroImage: 'credit_card_world_hero.png',
    minIncome: 'RM 100,000 per annum',
    annualFee: 'RM 10,000',
    description:
        'Enjoy instant reward redemptions with Premier Travel Credit Card! '
        'Get up to 5x reward points, complimentary travel insurance and '
        'annual passes to selected airport lounges.',
    featuredBenefits: [
        CreditCardBenefit(
          image: 'benefit_fee_waiver.png',
          text: 'Annual Fee Waiver',
        ),
        CreditCardBenefit(
          image: 'benefit_cashback.png',
          text: 'Up to 15%\nCashback',
        ),
        CreditCardBenefit(
          image: 'benefit_points.png',
          text: 'Earn 5x\nReward Points',
        ),
      ],
  );

  static const bankGoldMastercard =
      CreditCardProduct(
    name: 'Bank Gold Mastercard',
    bankingCategory: 'Conventional',
    tier: 'Gold',
    interest: 'Rewards',
    image: 'card_gold.png',
    minIncome: 'RM 100,000 per annum',
    annualFee: 'FREE*',
    description:
        'Credit card benefits and features.',
    featuredBenefits: [
        CreditCardBenefit(
          image: 'benefit_fee_waiver.png',
          text: 'Annual Fee Waiver',
        ),
        CreditCardBenefit(
          image: 'benefit_cashback.png',
          text: 'Up to 15%\nCashback',
        ),
        CreditCardBenefit(
          image: 'benefit_points.png',
          text: 'Earn 5x\nReward Points',
        ),
      ],
  );

  static const bankWomanMastercard =
      CreditCardProduct(
    name: 'Bank Woman Mastercard',
    bankingCategory: 'Conventional',
    tier: '',
    interest: 'Cashback',
    image: 'card_woman.png',
    minIncome: 'RM 100,000 per annum',
    annualFee: 'FREE*',
    description:
        'Credit card benefits and features.',
    featuredBenefits: [
        CreditCardBenefit(
          image: 'benefit_fee_waiver.png',
          text: 'Annual Fee Waiver',
        ),
        CreditCardBenefit(
          image: 'benefit_cashback.png',
          text: 'Up to 15%\nCashback',
        ),
        CreditCardBenefit(
          image: 'benefit_points.png',
          text: 'Earn 5x\nReward Points',
        ),
      ],
  );

  static const bankPlatinumIslamicMastercard =
      CreditCardProduct(
    name: 'Bank Platinum Islamic Mastercard',
    bankingCategory: 'Islamic',
    tier: 'Platinum',
    interest: '',
    image: 'card_platinum_islamic.png',
    minIncome: 'RM 100,000 per annum',
    annualFee: 'FREE*',
    description:
        'Credit card benefits and features.',
    featuredBenefits: [
        CreditCardBenefit(
          image: 'benefit_fee_waiver.png',
          text: 'Annual Fee Waiver',
        ),
        CreditCardBenefit(
          image: 'benefit_cashback.png',
          text: 'Up to 15%\nCashback',
        ),
        CreditCardBenefit(
          image: 'benefit_points.png',
          text: 'Earn 5x\nReward Points',
        ),
      ],
  );

  static const all = [
    bankWorldMastercard,
    bankGoldMastercard,
    bankWomanMastercard,
    bankPlatinumIslamicMastercard,
  ];
}