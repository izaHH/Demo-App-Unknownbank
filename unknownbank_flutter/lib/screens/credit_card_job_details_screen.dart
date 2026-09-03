import 'package:flutter/material.dart';

import '../models/credit_card_application_data.dart';
import '../theme/app_colors.dart';

class CreditCardJobDetailsScreen extends StatefulWidget {
  final String productName;
  final CreditCardApplicationData applicationData;

  final ValueChanged<CreditCardApplicationData>
      onApplicationDataChange;

  final VoidCallback onNext;

  const CreditCardJobDetailsScreen({
    super.key,
    required this.productName,
    required this.applicationData,
    required this.onApplicationDataChange,
    required this.onNext,
  });

  @override
  State<CreditCardJobDetailsScreen> createState() =>
      _CreditCardJobDetailsScreenState();
}

class _CreditCardJobDetailsScreenState
    extends State<CreditCardJobDetailsScreen> {
  late CreditCardApplicationData data;

  @override
  void initState() {
    super.initState();
    data = widget.applicationData;
  }

  void _update(CreditCardApplicationData updatedData) {
    setState(() {
      data = updatedData;
    });

    widget.onApplicationDataChange(updatedData);
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
                          'Applying for ${widget.productName}',
                          maxLines: 1,
                          overflow: TextOverflow.ellipsis,
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

                const Padding(
                  padding: EdgeInsets.fromLTRB(
                    20,
                    20,
                    23,
                    0,
                  ),
                  child: Row(
                    mainAxisAlignment:
                        MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        'Tell Us About Your Job',
                        style: TextStyle(
                          fontSize: 15,
                          fontWeight: FontWeight.bold,
                          color: Color(0xFF333333),
                        ),
                      ),

                      Text(
                        '60%',
                        style: TextStyle(
                          fontSize: 14,
                          fontWeight: FontWeight.w500,
                          color: Color(0xFF0EAF5F),
                        ),
                      ),
                    ],
                  ),
                ),

                const SizedBox(height: 11),

                Padding(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 20,
                  ),
                  child: ClipRRect(
                    borderRadius:
                        BorderRadius.circular(100),
                    child: const LinearProgressIndicator(
                      value: 0.60,
                      minHeight: 6,
                      backgroundColor:
                          Color(0xFFE6E8EA),
                      valueColor:
                          AlwaysStoppedAnimation(
                        Color(0xFF0EAF5F),
                      ),
                    ),
                  ),
                ),

                const SizedBox(height: 23),

                Expanded(
                  child: SingleChildScrollView(
                    padding: const EdgeInsets.fromLTRB(
                      20,
                      0,
                      20,
                      100,
                    ),
                    child: Column(
                      children: [
                        _TextField(
                          label: 'Employer Name',
                          value: data.employerName,
                          onChanged: (value) {
                            _update(
                              data.copyWith(
                                employerName: value,
                              ),
                            );
                          },
                        ),

                        const SizedBox(height: 18),

                        _DropdownField(
                          label: 'Occupation',
                          value: data.occupation,
                          options: const [
                            'Sales Manager',
                            'Manager',
                            'Executive',
                            'Engineer',
                            'Consultant',
                            'Others',
                          ],
                          onChanged: (value) {
                            _update(
                              data.copyWith(
                                occupation: value,
                              ),
                            );
                          },
                        ),

                        const SizedBox(height: 18),

                        _DropdownField(
                          label: 'Sector',
                          value: data.sector,
                          options: const [
                            'Logistic',
                            'Banking',
                            'Technology',
                            'Retail',
                            'Manufacturing',
                            'Government',
                            'Others',
                          ],
                          onChanged: (value) {
                            _update(
                              data.copyWith(
                                sector: value,
                              ),
                            );
                          },
                        ),

                        const SizedBox(height: 18),

                        _DropdownField(
                          label: 'Employment Type',
                          value: data.employmentType,
                          options: const [
                            'Private Employed',
                            'Government',
                            'Self Employed',
                          ],
                          onChanged: (value) {
                            _update(
                              data.copyWith(
                                employmentType: value,
                              ),
                            );
                          },
                        ),

                        const SizedBox(height: 18),

                        _DropdownField(
                          label:
                              'Business Classification',
                          value:
                              data.businessClassification,
                          options: const [
                            'Private Limited',
                            'Public Limited',
                            'Partnership',
                            'Sole Proprietor',
                            'Government',
                          ],
                          onChanged: (value) {
                            _update(
                              data.copyWith(
                                businessClassification:
                                    value,
                              ),
                            );
                          },
                        ),

                        const SizedBox(height: 18),

                        _DropdownField(
                          label:
                              'Length of Service (Year)',
                          value:
                              data.yearsOfService,
                          options: List.generate(
                            31,
                            (index) =>
                                index.toString(),
                          ),
                          onChanged: (value) {
                            _update(
                              data.copyWith(
                                yearsOfService: value,
                              ),
                            );
                          },
                        ),

                        const SizedBox(height: 18),

                        _DropdownField(
                          label:
                              'Length of Service (Months)',
                          value:
                              data.monthsOfService,
                          options: List.generate(
                            12,
                            (index) =>
                                index.toString(),
                          ),
                          onChanged: (value) {
                            _update(
                              data.copyWith(
                                monthsOfService: value,
                              ),
                            );
                          },
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
                  onPressed: widget.onNext,
                  style: FilledButton.styleFrom(
                    backgroundColor:
                        AppColors.primary,
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

class _TextField extends StatefulWidget {
  final String label;
  final String value;
  final ValueChanged<String> onChanged;

  const _TextField({
    required this.label,
    required this.value,
    required this.onChanged,
  });

  @override
  State<_TextField> createState() =>
      _TextFieldState();
}

class _TextFieldState extends State<_TextField> {
  late final TextEditingController controller;

  @override
  void initState() {
    super.initState();

    controller =
        TextEditingController(text: widget.value);
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment:
          CrossAxisAlignment.start,
      children: [
        Text(
          widget.label,
          style: const TextStyle(
            fontSize: 14,
            color: Color(0xFF333333),
          ),
        ),

        const SizedBox(height: 7),

        SizedBox(
          height: 46,
          child: TextField(
            controller: controller,
            onChanged: widget.onChanged,
            style: const TextStyle(
              fontSize: 15,
              color: Color(0xFF666666),
            ),
            decoration: InputDecoration(
              filled: true,
              fillColor:
                  const Color(0xFFF7F9FB),
              contentPadding:
                  const EdgeInsets.symmetric(
                horizontal: 19,
              ),
              border: OutlineInputBorder(
                borderRadius:
                    BorderRadius.circular(8),
                borderSide: const BorderSide(
                  color: Color(0xFFDDDDDD),
                ),
              ),
              enabledBorder:
                  OutlineInputBorder(
                borderRadius:
                    BorderRadius.circular(8),
                borderSide: const BorderSide(
                  color: Color(0xFFDDDDDD),
                ),
              ),
            ),
          ),
        ),
      ],
    );
  }
}

class _DropdownField extends StatelessWidget {
  final String label;
  final String value;
  final List<String> options;
  final ValueChanged<String> onChanged;

  const _DropdownField({
    required this.label,
    required this.value,
    required this.options,
    required this.onChanged,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment:
          CrossAxisAlignment.start,
      children: [
        Text(
          label,
          style: const TextStyle(
            fontSize: 14,
            color: Color(0xFF333333),
          ),
        ),

        const SizedBox(height: 7),

        Container(
          height: 46,
          padding: const EdgeInsets.only(
            left: 19,
            right: 10,
          ),
          decoration: BoxDecoration(
            color:
                const Color(0xFFF7F9FB),
            borderRadius:
                BorderRadius.circular(8),
            border: Border.all(
              color:
                  const Color(0xFFDDDDDD),
            ),
          ),
          child: DropdownButtonHideUnderline(
            child: DropdownButton<String>(
              value: value,
              isExpanded: true,
              icon: const Icon(
                Icons.keyboard_arrow_down,
                color: Color(0xFF777777),
              ),
              style: const TextStyle(
                color: Color(0xFF666666),
                fontSize: 15,
              ),
              items: options
                  .map(
                    (option) =>
                        DropdownMenuItem<String>(
                      value: option,
                      child: Text(option),
                    ),
                  )
                  .toList(),
              onChanged: (value) {
                if (value != null) {
                  onChanged(value);
                }
              },
            ),
          ),
        ),
      ],
    );
  }
}