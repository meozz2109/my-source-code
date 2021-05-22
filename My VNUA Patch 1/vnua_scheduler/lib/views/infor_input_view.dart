import 'package:connectivity/connectivity.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:vnua_scheduler/assets/CustomSwitch.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:vnua_scheduler/commands/data_fetch_command.dart';

import 'package:vnua_scheduler/models/mon_hoc.dart';
import 'package:vnua_scheduler/models/user_model.dart';
import 'package:vnua_scheduler/views/home_view.dart';
import 'package:vnua_scheduler/assets/constants.dart' as constants;
import 'package:vnua_scheduler/assets/dummy_data.dart' as dummydata;
import 'package:vnua_scheduler/views/update_server_error_view.dart';

import 'no_connect_error_view.dart';

class InforInputScreen extends StatefulWidget {

  @override
  _InforInputScreenState createState() => _InforInputScreenState();

  // FocusNode _textFocus = new FocusNode();

  //requiring the list of todos
  InforInputScreen({Key key}) : super(key: key);

  // you can have different listner functions if you wish
  // _controller.addListener(onChange);
  // _textFocus.addListener(onChange);

  @override
  Widget build(BuildContext context) {
    // ListView.builder(
    //   itemCount: dummydata.DUMMY_DATA.length,
    //   itemBuilder: (context, index) {
    //     return ListTile(
    //       title: Text("OK "),
    // title: Column(
    //   children: dummydata.DUMMY_DATA.map((course) {
    //       return TextButton(
    //           onPressed: _goToSingle(context, course["_maMonHoc"]),
    //           child: Text(course["_tenMonHoc"]));
    //     }).toList(),
    // ),
    // onTap: () {
    //   Navigator.push(
    //     context,
    //     MaterialPageRoute(
    //       builder: (context) => MainScreen(title: "VNUA Scheduler"),
    //     ),
    //   );
    // },
    // );
    // },
    // ),
    // );
  }
}

_goToSingle(BuildContext context, String courseID) {
  Navigator.of(context).pushNamed("single-course", arguments: courseID);
}

class QuantityInputFormatter extends TextInputFormatter {
  @override
  TextEditingValue formatEditUpdate(
      TextEditingValue oldValue, TextEditingValue newValue) {
    final str = (newValue.text) ?? '';
    return TextEditingValue(
      text: str,
      selection: TextSelection.collapsed(offset: (str ?? '').length),
    );
  }
}

