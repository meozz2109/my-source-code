import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:vnua_scheduler/commands/base_command.dart';
import 'package:vnua_scheduler/models/mon_hoc.dart';
import 'package:vnua_scheduler/models/mon_thi.dart';

class DataFetchCommand extends BaseCommand {

  Future<List<String>> fetchFDateCACSWeek(String userInputCode) async {
    List<String> firstDateCACSWeek = List.empty(growable: true);
    List<String> resultStringAll = await userService.handleNormalSchedule(userInputCode);

    // check Server update or Register Date -> Captcha code
    if(resultStringAll[0]=="TimeoutException"||resultStringAll[0]=="OtherException"){
      return ["Exception"];
    }

    firstDateCACSWeek.add(resultStringAll.elementAt(0));
    firstDateCACSWeek.add(resultStringAll.elementAt(1));

    return firstDateCACSWeek;
  }

  Future<List<String>> fetchUserInfo(String userInputCode) async {

    // call Service
    List<String> resultStringAll = await userService.handleNormalSchedule(userInputCode);

    // check Server update or Register Date -> Captcha code
    if(resultStringAll[0]=="TimeoutException"||resultStringAll[0]=="OtherException"){
      return ["Exception"];
    }

    List<String> resultUserInfoStringList = List.empty(growable: true);
    String fullNameOfUser = resultStringAll[2],
        classOfUser = resultStringAll[3],
        demoStringElement = resultStringAll[6];

    resultUserInfoStringList.add(fullNameOfUser);
    resultUserInfoStringList.add(classOfUser);
    resultUserInfoStringList.add(demoStringElement);

    // Fluttertoast.showToast(
    //     msg: 'Full name: ${fullNameOfUser}, \nClass: ${classOfUser}.',
    //     toastLength: Toast.LENGTH_SHORT,
    //     gravity: ToastGravity.CENTER,
    //     timeInSecForIosWeb: 1,
    //     backgroundColor: Colors.grey,
    //     textColor: Colors.black,
    //     fontSize: 16.0
    // );

    return resultUserInfoStringList;
  }

  Future<List<MonHoc>> fetchNorCourseList(String userInputCode) async {
    List<MonHoc> courseList = List.empty(growable: true);

    // call Service
    List<String> resultStringAll = await userService.handleNormalSchedule(userInputCode);

    // check Server update or Register Date -> Captcha code
    if(resultStringAll[0]=="TimeoutException"||resultStringAll[0]=="OtherException"){
      courseList.add(new MonHoc("", "Exception", "", "", "", "", "", "", "", ""));
      return courseList;
    }



    return courseList;
  }

  Future<List<MonThi>> fetchFinExCourseList(String userInputCode) async {
    List<MonThi> fecourseList = List.empty(growable: true);
    return fecourseList;
  }

}