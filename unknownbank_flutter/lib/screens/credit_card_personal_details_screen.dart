import 'package:flutter/material.dart';

import '../models/credit_card_application_data.dart';
import '../theme/app_colors.dart';

class CreditCardPersonalDetailsScreen
    extends StatefulWidget {
  final String productName;
  final CreditCardApplicationData applicationData;

  final ValueChanged<CreditCardApplicationData>
      onApplicationDataChange;

  final VoidCallback onNext;

  const CreditCardPersonalDetailsScreen({
    super.key,
    required this.productName,
    required this.applicationData,
    required this.onApplicationDataChange,
    required this.onNext,
  });

  @override
  State<CreditCardPersonalDetailsScreen>
      createState() =>
          _CreditCardPersonalDetailsScreenState();
}

class _CreditCardPersonalDetailsScreenState
    extends State<CreditCardPersonalDetailsScreen> {
  late CreditCardApplicationData data;

  @override
  void initState() {
    super.initState();
    data = widget.applicationData;
  }

  void _update(
    CreditCardApplicationData newData,
  ) {
    setState(() {
      data = newData;
    });

    widget.onApplicationDataChange(newData);
  }

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

            Padding(
              padding: const EdgeInsets.fromLTRB(
                20,
                20,
                23,
                0,
              ),
              child: const Row(
                mainAxisAlignment:
                    MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    'Personal Details',
                    style: TextStyle(
                      fontSize: 15,
                      fontWeight: FontWeight.bold,
                      color: Color(0xFF333333),
                    ),
                  ),

                  Text(
                    '20%',
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
                child: LinearProgressIndicator(
                  value: 0.20,
                  minHeight: 6,
                  backgroundColor:
                      const Color(0xFFE6E8EA),
                  valueColor:
                      const AlwaysStoppedAnimation(
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
                  90,
                ),
                child: Column(
                  children: [
                    _DropdownField(
                      label: 'Title',
                      value: data.title,
                      options: const [
                        'MS / PUAN',
                        'MR / ENCIK',
                      ],
                      onChanged: (value) {
                        _update(
                          data.copyWith(
                            title: value,
                          ),
                        );
                      },
                    ),

                    const SizedBox(height: 18),

                    _TextField(
                      label:
                          'Full Name (Completes as per NRIC)',
                      value: data.fullName,
                      onChanged: (value) {
                        _update(
                          data.copyWith(
                            fullName: value,
                          ),
                        );
                      },
                    ),

                    const SizedBox(height: 18),

                    _TextField(
                      label: 'IC No.',
                      value: data.icNumber,
                      onChanged: (value) {
                        _update(
                          data.copyWith(
                            icNumber: value,
                          ),
                        );
                      },
                    ),

                    const SizedBox(height: 18),

                    _TextField(
                      label: 'Date of birth',
                      value: data.dateOfBirth,
                      onChanged: (value) {
                        _update(
                          data.copyWith(
                            dateOfBirth: value,
                          ),
                        );
                      },
                    ),

                    const SizedBox(height: 18),

                    _TextField(
                      label: 'Email',
                      value: data.email,
                      onChanged: (value) {
                        _update(
                          data.copyWith(
                            email: value,
                          ),
                        );
                      },
                    ),

                    const SizedBox(height: 18),

                    Column(
                      crossAxisAlignment:
                          CrossAxisAlignment.start,
                      children: [
                        const Text(
                          'Mobile No.',
                          style: TextStyle(
                            fontSize: 14,
                            color:
                                Color(0xFF333333),
                          ),
                        ),

                        const SizedBox(height: 7),

                        Row(
                          children: [
                            SizedBox(
                              width: 100,
                              child: _DropdownBox(
                                value: data
                                    .phoneCountryCode,
                                options: const [
                                  '+60',
                                  '+65',
                                  '+62',
                                ],
                                onChanged: (value) {
                                  _update(
                                    data.copyWith(
                                      phoneCountryCode:
                                          value,
                                    ),
                                  );
                                },
                              ),
                            ),

                            const SizedBox(width: 10),

                            Expanded(
                              child: _TextBox(
                                value:
                                    data.phoneNumber,
                                keyboardType:
                                    TextInputType
                                        .phone,
                                onChanged: (value) {
                                  _update(
                                    data.copyWith(
                                      phoneNumber:
                                          value,
                                    ),
                                  );
                                },
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),

      bottomNavigationBar: SafeArea(
        top: false,
        child: Padding(
          padding: const EdgeInsets.fromLTRB(
            16,
            8,
            16,
            16,
          ),
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
    );
  }
}

class _TextField extends StatelessWidget {
  final String label;
  final String value;
  final ValueChanged<String> onChanged;

  const _TextField({
    required this.label,
    required this.value,
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

        _TextBox(
          value: value,
          onChanged: onChanged,
        ),
      ],
    );
  }
}

class _TextBox extends StatefulWidget {
  final String value;
  final ValueChanged<String> onChanged;
  final TextInputType? keyboardType;

  const _TextBox({
    required this.value,
    required this.onChanged,
    this.keyboardType,
  });

  @override
  State<_TextBox> createState() =>
      _TextBoxState();
}

class _TextBoxState extends State<_TextBox> {
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
    return SizedBox(
      height: 46,
      child: TextField(
        controller: controller,
        keyboardType: widget.keyboardType,
        onChanged: widget.onChanged,
        style: const TextStyle(
          color: Color(0xFF666666),
          fontSize: 15,
        ),
        decoration: InputDecoration(
          filled: true,
          fillColor: const Color(0xFFF7F9FB),
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
          enabledBorder: OutlineInputBorder(
            borderRadius:
                BorderRadius.circular(8),
            borderSide: const BorderSide(
              color: Color(0xFFDDDDDD),
            ),
          ),
        ),
      ),
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

        _DropdownBox(
          value: value,
          options: options,
          onChanged: onChanged,
        ),
      ],
    );
  }
}

class _DropdownBox extends StatelessWidget {
  final String value;
  final List<String> options;
  final ValueChanged<String> onChanged;

  const _DropdownBox({
    required this.value,
    required this.options,
    required this.onChanged,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 46,
      padding: const EdgeInsets.only(
        left: 19,
        right: 10,
      ),
      decoration: BoxDecoration(
        color: const Color(0xFFF7F9FB),
        borderRadius: BorderRadius.circular(8),
        border: Border.all(
          color: const Color(0xFFDDDDDD),
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
    );
  }
}