// class CustomSwitch extends StatefulWidget {
//   final bool value;
//   final ValueChanged<bool> onChanged;
//
//   CustomSwitch({Key key, this.value, this.onChanged}) : super(key: key);
//
//   @override
//   _CustomSwitchState createState() => _CustomSwitchState();
// }
//
// class _CustomSwitchState extends State<CustomSwitch>
//     with SingleTickerProviderStateMixin {
//   Animation _circleAnimation;
//   AnimationController _animationController;
//
//   @override
//   void initState() {
//     super.initState();
//     _animationController =
//         AnimationController(vsync: this, duration: Duration(milliseconds: 60));
//     _circleAnimation = AlignmentTween(
//             begin: widget.value ? Alignment.centerRight : Alignment.centerLeft,
//             end: widget.value ? Alignment.centerLeft : Alignment.centerRight)
//         .animate(CurvedAnimation(
//             parent: _animationController, curve: Curves.linear));
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     return AnimatedBuilder(
//       animation: _animationController,
//       builder: (context, child) {
//         return GestureDetector(
//           onTap: () {
//             if (_animationController.isCompleted) {
//               _animationController.reverse();
//             } else {
//               _animationController.forward();
//             }
//             widget.value == false
//                 ? widget.onChanged(true)
//                 : widget.onChanged(false);
//           },
//           child: Container(
//             width: 45.0,
//             height: 28.0,
//             decoration: BoxDecoration(
//               borderRadius: BorderRadius.circular(24.0),
//               color: _circleAnimation.value == Alignment.centerLeft
//                   ? Colors.grey
//                   : Colors.blue,
//             ),
//             child: Padding(
//               padding: const EdgeInsets.only(
//                   top: 2.0, bottom: 2.0, right: 2.0, left: 2.0),
//               child: Container(
//                 alignment:
//                     widget.value ? Alignment.centerRight : Alignment.centerLeft,
//                 child: Container(
//                   width: 20.0,
//                   height: 20.0,
//                   decoration: BoxDecoration(
//                       shape: BoxShape.circle, color: Colors.white),
//                 ),
//               ),
//             ),
//           ),
//         );
//       },
//     );
//   }
// }
class _InforInputScreenState extends State<InforInputScreen> {
  final TextEditingController controller = TextEditingController();
  bool status_check_role = false;
  bool UPDATE_SERVER_OR_REGISTER_DATE = false, NO_INTERNET_CONNECTION = false;

  @override
  void initState() {
    super.initState();

    // Start listening to change
    controller.addListener(_printLatestValue);
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    controller.text = '';
    return Scaffold(
      appBar: AppBar(
        leading: Builder(
          builder: (BuildContext context) {
            return IconButton(
              icon: const Icon(Icons.contact_mail_outlined),
              color: Color(0xff7D5E3C), //4C3F54, 7D5E3C
              onPressed: () {
                // Scaffold.of(context).openDrawer();
              },
              // tooltip: MaterialLocalizations.of(context).openAppDrawerTooltip,
            );
          },
        ),
        title: Text(
          'Xác thực thông tin',
          style: GoogleFonts.cabin(
            fontSize: 22,
            color: Color(0xff7D5E3C), //7D5E3C
          ),
        ),
      ),
      //passing in the ListView.builder
      body: new GestureDetector(
          onTap: () {
            FocusScope.of(context).requestFocus(new FocusNode());
            FocusScope.of(context).unfocus();
          },
          child: Padding(
              padding: const EdgeInsets.all(30),
              child: Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    Text(
                      "Hãy nhập vào mã sinh viên, mã học viên cao học hoặc mã giảng viên của bạn:",
                      style: GoogleFonts.robotoSlab(
                        fontSize: 25,
                        color: Color(0xff35212A),
                      ),
                    ),
                    SizedBox(height: 20),
                    Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget>[
                          TextFormField(
                            decoration: new InputDecoration(
                              icon: Icon(Icons.code),
                              labelText: "Mã số",
                              hintText: "Ví dụ: 651111",
                              hintStyle: GoogleFonts.markaziText(
                                  fontSize: 30, color: Colors.blueGrey),
                              labelStyle: GoogleFonts.sourceSansPro(
                                  fontSize: 30, color: Color(0xff867666)),
                              errorBorder: new OutlineInputBorder(
                                borderSide: new BorderSide(
                                    color: Colors.red, width: 0.0),
                              ),
                              focusedErrorBorder: new OutlineInputBorder(
                                borderSide: new BorderSide(
                                    color: Colors.lightBlueAccent, width: 0.0),
                              ),
                              border: new OutlineInputBorder(
                                borderSide: new BorderSide(
                                    color: Colors.black26, width: 0.0),
                              ),
                              focusedBorder: new OutlineInputBorder(
                                borderSide: new BorderSide(
                                    color: Colors.amber, width: 0.0),
                              ),
                            ),
                            style: GoogleFonts.encodeSans(
                              fontSize: 24,
                              color: Colors.deepPurpleAccent,
                            ),
                            //onChanged is called whenever we add or delete something on Text Field
                            onChanged: (txt) {
                              controller.value = TextEditingValue(
                                  text: txt,
                                  selection: TextSelection.fromPosition(
                                    TextPosition(offset: (txt ?? '').length),
                                  ));
                              // setState(() {
                              //   FabVisible = true;
                              // });
                            },
                            keyboardType: TextInputType.text,
                            textAlign: TextAlign.left,
                            // inputFormatters: [QuantityInputFormatter()],
                            // <TextInputFormatter>[
                            //   FilteringTextInputFormatter.allow("{0-9A-Za-z}")
                            // ],
                            controller: controller,
                            validator: (value) {
                              if (value.isEmpty) {
                                return 'CNP09';
                              }
                            }, // Only numbers can be entered
                          ),
                          controller.text.length > 0
                              ? new IconButton(
                                  icon: new Icon(Icons.clear),
                                  onPressed: () {
                                    controller.clear();
                                  })
                              : new SizedBox(
                                  height: 20,
                                  width: 20,
                                ),
                        ]),
                    SizedBox(height: 20),
                    Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget>[
                          IconButton(
                            icon: Icon(Icons.warning_sharp),
                            color: Color(0xffF8A055), //4C3F54, 7D5E3C
                            onPressed: () {
                            },
                            // tooltip: MaterialLocalizations.of(context).openAppDrawerTooltip,
                          ),
                          Text(
                            "Dành cho SV & HV Cao Học", //
                            style: GoogleFonts.cabin(
                              fontSize: 20,
                              color: Color(0xff000000),
                            ),
                          ),
                        ]),
                    SizedBox(height: 5),
                    Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget>[
                          Text(
                            'CĐ / ĐH\n(Cao Đẳng\n/ Đại Học)',
                            style: GoogleFonts.scada(
                              //libreFranklin
                              fontSize: 25,
                              color: Colors.deepOrangeAccent,
                            ),
                          ),
                          SizedBox(width: 12),
                          CustomSwitch(
                            activeColor: Color(0xff5677F9),
                            value: status_check_role,
                            onChanged: (value) {
                              status_check_role = value;
                            },
                          ),
                          SizedBox(width: 12),
                          Text(
                            'SĐH (Sau\nĐại Học)',
                            style: GoogleFonts.scada(
                              fontSize: 25,
                              color: Colors.pinkAccent,
                            ),
                          ),
                        ]),
                    SizedBox(height: 25),
                    ElevatedButton.icon(
                      style: ButtonStyle(
                        backgroundColor:
                            MaterialStateProperty.all<Color>(Color(0xffE2DFA2)),
                        foregroundColor:
                            MaterialStateProperty.all<Color>(Color(0xff92AAC7)),
                      ),
                      label: Text(
                        'Xác nhận',
                        style: GoogleFonts.signika(
                          fontSize: 25,
                          color: Colors.black45,
                        ),
                      ),
                      icon: Icon(Icons.input_sharp),
                      onPressed: () {

                        // check current Internet connectivity
                        _checkInternetConnectivity();

                        // check update server or captcha register date
                        _checkUpdateServerOrCaptchaRegisterDate();

                        if(NO_INTERNET_CONNECTION == false && UPDATE_SERVER_OR_REGISTER_DATE == false) {
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) =>
                                      MainScreen(
                                        title: "VNUA Scheduler",
                                        userInputCode: controller.text,
                                      )));
                        }
                        return showDialog(
                          context: context,
                          builder: (context) {
                            return AlertDialog(
                              // Retrieve the text the that user has entered by using the
                              // TextEditingController.
                              content: Text(
                                  "Xin chào, "+controller.text+"!"),
                            );
                          },
                        );
                      },
                    ),
                  ]))),
    );
  }

  void onChange() {
    String text = controller.text;
    // bool hasFocus = _textFocus.hasFocus;
    //do your text transforming
    // _controller.text = newText;
    // _controller.selection = new TextSelection(
    //     baseOffset: newText.length,
    //     extentOffset: newText.length
    // );
  }

  _printLatestValue() {
    print("Second text field: ${controller.text}");
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

  void _checkUpdateServerOrCaptchaRegisterDate() async {
    List<String> resultCheckServerUpdateOrRegister =
    await DataFetchCommand().commonFetchInfor(controller.text);
    if (resultCheckServerUpdateOrRegister[0] == "Exception") {
      UPDATE_SERVER_OR_REGISTER_DATE = true;
      _forwardConfirmUpdateServerScreen();
    }
  }

  void _forwardNoICScreen() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => ConfirmNoICScreen()),
    );
  }

  void _forwardConfirmUpdateServerScreen() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => ConfirmUpdateServerScreen()),
    );
  }


}
