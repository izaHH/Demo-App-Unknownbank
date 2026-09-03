import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';

import 'firebase_options.dart';
import 'package:firebase_analytics/firebase_analytics.dart';

import 'screens/username_entry_screen.dart';
import 'theme/app_theme.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();

  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );

  await FirebaseAnalytics.instance
    .setAnalyticsCollectionEnabled(false);

  runApp(const UnknownBankApp());
}

class UnknownBankApp extends StatelessWidget {
  const UnknownBankApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Unknownbank',
      debugShowCheckedModeBanner: false,
      theme: AppTheme.lightTheme,
      home: const UsernameEntryScreen(),
    );
  }
}