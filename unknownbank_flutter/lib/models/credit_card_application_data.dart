class CreditCardApplicationData {
  final String title;
  final String fullName;
  final String icNumber;
  final String dateOfBirth;
  final String email;
  final String phoneCountryCode;
  final String phoneNumber;

  final String nameOnCard;
  final String education;
  final String gender;
  final String race;
  final String maritalStatus;
  final String motherName;

  final String employerName;
  final String occupation;
  final String sector;
  final String employmentType;
  final String businessClassification;
  final String yearsOfService;
  final String monthsOfService;

  final String monthlyNetIncome;
  final String monthlyCommitments;
  final String retirementIncomeSource;
  final String statementDelivery;
  final String collectionState;
  final String collectionDistrict;
  final String collectionBranch;

  final String nricFrontDocument;
  final String nricBackDocument;
  final String salaryDocument;
  final List<String> additionalDocuments;

  const CreditCardApplicationData({
    this.title = 'MS / PUAN',
    this.fullName = 'Farah Amira Ali',
    this.icNumber = '851010145640',
    this.dateOfBirth = '10-10-1985',
    this.email = 'farah.amira@gmail.com',
    this.phoneCountryCode = '+60',
    this.phoneNumber = '122500440',

    this.nameOnCard = 'Farah Amira Ali',
    this.education = 'Degree',
    this.gender = 'Female',
    this.race = 'Malay',
    this.maritalStatus = 'Married',
    this.motherName = 'Dayang',

    this.employerName = 'Star Trading',
    this.occupation = 'Sales Manager',
    this.sector = 'Logistic',
    this.employmentType = 'Private Employed',
    this.businessClassification = 'Private Limited',
    this.yearsOfService = '10',
    this.monthsOfService = '6',

    this.monthlyNetIncome = '12,000',
    this.monthlyCommitments = '5,860',
    this.retirementIncomeSource = 'Rental Income',
    this.statementDelivery = 'Email',
    this.collectionState =
        'Wilayah Persekutuan Kuala Lumpur',
    this.collectionDistrict = 'Kuala Lumpur',
    this.collectionBranch = 'Bangsar',

    this.nricFrontDocument =
        'farah_amira_ID_front.pdf',
    this.nricBackDocument =
        'farah_amira_ID_back.pdf',
    this.salaryDocument =
        'farah_amira_PaySlip.pdf',
    this.additionalDocuments = const [],
  });

  CreditCardApplicationData copyWith({
    String? title,
    String? fullName,
    String? icNumber,
    String? dateOfBirth,
    String? email,
    String? phoneCountryCode,
    String? phoneNumber,

    String? nameOnCard,
    String? education,
    String? gender,
    String? race,
    String? maritalStatus,
    String? motherName,

    String? employerName,
    String? occupation,
    String? sector,
    String? employmentType,
    String? businessClassification,
    String? yearsOfService,
    String? monthsOfService,

    String? monthlyNetIncome,
    String? monthlyCommitments,
    String? retirementIncomeSource,
    String? statementDelivery,
    String? collectionState,
    String? collectionDistrict,
    String? collectionBranch,

    String? nricFrontDocument,
    String? nricBackDocument,
    String? salaryDocument,
    List<String>? additionalDocuments,
  }) {
    return CreditCardApplicationData(
      title: title ?? this.title,
      fullName: fullName ?? this.fullName,
      icNumber: icNumber ?? this.icNumber,
      dateOfBirth:
          dateOfBirth ?? this.dateOfBirth,
      email: email ?? this.email,
      phoneCountryCode:
          phoneCountryCode ?? this.phoneCountryCode,
      phoneNumber:
          phoneNumber ?? this.phoneNumber,

      nameOnCard:
          nameOnCard ?? this.nameOnCard,
      education:
          education ?? this.education,
      gender: gender ?? this.gender,
      race: race ?? this.race,
      maritalStatus:
          maritalStatus ?? this.maritalStatus,
      motherName:
          motherName ?? this.motherName,

      employerName:
          employerName ?? this.employerName,
      occupation:
          occupation ?? this.occupation,
      sector: sector ?? this.sector,
      employmentType:
          employmentType ?? this.employmentType,
      businessClassification:
          businessClassification ??
              this.businessClassification,
      yearsOfService:
          yearsOfService ?? this.yearsOfService,
      monthsOfService:
          monthsOfService ?? this.monthsOfService,

      monthlyNetIncome:
          monthlyNetIncome ?? this.monthlyNetIncome,
      monthlyCommitments:
          monthlyCommitments ??
              this.monthlyCommitments,
      retirementIncomeSource:
          retirementIncomeSource ??
              this.retirementIncomeSource,
      statementDelivery:
          statementDelivery ??
              this.statementDelivery,
      collectionState:
          collectionState ?? this.collectionState,
      collectionDistrict:
          collectionDistrict ??
              this.collectionDistrict,
      collectionBranch:
          collectionBranch ??
              this.collectionBranch,

      nricFrontDocument:
          nricFrontDocument ??
              this.nricFrontDocument,
      nricBackDocument:
          nricBackDocument ??
              this.nricBackDocument,
      salaryDocument:
          salaryDocument ?? this.salaryDocument,
      additionalDocuments:
          additionalDocuments ??
              this.additionalDocuments,
    );
  }
}