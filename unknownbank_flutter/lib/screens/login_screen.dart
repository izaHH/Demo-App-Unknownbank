import 'package:firebase_analytics/firebase_analytics.dart';
import 'package:flutter/material.dart';

import '../theme/app_colors.dart';
import 'main_shell.dart';

class LoginScreen extends StatefulWidget {
  final String username;

  const LoginScreen({
    super.key,
    required this.username,
  });

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final FirebaseAnalytics analytics = FirebaseAnalytics.instance;

  final TextEditingController passwordController =
      TextEditingController();

  bool passwordVisible = false;

  @override
  void initState() {
    super.initState();

    analytics.logScreenView(
      screenName: 'Login',
      screenClass: 'LoginScreen',
    );
  }

  @override
  void dispose() {
    passwordController.dispose();
    super.dispose();
  }

  String maskUsername(String username) {
    if (username.length <= 4) {
      return username;
    }

    final first = username.substring(0, 2);
    final last = username.substring(username.length - 2);

    final maskedLength = username.length - 4;

    return '$first ${'•' * maskedLength} $last';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        fit: StackFit.expand,
        children: [
          Image.asset(
            'assets/images/full_background_light.png',
            fit: BoxFit.cover,
          ),

          SafeArea(
            child: Padding(
              padding: const EdgeInsets.symmetric(
                horizontal: 20,
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const SizedBox(height: 36),

                  SizedBox(
                    height: 34,
                    child: Stack(
                      alignment: Alignment.center,
                      children: [
                        Align(
                          alignment: Alignment.centerLeft,
                          child: IconButton(
                          onPressed: () {
                            Navigator.of(context).pop();
                          },
                            icon: const Icon(
                              Icons.close,
                              size: 18,
                            ),
                          ),
                        ),

                        Image.asset(
                          'assets/images/unknownbank_logo.png',
                          width: 82,
                          height: 26,
                          fit: BoxFit.contain,
                        ),
                      ],
                    ),
                  ),

                  const SizedBox(height: 49),

                  Center(
                    child: Image.asset(
                      'assets/images/watermelon_avatar.png',
                      width: 80,
                      height: 80,
                    ),
                  ),

                  const SizedBox(height: 16),

                  Center(
                    child: Text(
                      maskUsername(widget.username),
                      style: const TextStyle(
                        fontSize: 16,
                        color: AppColors.textPrimary,
                      ),
                    ),
                  ),

                  const SizedBox(height: 8),

                  Center(
                    child: Container(
                      height: 36,
                      padding: const EdgeInsets.symmetric(
                        horizontal: 20,
                      ),
                      alignment: Alignment.center,
                      decoration: BoxDecoration(
                        border: Border.all(
                          color: AppColors.textPrimary,
                        ),
                        borderRadius: BorderRadius.circular(20),
                      ),
                      child: const Text(
                        'watermelon',
                        style: TextStyle(
                          fontSize: 14,
                          color: AppColors.textPrimary,
                        ),
                      ),
                    ),
                  ),

                  const SizedBox(height: 36),

                  const Text(
                    'Enter your password',
                    style: TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.w600,
                      color: AppColors.textPrimary,
                    ),
                  ),

                  const SizedBox(height: 16),

                  TextField(
                    controller: passwordController,
                    obscureText: !passwordVisible,
                    onChanged: (_) {
                      setState(() {});
                    },
                    decoration: InputDecoration(
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(8),
                      ),

                      focusedBorder: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(8),
                        borderSide: const BorderSide(
                          color: AppColors.primary,
                        ),
                      ),

                      suffixIcon: IconButton(
                        onPressed: () {
                          setState(() {
                            passwordVisible =
                                !passwordVisible;
                          });
                        },
                        icon: Icon(
                          passwordVisible
                              ? Icons.visibility_off
                              : Icons.visibility,
                        ),
                      ),
                    ),
                  ),

                  TextButton(
                    onPressed: () {},
                    style: TextButton.styleFrom(
                      padding: EdgeInsets.zero,
                    ),
                    child: const Text(
                      'Forgot Password',
                      style: TextStyle(
                        color: AppColors.link,
                      ),
                    ),
                  ),

                  const Spacer(),

                  SizedBox(
                    width: double.infinity,
                    height: 46,
                    child: FilledButton(
                      onPressed:
                        passwordController.text.isNotEmpty
                            ? () async {
                                await analytics.logEvent(
                                  name: 'login_button_click',
                                );

                                if (!mounted) return;

                                Navigator.of(context).pushAndRemoveUntil(
                                  MaterialPageRoute(
                                    builder: (_) => const MainShell(),
                                  ),
                                  (route) => false,
                                );
                              }
                            : null,

                      style: FilledButton.styleFrom(
                        backgroundColor:
                            AppColors.primary,

                        shape: RoundedRectangleBorder(
                          borderRadius:
                              BorderRadius.circular(100),
                        ),
                      ),

                      child: const Text(
                        'Login',
                        style: TextStyle(
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  ),

                  const SizedBox(height: 16),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}