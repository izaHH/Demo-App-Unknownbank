import 'package:flutter/material.dart';

import '../theme/app_colors.dart';
import 'home_screen.dart';
import 'apply_screen.dart';
import 'credit_card_selection_screen.dart';
import 'credit_card_detail_screen.dart';

class MainShell extends StatefulWidget {
  const MainShell({super.key});

  @override
  State<MainShell> createState() => _MainShellState();
}

class _MainShellState extends State<MainShell> {
  int selectedIndex = 0;

  Widget _currentScreen() {
    switch (selectedIndex) {
      case 1:
        return const _PlaceholderScreen(title: 'Account');

      case 2:
        return const _PlaceholderScreen(title: 'Scan');

      case 3:
        return const _PlaceholderScreen(title: 'Rewards');

      case 4:
        return const _PlaceholderScreen(title: 'Setting');

      default:
        return HomeScreen(
          onApply: _openApply,
        );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Positioned.fill(
            child: _currentScreen(),
          ),

          Align(
            alignment: Alignment.bottomCenter,
            child: _BottomNavigation(
              selectedIndex: selectedIndex,
              onSelected: (index) {
                setState(() {
                  selectedIndex = index;
                });
              },
            ),
          ),
        ],
      ),
    );
  }

  void _openApply() {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (_) => ApplyScreen(
          onBack: () {
            Navigator.of(context).pop();
          },

          onCreditCard: () {
            Navigator.of(context).push(
              MaterialPageRoute(
                builder: (_) =>
                    CreditCardSelectionScreen(
                  onBack: () {
                    Navigator.of(context).pop();
                  },

                onCardSelected: (product) {
                  Navigator.of(context).push(
                    MaterialPageRoute(
                      builder: (_) => CreditCardDetailScreen(
                        product: product,
                        onApplyNow: () {
                          debugPrint(
                            'Apply Now: ${product.name}',
                          );
                        },
                      ),
                    ),
                  );
                },

                  onFilter: () {
                    debugPrint(
                      'Credit Card Filter clicked',
                    );
                  },
                ),
              ),
            );
          },
        ),
      ),
    );
  }
}

class _BottomNavigation extends StatelessWidget {
  final int selectedIndex;
  final ValueChanged<int> onSelected;

  const _BottomNavigation({
    required this.selectedIndex,
    required this.onSelected,
  });

  @override
  Widget build(BuildContext context) {
    final bottomPadding =
        MediaQuery.paddingOf(context).bottom;

    return Container(
      height: 100 + bottomPadding,
      padding: EdgeInsets.only(
        bottom: bottomPadding,
      ),
      color: Colors.white,
      child: Row(
        mainAxisAlignment:
            MainAxisAlignment.spaceEvenly,
        crossAxisAlignment:
            CrossAxisAlignment.start,
        children: [
          _item(
            index: 0,
            image: 'nav_home.png',
            label: 'Home',
          ),

          _item(
            index: 1,
            image: 'nav_account.png',
            label: 'Account',
          ),

          _scanItem(),

          _item(
            index: 3,
            image: 'nav_rewards.png',
            label: 'Rewards',
          ),

          _item(
            index: 4,
            image: 'nav_setting.png',
            label: 'Setting',
          ),
        ],
      ),
    );
  }

  Widget _item({
    required int index,
    required String image,
    required String label,
  }) {
    final selected = selectedIndex == index;

    return InkWell(
      onTap: () {
        onSelected(index);
      },
      child: SizedBox(
        width: 64,
        height: 84,
        child: Column(
          mainAxisAlignment:
              MainAxisAlignment.center,
          children: [
            Image.asset(
              'assets/images/$image',
              width: 26,
              height: 26,
            ),

            const SizedBox(height: 6),

            Text(
              label,
              style: TextStyle(
                fontSize: 11,
                color: selected
                    ? AppColors.link
                    : const Color(0xFFA3A3A3),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _scanItem() {
    return InkWell(
      onTap: () {
        onSelected(2);
      },
      child: Transform.translate(
        offset: const Offset(0, -12),
        child: SizedBox(
          width: 80,
          height: 90,
          child: Column(
            children: [
              SizedBox(
                width: 80,
                height: 68,
                child: Stack(
                  alignment: Alignment.center,
                  children: [
                    Container(
                      width: 80,
                      height: 80,
                      decoration: const BoxDecoration(
                        shape: BoxShape.circle,
                        gradient: RadialGradient(
                          colors: [
                            Color(0x660DCBFF),
                            Color(0x220DCBFF),
                            Colors.transparent,
                          ],
                        ),
                      ),
                    ),

                    Container(
                      width: 57,
                      height: 57,
                      decoration: BoxDecoration(
                        color: Colors.white,
                        shape: BoxShape.circle,
                        boxShadow: [
                          BoxShadow(
                            color: Colors.black
                                .withValues(alpha: 0.15),
                            blurRadius: 6,
                          ),
                        ],
                      ),
                      child: Image.asset(
                        'assets/images/nav_scan.png',
                        width: 57,
                        height: 57,
                      ),
                    ),
                  ],
                ),
              ),

              const Text(
                'Scan',
                style: TextStyle(
                  fontSize: 11,
                  color: Color(0xFFA3A3A3),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _PlaceholderScreen extends StatelessWidget {
  final String title;

  const _PlaceholderScreen({
    required this.title,
  });

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        title,
        style: const TextStyle(
          fontSize: 24,
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }
}