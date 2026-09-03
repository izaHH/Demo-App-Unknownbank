import 'package:firebase_analytics/firebase_analytics.dart';
import 'package:flutter/material.dart';

import '../theme/app_colors.dart';

class HomeScreen extends StatefulWidget {
  final VoidCallback? onApply;
  final VoidCallback? onCreditCardClick;
  final VoidCallback? onShop;

  const HomeScreen({
    super.key,
    this.onApply,
    this.onCreditCardClick,
    this.onShop,
  });

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final FirebaseAnalytics analytics = FirebaseAnalytics.instance;

  String selectedCategory = 'Accounts';

  final List<String> categories = const [
    'Accounts',
    'Credit Card',
    'Loan',
    'Investment',
  ];

  Future<void> _selectCategory(String category) async {
    setState(() {
      selectedCategory = category;
    });

    await analytics.logEvent(
      name: 'home_category_click',
      parameters: {
        'category': category,
      },
    );
  }

  Future<void> _quickAction(String action) async {
    await analytics.logEvent(
      name: 'home_quick_action_click',
      parameters: {
        'quick_action': action,
      },
    );

    if (action == 'Apply') {
      widget.onApply?.call();
    }

    if (action == 'e-Shop') {
      widget.onShop?.call();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Stack(
        children: [
          Positioned.fill(
            child: Image.asset(
              'assets/images/full_background_light.png',
              fit: BoxFit.cover,
            ),
          ),

          SingleChildScrollView(
            child: SizedBox(
              height: 900,
              width: double.infinity,
              child: Stack(
                children: [
                  const Positioned(
                    top: 52,
                    left: 0,
                    right: 0,
                    child: _HomeHeader(),
                  ),

                  Positioned(
                    top: 142,
                    left: 0,
                    right: 0,
                    child: SizedBox(
                      height: 40,
                      child: ListView.separated(
                        padding: const EdgeInsets.symmetric(
                          horizontal: 16,
                        ),
                        scrollDirection: Axis.horizontal,
                        itemCount: categories.length,
                        separatorBuilder: (_, _) =>
                            const SizedBox(width: 10),
                        itemBuilder: (context, index) {
                          final category = categories[index];

                          return _CategoryChip(
                            text: category,
                            selected:
                                selectedCategory == category,
                            onTap: () {
                              _selectCategory(category);
                            },
                          );
                        },
                      ),
                    ),
                  ),

                  Positioned(
                    top: 204,
                    left: 0,
                    right: 0,
                    child: SizedBox(
                      height: 170,
                      child: ListView(
                        padding: const EdgeInsets.only(
                          left: 16,
                          right: 16,
                        ),
                        scrollDirection: Axis.horizontal,
                        children: [
                          _buildAccountCard(),

                          if (selectedCategory == 'Accounts') ...[
                            const SizedBox(width: 15),
                            Container(
                              width: 290,
                              height: 160,
                              decoration: BoxDecoration(
                                color: Colors.white,
                                borderRadius:
                                    BorderRadius.circular(12),
                              ),
                            ),
                          ],
                        ],
                      ),
                    ),
                  ),

                  Positioned(
                    top: 420,
                    left: 0,
                    right: 0,
                    child: _QuickActionsSection(
                      onActionSelected: _quickAction,
                    ),
                  ),

                  const Positioned(
                    top: 659,
                    left: 0,
                    right: 0,
                    child: _HighlightsSection(),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildAccountCard() {
    switch (selectedCategory) {
      case 'Credit Card':
        return _AccountSummaryCard(
          accountName: 'Bank World Mastercard',
          accountNumber: '884-2',
          balanceLabel: 'Outstanding balance',
          balance: 'RM 2,450.00',
          onTap: widget.onCreditCardClick,
        );

      case 'Loan':
        return const _AccountSummaryCard(
          accountName: 'Personal Loan',
          accountNumber: '291-8',
          balanceLabel: 'Outstanding amount',
          balance: 'RM 18,500.00',
        );

      case 'Investment':
        return const _AccountSummaryCard(
          accountName: 'Investment Account',
          accountNumber: '731-4',
          balanceLabel: 'Current value',
          balance: 'RM 12,300.00',
        );

      default:
        return const _AccountSummaryCard(
          accountName: 'Bank Basic Account',
          accountNumber: '566-5',
          balanceLabel: 'Available balance',
          balance: 'RM 5,000.00',
        );
    }
  }
}

class _HomeHeader extends StatelessWidget {
  const _HomeHeader();

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 45,
      child: Stack(
        children: [
          Positioned(
            left: 17,
            child: Image.asset(
              'assets/images/header_profile.png',
              width: 38,
              height: 38,
            ),
          ),

          Positioned(
            right: 16,
            top: 8,
            child: Row(
              children: [
                Image.asset(
                  'assets/images/header_support.png',
                  width: 34,
                  height: 34,
                ),

                const SizedBox(width: 10),

                Image.asset(
                  'assets/images/header_notification.png',
                  width: 34,
                  height: 34,
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class _CategoryChip extends StatelessWidget {
  final String text;
  final bool selected;
  final VoidCallback onTap;

  const _CategoryChip({
    required this.text,
    required this.selected,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return InkWell(
      borderRadius: BorderRadius.circular(20),
      onTap: onTap,
      child: Container(
        height: 36,
        padding: const EdgeInsets.symmetric(
          horizontal: 16,
        ),
        alignment: Alignment.center,
        decoration: BoxDecoration(
          color:
              selected ? AppColors.link : Colors.transparent,
          borderRadius: BorderRadius.circular(20),
          border: Border.all(
            color: AppColors.link,
          ),
        ),
        child: Text(
          text,
          style: TextStyle(
            fontSize: 14,
            color:
                selected ? Colors.white : AppColors.link,
          ),
        ),
      ),
    );
  }
}

class _AccountSummaryCard extends StatefulWidget {
  final String accountName;
  final String accountNumber;
  final String balanceLabel;
  final String balance;
  final VoidCallback? onTap;

  const _AccountSummaryCard({
    required this.accountName,
    required this.accountNumber,
    required this.balanceLabel,
    required this.balance,
    this.onTap,
  });

  @override
  State<_AccountSummaryCard> createState() =>
      _AccountSummaryCardState();
}

class _AccountSummaryCardState
    extends State<_AccountSummaryCard> {
  bool balanceVisible = true;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: widget.onTap,
      child: Container(
        width: 340,
        height: 160,
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(12),
          boxShadow: const [
            BoxShadow(
              color: Color(0x22000000),
              blurRadius: 5,
              offset: Offset(0, 2),
            ),
          ],
        ),
        child: Stack(
          children: [
            Positioned(
              left: 20,
              top: 20,
              child: Container(
                width: 42,
                height: 42,
                alignment: Alignment.center,
                decoration: const BoxDecoration(
                  color: Color(0xFF1D75D9),
                  shape: BoxShape.circle,
                ),
                child: Image.asset(
                  'assets/images/unknownbank_logo_white.png',
                  width: 22,
                  height: 22,
                ),
              ),
            ),

            Positioned(
              left: 74,
              top: 20,
              child: Text(
                widget.accountName,
                style: const TextStyle(
                  color: Color(0xFF333333),
                  fontSize: 15,
                ),
              ),
            ),

            Positioned(
              left: 74,
              top: 45,
              child: Row(
                children: [
                  ...List.generate(
                    6,
                    (index) => Padding(
                      padding: EdgeInsets.only(
                        right: index == 5 ? 4 : 3,
                      ),
                      child: Container(
                        width: 6,
                        height: 6,
                        decoration: const BoxDecoration(
                          color: Color(0xFF666666),
                          shape: BoxShape.circle,
                        ),
                      ),
                    ),
                  ),

                  Text(
                    widget.accountNumber,
                    style: const TextStyle(
                      color: Color(0xFF666666),
                      fontSize: 13,
                    ),
                  ),
                ],
              ),
            ),

            Positioned(
              top: 5,
              right: 5,
              child: IconButton(
                onPressed: () {
                  setState(() {
                    balanceVisible = !balanceVisible;
                  });
                },
                icon: Icon(
                  balanceVisible
                      ? Icons.visibility
                      : Icons.visibility_off,
                  color: const Color(0xFF777777),
                  size: 24,
                ),
              ),
            ),

            Positioned(
              left: 20,
              bottom: 26,
              child: Text(
                widget.balanceLabel,
                style: const TextStyle(
                  color: Color(0xFF555555),
                  fontSize: 15,
                ),
              ),
            ),

            Positioned(
              right: 20,
              bottom: 17,
              child: Text(
                balanceVisible
                    ? widget.balance
                    : '••••••••',
                style: const TextStyle(
                  color: Color(0xFF333333),
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class _QuickActionsSection extends StatelessWidget {
  final ValueChanged<String> onActionSelected;

  const _QuickActionsSection({
    required this.onActionSelected,
  });

  static const List<_QuickActionData> actions = [
    _QuickActionData(
      image: 'quick_egold.png',
      label: 'e-Gold',
    ),
    _QuickActionData(
      image: 'quick_pay_bills.png',
      label: 'Pay Bills',
    ),
    _QuickActionData(
      image: 'quick_insurance.png',
      label: 'Insurance',
    ),
    _QuickActionData(
      image: 'quick_apply.png',
      label: 'Apply',
    ),
    _QuickActionData(
      image: 'quick_cash_loan.png',
      label: 'Cash Loan',
    ),
    _QuickActionData(
      image: 'quick_etrade.png',
      label: 'e-Trade',
    ),
    _QuickActionData(
      image: 'quick_eshop.png',
      label: 'e-Shop',
    ),
    _QuickActionData(
      image: 'quick_more.png',
      label: 'More',
    ),
  ];

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        const Padding(
          padding: EdgeInsets.symmetric(
            horizontal: 20,
          ),
          child: Row(
            mainAxisAlignment:
                MainAxisAlignment.spaceBetween,
            children: [
              Text(
                'Quick Actions',
                style: TextStyle(
                  fontSize: 13,
                  fontWeight: FontWeight.bold,
                  color: Color(0xFF333333),
                ),
              ),
              Text(
                'View All',
                style: TextStyle(
                  fontSize: 13,
                  color: AppColors.link,
                ),
              ),
            ],
          ),
        ),

        const SizedBox(height: 25),

        Padding(
          padding: const EdgeInsets.symmetric(
            horizontal: 28,
          ),
          child: GridView.builder(
            padding: EdgeInsets.zero,
            shrinkWrap: true,
            physics:
                const NeverScrollableScrollPhysics(),
            itemCount: actions.length,
            gridDelegate:
                const SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 4,
              mainAxisExtent: 83,
              mainAxisSpacing: 2,
              crossAxisSpacing: 12,
            ),
            itemBuilder: (context, index) {
              final action = actions[index];

              return InkWell(
                onTap: () {
                  onActionSelected(action.label);
                },
                child: Column(
                  children: [
                    Image.asset(
                      'assets/images/${action.image}',
                      width: 40,
                      height: 40,
                    ),

                    const SizedBox(height: 8),

                    Text(
                      action.label,
                      maxLines: 1,
                      textAlign: TextAlign.center,
                      style: const TextStyle(
                        fontSize: 11,
                        color: Color(0xFF333333),
                      ),
                    ),
                  ],
                ),
              );
            },
          ),
        ),
      ],
    );
  }
}

class _QuickActionData {
  final String image;
  final String label;

  const _QuickActionData({
    required this.image,
    required this.label,
  });
}

class _HighlightsSection extends StatelessWidget {
  const _HighlightsSection();

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        const Padding(
          padding: EdgeInsets.symmetric(horizontal: 16),
          child: Row(
            mainAxisAlignment:
                MainAxisAlignment.spaceBetween,
            children: [
              Text(
                'Highlights',
                style: TextStyle(
                  fontSize: 14,
                  fontWeight: FontWeight.bold,
                  color: Color(0xFF333333),
                ),
              ),

              Text(
                'View All',
                style: TextStyle(
                  fontSize: 13,
                  color: AppColors.link,
                ),
              ),
            ],
          ),
        ),

        const SizedBox(height: 17),

        SizedBox(
          height: 160,
          child: ListView(
            padding: const EdgeInsets.only(left: 16),
            scrollDirection: Axis.horizontal,
            children: [
              _highlight(
                'assets/images/highlight_1.png',
              ),

              const SizedBox(width: 10),

              _highlight(
                'assets/images/highlight_2.png',
              ),

              const SizedBox(width: 16),
            ],
          ),
        ),
      ],
    );
  }

  Widget _highlight(String path) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(8),
      child: Image.asset(
        path,
        width: 290,
        height: 160,
        fit: BoxFit.cover,
      ),
    );
  }
}