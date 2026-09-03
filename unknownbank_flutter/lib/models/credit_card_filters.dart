import 'credit_card_product.dart';

class CreditCardFilters {
  final String searchText;
  final String tier;
  final String interest;

  const CreditCardFilters({
    this.searchText = '',
    this.tier = 'All',
    this.interest = 'All',
  });

  CreditCardFilters copyWith({
    String? searchText,
    String? tier,
    String? interest,
  }) {
    return CreditCardFilters(
      searchText: searchText ?? this.searchText,
      tier: tier ?? this.tier,
      interest: interest ?? this.interest,
    );
  }

  bool get isDefault =>
      searchText.isEmpty &&
      tier == 'All' &&
      interest == 'All';

  int get activeCount {
    var count = 0;

    if (searchText.trim().isNotEmpty) count++;
    if (tier != 'All') count++;
    if (interest != 'All') count++;

    return count;
  }

  List<CreditCardProduct> apply(
    List<CreditCardProduct> products,
  ) {
    return products.where((product) {
      final search = searchText.trim().toLowerCase();

      final matchesSearch =
          search.isEmpty ||
          product.name.toLowerCase().contains(search);

      final matchesTier =
          tier == 'All' ||
          product.tier.toLowerCase() ==
              tier.toLowerCase();

      final matchesInterest =
          interest == 'All' ||
          (interest == 'Islamic'
              ? product.bankingCategory
                      .toLowerCase() ==
                  'islamic'
              : product.interest.toLowerCase() ==
                  interest.toLowerCase());

      return matchesSearch &&
          matchesTier &&
          matchesInterest;
    }).toList();
  }

  @override
  bool operator ==(Object other) {
    return other is CreditCardFilters &&
        other.searchText == searchText &&
        other.tier == tier &&
        other.interest == interest;
  }

  @override
  int get hashCode =>
      Object.hash(searchText, tier, interest);
}