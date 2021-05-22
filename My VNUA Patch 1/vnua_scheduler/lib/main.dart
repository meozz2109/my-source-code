import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:provider/provider.dart';
import 'package:vnua_scheduler/services/user_service.dart';
import 'package:vnua_scheduler/views/home_view.dart';
import 'package:vnua_scheduler/views/infor_input_mod_view.dart';
import 'package:vnua_scheduler/views/infor_input_view.dart';
import 'package:vnua_scheduler/views/splash_view.dart';

import 'assets/theme.dart';
import 'commands/base_command.dart' as Commands;
import 'models/app_model.dart';
import 'models/mon_hoc.dart';
import 'models/user_model.dart';

final darkTheme = ThemeData(
  primarySwatch: Colors.grey,
  primaryColor: Colors.black,
  brightness: Brightness.dark,
  backgroundColor: const Color(0xFF212121),
  accentColor: Colors.yellow[700],
  accentIconTheme: IconThemeData(color: Colors.black),
  dividerColor: Colors.black12,
);

final lightTheme = ThemeData(
  primarySwatch: Colors.grey,
  primaryColor: Colors.white,
  brightness: Brightness.light,
  backgroundColor: const Color(0xFFE5E5E5),
  accentColor: Colors.black,
  accentIconTheme: IconThemeData(color: Colors.white),
  dividerColor: Colors.white54,
);

class ThemeNotifier with ChangeNotifier {
  ThemeData _themeData;

  ThemeNotifier(this._themeData);

  getTheme() => _themeData;

  setTheme(ThemeData themeData) async {
    _themeData = themeData;
    notifyListeners();
  }
}

void main() {
  runApp(
    MyApp(),
    // SplashView(
    //   key: UniqueKey(),
    //   onInitializationComplete: () => runMainApp(),
    // ),
  );
}

void runMainApp() {
  runApp(
    MyApp(),
  );
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {

    return MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (c) => AppModel()),
          ChangeNotifierProvider(create: (c) => UserModel()),
          Provider(create: (c) => UserService()),
          // FutureProvider<UserService>(
          //   create: (context) async {
          //     var service = UserService(Provider.of(context, listen: false));
          //     await service.init();
          //     return service;
          //   },
          // ),
        ],
        child: Builder(builder: (context) {
          // Save a context our Commands can use to access provided Models and Services
          Commands.init(context);
          return MaterialApp(
            // hide the debug banner
            debugShowCheckedModeBanner: false,
            title: 'VNUA Scheduler',
            theme:
            ThemeData(
              // This is the theme of your application.
              //
              // Try running your application with "flutter run". You'll see the
              // application has a blue toolbar. Then, without quitting the app, try
              // changing the primarySwatch below to Colors.green and then invoke
              // "hot reload" (press "r" in the console where you ran "flutter run",
              // or simply save your changes to "hot reload" in a Flutter IDE).
              // Notice that the counter didn't reset back to zero; the application
              // is not restarted.
              primarySwatch: Colors.lightGreen,
            ),
            home: VNUAScheduler(),
            // routes: {
            //   "single-course": (context) => MainScreen(),
            // },
          );
        }));
  }
}
class VNUAScheduler extends StatelessWidget {

  const VNUAScheduler({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    // mock data
    // _generateMockData(this.userModel);

    // Fluttertoast.showToast(
    //     msg: 'Demo: ',
    //     toastLength: Toast.LENGTH_SHORT,
    //     gravity: ToastGravity.CENTER,
    //     timeInSecForIosWeb: 1,
    //     backgroundColor: Colors.grey,
    //     textColor: Colors.black,
    //     fontSize: 16.0
    // );

    // Bind to AppModel.currentUser
    String currentUser = context.select<AppModel, String>((value) => value.currentUser);

    // return the current view, based on the currentUser value:
    return Scaffold(
      body: currentUser != null ? InforInputModifiedScreen() : InforInputModifiedScreen()
    );
  }
}

// This menu button widget updates a _selection field (of type WhyFarther,
// not shown here).

// Another method
void _generateMockData(UserModel userModel) {
  List<MonHoc> listCourse = List.empty(growable: true);
  listCourse.add(new MonHoc("CD02106", "Hình họa-Vẽ kỹ thuật", "ND305", "19/04/2021", "Năm", "4", "10h55 - 11h45", "10h55", "11h45", "Tiết 4 - 5"));
  listCourse.add(new MonHoc("ML01007", "Xã hội học đại cương 1", "B.201", "31/05/2021", "Ba", "6", "12h45 - 3h25", "12h45", "3h25", "Tiết 6 - 8"));

  userModel.listMonHoc=listCourse;
}