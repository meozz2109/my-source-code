import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class ConfirmNoICScreen extends StatelessWidget {
  ConfirmNoICScreen({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final Size size = MediaQuery.of(context).size;
    return WillPopScope(
        child: Scaffold(
          backgroundColor: Colors.white,
          body: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                SizedBox(height: 100),
                Image.asset(
                  "lib/assets/images/ic_no_in_con.png",
                  width: size.width,
                  // scale: 0.6,
                  fit: BoxFit.fitWidth,
                ),
                SizedBox(height: 40),
                Text("Không có kết nối mạng",
                    style: GoogleFonts.bitter(
                      fontSize: 20,
                      color: Colors.black,
                    )),
                SizedBox(height: 40),
                Text("Vui lòng kiểm tra lại kết nối mạng",
                    style: GoogleFonts.bitter(
                      fontSize: 18,
                      color: Color(0xff7DA3A1),
                    )),
                SizedBox(height: 40),
                TextButton(
                    onPressed: () {},
                    style: TextButton.styleFrom(
                      primary: Color(0xff018D44),
                    ),
                    child: Text(
                      "NHẤN ĐỂ THỬ LẠI",
                      style: GoogleFonts.signika(
                        fontSize: 24,
                        // color: Color(0xff018D44),
                      ),
                    ))
              ],
            ),
          ),
        ),
        onWillPop: () async => false);
  }
}
