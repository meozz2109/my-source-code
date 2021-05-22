import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AboutUsScreen extends StatelessWidget {

  AboutUsScreen({Key key}):super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              "ABOUT US VIEW"
              ,style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
    );
  }

}




