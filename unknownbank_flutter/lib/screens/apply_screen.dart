import 'package:flutter/material.dart';

import '../theme/app_colors.dart';

class ApplyScreen extends StatelessWidget {
  final VoidCallback onBack;
  final VoidCallback onCreditCard;

  const ApplyScreen({
    super.key,
    required this.onBack,
    required this.onCreditCard,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Positioned.fill(
            child: Image.asset(
              'assets/images/full_background_light.png',
              fit: BoxFit.cover,
            ),
          ),

          SafeArea(
            child: SingleChildScrollView(
              child: Column(
                children: [
                  SizedBox(
                    height: 56,
                    child: Stack(
                      alignment: Alignment.center,
                      children: [
                        Align(
                          alignment: Alignment.centerLeft,
                          child: Padding(
                            padding: const EdgeInsets.only(left: 8),
                            child: IconButton(
                              onPressed: onBack,
                              icon: const Icon(
                                Icons.arrow_back,
                                color: AppColors.textPrimary,
                              ),
                            ),
                          ),
                        ),

                        const Text(
                          'Apply',
                          style: TextStyle(
                            fontSize: 13,
                            fontWeight: FontWeight.bold,
                            color: AppColors.textPrimary,
                          ),
                        ),
                      ],
                    ),
                  ),

                  const Align(
                    alignment: Alignment.centerLeft,
                    child: Padding(
                      padding: EdgeInsets.only(
                        left: 20,
                        top: 13,
                        bottom: 20,
                      ),
                      child: Text(
                        'Select product',
                        style: TextStyle(
                          fontSize: 15,
                          fontWeight: FontWeight.bold,
                          color: AppColors.textPrimary,
                        ),
                      ),
                    ),
                  ),

                  Padding(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 20,
                    ),
                    child: Column(
                      children: [
                        _ProductCard(
                          image: 'apply_credit_card.png',
                          title: 'Credit Card',
                          description:
                              'Enjoy cashbacks, rewards and exclusive privileges.',
                          onTap: onCreditCard,
                        ),

                        const SizedBox(height: 15),

                        const _ProductCard(
                          image: 'apply_loan.png',
                          title: 'Loan',
                          description:
                              'Get the financing that you need to provide financial comfort for those you value the most.',
                        ),

                        const SizedBox(height: 15),

                        const _ProductCard(
                          image: 'apply_investment.png',
                          title: 'Investment',
                          description:
                              'Transact globally with no conversion fees.',
                        ),

                        const SizedBox(height: 15),

                        const _ProductCard(
                          image: 'apply_insurance.png',
                          title: 'Insurance',
                          description:
                              'Drive your dream car with fast approvals, flexible terms and easy payments.',
                        ),

                        const SizedBox(height: 15),

                        const _ProductCard(
                          image: 'apply_deposit.png',
                          title: 'Deposit Account',
                          description:
                              'Start your financial journey to save, invest and grow.',
                          enabled: false,
                        ),

                        const SizedBox(height: 30),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class _ProductCard extends StatelessWidget {
  final String image;
  final String title;
  final String description;
  final bool enabled;
  final VoidCallback? onTap;

  const _ProductCard({
    required this.image,
    required this.title,
    required this.description,
    this.enabled = true,
    this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return Opacity(
      opacity: enabled ? 1 : 0.18,
      child: InkWell(
        onTap: enabled ? onTap : null,
        borderRadius: BorderRadius.circular(12),
        child: Container(
          height: 130,
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(12),
            border: Border.all(
              color: const Color(0xFFEEEEEE),
            ),
            boxShadow: const [
              BoxShadow(
                color: Color(0x22000000),
                blurRadius: 4,
                offset: Offset(0, 2),
              ),
            ],
          ),
          child: Row(
            children: [
              SizedBox(
                width: 137,
                height: 130,
                child: Center(
                  child: Image.asset(
                    'assets/images/$image',
                    width: 80,
                    height: 80,
                    fit: BoxFit.contain,
                  ),
                ),
              ),

              Expanded(
                child: Padding(
                  padding: const EdgeInsets.only(right: 8),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment:
                        CrossAxisAlignment.start,
                    children: [
                      Text(
                        title,
                        style: const TextStyle(
                          color: Color(0xFF333333),
                          fontSize: 15,
                          fontWeight: FontWeight.bold,
                        ),
                      ),

                      const SizedBox(height: 6),

                      Text(
                        description,
                        style: const TextStyle(
                          color: Color(0xFF878787),
                          fontSize: 12,
                          height: 1.17,
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}