import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:vnua_scheduler/commands/base_command.dart';
import 'package:vnua_scheduler/models/mon_hoc.dart';
import 'package:vnua_scheduler/models/mon_thi.dart';
import 'package:jiffy/jiffy.dart';

class DataFetchCommand extends BaseCommand {

  Future<List<String>> fetchFDateCACSWeek(List<String> r) async {
    List<String> firstDateCACSWeek = List.empty(growable: true);
    List<String> resultStringAll = r;

    // check Server update or Register Date -> Captcha code
    if(resultStringAll[0]=="TimeoutException"||resultStringAll[0]=="OtherException"){
      return ["Exception"];
    }

    firstDateCACSWeek.add(resultStringAll.elementAt(0));
    firstDateCACSWeek.add(resultStringAll.elementAt(1));

    print(firstDateCACSWeek.toString()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ********************************");
    return firstDateCACSWeek;
  }

  Future<List<String>> fetchUserInfo(List<String> r) async {

    // call Service
    List<String> resultStringAll = r;
    // check Server update or Register Date -> Captcha code
    if(resultStringAll[0]=="TimeoutException"||resultStringAll[0]=="OtherException"){
      return ["Exception"];
    }
    List<String> resultUserInfoStringList = List.empty(growable: true);
    String fullNameOfUser = resultStringAll.elementAt(2),
        classOfUser = resultStringAll.elementAt(3) != " " ? resultStringAll.elementAt(3) : " ";
    String demoStringElement =  resultStringAll.elementAt(5);
    resultUserInfoStringList.add(fullNameOfUser);
    resultUserInfoStringList.add(classOfUser);
    resultUserInfoStringList.add(demoStringElement);

        // store

    return resultUserInfoStringList;
  }

  Future<List<MonHoc>> fetchNorCourseList(List<String> r) async {
    List<MonHoc> courseList = List.empty(growable: true);

    // call Service
    List<String> resultStringAll = r,
        dayOfWeekStringList = List.empty(growable: true),
        shiftStringList = List.empty(growable: true),
        locationStringList = List.empty(growable: true),
        studyWeekStringList = List.empty(growable: true);

    // check Server update or Register Date -> Captcha code
    if(resultStringAll[0]=="TimeoutException"||resultStringAll[0]=="OtherException"){
      courseList.add(new MonHoc("", "Exception", "", "", "", "", "", "", "", ""));
      return courseList;
    }

    var firstDate = resultStringAll[0];

    dayOfWeekStringList = resultStringAll[7].split(" :-: ");
    shiftStringList = resultStringAll[8].split(" :-: ");
    locationStringList = resultStringAll[9].split(" :-: ");
    studyWeekStringList = resultStringAll[10].split(" :-: ");

    // Handle tuan_hoc object
    String weekCode = "", dateS = Jiffy(DateTime(int.parse(firstDate.substring(firstDate.length-4, firstDate.length)),
        int.parse(firstDate.substring(firstDate.length-7, firstDate.length-5)),
        int.parse(firstDate.substring(0, 2)))).format("dd/MM/yyyy"),
        dateE = "", engDateS = "", engDateE = "";
    try {
      dateE = Jiffy(DateTime(int.parse(firstDate.substring(firstDate.length-4, firstDate.length)),
          int.parse(firstDate.substring(firstDate.length-7, firstDate.length-5)),
          int.parse(firstDate.substring(0, 2)))).add(days: 7).format("dd/MM/yyyy");

    } catch(_){
      print(_);
    }

    return courseList;
  }

  Future<List<MonThi>> fetchFinExCourseList(String userInputCode) async {
    List<MonThi> fecourseList = List.empty(growable: true);
    return fecourseList;
  }

  Future<List<String>> commonFetchInfor(String userInputCode) async {
    List<String> resultStringAll = await userService.handleNormalSchedule(userInputCode);
    return resultStringAll;
  }

}