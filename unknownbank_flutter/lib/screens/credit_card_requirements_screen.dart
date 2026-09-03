import 'package:flutter/material.dart';

import '../theme/app_colors.dart';

class CreditCardRequirementsScreen extends StatelessWidget {
  final String productName;
  final VoidCallback onNext;

  const CreditCardRequirementsScreen({
    super.key,
    required this.productName,
    required this.onNext,
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

                      Padding(
                        padding: const EdgeInsets.only(
                          left: 55,
                          right: 30,
                        ),
                        child: Text(
                          'Applying for $productName',
                          maxLines: 1,
                          overflow: TextOverflow.ellipsis,
                          textAlign: TextAlign.center,
                          style: const TextStyle(
                            fontSize: 13,
                            fontWeight: FontWeight.bold,
                            color: Color(0xFF333333),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),

                Expanded(
                  child: SingleChildScrollView(
                    padding: const EdgeInsets.only(
                      bottom: 90,
                    ),
                    child: Column(
                      crossAxisAlignment:
                          CrossAxisAlignment.start,
                      children: [
                        const Padding(
                          padding: EdgeInsets.fromLTRB(
                            20,
                            20,
                            20,
                            0,
                          ),
                          child: Text(
                            'You must be Malaysian aged 21 - 65.',
                            style: TextStyle(
                              fontSize: 15,
                              height: 1.47,
                              fontWeight: FontWeight.bold,
                              color: Color(0xFF333333),
                            ),
                          ),
                        ),

                        const Padding(
                          padding: EdgeInsets.fromLTRB(
                            20,
                            4,
                            20,
                            0,
                          ),
                          child: Text(
                            'Below are the documents required for this application',
                            style: TextStyle(
                              fontSize: 14,
                              height: 1.57,
                              color: Color(0xFF44474D),
                            ),
                          ),
                        ),

                        const SizedBox(height: 30),

                        const Padding(
                          padding: EdgeInsets.symmetric(
                            horizontal: 20,
                          ),
                          child: _RequirementGroup(
                            title: 'Self Employed',
                            items: [
                              'Valid Malaysia NRIC',
                              'BE Form with official Tax Receipt AND',
                              'Last 6 Months Bank Statement AND',
                              'Copy of Business Registration',
                            ],
                          ),
                        ),

                        const SizedBox(height: 20),

                        const Padding(
                          padding: EdgeInsets.symmetric(
                            horizontal: 20,
                          ),
                          child: _RequirementGroup(
                            title: 'Employee',
                            items: [
                              'Valid Malaysia NRIC',
                              'Latest Salary Slip OR',
                              'EA Form OR',
                              'EPF Statement',
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ],
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
                  onPressed: onNext,
                  style: FilledButton.styleFrom(
                    backgroundColor: AppColors.primary,
                    shape: RoundedRectangleBorder(
                      borderRadius:
                          BorderRadius.circular(100),
                    ),
                  ),
                  child: const Text(
                    'Next',
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

class _RequirementGroup extends StatelessWidget {
  final String title;
  final List<String> items;

  const _RequirementGroup({
    required this.title,
    required this.items,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(20),
      decoration: BoxDecoration(
        color: const Color(0xFFF5F5F5),
        borderRadius: BorderRadius.circular(12),
      ),
      child: Column(
        crossAxisAlignment:
            CrossAxisAlignment.start,
        children: [
          Text(
            title,
            style: const TextStyle(
              fontSize: 14,
              height: 1.57,
              fontWeight: FontWeight.bold,
              color: Color(0xFF333333),
            ),
          ),

          const SizedBox(height: 10),

          for (int i = 0; i < items.length; i++) ...[
            _RequirementItem(
              text: items[i],
            ),

            if (i != items.length - 1)
              const SizedBox(height: 8),
          ],
        ],
      ),
    );
  }
}

class _RequirementItem extends StatelessWidget {
  final String text;

  const _RequirementItem({
    required this.text,
  });

  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment:
          CrossAxisAlignment.center,
      children: [
        Image.asset(
          'assets/images/requirement_check.png',
          width: 16,
          height: 16,
        ),

        const SizedBox(width: 10),

        Expanded(
          child: Text(
            text,
            style: const TextStyle(
              fontSize: 13,
              height: 1.69,
              color: Color(0xFF666666),
            ),
          ),
        ),
      ],
    );
  }
}