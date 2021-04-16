import 'dart:async';
import 'dart:io';
import 'dart:math';
import 'package:flutter/material.dart';
import 'package:html/parser.dart' show parse;
import 'package:html/dom.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:http/http.dart' as http;
import 'package:jiffy/jiffy.dart';
import 'package:logger/logger.dart';
import 'package:vnua_scheduler/assets/constants.dart' as constants;
import 'package:get_it/get_it.dart';
import 'package:vnua_scheduler/models/mon_hoc.dart';
import 'package:vnua_scheduler/models/user_model.dart';

class UserService {
  // final Firestore _db = Firestore.instance;
  // final String _collectionName = 'users';
  // CollectionReference _ref;

  // UserModel _cachedUser; //<----- Cached Here
  //
  // UserService() {
  //   this._ref = _db.collection(_collectionName);
  // }
  //
  // UserModel getCachedUser() {
  //   return _cachedUser;
  // }
  //
  // Future<UserModel> getUser(String id) async {
  //   DocumentSnapshot doc = await _ref.document(id).get();
  //
  //   if (!doc.exists) {
  //     log("UserService.getUser(): Empty companyID ($id)");
  //     return null;
  //   }
  //
  //   _cachedUser = UserModel.fromDocument(doc.data, doc.documentID);
  //   return _cachedUser;
  // }

  // Future<bool> login(String user, String pass) async {
  //   // Fake a network service call, and return true
  //   await Future.delayed(Duration(seconds: 1));
  //   return true;
  // }
  //
  // Future<List<String>> getPosts(String user) async {
  //   // Fake a service call, and return some posts
  //   await Future.delayed(Duration(seconds: 1));
  //   return List.generate(50, (index) => "Item ${Random().nextInt(999)}}");
  // }

