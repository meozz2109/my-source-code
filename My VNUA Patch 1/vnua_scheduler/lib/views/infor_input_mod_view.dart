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

class InforInputModifiedScreen extends StatefulWidget {
  @override
  _InforInputModifiedScreenState createState() =>
      _InforInputModifiedScreenState();

  // FocusNode _textFocus = new FocusNode();

  //requiring the list of todos
  InforInputModifiedScreen({Key key}) : super(key: key);

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
class _InforInputModifiedScreenState extends State<InforInputModifiedScreen> {
  final TextEditingController controller = TextEditingController(text: "");
  bool status_check_role = false;
  bool UPDATE_SERVER_OR_REGISTER_DATE = false, NO_INTERNET_CONNECTION = false;
  FocusNode _focusNode = new FocusNode();
  String result = "";

  String nameOfUser = '';

  @override
  void initState() {
    super.initState();
    _focusNode = FocusNode();
    // Start listening to change
    controller.addListener(_printLatestValue);
  }

  @override
  void dispose() {
    _focusNode.dispose();
    controller.dispose();
    super.dispose();
  }

  void _requestFocus() {
    setState(() {
      FocusScope.of(context).requestFocus(_focusNode);

    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        resizeToAvoidBottomInset: false,
        //passing in the ListView.builder
        body: SingleChildScrollView(
          physics: NeverScrollableScrollPhysics(),
          child: new GestureDetector(
              onTap: () {
                FocusScope.of(context).requestFocus(new FocusNode());
                FocusScope.of(context).unfocus();
              },
              child: Padding(
                  padding: const EdgeInsets.all(0),
                  child: Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: <Widget>[
                        SizedBox(height: 100),
                        Image.asset(
                          "lib/assets/images/ic_logo_vnua_st_one.png",
                          width: 100,
                          height: 100,
                          scale: 0.6,
                          fit: BoxFit.fitWidth,
                        ),
                        SizedBox(height: 50),
                        // Row(
                        //     mainAxisAlignment: MainAxisAlignment.start,
                        //     crossAxisAlignment: CrossAxisAlignment.center,
                        //     children: <Widget>[
                        Container(
                          margin: EdgeInsets.fromLTRB(15.0, 10.0, 15.0, 10.0),
                          child: new Theme(
                            data: new ThemeData(
                                primaryColor: Colors.green,
                                primaryColorDark: Color(0xff018D44)),
                            child: TextField(
                              onTap: _requestFocus,
                              focusNode: _focusNode,
                              keyboardType: TextInputType.text,
                              // onSaved: (newValue) =>  = newValue,
                              // onChanged: (newText) {
                              //   // controller.value = TextEditingValue(
                              //   //     text: txt);
                              //   controller.value = TextEditingValue(
                              //     text: newText,
                              //     selection: TextSelection.collapsed(offset: newText.length),
                              //   );
                              // },
                              // onSubmitted: (String str){
                              //   setState((){
                              //     result =  str;
                              //   });
                              //   controller.text = result;
                              // },
                              // validator: (value) {
                              //   // if (value.isEmpty) {
                              //   //   addError(error: kEmailNullError);
                              //   //   return "";
                              //   // } else if (!emailValidatorRegExp.hasMatch(value)) {
                              //   //   addError(error: kInvalidEmailError);
                              //   //   return "";
                              //   // }
                              //   return null;
                              // },
                              decoration: InputDecoration(
                                // icon: Icon(Icons.code),
                                labelText: "Mã số *",
                                hintText: "Nhập vào mã số",
                                // labelStyle: GoogleFonts.markaziText(
                                //     fontSize: 25, color: Colors.grey[600]),
                                hintStyle: GoogleFonts.signika(
                                    fontSize: 28, color: Color(0xff867666)),
                                labelStyle: GoogleFonts.rubik(
                                    fontSize: 22,
                                    color: _focusNode.hasFocus
                                        ? Color(0xff018D44)
                                        : Color(0xff867666)),
                                helperText:
                                '* Mã sinh viên, mã học viên Cao Học\n hoặc mã giảng viên.',
                                helperStyle:
                                GoogleFonts.markaziText(fontSize: 20),
                                // errorBorder: new OutlineInputBorder(
                                //   borderRadius: const BorderRadius.all(
                                //     const Radius.circular(10.0),
                                //   ),
                                //   // borderSide: new BorderSide(
                                //   //     color: Colors.red, width: 1.0),
                                // ),
                                // focusedErrorBorder: new OutlineInputBorder(
                                //   borderRadius: const BorderRadius.all(
                                //     const Radius.circular(15.0),
                                //   ),
                                //   borderSide: new BorderSide(
                                //       color: Colors.lightBlueAccent, width: 1.0),
                                // ),
                                border: new OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(28),
                                  borderSide:
                                      BorderSide(color: Color(0xff018D44)),
                                  gapPadding: 10,
                                ),
                                filled: true,
                                fillColor: Colors.white70,
                                focusedBorder: new OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(28),
                                  borderSide:
                                      BorderSide(color: Color(0xff018D44)),
                                  gapPadding: 10,
                                ),
                                // enabledBorder: new OutlineInputBorder(
                                //   borderRadius: BorderRadius.circular(28),
                                //   borderSide: BorderSide(color:  Color(0xff018D44)),
                                //   gapPadding: 10,
                                // ),
                                contentPadding: EdgeInsets.symmetric(
                                    horizontal: 42, vertical: 12),
                                // ),
                                // If  you are using latest version of flutter then lable text and hint text shown like this
                                // if you r using flutter less then 1.20.* then maybe this is not working properly
                                floatingLabelBehavior:
                                    FloatingLabelBehavior.always,
                                // suffixIcon: CustomSurffixIcon(svgIcon: "assets/icons/Mail.svg"),
                              ),
                              // onChanged is called whenever we add or delete something on Text Field
                              textAlign: TextAlign.left,
                              // inputFormatters: [QuantityInputFormatter()],
                              // <TextInputFormatter>[
                              //   FilteringTextInputFormatter.allow("{0-9A-Za-z}")
                              // ],
                              controller: controller,
                              obscureText: false,
                              style: GoogleFonts.encodeSans(
                                      fontSize: 20,
                                      color: Colors.deepPurpleAccent,
                                    ),
                            ),
                            // TextField(
                            //     enabled: true,
                            //     decoration: new InputDecoration(
                            //       // border: new OutlineInputBorder(
                            //       //     borderSide: new BorderSide(
                            //       //         color: Color(0xffA0A3A1), width: 1.0)),
                            //       focusedBorder: new OutlineInputBorder(
                            //         borderRadius: BorderRadius.circular(10.0),
                            //         borderSide: new BorderSide(
                            //             color: Color(0xff018D44), width: 1.0),
                            //       ),
                            //       filled: true,
                            //       fillColor: Color(0xFFF2F2F2),
                            //       disabledBorder: OutlineInputBorder(
                            //         borderRadius:
                            //             BorderRadius.all(Radius.circular(15)),
                            //         borderSide: BorderSide(
                            //             width: 1, color: Colors.orange),
                            //       ),
                            //       enabledBorder: OutlineInputBorder(
                            //         borderRadius:
                            //             BorderRadius.all(Radius.circular(15)),
                            //         borderSide: BorderSide(
                            //             width: 1, color: Colors.green),
                            //       ),
                            //       border: OutlineInputBorder(
                            //           borderRadius:
                            //               BorderRadius.all(Radius.circular(15)),
                            //           borderSide: BorderSide(
                            //             width: 1, color: Colors.grey[400],
                            //           )),
                            //       // errorBorder: OutlineInputBorder(
                            //       //     borderRadius:
                            //       //         BorderRadius.all(Radius.circular(4)),
                            //       //     borderSide: BorderSide(
                            //       //         width: 1, color: Colors.black)),
                            //       // focusedErrorBorder: OutlineInputBorder(
                            //       //     borderRadius:
                            //       //         BorderRadius.all(Radius.circular(15)),
                            //       //     borderSide: BorderSide(
                            //       //         width: 1,
                            //       //         color: Colors.yellowAccent)),
                            //       // errorText: "Demo error",
                            //       hintText: 'Mã số',
                            //       hintStyle: GoogleFonts.markaziText(
                            //           fontSize: 20, color: Colors.grey[600]),
                            //       helperText:
                            //           '* Mã sinh viên, mã học viên Cao Học\n hoặc mã giảng viên.',
                            //       helperStyle:
                            //           GoogleFonts.markaziText(fontSize: 20),
                            //       labelText: 'Mã số',
                            //       prefixIcon: const Icon(
                            //         Icons.code_sharp,
                            //         color: Colors.black,
                            //       ),
                            //       prefixText: ' ',
                            //       // suffixText: 'USD',
                            //       // suffixStyle:
                            //       //     const TextStyle(color: Colors.green)
                            //     ),
                            //     //onChanged is called whenever we add or delete something on Text Field
                            //     //   onChanged: (txt) {
                            //     //     controller.value = TextEditingValue(
                            //     //         text: txt,
                            //     //         selection: TextSelection.fromPosition(
                            //     //           TextPosition(offset: (txt ?? '').length),
                            //     //         ));
                            //     //     // setState(() {
                            //     //     //   FabVisible = true;
                            //     //     // });
                            //     //   },
                            //     //   keyboardType: TextInputType.text,
                            //     textAlign: TextAlign.left,
                            //     controller: controller,
                            //     style: GoogleFonts.encodeSans(
                            //       fontSize: 20,
                            //       color: Colors.deepPurpleAccent,
                            //     ),
                            //     obscureText: false),
                            // ),
                            // TextField(
                            //   decoration: new InputDecoration(
                            //     icon: Icon(Icons.code),
                            //     // labelText: " ",
                            //     hintText: "Mã số",
                            //     hintStyle: GoogleFonts.markaziText(
                            //         fontSize: 30, color: Colors.grey[600]),
                            //     // labelStyle: GoogleFonts.sourceSansPro(
                            //     //     fontSize: 25, color: Color(0xff867666)),
                            //     errorBorder: new OutlineInputBorder(
                            //       borderRadius: const BorderRadius.all(
                            //         const Radius.circular(10.0),
                            //       ),
                            //       // borderSide: new BorderSide(
                            //       //     color: Colors.red, width: 1.0),
                            //     ),
                            //     focusedErrorBorder: new OutlineInputBorder(
                            //       borderRadius: const BorderRadius.all(
                            //         const Radius.circular(15.0),
                            //       ),
                            //       borderSide: new BorderSide(
                            //           color: Colors.lightBlueAccent, width: 1.0),
                            //     ),
                            //     border: new OutlineInputBorder(
                            //       borderRadius: const BorderRadius.all(
                            //         const Radius.circular(15.0),
                            //       ),
                            //       borderSide: new BorderSide(
                            //           color: Color(0xffC9A66B), width: 1.0),
                            //     ),
                            //     filled: true,
                            //     fillColor: Colors.white70,
                            //     contentPadding: EdgeInsets.all(10),
                            //     focusedBorder: new OutlineInputBorder(
                            //       borderRadius: const BorderRadius.all(
                            //         const Radius.circular(15.0),
                            //       ),
                            //       borderSide: new BorderSide(
                            //           color: Color(0xff018D44), width: 1.0),
                            //     ),
                            //   ),
                            //   //onChanged is called whenever we add or delete something on Text Field
                            //   onChanged: (txt) {
                            //     controller.value = TextEditingValue(
                            //         text: txt,
                            //         selection: TextSelection.fromPosition(
                            //           TextPosition(offset: (txt ?? '').length),
                            //         ));
                            //     // setState(() {
                            //     //   FabVisible = true;
                            //     // });
                            //   },
                            //   keyboardType: TextInputType.text,
                            //   textAlign: TextAlign.left,
                            //   // inputFormatters: [QuantityInputFormatter()],
                            //   // <TextInputFormatter>[
                            //   //   FilteringTextInputFormatter.allow("{0-9A-Za-z}")
                            //   // ],
                            //   controller: controller,
                            //   // validator: (value) {
                            //   //   if (value.isEmpty) {
                            //   //     return 'CNP09';
                            //   //   }
                            //   // }, // Only numbers can be entered
                            // ),

                            // Clear button

                            // controller.text.length > 0
                            //     ? new IconButton(
                            //         icon: new Icon(Icons.clear),
                            //         onPressed: () {
                            //           controller.clear();
                            //         })
                            //     : new SizedBox(
                            //         height: 20,
                            //         width: 20,
                            //       ),
                          ),
                        ),
                        // ]),
                        SizedBox(height: 40),
                        Row(
                            mainAxisAlignment: MainAxisAlignment.start,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: <Widget>[
                              SizedBox(width: 20,),
                              IconButton(
                                icon: Icon(Icons.warning_sharp),
                                color: Color(0xffF8A055), //4C3F54, 7D5E3C
                                onPressed: () {},
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
                        SizedBox(
                          width: 250,
                          child: ElevatedButton.icon(
                            style: ButtonStyle(
                              backgroundColor: MaterialStateProperty.all<Color>(
                                  Color(0xffE2DFA2)),
                              foregroundColor: MaterialStateProperty.all<Color>(
                                  Color(0xff018D44)),
                            ),
                            label: Text(
                              'Xác nhận',
                              style: GoogleFonts.signika(
                                fontSize: 24,
                                color: Colors.black45,
                              ),
                            ),
                            icon: Icon(Icons.input_sharp),
                            onPressed: () {

                              // validate case NULL , case Special Characters including, case above 10 chars length, case
                              String res = _validateInputString(controller.text);

                              if(res == 'pass'){

                                // check current Internet connectivity
                                _checkInternetConnectivity();

                                // check update server or captcha register date
                                _checkUpdateServerOrCaptchaRegisterDate();

                                // Fluttertoast.showToast(msg: "Damn "+nameOfUser);

                                if (NO_INTERNET_CONNECTION == false &&
                                    UPDATE_SERVER_OR_REGISTER_DATE == false) {
                                  Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                          builder: (context) => MainScreen(
                                            title: "My VNUA",
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
                                          "My VNUA chào bạn!"),
                                    );
                                  },
                                );
                              } else {
                                // fail the validation
                              }


                            },
                          ),
                        ),
                      ]))),
        ),
      // bottomNavigationBar: BottomNavigationBar(
      //   items: [
      //     BottomNavigationBarItem(
      //         icon: Icon(FontAwesomeIcons.calendar),
      //         label: 'TKB',
      //         backgroundColor: Colors.white30
      //     ),
      //     BottomNavigationBarItem(
      //         icon: Icon(FontAwesomeIcons.solidCalendar),
      //         label: 'Lịch thi',
      //         backgroundColor: Colors.lightBlueAccent
      //     ),
      //     BottomNavigationBarItem(
      //         icon: Icon(FontAwesomeIcons.user),
      //         label: 'Cá nhân',
      //         backgroundColor: Colors.yellowAccent
      //     ),
      //     BottomNavigationBarItem(
      //         icon: Icon(FontAwesomeIcons.bell),
      //         label: 'Thông báo',
      //         backgroundColor: Colors.green
      //     ),
      //     BottomNavigationBarItem(
      //         icon: Icon(FontAwesomeIcons.coins),
      //         label: 'Cài đặt',
      //         backgroundColor: Colors.deepOrangeAccent
      //     ),
      //   ],
      // ),
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

    setState(() {

      // update nameOfUser and reload UI state
      // set nameOfUser
      nameOfUser = resultCheckServerUpdateOrRegister[2];

      Fluttertoast.showToast(msg: "Damn "+resultCheckServerUpdateOrRegister[2]);
    });

    if (resultCheckServerUpdateOrRegister[0] == "Exception") {
      UPDATE_SERVER_OR_REGISTER_DATE = true;
      _forwardConfirmUpdateServerScreen();
    }

  }

  String _validateInputString(String str) {
    String result = 'pass';
    if(str == null || str == ''){
      // notify to turn red the text field round border and set the warning message
      // MESSAGE CONTENT: 'Bạn chưa nhập vào mã số"
      result = 'fail';
    } else if(str.length>10){
      // notify to turn red the text field round border and set the warning message
      // MESSAGE CONTENT: 'Mã số bạn nhập vào chưa đúng. Xin mời nhập lại"
      result = 'fail';
    } else if(str.contains(RegExp(r"[!~@#$%^&*()_-+=;:]+")) == true){
      // notify to turn red the text field round border and set the warning message
      // MESSAGE CONTENT: 'Mã số bạn nhập vào chưa đúng. Xin mời nhập lại"
      result = 'fail';
    }
    return result;
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


