import 'package:flutter/material.dart';

import '../models/credit_card_application_data.dart';
import '../theme/app_colors.dart';

import 'credit_card_personal_details_screen.dart';
import 'credit_card_about_you_screen.dart';
import 'credit_card_job_details_screen.dart';
import 'credit_card_extra_details_screen.dart';

class CreditCardReviewScreen extends StatefulWidget {
  final String productName;
  final CreditCardApplicationData applicationData;

  final ValueChanged<CreditCardApplicationData>
      onApplicationDataChange;

  final VoidCallback onNext;

  const CreditCardReviewScreen({
    super.key,
    required this.productName,
    required this.applicationData,
    required this.onApplicationDataChange,
    required this.onNext,
  });

  @override
  State<CreditCardReviewScreen> createState() =>
      _CreditCardReviewScreenState();
}

class _CreditCardReviewScreenState
    extends State<CreditCardReviewScreen> {
  late CreditCardApplicationData data;

  @override
  void initState() {
    super.initState();
    data = widget.applicationData;
  }

  void _update(
    CreditCardApplicationData updatedData,
  ) {
    setState(() {
      data = updatedData;
    });

    widget.onApplicationDataChange(updatedData);
  }

  Future<void> _editPersonal() async {
    await Navigator.of(context).push(
      MaterialPageRoute(
        builder: (personalContext) =>
            CreditCardPersonalDetailsScreen(
          productName: widget.productName,
          applicationData: data,

          onApplicationDataChange: _update,

          onNext: () {
            Navigator.of(personalContext).push(
              MaterialPageRoute(
                builder: (aboutContext) =>
                    CreditCardAboutYouScreen(
                  productName:
                      widget.productName,
                  applicationData: data,

                  onApplicationDataChange:
                      _update,

                  onNext: () {
                    // About You -> Review
                    Navigator.of(aboutContext).pop();

                    // Personal Details -> Review
                    Navigator.of(personalContext).pop();
                  },
                ),
              ),
            );
          },
        ),
      ),
    );

    if (mounted) {
      setState(() {});
    }
  }

  Future<void> _editJob() async {
    await Navigator.of(context).push(
      MaterialPageRoute(
        builder: (jobContext) =>
            CreditCardJobDetailsScreen(
          productName: widget.productName,
          applicationData: data,

          onApplicationDataChange: _update,

          onNext: () {
            Navigator.of(jobContext).pop();
          },
        ),
      ),
    );

    if (mounted) {
      setState(() {});
    }
  }

  Future<void> _editExtra() async {
    await Navigator.of(context).push(
      MaterialPageRoute(
        builder: (extraContext) =>
            CreditCardExtraDetailsScreen(
          productName: widget.productName,
          applicationData: data,

          onApplicationDataChange: _update,

          onNext: () {
            Navigator.of(extraContext).pop();
          },
        ),
      ),
    );

    if (mounted) {
      setState(() {});
    }
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
                              const EdgeInsets.only(
                            left: 8,
                          ),
                          child: IconButton(
                            onPressed: () {
                              Navigator.of(context).pop();
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
                        padding: const EdgeInsets.only(
                          left: 55,
                          right: 30,
                        ),
                        child: Text(
                          'Applying for ${widget.productName}',
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
                    23,
                    0,
                  ),
                  child: Row(
                    mainAxisAlignment:
                        MainAxisAlignment
                            .spaceBetween,
                    children: [
                      Text(
                        'Review Details',
                        style: TextStyle(
                          fontSize: 15,
                          fontWeight:
                              FontWeight.bold,
                          color:
                              Color(0xFF333333),
                        ),
                      ),

                      Text(
                        '100%',
                        style: TextStyle(
                          fontSize: 14,
                          fontWeight:
                              FontWeight.w500,
                          color:
                              Color(0xFF0EAF5F),
                        ),
                      ),
                    ],
                  ),
                ),

                const SizedBox(height: 11),

                Padding(
                  padding:
                      const EdgeInsets.symmetric(
                    horizontal: 20,
                  ),
                  child: Container(
                    height: 6,
                    decoration: BoxDecoration(
                      color:
                          const Color(0xFF0EAF5F),
                      borderRadius:
                          BorderRadius.circular(
                        100,
                      ),
                    ),
                  ),
                ),

                const SizedBox(height: 26),

                Expanded(
                  child: SingleChildScrollView(
                    padding:
                        const EdgeInsets.fromLTRB(
                      20,
                      0,
                      20,
                      110,
                    ),
                    child: Column(
                      children: [
                        _ReviewCard(
                          title:
                              'Personal Details',
                          showPersonIcon: true,
                          showEditIcon: true,
                          onEdit: _editPersonal,
                          details: [
                            MapEntry(
                              'Title',
                              data.title,
                            ),
                            MapEntry(
                              'Name',
                              data.fullName,
                            ),
                            MapEntry(
                              'IC No.',
                              data.icNumber,
                            ),
                            MapEntry(
                              'Date of Birth',
                              data.dateOfBirth,
                            ),
                            MapEntry(
                              'Email',
                              data.email,
                            ),
                            MapEntry(
                              'Mobile No.',
                              '${data.phoneCountryCode} ${data.phoneNumber}',
                            ),
                            MapEntry(
                              'Name on Card',
                              data.nameOnCard,
                            ),
                            MapEntry(
                              'Education',
                              data.education,
                            ),
                            MapEntry(
                              'Gender',
                              data.gender,
                            ),
                            MapEntry(
                              'Race',
                              data.race,
                            ),
                            MapEntry(
                              'Marital Status',
                              data.maritalStatus,
                            ),
                            MapEntry(
                              "Mother's Name",
                              data.motherName,
                            ),
                          ],
                        ),

                        const SizedBox(height: 16),

                        _ReviewCard(
                          title: 'Job Details',
                          onEdit: _editJob,
                          details: [
                            MapEntry(
                              'Employer Name',
                              data.employerName,
                            ),
                            MapEntry(
                              'Occupation',
                              data.occupation,
                            ),
                            MapEntry(
                              'Sector',
                              data.sector,
                            ),
                            MapEntry(
                              'Employment Type',
                              data.employmentType,
                            ),
                            MapEntry(
                              'Business Classification',
                              data
                                  .businessClassification,
                            ),
                            MapEntry(
                              'Length of Service',
                              '${data.yearsOfService} years '
                                  '${data.monthsOfService} months',
                            ),
                          ],
                        ),

                        const SizedBox(height: 16),

                        _ReviewCard(
                          title: 'Extra Details',
                          onEdit: _editExtra,
                          details: [
                            MapEntry(
                              'Monthly Net Income',
                              'RM ${data.monthlyNetIncome}',
                            ),
                            MapEntry(
                              'Monthly Commitments',
                              'RM ${data.monthlyCommitments}',
                            ),
                            MapEntry(
                              'Retirement Income Source',
                              data
                                  .retirementIncomeSource,
                            ),
                            MapEntry(
                              'Statement Delivery',
                              data.statementDelivery,
                            ),
                            MapEntry(
                              'Collection State',
                              data.collectionState,
                            ),
                            MapEntry(
                              'Collection District',
                              data.collectionDistrict,
                            ),
                            MapEntry(
                              'Collection Branch',
                              data.collectionBranch,
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
                    shape:
                        RoundedRectangleBorder(
                      borderRadius:
                          BorderRadius.circular(
                        100,
                      ),
                    ),
                  ),
                  child: const Text(
                    'Next',
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

class _ReviewCard extends StatelessWidget {
  final String title;
  final List<MapEntry<String, String>> details;
  final VoidCallback onEdit;

  final bool showPersonIcon;
  final bool showEditIcon;

  const _ReviewCard({
    required this.title,
    required this.details,
    required this.onEdit,
    this.showPersonIcon = false,
    this.showEditIcon = false,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(17),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius:
            BorderRadius.circular(12),
        border: Border.all(
          color: const Color(0xFFEEEEEE),
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
        crossAxisAlignment:
            CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              if (showPersonIcon) ...[
                Image.asset(
                  'assets/images/review_person.png',
                  width: 16,
                  height: 16,
                ),

                const SizedBox(width: 8),
              ],

              Expanded(
                child: Text(
                  title,
                  style: const TextStyle(
                    fontSize: 14,
                    fontWeight:
                        FontWeight.bold,
                    color: Colors.black,
                  ),
                ),
              ),

              InkWell(
                onTap: onEdit,
                child: Row(
                  children: [
                    const Text(
                      'EDIT',
                      style: TextStyle(
                        fontSize: 12,
                        letterSpacing: 0.6,
                        color:
                            AppColors.primary,
                      ),
                    ),

                    if (showEditIcon) ...[
                      const SizedBox(width: 4),

                      Image.asset(
                        'assets/images/review_edit.png',
                        width: 12,
                        height: 12,
                      ),
                    ],
                  ],
                ),
              ),
            ],
          ),

          const SizedBox(height: 9),

          const Divider(
            height: 1,
            color: Color(0xFFF2F4F6),
          ),

          const SizedBox(height: 8),

          for (int i = 0;
              i < details.length;
              i++)
            _ReviewDetail(
              label: details[i].key,
              value: details[i].value,
              addBottomSpacing:
                  i != details.length - 1,
            ),
        ],
      ),
    );
  }
}

class _ReviewDetail extends StatelessWidget {
  final String label;
  final String value;
  final bool addBottomSpacing;

  const _ReviewDetail({
    required this.label,
    required this.value,
    required this.addBottomSpacing,
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
            fontSize: 13,
            height: 1.54,
            color: Color(0xFF666666),
          ),
        ),

        Text(
          value,
          style: const TextStyle(
            fontSize: 14,
            height: 1.43,
            color: Color(0xFF191C1E),
          ),
        ),

        if (addBottomSpacing)
          const SizedBox(height: 12),
      ],
    );
  }
}