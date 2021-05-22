import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:intl/intl.dart';
import 'package:jiffy/jiffy.dart';
import 'package:logger/logger.dart';
import 'package:provider/provider.dart';
import 'package:vnua_scheduler/assets/constants.dart';
import 'package:vnua_scheduler/commands/data_fetch_command.dart';
import 'package:vnua_scheduler/models/mon_hoc.dart';
import 'package:vnua_scheduler/models/user_model.dart';
import 'package:vnua_scheduler/services/user_service.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:connectivity/connectivity.dart';
import 'package:vnua_scheduler/views/list_items/list_items.dart';
import 'package:vnua_scheduler/views/setting_view.dart';
import 'package:vnua_scheduler/assets/dummy_data.dart';
import 'package:vnua_scheduler/views/splash_view.dart';
import 'package:vnua_scheduler/views/update_server_error_view.dart';

import 'about_us_view.dart';
import 'no_connect_error_view.dart';

class MainScreen extends StatefulWidget {
  MainScreen({Key key, this.title, this.userInputCode}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;
  final String userInputCode;
  List<ListItem> mainListItems;

  @override
  _MainScreenState createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen>
    with SingleTickerProviderStateMixin {
  int _currentIndex = 0;

  static const int perSchedule = 0;
  static const int finalExam = 1;
  static const int notifications = 2;
  static const int profile = 3;
  static const int ticketer = 4;
  static const int admin = 5;

  final _tabs = {
    perSchedule: 'agenda',
    finalExam: 'mySchedule',
    notifications: 'notifications',
    profile: 'profile',
    ticketer: 'ticketer',
    admin: 'admin',
  };

  bool UPDATE_SERVER_OR_REGISTER_DATE = false, NO_INTERNET_CONNECTION = false;
  List<String> _userInforStringList = List.empty(growable: true),
      _firstDateCAndCSWeek = List.empty(growable: true);
  ScrollController _scrollViewController;
  double screenSize;
  double screenRatio;
  AppBar appBar;
  List<Tab> tabList = List.empty(growable: true);
  List<String> opratorsAmount = List.empty(growable: true);
  TabController _tabController;
  AnimationController _animationController;
  String _currentDate = DateFormat('dd/MM/yyyy').format(DateTime.now()),
      _fullName = "",
      _class = "",
      _currentStudyWeek = "",
      _currentStudyYear = "",
      _currentWeekStartDate = "",
      _currentWeekEndDate = "",
      _currentChosenWeek = "",
      _currentChosenWSDate = "",
      _currentChosenWEDate = "",
      _finalStudyWeek = "";
  int finalWeek = 0;
  var logger = Logger();

  PageController pageController;
  final ValueNotifier<int> currentIndex = ValueNotifier<int>(0);

  @override
  void initState() {
    _callCommandSolProb();

    // _forwardIntroSplashScreen();
    // _animationController =
    //     AnimationController(vsync: this, duration: Duration(milliseconds: 450));
    //
    // _scrollViewController = ScrollController(initialScrollOffset: 0.0);
    // tabList.add(new Tab(
    //   child: Text(
    //     "Thời khóa biểu",
    //     // style: GoogleFonts.robotoSlab(
    //     //   fontSize: 20,
    //     //   color: Color(0xff7DA3A1), //E73F0B
    //     // ),
    //   ),
    //
    //   // icon: FaIcon(FontAwesomeIcons.school),
    // ));
    // tabList.add(new Tab(
    //   child: Text(
    //     'Lịch thi',
    //     // style: GoogleFonts.robotoSlab(
    //     //   fontSize: 20,
    //     //   color: Color(0xff7DA3A1), //E73F0B
    //     // ),
    //   ),
    //   // icon: FaIcon(FontAwesomeIcons.accusoft),
    // ));
    // _tabController = new TabController(vsync: this, length: tabList.length);
    //
    // widget.mainListItems = List<ListItem>.generate(
    //   20,
    //   (i) => i % 6 == 0
    //       ? DateHeaderItem("Thứ $i, ", "$i/$i/$i")
    //       : MainContentItem("1$i" + "h00 - 1$i" + "h00", "$i" + " - " + "$i",
    //           "Demo name $i", "Demo place $i"),
    // );
    //
    // opratorsAmount.add("Demo 1");
    // opratorsAmount.add("Demo 2");
    // opratorsAmount.add("Demo 3");
    //
    // // Tab controller
    // pageController = PageController(initialPage: 0);
    // pageController.addListener(() {
    //   if (pageController.page.round() != currentIndex.value) {
    //     currentIndex.value = pageController.page.round();
    //   }
    // });
    // currentIndex.addListener(() {
    //   setState(() {});
    // });

    super.initState();
  }

  @override
  void dispose() {
    _tabController.dispose();
    _scrollViewController.dispose();
    super.dispose();
  }

  void _callCommandSolProb() async {
    // Fluttertoast.showToast(
    //     msg: 'Demo before',
    //     toastLength: Toast.LENGTH_SHORT,
    //     gravity: ToastGravity.CENTER,
    //     timeInSecForIosWeb: 1,
    //     backgroundColor: Colors.grey,
    //     textColor: Colors.black,
    //     fontSize: 16.0);

    List<String> listString = await DataFetchCommand().commonFetchInfor(widget.userInputCode);
      _firstDateCAndCSWeek =
          await DataFetchCommand().fetchFDateCACSWeek(listString);

      _userInforStringList =
          await DataFetchCommand().fetchUserInfo(listString);
      _fullName = _userInforStringList[0];
      _class = _userInforStringList[1];
      // Fluttertoast.showToast(
      //     msg: 'Test ' + _fullName + ", " + _class,
      //     toastLength: Toast.LENGTH_LONG,
      //     gravity: ToastGravity.CENTER,
      //     timeInSecForIosWeb: 1,
      //     backgroundColor: Colors.grey,
      //     textColor: Colors.black,
      //     fontSize: 16.0);
      _finalStudyWeek = _userInforStringList[2];
      // finalWeek = int.parse(_finalStudyWeek);
      // .length.toString();

      // handle to split out Current Study Week, Start date of CSW & End date of CSW
          ;
      var listSplit = _firstDateCAndCSWeek[1].split(", ");
      _currentStudyWeek = listSplit[0];
      _currentStudyYear = listSplit[2];

      var dateListSplit = listSplit[1].split("-");
      _currentWeekStartDate = dateListSplit[0];
      _currentWeekEndDate = dateListSplit[1];

      // set to current chosen insteads
      _currentChosenWeek = _currentStudyWeek;
      _currentChosenWSDate = _currentWeekStartDate;
      _currentChosenWEDate = _currentWeekEndDate;

      setState((){});
  }

  void _testServiceAndCommand() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      // _tabController.animateTo(1,
      //     curve: Curves.bounceInOut, duration: Duration(milliseconds: 10));

      // _scrollViewController.animateTo(
      //     _scrollViewController.position.minScrollExtent,
      //     duration: Duration(milliseconds: 1000),
      //     curve: Curves.decelerate);

      // _scrollViewController
      //     .jumpTo(_scrollViewController.position.maxScrollExtent);

      _callCommandSolProb();
      // _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    // calculate the screen size
    screenSize = MediaQuery.of(context).size.width;

    FocusScope.of(context).requestFocus(new FocusNode());
    FocusScope.of(context).unfocus();

    // widget.mainListItems = List<ListItem>.generate(
    //   20,
    //   (i) => i % 6 == 0
    //       ? DateHeaderItem("Thứ $i, ", "$i/$i/$i")
    //       : MainContentItem("1$i" + "h00 - 1$i" + "h00", "$i" + " - " + "$i",
    //           "Demo name $i", "Demo place $i"),
    // );

    // final courseID = ModalRoute.of(context).settings.arguments;

    // find the courseID that matches the ID passed from the InforInputScreen
    // final course = DUMMY_DATA.firstWhere((element) => element["_maMonHoc"] == courseID);

    // var result = await _userService.handleNormalSchedule();
    // logger.i("Result: "+result);
    //
    // Fluttertoast.showToast(
    //     msg: 'CALL User Service DEMO',
    //     toastLength: Toast.LENGTH_SHORT,
    //     gravity: ToastGravity.CENTER,
    //     timeInSecForIosWeb: 1,
    //     backgroundColor: Colors.grey,
    //     textColor: Colors.black,
    //     fontSize: 16.0
    // );

    // demo calling handleNormalSchedule method of User Service
    // _callUserService(context);

    // Fluttertoast.showToast(
    //     msg: 'Body DEMO',
    //     toastLength: Toast.LENGTH_SHORT,
    //     gravity: ToastGravity.CENTER,
    //     timeInSecForIosWeb: 1,
    //     backgroundColor: Colors.grey,
    //     textColor: Colors.black,
    //     fontSize: 16.0
    // );

    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.

    return Scaffold(
      backgroundColor: Color(0xffC2C2C2),
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        elevation: 1.0,
        centerTitle: true,
        leading: Builder(
          builder: (BuildContext context) {
            return IconButton(
              icon: Image.asset(
                "lib/assets/images/ic_logo_vnua_st_one.png",
                width: 50,
                height: 50,
                scale: 0.6,
                fit: BoxFit.fitWidth,
              ),
              color: Color(0xff4C3F54),
              onPressed: () {
                _callCommandSolProb();

                // Scaffold.of(context).openDrawer();
              },
              // tooltip: MaterialLocalizations.of(context).openAppDrawerTooltip,
            );
          },
        ),
        title: Text(
          widget.title,
          style: GoogleFonts.bitter(
            fontSize: 20,
            color: Color(0xffD72C16), //E73F0B
          ),
        ),
        actions: [
          PopupMenuButton<String>(
            elevation: 2,
            padding: EdgeInsets.symmetric(horizontal: 0.5),
            onSelected: _showCheckedMenuSelections,
            itemBuilder: (BuildContext context) => <PopupMenuEntry<String>>[
              // CheckedPopupMenuItem<String>(
              //   value: "",
              //   checked: true,
              //   child: Text("", style: Theme.of(context).textTheme.bodyText1),
              // ),
              PopupMenuItem<String>(
                value: 'update',
                child: ListTile(
                  contentPadding: const EdgeInsets.symmetric(horizontal: 0),
                  leading: IconButton(
                    icon: Icon(
                      Icons.update,
                      color: Color(0xffD72C16),
                    ),
                    onPressed: () {
                      _callCommandSolProb();
                    },
                  ),
                  title: Container(
                    width: 100,
                    child: Text(
                      'Cập nhật lịch hiện tại',
                      style: GoogleFonts.markaziText(
                        fontSize: 18,
                        color: Colors.black,
                      ),
                    ),
                  ),
                ),
              ),
              // PopupMenuDivider(),
              PopupMenuItem<String>(
                value: 'info',
                child: ListTile(
                  contentPadding: const EdgeInsets.symmetric(horizontal: 0),
                  // minVerticalPadding: 0.25,
                  leading: IconButton(
                    icon: Icon(
                      Icons.info,
                      color: Color(0xff04BFBF),
                    ),
                    onPressed: () {
                      // Navigator.push(
                      //   context,
                      //   MaterialPageRoute(builder: (context) => AboutUsScreen()),
                      // );
                    },
                  ),
                  title: Container(
                    width: 100,
                    child: Text(
                      'Thông tin ứng dụng',
                      style: GoogleFonts.markaziText(
                        fontSize: 18,
                        color: Colors.black,
                      ),
                    ),
                  ),
                ),
              ),
              // PopupMenuDivider(),
              PopupMenuItem<String>(
                value: 'setting',
                child: ListTile(
                  contentPadding: const EdgeInsets.symmetric(horizontal: 0),
                  // minVerticalPadding: 0.25,
                  leading: IconButton(
                    icon: Icon(
                      Icons.settings_sharp,
                      color: Color(0xff867666),
                    ),
                    // onPressed: (){
                    //   Navigator.push(
                    //     context,
                    //     MaterialPageRoute(builder: (context) => SettingScreen()),
                    //   );
                    // },
                  ),
                  title: Container(
                    width: 100,
                    child: Text(
                      'Cài đặt',
                      style: GoogleFonts.markaziText(
                        fontSize: 18,
                        color: Colors.black,
                      ),
                    ),
                  ),
                ),
              ),
            ],
          ),
          // PopupMenuButton(
          //   itemBuilder: (BuildContext bc) => [
          //     PopupMenuItem(child: Text("Update schedule"), value: "/upgrade-schedule"),
          //     PopupMenuItem(
          //         child: Text("New Group Chat"), value: "/new-group-chat"),
          //     PopupMenuItem(child: Text("Settings"), value: "/settings"),
          //   ],
          //   onSelected: (route) {
          //     print(route);
          //     // Note You must create respective pages for navigation
          //     Navigator.pushNamed(context, route);
          //   },
          // ),
        ],
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          // Column is also a layout widget. It takes a list of children and
          // arranges them vertically. By default, it sizes itself to fit its
          // children horizontally, and tries to be as tall as its parent.
          //
          // Invoke "debug painting" (press "p" in the console, choose the
          // "Toggle Debug Paint" action from the Flutter Inspector in Android
          // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
          // to see the wireframe for each widget.
          //
          // Column has various properties to control how it sizes itself and
          // how it positions its children. Here we use mainAxisAlignment to
          // center the children vertically; the main axis here is the vertical
          // axis because Columns are vertical (the cross axis would be
          // horizontal).
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            // Text(
            //   "Ngày BD KHHT: ${_firstDateCount}",
            //   // "${course["_tenMonHoc"]}
            //   style: GoogleFonts.encodeSans(
            //     fontSize: 20,
            //     color: Colors.lightGreen,
            //   ),
            // ),
            // Padding(
            //   padding: EdgeInsets.only(left: 0.0, top: 10.0, right: 0.0, bottom: 0.0),
            //   child: Text(
            //     "Ngày hiện tại: ${_currentDate}",
            //     // ${course["_ngayHoc"]}
            //     style: GoogleFonts.encodeSans(
            //       fontSize: 20,
            //       color: Color(0xff04BFBF),
            //     ),
            //   ),
            // ),
            Container(
                width: screenSize,
                margin: EdgeInsets.only(top: 7, bottom: 0, left: 10, right: 10),
                height: 100,
                decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.all(Radius.circular(20.0)),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.grey.withOpacity(0.5),
                        spreadRadius: 5,
                        blurRadius: 7,
                        offset: Offset(0, 3),
                      )
                    ]),
                child: Padding(
                    padding: const EdgeInsets.all(0.0),
                    child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          Padding(
                            padding: EdgeInsets.only(
                                left: 10.0, top: 0.0, right: 0.0, bottom: 0.0),
                            child: Text(
                              "Mã số: " + widget.userInputCode,
                              // ${course["_ngayHoc"]}
                              style: GoogleFonts.encodeSans(
                                fontSize: 18,
                                color: Color(0xff7B61F8),
                              ),
                            ),
                          ),
                          Padding(
                            padding: EdgeInsets.only(
                                left: 10.0, top: 5.0, right: 0.0, bottom: 0.0),
                            child: Text(
                              "Họ tên: ${_fullName}",
                              // ${course["_ngayHoc"]}
                              style: GoogleFonts.encodeSans(
                                fontSize: 18,
                                color: Color(0xff4C3F54),
                              ),
                            ),
                          ),
                          Padding(
                            padding: EdgeInsets.only(
                                left: 10.0, top: 5.0, right: 0.0, bottom: 0.0),
                            child: Text(
                              "Lớp: ${_class}",
                              // ${course["_ngayHoc"]}
                              style: GoogleFonts.encodeSans(
                                fontSize: 18,
                                color: Color(0xffED5752),
                              ),
                            ),
                          ),
                        ]))),
            Divider(),
            Container(
              color: Colors.white,
              child: Row(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    SizedBox(width: 25),
                    // IconButton(
                    //   splashColor: Colors.black38,
                    //   icon: Icon(Icons.arrow_back_ios_sharp),
                    //   onPressed: () => _reduceChosenStudyWeek(),
                    //
                    // ),
                    new Container(
                      child: new Material(
                        child: new InkWell(
                          customBorder: new CircleBorder(),
                          onTap: () => _reduceChosenStudyWeek(),
                          child: new Container(
                              child: Icon(
                            Icons.arrow_back_ios_sharp,
                            size: 22,
                            color: Colors.black54,
                          )),
                        ),
                        color: Colors.transparent,
                      ),
                    ),
                    SizedBox(width: 25),
                    Text(
                      "Tuần ${_currentChosenWeek} (${_currentChosenWSDate} - ${_currentChosenWEDate})",
                      // ${course["_ngayHoc"]}
                      style: GoogleFonts.encodeSans(
                        fontSize: 20,
                        color: Color(0xff917E60),
                      ),
                    ),
                    SizedBox(width: 25),
                    new Container(
                      child: new Material(
                        child: new InkWell(
                          customBorder: new CircleBorder(),
                          onTap: () => _increaseChosenStudyWeek(),
                          child: new Container(
                              child: Icon(
                            Icons.arrow_forward_ios_sharp,
                            size: 22,
                            color: Colors.black54,
                          )),
                        ),
                        color: Colors.transparent,
                      ),
                    ),
                    SizedBox(width: 25),
                  ]),
            ),
            TextFormField(
              decoration: InputDecoration(
                hintText: '${_finalStudyWeek}',
                hintStyle: TextStyle(color: Colors.white),
              ),
              keyboardType: TextInputType.multiline,
              maxLines: 22,
            )
          ],
        ),
      ),
      // bottomNavigationBar:
      // createBottomNavigation(isAdmin, isTicketer),
      // floatingActionButton: FloatingActionButton(
      //   onPressed: _testServiceAndCommand,
      //   tooltip: 'Increment',
      //   child: Icon(Icons.add),
      // ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

