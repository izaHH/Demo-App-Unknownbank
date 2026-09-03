import 'package:flutter/material.dart';

import '../models/credit_card_product.dart';
import '../theme/app_colors.dart';

class CreditCardAlmostThereScreen
    extends StatelessWidget {
  final CreditCardProduct product;
  final String applicationReference;
  final VoidCallback onUploadNow;

  const CreditCardAlmostThereScreen({
    super.key,
    required this.product,
    required this.applicationReference,
    required this.onUploadNow,
  });

  @override
  Widget build(BuildContext context) {
    final image = product.name ==
            'Bank World Mastercard'
        ? 'application_card_world.png'
        : product.image;

    final isWorld =
        product.name == 'Bank World Mastercard';

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
              crossAxisAlignment:
                  CrossAxisAlignment.start,
              children: [
                SizedBox(
                  height: 56,
                  child: Stack(
                    alignment: Alignment.center,
                    children: [
                      Align(
                        alignment:
                            Alignment.centerLeft,
                        child: Padding(
                          padding:
                              const EdgeInsets.only(
                            left: 8,
                          ),
                          child: IconButton(
                            onPressed: () {
                              Navigator.of(context)
                                  .pop();
                            },
                            icon: const Icon(
                              Icons.arrow_back,
                              color:
                                  AppColors.textPrimary,
                            ),
                          ),
                        ),
                      ),

                      Padding(
                        padding:
                            const EdgeInsets.only(
                          left: 55,
                          right: 30,
                        ),
                        child: Text(
                          'Applying for ${product.name}',
                          maxLines: 1,
                          overflow:
                              TextOverflow.ellipsis,
                          style: const TextStyle(
                            fontSize: 13,
                            fontWeight:
                                FontWeight.bold,
                            color:
                                Color(0xFF333333),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),

                const Padding(
                  padding: EdgeInsets.fromLTRB(
                    20,
                    20,
                    20,
                    0,
                  ),
                  child: Text(
                    'You are almost there.',
                    style: TextStyle(
                      fontSize: 15,
                      fontWeight: FontWeight.bold,
                      color: Color(0xFF333333),
                    ),
                  ),
                ),

                const Padding(
                  padding: EdgeInsets.fromLTRB(
                    20,
                    5,
                    20,
                    0,
                  ),
                  child: Text(
                    'Upload your documents to complete your application',
                    style: TextStyle(
                      fontSize: 14,
                      height: 1.57,
                      color: Color(0xFF44474D),
                    ),
                  ),
                ),

                const SizedBox(height: 35),

                Padding(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 20,
                  ),
                  child: Container(
                    width: double.infinity,
                    padding: const EdgeInsets.fromLTRB(
                      17,
                      17,
                      17,
                      27,
                    ),
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius:
                          BorderRadius.circular(12),
                      border: Border.all(
                        color:
                            const Color(0xFFEEEEEE),
                      ),
                      boxShadow: const [
                        BoxShadow(
                          color: Color(0x22000000),
                          blurRadius: 6,
                          offset: Offset(0, 2),
                        ),
                      ],
                    ),
                    child: Column(
                      children: [
                        Row(
                          mainAxisAlignment:
                              MainAxisAlignment
                                  .spaceBetween,
                          children: [
                            const Text(
                              'Reference Number',
                              style: TextStyle(
                                fontSize: 14,
                                color: Colors.black,
                              ),
                            ),

                            Text(
                              applicationReference,
                              style: const TextStyle(
                                fontSize: 14,
                                fontWeight:
                                    FontWeight.bold,
                                color: Colors.black,
                              ),
                            ),
                          ],
                        ),

                        const SizedBox(height: 11),

                        const Divider(
                          height: 1,
                          color:
                              Color(0xFFF2F4F6),
                        ),

                        const SizedBox(height: 20),

                        SizedBox(
                          width: 280,
                          height: 175,
                          child: Image.asset(
                            'assets/images/$image',
                            fit: isWorld
                                ? BoxFit.cover
                                : BoxFit.contain,
                          ),
                        ),

                        const SizedBox(height: 15),

                        Text(
                          product.name,
                          textAlign:
                              TextAlign.center,
                          style: const TextStyle(
                            fontSize: 15,
                            color:
                                Color(0xFF333333),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),

                const SizedBox(height: 26),

                const Padding(
                  padding: EdgeInsets.symmetric(
                    horizontal: 20,
                  ),
                  child: Text(
                    '*You may close this page if you have uploaded your documents.',
                    style: TextStyle(
                      fontSize: 14,
                      height: 1.43,
                      color: Color(0xFF44474D),
                    ),
                  ),
                ),

                const SizedBox(height: 20),

                const Padding(
                  padding: EdgeInsets.symmetric(
                    horizontal: 20,
                  ),
                  child: Text(
                    '*If you do not have your supporting documents ready you may opt to upload them later before your application expires.',
                    style: TextStyle(
                      fontSize: 14,
                      height: 1.43,
                      color: Color(0xFF44474D),
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
                  onPressed: onUploadNow,
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
                    'Upload Now',
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