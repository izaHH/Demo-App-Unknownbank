import 'package:flutter/material.dart';

import '../theme/app_colors.dart';

class CreditCardApprovalScreen extends StatefulWidget {
  final String applicationReference;
  final String applicationDate;
  final VoidCallback onClose;
  final VoidCallback onBackToHome;
  final VoidCallback? onScreenShown;

  const CreditCardApprovalScreen({
    super.key,
    required this.applicationReference,
    required this.applicationDate,
    required this.onClose,
    required this.onBackToHome,
    this.onScreenShown,
  });

  @override
  State<CreditCardApprovalScreen> createState() =>
      _CreditCardApprovalScreenState();
}

class _CreditCardApprovalScreenState
    extends State<CreditCardApprovalScreen> {
  @override
  void initState() {
    super.initState();

    WidgetsBinding.instance.addPostFrameCallback((_) {
      widget.onScreenShown?.call();
    });
  }

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
            child: Padding(
              padding: const EdgeInsets.symmetric(
                horizontal: 29,
              ),
              child: Column(
                crossAxisAlignment:
                    CrossAxisAlignment.start,
                children: [
                  SizedBox(
                    width: double.infinity,
                    height: 70,
                    child: Align(
                      alignment: Alignment.centerLeft,
                      child: IconButton(
                        onPressed: widget.onClose,
                        icon: Image.asset(
                          'assets/images/approval_close.png',
                          width: 24,
                          height: 24,
                        ),
                      ),
                    ),
                  ),

                  Center(
                    child: Image.asset(
                      'assets/images/approval_success.png',
                      width: 100,
                      height: 100,
                    ),
                  ),

                  const SizedBox(height: 25),

                  const SizedBox(
                    width: double.infinity,
                    child: Text(
                      'Your application is approved\nin principle',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 20,
                        height: 1.4,
                        fontWeight: FontWeight.bold,
                        color: Colors.black,
                      ),
                    ),
                  ),

                  const SizedBox(height: 13),

                  const SizedBox(
                    width: double.infinity,
                    child: Text(
                      'You will be notified of the status of your application\n'
                      'via SMS and/ or Email',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 14,
                        height: 1.71,
                        color: Color(0xFF555555),
                      ),
                    ),
                  ),

                  const SizedBox(height: 31),

                  Container(
                    width: double.infinity,
                    height: 108,
                    padding: const EdgeInsets.symmetric(
                      horizontal: 17,
                      vertical: 15,
                    ),
                    decoration: BoxDecoration(
                      color: const Color(0xFFF7F8FA),
                      borderRadius:
                          BorderRadius.circular(11),
                    ),
                    child: Column(
                      children: [
                        Row(
                          mainAxisAlignment:
                              MainAxisAlignment
                                  .spaceBetween,
                          children: [
                            const Text(
                              'REFERENCE NO.',
                              style: TextStyle(
                                fontSize: 12,
                                color:
                                    Color(0xFF555555),
                              ),
                            ),

                            Text(
                              widget
                                  .applicationReference,
                              style: const TextStyle(
                                fontSize: 18,
                                color:
                                    Color(0xFF191C1E),
                              ),
                            ),
                          ],
                        ),

                        const SizedBox(height: 13),

                        const Divider(
                          height: 1,
                          color: Color(0xFFDDDDDD),
                        ),

                        const SizedBox(height: 13),

                        Row(
                          mainAxisAlignment:
                              MainAxisAlignment
                                  .spaceBetween,
                          children: [
                            const Text(
                              'DATE',
                              style: TextStyle(
                                fontSize: 12,
                                color:
                                    Color(0xFF555555),
                              ),
                            ),

                            Text(
                              widget.applicationDate,
                              style: const TextStyle(
                                fontSize: 14,
                                color:
                                    Color(0xFF333333),
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),

                  const SizedBox(height: 40),

                  const Text(
                    'Next Steps',
                    style: TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.bold,
                      color: Colors.black,
                    ),
                  ),

                  const SizedBox(height: 20),

                  const _NextStepItem(
                    image:
                        'assets/images/approval_email.png',
                    title: 'Check your email',
                    description:
                        "We've sent a confirmation with details.",
                  ),

                  const SizedBox(height: 27),

                  const _NextStepItem(
                    image:
                        'assets/images/approval_under_review.png',
                    title: 'Under Review',
                    description:
                        'Our team is verifying your information.',
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
                  onPressed:
                      widget.onBackToHome,
                  style: FilledButton.styleFrom(
                    backgroundColor:
                        AppColors.primary,
                    shape:
                        RoundedRectangleBorder(
                      borderRadius:
                          BorderRadius.circular(
                        100,
                      ),
                    ),
                  ),
                  child: const Text(
                    'Back To Home',
                    style: TextStyle(
                      fontSize: 15,
                      fontWeight:
                          FontWeight.bold,
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

class _NextStepItem extends StatelessWidget {
  final String image;
  final String title;
  final String description;

  const _NextStepItem({
    required this.image,
    required this.title,
    required this.description,
  });

  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment:
          CrossAxisAlignment.start,
      children: [
        Image.asset(
          image,
          width: 20,
          height: 20,
        ),

        const SizedBox(width: 12),

        Expanded(
          child: Column(
            crossAxisAlignment:
                CrossAxisAlignment.start,
            children: [
              Text(
                title,
                style: const TextStyle(
                  fontSize: 15,
                  fontWeight:
                      FontWeight.bold,
                  color: Colors.black,
                ),
              ),

              const SizedBox(height: 2),

              Text(
                description,
                style: const TextStyle(
                  fontSize: 14,
                  height: 1.36,
                  color:
                      Color(0xFF666666),
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}