//   BottomNavigationBar createBottomNavigation(
//       [bool isAdmin = false, bool isTicketer = false]) {
//     final itemHeight = 22.0;
//     final textSize = 12.0;
//
//     return BottomNavigationBar(
//       // if admin logs out we can't remain on admin page
//       currentIndex: _adminAwareIndex(_currentIndex, isAdmin, isTicketer),
//       onTap: (index) {
//         analytics.setCurrentScreen(
//           screenName: '/home/${_tabs[index]}',
//           screenClassOverride: '${_tabs[index]}',
//         );
//         if (index == notifications) {
//           final notif =
//           RepositoryProvider.of<AppNotificationsUnreadStatusRepository>(
//               context);
//           notif.setLatestNotificationReadTime();
//         }
//         setState(() {
//           _currentIndex = index;
//         });
//       },
//       unselectedItemColor: Theme.of(context).brightness == Brightness.light
//           ? Theme.of(context).bottomAppBarTheme.color
//           : Colors.white,
//       type: BottomNavigationBarType.fixed,
//       selectedFontSize: textSize,
//       unselectedFontSize: textSize,
//       items: [
//         BottomNavigationBarItem(
//           icon: Container(
//             height: itemHeight,
//             child: Icon(LineIcons.calendar),
//           ),
//           title: BottomBarTitle(
//             title: 'Agenda',
//             showTitle: _currentIndex != agenda,
//           ),
//         ),
//         BottomNavigationBarItem(
//           icon: Icon(LineIcons.calendar_check_o),
//           title: BottomBarTitle(
//             title: 'My Schedule',
//             showTitle: _currentIndex != mySchedule,
//           ),
//         ),
//         BottomNavigationBarItem(
//           icon: NotificationIndicator(
//             child: Icon(LineIcons.bell),
//           ),
//           title: BottomBarTitle(
//             title: 'Notifications',
//             showTitle: _currentIndex != notifications,
//           ),
//         ),
//         BottomNavigationBarItem(
//           icon: Icon(LineIcons.cog),
//           title: BottomBarTitle(
//             title: 'Settings',
//             showTitle: _currentIndex != profile,
//           ),
//         ),
//         if (isTicketer == true)
//           BottomNavigationBarItem(
//             icon: Icon(LineIcons.ticket),
//             title: BottomBarTitle(
//               title: 'Bilety',
//               showTitle: _currentIndex != ticketer,
//             ),
//           ),
//         if (isAdmin == true)
//           BottomNavigationBarItem(
//             icon: Icon(LineIcons.shield),
//             title: BottomBarTitle(
//               title: 'Admin',
//               showTitle: _currentIndex != admin,
//             ),
//           ),
//       ],
//     );
//   }
//
//   _showSearch(BuildContext context) async {
//     try {
//       final res =
//       await Navigator.push<Talk>(context, _buildSearchPage(context));
//       if (res != null) {
//         analytics.logEvent(
//           name: 'search_completed',
//           parameters: {
//             'selected_talk_id': res.id,
//             'selected_talk': '$res',
//           },
//         );
//         if (res.type == TalkType.other) {
//           return;
//         } else {
//           await Navigator.push(
//             context,
//             MaterialPageRoute(
//               builder: (context) => TalkPage(res.id),
//               settings: RouteSettings(name: 'agenda/${res.id}'),
//             ),
//           );
//         }
//       }
//     } catch (e) {
//       logger.errorException(e);
//     }
//   }
//
//   _buildSearchPage(BuildContext context) {
//     return MaterialPageRoute<Talk>(
//       settings: RouteSettings(
//         name: 'search',
//         isInitialRoute: false,
//       ),
//       builder: (BuildContext context) => SearchResultsPage(),
//     );
//   }
//
//   _adminAwareIndex(int index, bool isAdmin, bool isTicketer) {
//     if (isAdmin) {
//       if (isTicketer) {
//         return index;
//       } else {
//         if (index == ticketer || index == admin) {
//           return admin - 1;
//         } else {
//           return index;
//         }
//       }
//     } else {
//       if (isTicketer) {
//         if (index == ticketer || index == admin) {
//           return ticketer;
//         } else {
//           return index;
//         }
//       } else {
//         if (index == ticketer || index == admin) {
//           return profile;
//         } else {
//           return index;
//         }
//       }
//     }
//   }
// }
//
// class NotificationIndicator extends StatelessWidget {
//   final Widget child;
//
//   const NotificationIndicator({Key key, this.child}) : super(key: key);
//   @override
//   Widget build(BuildContext context) {
//     final notif =
//     RepositoryProvider.of<AppNotificationsUnreadStatusRepository>(context);
//     return StreamBuilder<bool>(
//       stream: notif.hasUnreadNotifications(),
//       builder: (context, snapshot) {
//         return Stack(
//           children: <Widget>[
//             child,
//             if (snapshot.hasData && snapshot.data == true)
//               Positioned(
//                 top: 10,
//                 right: 5,
//                 child: Container(
//                   width: 7,
//                   height: 7,
//                   decoration: BoxDecoration(
//                     shape: BoxShape.circle,
//                     color: Colors.red,
//                   ),
//                 ),
//               )
//           ],
//         );
//       },
//     );
//   }

  void _showCheckedMenuSelections(String value) {
    switch (value) {
      case "update":
        break;
      case "info":
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => AboutUsScreen()),
        );
        break;
      case "setting":
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => SettingScreen()),
        );
        break;
      default:
        break;
    }
  }

  void _forwardConfirmUpdateServerScreen() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => ConfirmUpdateServerScreen()),
    );
  }

  void _checkInternetConnectivity() async {
    var connectivityResult = await (Connectivity().checkConnectivity());
    if (connectivityResult == ConnectivityResult.mobile) {
      // Currently connected to a mobile network.
    } else if (connectivityResult == ConnectivityResult.wifi) {
      // Currently connected to a wifi network.
    } else {
      NO_INTERNET_CONNECTION = true;
      _forwardNoICScreen();
    }
  }

  void _forwardNoICScreen() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => ConfirmNoICScreen()),
    );
  }

  void _forwardIntroSplashScreen() {
    Navigator.push(
      context,
      MaterialPageRoute(
          builder: (context) => SplashView(
              onInitializationComplete: null,
              nameOfUser: _fullName,
              initializationShouldFail: false)),
    );
  }

  void _reduceChosenStudyWeek() async {

      int curNumCW = int.parse(_currentChosenWeek) - 1;
      if (curNumCW > 0) {
        String curNumSDCW = _currentChosenWSDate,
            curNumEDCW = _currentChosenWEDate;

        _currentChosenWeek = curNumCW.toString();

        curNumSDCW = Jiffy(DateTime(
                int.parse(_currentStudyYear),
                int.parse(_currentChosenWSDate.substring(
                    _currentChosenWSDate.length - 2,
                    _currentChosenWSDate.length)),
                int.parse(_currentChosenWSDate.substring(0, 2))))
            .subtract(days: 7)
            .format("dd/MM");

        curNumEDCW = Jiffy(DateTime(
                int.parse(_currentStudyYear),
                int.parse(_currentChosenWEDate.substring(
                    _currentChosenWEDate.length - 2,
                    _currentChosenWEDate.length)),
                int.parse(_currentChosenWEDate.substring(0, 2))))
            .subtract(days: 7)
            .format("dd/MM");

        _currentChosenWSDate = curNumSDCW;
        _currentChosenWEDate = curNumEDCW;
      }

      // confirm

      ScaffoldMessenger.of(context).showSnackBar(SnackBar(
        content: Text(
          "Tuần cuối: ${finalWeek}",
        ),
      ));

      setState((){});
  }

  void _increaseChosenStudyWeek() async {

      int curNumCW = int.parse(_currentChosenWeek) + 1;

      if (curNumCW < finalWeek) {
        String curNumSDCW = _currentChosenWSDate,
            curNumEDCW = _currentChosenWEDate;

        _currentChosenWeek = curNumCW.toString();

        ScaffoldMessenger.of(context).showSnackBar(SnackBar(
          content: Text(
            "Tuần cuối: ${finalWeek}",
          ),
        ));

        curNumSDCW = Jiffy(DateTime(
                int.parse(_currentStudyYear),
                int.parse(_currentChosenWSDate.substring(
                    _currentChosenWSDate.length - 2,
                    _currentChosenWSDate.length)),
                int.parse(_currentChosenWSDate.substring(0, 2))))
            .add(days: 7)
            .format("dd/MM");

        curNumEDCW = Jiffy(DateTime(
                int.parse(_currentStudyYear),
                int.parse(_currentChosenWEDate.substring(
                    _currentChosenWEDate.length - 2,
                    _currentChosenWEDate.length)),
                int.parse(_currentChosenWEDate.substring(0, 2))))
            .add(days: 7)
            .format("dd/MM");

        _currentChosenWSDate = curNumSDCW;
        _currentChosenWEDate = curNumEDCW;
      }
      // confirm
      // ScaffoldMessenger.of(context).showSnackBar(SnackBar(
      //   content: Text(
      //     ""+curNumCW.toString()
      //   ),
      // ));
      setState((){});
  }
}

