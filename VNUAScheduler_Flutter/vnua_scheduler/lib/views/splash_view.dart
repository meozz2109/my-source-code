import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../main.dart';

class SplashView extends StatefulWidget {
  final VoidCallback onInitializationComplete;
  final bool initializationShouldFail;
  final String nameOfUser;

  const SplashView({
    Key key,
    @required this.onInitializationComplete,
    this.initializationShouldFail = false, this.nameOfUser,
  }) : super(key: key);

  @override
  _SplashViewState createState() => _SplashViewState();
}

class _SplashViewState extends State<SplashView> {
  bool _hasError = false;

  @override
  void initState() {
    super.initState();
    if (widget.initializationShouldFail) {
      fakeLoadingAndError();
    } else {
      fakeLoadingAndSuccess();
    }
  }

  Future<void> fakeLoadingAndError() async {
    Future.delayed(
      Duration(milliseconds: 1000),
      () {
        setState(() {
          _hasError = true;
        });
      },
    );
  }

  Future<void> fakeLoadingAndSuccess() async {
    // >>> initialize async dependencies <<<
    // >>> register favorite dependency manager <<<
    // >>> reap benefits <<<
    Future.delayed(
      Duration(milliseconds: 1000),
      () => widget.onInitializationComplete(),
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Splash Screen',
      // theme: ThemeData(
      //   primarySwatch: Colors.deepPurple,
      // ),
      home: _buildBody(),
    );
  }

  Widget _buildBody() {
    if (_hasError) {
      return Center(
          child: ElevatedButton(
        onPressed: () => main(),
        child: Column(children: <Widget>[
          Expanded(
            flex: 1,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                CircleAvatar(
                  backgroundColor: Color(0xff018D44),
                  radius: 50.0,
                  child: Text(
                    'Xin chào, ${widget.nameOfUser}!',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 32,
                    ),
                  ),
                ),
              ],
            ),
          ),
        ]),
      ));
    }
    // return Center(
    //   child: CircularProgressIndicator(),
    // );
  }
}
