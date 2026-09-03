import 'package:flutter/material.dart';

import '../models/credit_card_filters.dart';
import '../models/credit_card_product.dart';
import '../theme/app_colors.dart';
import 'credit_card_filter_screen.dart';

class CreditCardSelectionScreen extends StatefulWidget {
  final VoidCallback onBack;
  final ValueChanged<CreditCardProduct> onCardSelected;

  const CreditCardSelectionScreen({
    super.key,
    required this.onBack,
    required this.onCardSelected,
  });

  @override
  State<CreditCardSelectionScreen> createState() =>
      _CreditCardSelectionScreenState();
}

class _CreditCardSelectionScreenState
    extends State<CreditCardSelectionScreen> {
  CreditCardFilters filters = const CreditCardFilters();

  Future<void> _openFilters() async {
    final result =
        await Navigator.of(context).push<CreditCardFilters>(
      MaterialPageRoute(
        builder: (_) => CreditCardFilterScreen(
          filters: filters,
        ),
      ),
    );

    if (result != null) {
      setState(() {
        filters = result;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    final products =
        filters.apply(CreditCardProducts.all);

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
                            padding:
                                const EdgeInsets.only(left: 8),
                            child: IconButton(
                              onPressed: widget.onBack,
                              icon: const Icon(
                                Icons.arrow_back,
                                color: AppColors.textPrimary,
                              ),
                            ),
                          ),
                        ),

                        const Text(
                          'Credit Card',
                          style: TextStyle(
                            fontSize: 13,
                            fontWeight: FontWeight.bold,
                            color: AppColors.textPrimary,
                          ),
                        ),
                      ],
                    ),
                  ),

                  Padding(
                    padding: const EdgeInsets.fromLTRB(
                      20,
                      13,
                      20,
                      19,
                    ),
                    child: Row(
                      children: [
                        const Expanded(
                          child: Text(
                            'Select the right credit card for you',
                            style: TextStyle(
                              fontSize: 15,
                              fontWeight: FontWeight.bold,
                              color: AppColors.textPrimary,
                            ),
                          ),
                        ),

                        InkWell(
                          onTap: _openFilters,
                          borderRadius:
                              BorderRadius.circular(20),
                          child: SizedBox(
                            width: 40,
                            height: 40,
                            child: Stack(
                              children: [
                                Center(
                                  child: Image.asset(
                                    'assets/images/credit_card_filter.png',
                                    width: 36,
                                    height: 36,
                                  ),
                                ),

                                if (filters.activeCount > 0)
                                  Positioned(
                                    right: 0,
                                    top: 0,
                                    child: Container(
                                      width: 17,
                                      height: 17,
                                      alignment:
                                          Alignment.center,
                                      decoration:
                                          const BoxDecoration(
                                        color:
                                            AppColors.primary,
                                        shape: BoxShape.circle,
                                      ),
                                      child: Text(
                                        '${filters.activeCount}',
                                        style:
                                            const TextStyle(
                                          fontSize: 10,
                                          fontWeight:
                                              FontWeight.bold,
                                          color: Colors.white,
                                        ),
                                      ),
                                    ),
                                  ),
                              ],
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),

                  Padding(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 20,
                    ),
                    child: products.isEmpty
                        ? const Padding(
                            padding:
                                EdgeInsets.only(top: 60),
                            child: Column(
                              children: [
                                Text(
                                  'No credit cards found',
                                  style: TextStyle(
                                    fontSize: 16,
                                    fontWeight:
                                        FontWeight.bold,
                                  ),
                                ),

                                SizedBox(height: 8),

                                Text(
                                  'Try changing your search or filters.',
                                ),
                              ],
                            ),
                          )
                        : Column(
                            children: [
                              for (int i = 0;
                                  i < products.length;
                                  i++) ...[
                                _CreditCardOption(
                                  product: products[i],
                                  onTap: () {
                                    widget.onCardSelected(
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
                  fontSize: 15,
                  height: 1.13,
                  fontWeight: FontWeight.bold,
                  color: Color(0xFF333333),
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