Widget _getTab(Tab tab) {
  switch (tab.text) {
    case 'Thời khóa biểu':
      return NormScheduleTab();
    case 'Lịch thi':
      return FinExScheduleTab();
  }
}

class NormScheduleTab extends StatefulWidget {
  NormScheduleTab({Key key}) : super(key: key);

  @override
  _NormScheduleTabState createState() => _NormScheduleTabState();
}

class _NormScheduleTabState extends State<NormScheduleTab> {
  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Text(
          "Thời khóa biểu CONTENT",
          style: GoogleFonts.bitter(
            fontSize: 20,
            color: Color(0xff000000), //E73F0B
          ),
        ),
        // Expanded(
        //     child: ListView.builder(
        //   itemCount: 100,
        //   itemBuilder: (context, index) => Text("ITEM $index FROM TAB 2"),
        // )),
      ],
    );
  }
}

class FinExScheduleTab extends StatefulWidget {
  FinExScheduleTab({Key key}) : super(key: key);

  @override
  _NormScheduleTabState createState() => _NormScheduleTabState();
}

class FinExScheduleTabState extends State<FinExScheduleTab> {
  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Text(
          "Lịch thi CONTENT",
          style: GoogleFonts.bitter(
            fontSize: 20,
            color: Color(0xff000000), //E73F0B
          ),
        ),
        // Expanded(
        //     child: ListView.builder(
        //       itemCount: 100,
        //       itemBuilder: (context, index) => Text("ITEM $index FROM TAB 2"),
        //     )),
      ],
    );
  }
}


//   @override
//   void initState() {
//     WidgetsBinding.instance.addPostFrameCallback((timeStamp) {
//       ScrollController controller = PrimaryScrollController.of(context);
//       controller.jumpTo(controller.positions.last.maxScrollExtent);
//     });
//   }
// }
//

// class KeepAlive extends StatefulWidget {
//   const KeepAlive({
//     @required Key key,
//     @required this.data,
//   }) : super(key: key);
//
//   final ListItem data;
//
//   @override
//   _KeepAliveState createState() => _KeepAliveState();
// }
//
// class _KeepAliveState extends State<KeepAlive>
//     with AutomaticKeepAliveClientMixin {
//   @override
//   bool get wantKeepAlive => true;
//
//   @override
//   Widget build(BuildContext context) {
//     super.build(context);
//     return Text("");
//   }
// }