  Future<List<String>> handleNormalSchedule(String inputCode) async {
    List<String> resStrList = List.empty(growable: true),
          listTextCourseFilter = List.empty(growable: true),
        listTextCourseFilterOne = List.empty(growable: true),
        listCourseCode = List.empty(growable: true),
        listCourseName = List.empty(growable: true),
        listDayOfWeek  = List.empty(growable: true),
        listShift  = List.empty(growable: true),
        listLocation  = List.empty(growable: true),
        listWeek  = List.empty(growable: true);
    List<MonHoc>
        listCourseEachDay = List.empty(growable: true),
        listCourseCurrentWeek  = List.empty(growable: true),
        listCourseTheWeekAT  = List.empty(growable: true),
        listCourseTheWeekBY  = List.empty(growable: true),
        listCourseTomo  = List.empty(growable: true),
        listCourseYest  = List.empty(growable: true),
        listCourseMondayCW  = List.empty(growable: true),
        listCourseTuesdayCW  = List.empty(growable: true),
        listCourseWednesdayCW  = List.empty(growable: true),
        listCourseThursdayCW  = List.empty(growable: true),
        listCourseFridayCW  = List.empty(growable: true),
        listCourseSaturdayCW  = List.empty(growable: true),
        listCourseSundayCW  = List.empty(growable: true);
    var urlNorSche = Uri.parse(""+constants.NOR_SCHE_URL+inputCode);

    // Reg - exp
    final patternNumber = new RegExp("[0-9]+"),
        patternUnicodeChar = new RegExp("\\p{L}+"),
        patternText = new RegExp("((?=.''d*)(?=.*[A-Z])(?=[_]*).{1,20})"),
        patternOneDigit = new RegExp("[\\d]{1}"),
        patternDSSV = new RegExp("[^a-zA-Z]+"),
        patternDV = new RegExp("[^DV]"),
        patternCBGD = new RegExp("\\b[a-zA-Z]{2,3}\\d{2,3}\\b");

    // check Update Server or Register Date -> Captcha Code
    try {
      var response = await http.get(urlNorSche).timeout(
          const Duration(seconds: 2));
    } on TimeoutException catch (_) {
      // A timeout occurred.
      List<String> exceptionStrList = List.empty(growable: true);
      exceptionStrList.add("TimeoutException");
      return exceptionStrList;
    } on SocketException catch (_) {
      // Other exception
      List<String> exceptionStrList = List.empty(growable: true);
      exceptionStrList.add("OtherException");
      return exceptionStrList;
    }
    var response = await http.get(urlNorSche).timeout(
        const Duration(seconds: 2));
    var logger = Logger();
    String htmlToParse = "",
          firstDateCount = "",
          currentUpdateDate = "",
          currentStudyWeek = "",
          currentStudyYear = "",
          startDateCSW = "",
          endDateCSW = "",
          firstStringParseElement = "",
          textRawHandled = "";

    // Fluttertoast.showToast(
    //     msg: ' success or not: ${response.statusCode.toString()}',
    //     toastLength: Toast.LENGTH_SHORT,
    //     gravity: ToastGravity.CENTER,
    //     timeInSecForIosWeb: 1,
    //     backgroundColor: Colors.grey,
    //     textColor: Colors.black,
    //     fontSize: 16.0
    // );

    //If the http request is successful the statusCode will be 200
    if(response.statusCode == 200){
      var document = parse(response.body);
      var elementsDate = document.getElementsByTagName("span");
      firstDateCount = elementsDate[12].text.substring(elementsDate[12].text.length-11, elementsDate[12].text.length-1);
      currentUpdateDate = elementsDate[14].text;

      var elementsLabel = document.querySelectorAll("table.body-table");
      var elementUserFullName = document.querySelector("span#"+constants.INFO_SPAN_ID+"TenSV"+".Label"),
          elementUserClass = document.querySelector("span#"+constants.INFO_SPAN_ID+"LopSV"+".Label");

      String userFullName = ""+elementUserFullName.text.substring(0, elementUserFullName.text.indexOf("-")).trim(),
             userClass = ""+elementUserClass.text.substring(0, elementUserClass.text.indexOf("-")+1)
                 +elementUserClass.text.substring(elementUserClass.text.lastIndexOf(":")+1, elementUserClass.text.length);

      // var firstChild = document.firstChild,
      //     parent = document.parent,
      //     children = document.children,
      //     fourthChild = document.children[4];

      // 1. Add First Date to count
      resStrList.add(firstDateCount);

      // Handle calculating current study week along with its start & end date
      try {

        // Declare local variable
        DateTime dt = DateTime.now();
        int currentDateOfYear = Jiffy(DateTime(dt.year, dt.month, dt.day)).dayOfYear,
            kickOffDateOfYear = Jiffy(DateTime(int.parse(firstDateCount.substring(firstDateCount.length-4, firstDateCount.length)),
                int.parse(firstDateCount.substring(firstDateCount.length-7, firstDateCount.length-5)),
                int.parse(firstDateCount.substring(0, 2)))).dayOfYear,
            currentWeekOfYear = 0,
            currentWeekOfMonth = 0,
            minusDateOfYear = 0;

            currentStudyYear = firstDateCount.substring(firstDateCount.length-4, firstDateCount.length);

        // Fluttertoast.showToast(
        //     msg: 'Demo '+currentDateOfYear.toString()+", "+kickOffDateOfYear.toString(),
        //     toastLength: Toast.LENGTH_SHORT,
        //     gravity: ToastGravity.CENTER,
        //     timeInSecForIosWeb: 1,
        //     backgroundColor: Colors.grey,
        //     textColor: Colors.black,
        //     fontSize: 16.0
        // );

        // Case 1: Current study year equal year which start study semester
        if(DateTime.now().year == int.parse(firstDateCount.substring(firstDateCount.length-4, firstDateCount.length))){
          // Fluttertoast.showToast(
          //     msg: 'Demo 1',
          //     toastLength: Toast.LENGTH_SHORT,
          //     gravity: ToastGravity.CENTER,
          //     timeInSecForIosWeb: 1,
          //     backgroundColor: Colors.grey,
          //     textColor: Colors.black,
          //     fontSize: 16.0
          // );
          minusDateOfYear = currentDateOfYear - kickOffDateOfYear + 1;
          currentWeekOfYear =  ((minusDateOfYear ~/ 7) + 1);
          currentWeekOfMonth = currentWeekOfYear%4;

          // Fluttertoast.showToast(
          //     msg: 'Demo '+minusDateOfYear.toString()+", "+currentWeekOfYear.toString(),
          //     toastLength: Toast.LENGTH_SHORT,
          //     gravity: ToastGravity.CENTER,
          //     timeInSecForIosWeb: 1,
          //     backgroundColor: Colors.grey,
          //     textColor: Colors.black,
          //     fontSize: 16.0
          // );

        } else {
          // Case 2: The others

          Fluttertoast.showToast(
              msg: 'Demo 2',
              toastLength: Toast.LENGTH_SHORT,
              gravity: ToastGravity.CENTER,
              timeInSecForIosWeb: 1,
              backgroundColor: Colors.grey,
              textColor: Colors.black,
              fontSize: 16.0
          );

          // Check leap year
            bool isLeap = false;
            int allDays = 365;

            if(dt.year % 4 == 0){
              if(dt.year % 100 == 0){
                if(dt.year%400==0){
                  isLeap = true;
                } else {
                  isLeap = false;
                }
              } else {
                isLeap = true;
              }
            } else {
              isLeap = false;
            }

            if(isLeap){
              allDays++;
            }

            int dayLastYear = allDays - kickOffDateOfYear + 1 + currentDateOfYear,
                currentWeekOfYear = ((dayLastYear ~/ 7) + 1),
                currentWeekOfMonth = currentWeekOfYear%4;
        }

        // Fluttertoast.showToast(
        //     msg: 'Damnn ',
        //     toastLength: Toast.LENGTH_SHORT,
        //     gravity: ToastGravity.CENTER,
        //     timeInSecForIosWeb: 1,
        //     backgroundColor: Colors.grey,
        //     textColor: Colors.black,
        //     fontSize: 16.0
        // );

        // set current study week
        currentStudyWeek = currentWeekOfYear.toString();

        // set current start date of CSW
        startDateCSW = Jiffy(DateTime(int.parse(firstDateCount.substring(firstDateCount.length-4, firstDateCount.length)),
            int.parse(firstDateCount.substring(firstDateCount.length-7, firstDateCount.length-5)),
            int.parse(firstDateCount.substring(0, 2)))).add(days: (currentWeekOfYear-1)*7).format("dd/MM");

        // set current end date of CSW
        endDateCSW = Jiffy(DateTime(int.parse(firstDateCount.substring(firstDateCount.length-4, firstDateCount.length)),
            int.parse(firstDateCount.substring(firstDateCount.length-7, firstDateCount.length-5)),
            int.parse(firstDateCount.substring(0, 2)))).add(days: currentWeekOfYear*7 -1).format("dd/MM");

      } catch(_){
        print(_);
      }

      // 2. Add Current Study Week along with its start & end date
      resStrList.add(currentStudyWeek+", "+startDateCSW+"-"+endDateCSW+", "+currentStudyYear);

      // 3. Add User Full Name
      resStrList.add(userFullName);

      // 4. Add User Class
      resStrList.add(userClass);

      String res = "";
      // Handle to store Course List elements
      for(var i=0;i<elementsLabel.length;i++){
        var st = elementsLabel.elementAt(i).text.trim();
        if(st.length>2 && st.length<=10){
          // do nothing
        } else if(st.length>50) {
          firstStringParseElement = st;
          textRawHandled = st.replaceAll(" ", "*");

          // add to course code list
          listCourseCode.add(textRawHandled.substring(0, 8));

          // add to string filter list
          listTextCourseFilter.add(textRawHandled.substring(8, textRawHandled.length));

        }
      }

      // Find the quantity of "DSSV" words
      int maxAppearanceQuantity = 0, checkC = 0;
      String subStringCheck = "DSSV";
      for(var i = 0;i<listTextCourseFilter.length;i++){
        String st = listTextCourseFilter.elementAt(i);
        while ((checkC = st.indexOf(subStringCheck, checkC))!= -1){
          maxAppearanceQuantity++;
          checkC += subStringCheck.length;
        }
      }

      // Handle "DSSV" clone into double, triple, or etc.
      int countChar = 0;
      bool flagCheck = false;
      String stringClone = "", theLastFourChar = "";
      for(var i = 0;i<listTextCourseFilter.length;i++){
        stringClone = listTextCourseFilter.elementAt(i);
        for(var e = 0;e<maxAppearanceQuantity;e++){
          theLastFourChar = stringClone.substring(stringClone.length-4, stringClone.length);

          if(theLastFourChar == "DSSV"){
            String sb = stringClone;
            stringClone = sb.substring(0, sb.length-6);
          } else {
            // check duplicate
            if(listTextCourseFilter.length==0 && countChar < maxAppearanceQuantity){
              listTextCourseFilterOne.add(stringClone);
              flagCheck = false;
              countChar++;
            } else {
              for(var f=0;f<listTextCourseFilterOne.length;f++){
                if(stringClone == listTextCourseFilterOne.elementAt(f)){
                  // do nothing
                } else {
                  flagCheck = true;
                }
              }
              if(flagCheck == true && countChar < maxAppearanceQuantity && countChar == i){
                listTextCourseFilterOne.add(stringClone);
                flagCheck = false;
                countChar++;
              }
            }
          }
        }
      }

      // remove all CBGD from listTextCourseFilterOne, merge into listTextCourseFilterTwo
      List<String> listTextCourseFilterTwo = List.empty(growable: true);
      for(var q = 0;q<listTextCourseFilterOne.length;q++){
        List<String> arrStringMain = listTextCourseFilterOne.elementAt(q).split("\\*");
        int storedInd = 0;
        String storedStr = "", lastStr = "";

        // remove all CBGD
        for(var w = arrStringMain.length-1;w>=0;w--){
          if(patternCBGD.hasMatch(arrStringMain[w])){
            storedStr = arrStringMain[w];
            storedInd = w;
            break;
          }
        }

        // merge into listTextCourseFilterTwo
        for(var r = 0;r<arrStringMain.length;r++){
          if(arrStringMain[r] != storedStr){
            lastStr += arrStringMain[r]+"*";
          }
        }

        listTextCourseFilterTwo.add(lastStr);
      }

      // List string of all courses
      try {
        for(var t = 0;t<listTextCourseFilterTwo.length;t++){
          if(listTextCourseFilterTwo.elementAt(t).contains("*0*")){
            continue;
          }
          List<String> arrStringMain = listTextCourseFilterTwo.elementAt(t).split("\\*");
          int index = 0;

          // handle Course Name
          String courseName = "";
          for(var y = 0;y<arrStringMain.length-1;y++){
            if(patternNumber.hasMatch(arrStringMain[y]) && !patternOneDigit.hasMatch(arrStringMain[y])){
              listCourseName.add(courseName.substring(0, courseName.length-1));
              courseName = "";
              index = y;
              break;
            }
            courseName += arrStringMain[y];
          }

          // handle dayOfWeek
          String daysOfWeek = "";
          bool findCheck = false;
          for(var u = index+4;u<arrStringMain.length-1;u++){
            if(patternUnicodeChar.hasMatch(arrStringMain[u])
              && daysOfWeek == ""){
              daysOfWeek += arrStringMain[u];
              index = u+1;
            } else {
              if(patternUnicodeChar.hasMatch(arrStringMain[u])
                  && daysOfWeek != ""){
                daysOfWeek += ", "+arrStringMain[u];
                findCheck = true;
              }

              if(findCheck && patternNumber.hasMatch(arrStringMain[u])){
                index = u;
                break;
              }
            }
          }

          if(daysOfWeek != ""){
            listDayOfWeek.add(daysOfWeek);
          }

          // handle shifts
          String shifts = "";
          for(var o = index; o < arrStringMain.length-1;o++){
            if(patternNumber.hasMatch(arrStringMain[o]) && shifts == ""){
              shifts+=arrStringMain[o];
            } else {
              if(patternNumber.hasMatch(arrStringMain[o]) && shifts != ""){
                shifts+= ", "+arrStringMain[o];
              } else {
                index = o;
                break;
              }
            }
          }
          listShift.add(shifts);
          
          // handle locations
          String locations = "";
          for(var p = index;p<arrStringMain.length-1;p++){
            if(patternText.hasMatch(arrStringMain[p]) && locations == ""){
              locations += arrStringMain[p];
            } else {
              if(patternText.hasMatch(arrStringMain[p]) && locations != ""){
                locations += ", " + arrStringMain[p];
              } else {
                index = p;
                break;
              }
            }
          }
          if(listLocation.length <= listDayOfWeek.length){
            listLocation.add(locations);
          }

          // handle weeks
          String weeks = "";
          for(var a = index;a<arrStringMain.length;a++){
            if(patternDSSV.hasMatch(arrStringMain[a]) && weeks == ""){
              weeks += arrStringMain[a];
            } else {
              if(patternDSSV.hasMatch(arrStringMain[a]) && weeks != ""){
                weeks += ", "+arrStringMain[a];
              } else {
                continue;
              }
            }
          }
          listWeek.add(weeks);
        }
      } catch(_){
        print(_);
      }

      // Find final week
      int finalIndexWeek = 0, subFinalIndexWeek = 0;
      for(int s = 0;s<listWeek.length;s++){
        String week = listWeek.elementAt(s);
        if(week.contains(",")){
          List<String> weekList = week.split(", ");
          for(var d = 0;d<weekList.length;d++){
            String weeek = weekList[d];

            // check every char
            for(var g=0;g<weeek.length;g++){
              if(weeek[g]=='-'){
                // mean not
              } else {
                subFinalIndexWeek = g+1;
                if(subFinalIndexWeek>finalIndexWeek){
                  finalIndexWeek = subFinalIndexWeek;
                }
              }
            }
          }
        } else {
          for(var h = 0;h<week.length;h++){
            if(week[h]=='-'){
              // mean not
            } else {
              subFinalIndexWeek = h+1;

              if(subFinalIndexWeek > finalIndexWeek){
                finalIndexWeek = subFinalIndexWeek;
              }
            }
          }
        }
      }

      // Handle Tuan obj
      

      // 5. Add Study Year
      resStrList.add(currentStudyYear);

      // 6. Add elements demo
      resStrList.add(firstStringParseElement);

      // 7. Add final week
      resStrList.add(finalIndexWeek.toString());

      // Confirm by logging
      logger.i("Test: "+currentStudyWeek+", "+startDateCSW+"-"+endDateCSW);

    } else {
      Fluttertoast.showToast(
          msg: 'WRONG INPUT CODE. ',
          toastLength: Toast.LENGTH_SHORT,
          gravity: ToastGravity.CENTER,
          timeInSecForIosWeb: 1,
          backgroundColor: Colors.grey,
          textColor: Colors.black,
          fontSize: 16.0
      );
    }

    await Future.delayed(Duration(seconds: 1));
    return resStrList;
  }
}