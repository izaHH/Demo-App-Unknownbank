import 'package:flutter/material.dart';

import '../models/credit_card_product.dart';
import '../theme/app_colors.dart';

class CreditCardSelectionScreen extends StatelessWidget {
  final VoidCallback onBack;
  final ValueChanged<CreditCardProduct> onCardSelected;
  final VoidCallback? onFilter;

  const CreditCardSelectionScreen({
    super.key,
    required this.onBack,
    required this.onCardSelected,
    this.onFilter,
  });

  @override
  Widget build(BuildContext context) {
    final products = CreditCardProducts.all;

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
                            padding: const EdgeInsets.only(
                              left: 8,
                            ),
                            child: IconButton(
                              onPressed: onBack,
                              icon: const Icon(
                                Icons.arrow_back,
                                color:
                                    AppColors.textPrimary,
                              ),
                            ),
                          ),
                        ),

                        const Text(
                          'Credit Card',
                          style: TextStyle(
                            color:
                                AppColors.textPrimary,
                            fontSize: 13,
                            fontWeight:
                                FontWeight.bold,
                          ),
                        ),
                      ],
                    ),
                  ),

                  Padding(
                    padding: const EdgeInsets.only(
                      left: 20,
                      right: 20,
                      top: 13,
                      bottom: 19,
                    ),
                    child: Row(
                      children: [
                        const Expanded(
                          child: Text(
                            'Select the right credit card for you',
                            style: TextStyle(
                              color:
                                  AppColors.textPrimary,
                              fontSize: 15,
                              fontWeight:
                                  FontWeight.bold,
                            ),
                          ),
                        ),

                        InkWell(
                          onTap: onFilter,
                          borderRadius:
                              BorderRadius.circular(20),
                          child: SizedBox(
                            width: 40,
                            height: 40,
                            child: Center(
                              child: Image.asset(
                                'assets/images/credit_card_filter.png',
                                width: 36,
                                height: 36,
                              ),
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),

                  Padding(
                    padding:
                        const EdgeInsets.symmetric(
                      horizontal: 20,
                    ),
                    child: Column(
                      children: [
                        for (
                          int i = 0;
                          i < products.length;
                          i++
                        ) ...[
                          _CreditCardOption(
                            product: products[i],
                            onTap: () {
                              onCardSelected(
                                products[i],
                              );
                            },
                          ),

                          if (i !=
                              products.length - 1)
                            const SizedBox(
                              height: 15,
                            ),
                        ],

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

class _CreditCardOption extends StatelessWidget {
  final CreditCardProduct product;
  final VoidCallback onTap;

  const _CreditCardOption({
    required this.product,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onTap,
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
            const SizedBox(width: 22),

            Image.asset(
              'assets/images/${product.image}',
              width: 140,
              height: 88,
              fit: BoxFit.contain,
            ),

            const SizedBox(width: 18),

            Expanded(
              child: Text(
                product.name,
                style: const TextStyle(
                  color: Color(0xFF333333),
                  fontSize: 15,
                  height: 1.13,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),

            const SizedBox(width: 10),
          ],
        ),
      ),
    );
  }
}