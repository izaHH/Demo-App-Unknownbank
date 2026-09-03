import 'package:flutter/material.dart';

import '../models/credit_card_product.dart';
import '../theme/app_colors.dart';

class CreditCardDetailScreen extends StatelessWidget {
  final CreditCardProduct product;
  final VoidCallback onApplyNow;

  const CreditCardDetailScreen({
    super.key,
    required this.product,
    required this.onApplyNow,
  });

  @override
  Widget build(BuildContext context) {
    final heroImage = product.heroImage ?? product.image;

    return Scaffold(
      backgroundColor: Colors.white,
      body: Stack(
        children: [
          Positioned(
            top: 0,
            left: 0,
            right: 0,
            child: SizedBox(
              height: 420,
              child: Image.asset(
                'assets/images/$heroImage',
                fit: product.heroImage != null
                    ? BoxFit.cover
                    : BoxFit.contain,
              ),
            ),
          ),

          SafeArea(
            child: SingleChildScrollView(
              padding: const EdgeInsets.only(
                bottom: 100,
              ),
              child: Column(
                children: [
                  SizedBox(
                    height: 56,
                    child: Align(
                      alignment: Alignment.centerLeft,
                      child: Padding(
                        padding: const EdgeInsets.only(
                          left: 8,
                        ),
                        child: IconButton(
                          onPressed: () {
                            Navigator.of(context).pop();
                          },
                          icon: const Icon(
                            Icons.arrow_back,
                            color: AppColors.textPrimary,
                          ),
                        ),
                      ),
                    ),
                  ),

                  const SizedBox(height: 5),

                  Padding(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 20,
                    ),
                    child: Text(
                      product.name,
                      textAlign: TextAlign.center,
                      style: const TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                        color: Color(0xFF333333),
                      ),
                    ),
                  ),

                  const SizedBox(height: 12),

                  Padding(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 30,
                    ),
                    child: Row(
                      children: [
                        Expanded(
                          child: _InfoColumn(
                            title: 'Min. Income',
                            value: product.minIncome,
                          ),
                        ),

                        Container(
                          width: 1,
                          height: 31,
                          color: const Color(0xFFCFCFCF),
                        ),

                        Expanded(
                          child: _InfoColumn(
                            title: 'Annual Fee',
                            value: product.annualFee,
                          ),
                        ),
                      ],
                    ),
                  ),

                  const SizedBox(height: 245),

                  Padding(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 20,
                    ),
                    child: Text(
                      product.description,
                      textAlign: TextAlign.center,
                      style: const TextStyle(
                        fontSize: 15,
                        height: 1.47,
                        color: Color(0xFF444444),
                      ),
                    ),
                  ),

                  const SizedBox(height: 34),

                  const Text(
                    'Featured Benefits',
                    style: TextStyle(
                      fontSize: 15,
                      fontWeight: FontWeight.bold,
                      color: Color(0xFF333333),
                    ),
                  ),

                  const SizedBox(height: 18),

                  Padding(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 16,
                    ),
                    child: Row(
                      children: [
                        for (int i = 0;
                            i <
                                product
                                    .featuredBenefits.length;
                            i++) ...[
                          Expanded(
                            child: _BenefitCard(
                              benefit: product
                                  .featuredBenefits[i],
                            ),
                          ),

                          if (i !=
                              product.featuredBenefits
                                      .length -
                                  1)
                            const SizedBox(width: 10),
                        ],
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),

          Positioned(
            left: 16,
            right: 16,
            bottom: 16,
            child: SafeArea(
              top: false,
              child: SizedBox(
                height: 46,
                child: FilledButton(
                  onPressed: onApplyNow,
                  style: FilledButton.styleFrom(
                    backgroundColor:
                        AppColors.primary,
                    shape: RoundedRectangleBorder(
                      borderRadius:
                          BorderRadius.circular(100),
                    ),
                  ),
                  child: const Text(
                    'Apply Now',
                    style: TextStyle(
                      fontSize: 15,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class _InfoColumn extends StatelessWidget {
  final String title;
  final String value;

  const _InfoColumn({
    required this.title,
    required this.value,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(
          title,
          style: const TextStyle(
            fontSize: 12,
            color: Color(0xFF777777),
          ),
        ),
        const SizedBox(height: 4),
        Text(
          value,
          textAlign: TextAlign.center,
          style: const TextStyle(
            fontSize: 12,
            fontWeight: FontWeight.bold,
            color: Color(0xFF333333),
          ),
        ),
      ],
    );
  }
}

class _BenefitCard extends StatelessWidget {
  final CreditCardBenefit benefit;

  const _BenefitCard({
    required this.benefit,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 148,
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(10),
        border: Border.all(
          color: const Color(0xFFEEEEEE),
        ),
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Image.asset(
            'assets/images/${benefit.image}',
            width: 52,
            height: 52,
          ),
          const SizedBox(height: 18),
          Text(
            benefit.text,
            textAlign: TextAlign.center,
            style: const TextStyle(
              fontSize: 13,
              height: 1.23,
              color: Color(0xFF333333),
            ),
          ),
        ],
      ),
    );
  }
}