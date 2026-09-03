import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';

import '../models/credit_card_application_data.dart';
import '../theme/app_colors.dart';

class CreditCardUploadDocumentsScreen
    extends StatefulWidget {
  final String productName;
  final CreditCardApplicationData applicationData;

  final ValueChanged<CreditCardApplicationData>
      onApplicationDataChange;

  final VoidCallback onUploadNow;

  const CreditCardUploadDocumentsScreen({
    super.key,
    required this.productName,
    required this.applicationData,
    required this.onApplicationDataChange,
    required this.onUploadNow,
  });

  @override
  State<CreditCardUploadDocumentsScreen>
      createState() =>
          _CreditCardUploadDocumentsScreenState();
}

class _CreditCardUploadDocumentsScreenState
    extends State<CreditCardUploadDocumentsScreen> {
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

  Future<String?> _pickDocument() async {
  final file = await FilePicker.pickFile(
    type: FileType.custom,
    allowedExtensions: [
      'pdf',
      'jpg',
      'jpeg',
      'png',
    ],
  );

  if (file == null) {
    return null;
  }

  return file.name;
}

  Future<void> _selectFront() async {
    final fileName = await _pickDocument();

    if (fileName != null) {
      _update(
        data.copyWith(
          nricFrontDocument: fileName,
        ),
      );
    }
  }

  Future<void> _selectBack() async {
    final fileName = await _pickDocument();

    if (fileName != null) {
      _update(
        data.copyWith(
          nricBackDocument: fileName,
        ),
      );
    }
  }

  Future<void> _selectSalary() async {
    final fileName = await _pickDocument();

    if (fileName != null) {
      _update(
        data.copyWith(
          salaryDocument: fileName,
        ),
      );
    }
  }

  Future<void> _addDocument() async {
    final fileName = await _pickDocument();

    if (fileName != null) {
      _update(
        data.copyWith(
          additionalDocuments: [
            ...data.additionalDocuments,
            fileName,
          ],
        ),
      );
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

                const Align(
                  alignment: Alignment.centerLeft,
                  child: Padding(
                    padding: EdgeInsets.fromLTRB(
                      20,
                      20,
                      20,
                      0,
                    ),
                    child: Text(
                      'Upload Your Documents',
                      style: TextStyle(
                        fontSize: 15,
                        fontWeight:
                            FontWeight.bold,
                        color:
                            Color(0xFF333333),
                      ),
                    ),
                  ),
                ),

                const Align(
                  alignment: Alignment.centerLeft,
                  child: Padding(
                    padding: EdgeInsets.fromLTRB(
                      20,
                      5,
                      20,
                      0,
                    ),
                    child: Text(
                      'Please upload your documents in PDF, JPG or PNG.',
                      style: TextStyle(
                        fontSize: 14,
                        color:
                            Color(0xFF44474D),
                      ),
                    ),
                  ),
                ),

                const SizedBox(height: 22),

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
                        _UploadDocumentCard(
                          title: 'NRIC Front',
                          fileName:
                              data.nricFrontDocument,
                          onTap: _selectFront,
                        ),

                        const SizedBox(height: 15),

                        _UploadDocumentCard(
                          title: 'NRIC Back',
                          fileName:
                              data.nricBackDocument,
                          onTap: _selectBack,
                        ),

                        const SizedBox(height: 15),

                        _UploadDocumentCard(
                          title:
                              'Salary Slip / EA Form / EPF Statement',
                          fileName:
                              data.salaryDocument,
                          onTap: _selectSalary,
                        ),

                        for (int i = 0;
                            i <
                                data
                                    .additionalDocuments
                                    .length;
                            i++) ...[
                          const SizedBox(
                            height: 15,
                          ),

                          _UploadDocumentCard(
                            title:
                                'Additional Document ${i + 1}',
                            fileName: data
                                .additionalDocuments[i],
                            onTap: () {},
                          ),
                        ],

                        const SizedBox(height: 15),

                        InkWell(
                          onTap: _addDocument,
                          borderRadius:
                              BorderRadius.circular(
                            8,
                          ),
                          child: Container(
                            width: double.infinity,
                            height: 50,
                            alignment:
                                Alignment.center,
                            decoration: BoxDecoration(
                              border: Border.all(
                                color: const Color(
                                  0xFFC8C8C8,
                                ),
                              ),
                              borderRadius:
                                  BorderRadius.circular(
                                8,
                              ),
                            ),
                            child: const Row(
                              mainAxisAlignment:
                                  MainAxisAlignment
                                      .center,
                              children: [
                                Icon(
                                  Icons.add,
                                  size: 18,
                                  color: Color(
                                    0xFF333333,
                                  ),
                                ),

                                SizedBox(width: 4),

                                Text(
                                  'Add More Document',
                                  style: TextStyle(
                                    fontSize: 15,
                                    fontWeight:
                                        FontWeight.bold,
                                    color: Color(
                                      0xFF333333,
                                    ),
                                  ),
                                ),
                              ],
                            ),
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
                  onPressed: widget.onUploadNow,
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

class _UploadDocumentCard
    extends StatelessWidget {
  final String title;
  final String fileName;
  final VoidCallback onTap;

  const _UploadDocumentCard({
    required this.title,
    required this.fileName,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    final completed =
        fileName.trim().isNotEmpty;

    return InkWell(
      onTap: onTap,
      borderRadius: BorderRadius.circular(8),
      child: Container(
        width: double.infinity,
        height: 150,
        padding: const EdgeInsets.symmetric(
          horizontal: 16,
          vertical: 16,
        ),
        decoration: BoxDecoration(
          border: Border.all(
            color: const Color(0xFFC8C8C8),
          ),
          borderRadius: BorderRadius.circular(8),
        ),
        child: Column(
          mainAxisAlignment:
              MainAxisAlignment.center,
          children: [
            Image.asset(
              'assets/images/upload_document_icon.png',
              width: 42,
              height: 42,
            ),

            const SizedBox(height: 10),

            Text(
              title,
              textAlign: TextAlign.center,
              style: const TextStyle(
                fontSize: 14,
                height: 1.29,
                fontWeight: FontWeight.bold,
                color: Colors.black,
              ),
            ),

            if (completed) ...[
              const SizedBox(height: 5),

              Text(
                fileName,
                maxLines: 1,
                overflow:
                    TextOverflow.ellipsis,
                style: const TextStyle(
                  fontSize: 13,
                  color: Color(0xFF666666),
                ),
              ),

              const SizedBox(height: 4),

              Row(
                mainAxisAlignment:
                    MainAxisAlignment.center,
                children: [
                  Image.asset(
                    'assets/images/upload_completed_check.png',
                    width: 12,
                    height: 12,
                  ),

                  const SizedBox(width: 3),

                  const Text(
                    'COMPLETED',
                    style: TextStyle(
                      fontSize: 12,
                      letterSpacing: 0.5,
                      color:
                          Color(0xFF0EAF5F),
                    ),
                  ),
                ],
              ),
            ],
          ],
        ),
      ),
    );
  }
}