import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class SettingScreen extends StatefulWidget {

  SettingScreen({Key key}):super(key: key);

  @override
  _SettingScreenState createState() => _SettingScreenState();

}

class _SettingScreenState extends State<SettingScreen> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              "SETTING VIEW"
              ,style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
    );
  }
}