import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class ConfirmUpdateServerScreen extends StatelessWidget {
  ConfirmUpdateServerScreen({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final Size size = MediaQuery.of(context).size;
    return Scaffold(
      backgroundColor: Color(0xffDF9FA0),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
              "OOPS! ĐÃ CÓ LỖI XẢY RA.",
              style: GoogleFonts.rubik(
                fontSize: 28,
                color: Colors.black,
              ),
            ),
            SizedBox(height: 120),
            // Image(image: AssetImage("lib/assets/images/ic_cat_error.png")),
            Image.asset(
              "lib/assets/images/ic_cat_error.png",
              width: size.width,
              // scale: 0.6,
              fit: BoxFit.fitWidth,
            ),
            SizedBox(height: 80),
            Text("Vui lòng thử lại sau.",
                style: GoogleFonts.rubik(
                  fontSize: 22,
                  color: Color(0xff7B61F8),
                )),
          ],
        ),
      ),
    );
  }
}
