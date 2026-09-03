import 'package:flutter/material.dart';

import '../models/credit_card_filters.dart';
import '../models/credit_card_product.dart';
import '../theme/app_colors.dart';

class CreditCardFilterScreen extends StatefulWidget {
  final CreditCardFilters filters;

  const CreditCardFilterScreen({
    super.key,
    required this.filters,
  });

  @override
  State<CreditCardFilterScreen> createState() =>
      _CreditCardFilterScreenState();
}

class _CreditCardFilterScreenState
    extends State<CreditCardFilterScreen> {
  late CreditCardFilters draftFilters;

  @override
  void initState() {
    super.initState();
    draftFilters = widget.filters;
  }

  int get resultCount => draftFilters
      .apply(CreditCardProducts.all)
      .length;

  bool get hasChanges =>
      draftFilters != widget.filters;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SafeArea(
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
                        onPressed: () {
                          Navigator.pop(context);
                        },
                        icon: const Icon(
                          Icons.arrow_back,
                          color: AppColors.textPrimary,
                        ),
                      ),
                    ),
                  ),

                  const Text(
                    'Filters',
                    style: TextStyle(
                      fontSize: 13,
                      fontWeight: FontWeight.bold,
                      color: AppColors.textPrimary,
                    ),
                  ),

                  Align(
                    alignment: Alignment.centerRight,
                    child: Padding(
                      padding:
                          const EdgeInsets.only(right: 20),
                      child: GestureDetector(
                        onTap: draftFilters.isDefault
                            ? null
                            : () {
                                setState(() {
                                  draftFilters =
                                      const CreditCardFilters();
                                });
                              },
                        child: Text(
                          'Clear All',
                          style: TextStyle(
                            fontSize: 13,
                            fontWeight: FontWeight.bold,
                            color: draftFilters.isDefault
                                ? const Color(0xFFAAAAAA)
                                : AppColors.primary,
                          ),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),

            Expanded(
              child: SingleChildScrollView(
                padding:
                    const EdgeInsets.only(bottom: 30),
                child: Column(
                  crossAxisAlignment:
                      CrossAxisAlignment.start,
                  children: [
                    const _SectionHeader(
                      title: 'Search for',
                    ),

                    const SizedBox(height: 15),

                    Padding(
                      padding:
                          const EdgeInsets.symmetric(
                        horizontal: 20,
                      ),
                      child: TextField(
                        onChanged: (value) {
                          setState(() {
                            draftFilters =
                                draftFilters.copyWith(
                              searchText: value,
                            );
                          });
                        },
                        controller: TextEditingController(
                          text:
                              draftFilters.searchText,
                        )
                          ..selection =
                              TextSelection.collapsed(
                            offset: draftFilters
                                .searchText.length,
                          ),
                        decoration:
                            const InputDecoration(
                          hintText: 'Credit Card',
                          suffixIcon:
                              Icon(Icons.search),
                          border: OutlineInputBorder(),
                          contentPadding:
                              EdgeInsets.symmetric(
                            horizontal: 19,
                          ),
                        ),
                      ),
                    ),

                    const SizedBox(height: 30),
                    const Divider(height: 1),
                    const SizedBox(height: 20),

                    const _SectionHeader(
                      title: 'Credit Card Tiers',
                    ),

                    const SizedBox(height: 20),

                    _FilterRow(
                      values: const [
                        'All',
                        'Silver',
                        'Gold',
                        'World',
                        'Platinum',
                      ],
                      selected: draftFilters.tier,
                      onSelected: (value) {
                        setState(() {
                          draftFilters =
                              draftFilters.copyWith(
                            tier: value,
                          );
                        });
                      },
                    ),

                    const SizedBox(height: 30),
                    const Divider(height: 1),
                    const SizedBox(height: 20),

                    const _SectionHeader(
                      title: 'Interest',
                    ),

                    const SizedBox(height: 20),

                    _FilterRow(
                      values: const [
                        'All',
                        'Islamic',
                        'Cashback',
                        'Travel',
                        'Rewards',
                        'Petrol',
                        'Dining',
                      ],
                      selected:
                          draftFilters.interest,
                      onSelected: (value) {
                        setState(() {
                          draftFilters =
                              draftFilters.copyWith(
                            interest: value,
                          );
                        });
                      },
                    ),
                  ],
                ),
              ),
            ),

            Padding(
              padding: const EdgeInsets.fromLTRB(
                16,
                8,
                16,
                16,
              ),
              child: SizedBox(
                width: double.infinity,
                height: 46,
                child: FilledButton(
                  onPressed:
                      resultCount > 0 && hasChanges
                          ? () {
                              Navigator.pop(
                                context,
                                draftFilters,
                              );
                            }
                          : null,
                  style: FilledButton.styleFrom(
                    backgroundColor:
                        AppColors.primary,
                  ),
                  child: Text(
                    resultCount == 1
                        ? 'Show 1 Credit Card'
                        : 'Show $resultCount Credit Cards',
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class _SectionHeader extends StatelessWidget {
  final String title;

  const _SectionHeader({
    required this.title,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding:
          const EdgeInsets.symmetric(horizontal: 20),
      child: Row(
        mainAxisAlignment:
            MainAxisAlignment.spaceBetween,
        children: [
          Text(
            title,
            style: const TextStyle(
              fontSize: 15,
              fontWeight: FontWeight.bold,
              color: Color(0xFF333333),
            ),
          ),
          const Icon(
            Icons.keyboard_arrow_up,
            size: 20,
            color: Color(0xFF666666),
          ),
        ],
      ),
    );
  }
}

class _FilterRow extends StatelessWidget {
  final List<String> values;
  final String selected;
  final ValueChanged<String> onSelected;

  const _FilterRow({
    required this.values,
    required this.selected,
    required this.onSelected,
  });

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      scrollDirection: Axis.horizontal,
      padding:
          const EdgeInsets.symmetric(horizontal: 20),
      child: Row(
        children: [
          for (int i = 0;
              i < values.length;
              i++) ...[
            _FilterChip(
              text: values[i],
              selected: selected == values[i],
              onTap: () {
                onSelected(values[i]);
              },
            ),
            if (i != values.length - 1)
              const SizedBox(width: 10),
          ],
        ],
      ),
    );
  }
}

class _FilterChip extends StatelessWidget {
  final String text;
  final bool selected;
  final VoidCallback onTap;

  const _FilterChip({
    required this.text,
    required this.selected,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onTap,
      borderRadius: BorderRadius.circular(8),
      child: Container(
        height: 36,
        alignment: Alignment.center,
        padding:
            const EdgeInsets.symmetric(horizontal: 15),
        decoration: BoxDecoration(
          color: selected
              ? AppColors.link
              : Colors.transparent,
          borderRadius: BorderRadius.circular(8),
          border: Border.all(
            color: selected
                ? AppColors.link
                : const Color(0xFF666666),
          ),
        ),
        child: Text(
          text,
          style: TextStyle(
            fontSize: 15,
            color: selected
                ? Colors.white
                : const Color(0xFF666666),
          ),
        ),
      ),
    );
  }
}