import 'dart:async';
import 'dart:io';
import 'dart:math';
import 'dart:developer' as developer;
import 'package:flutter/material.dart';
import 'package:html/parser.dart' show parse;
import 'package:html/dom.dart' as dom;
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
        listDayOfWeek = List.empty(growable: true),
        listShift = List.empty(growable: true),
        listLocation = List.empty(growable: true),
        listWeek = List.empty(growable: true);
    // List<MonHoc> listCourseEachDay = List.empty(growable: true),
    //     listCourseCurrentWeek = List.empty(growable: true),
    //     listCourseTheWeekAT = List.empty(growable: true),
    //     listCourseTheWeekBY = List.empty(growable: true),
    //     listCourseTomo = List.empty(growable: true),
    //     listCourseYest = List.empty(growable: true),
    //     listCourseMondayCW = List.empty(growable: true),
    //     listCourseTuesdayCW = List.empty(growable: true),
    //     listCourseWednesdayCW = List.empty(growable: true),
    //     listCourseThursdayCW = List.empty(growable: true),
    //     listCourseFridayCW = List.empty(growable: true),
    //     listCourseSaturdayCW = List.empty(growable: true),
    //     listCourseSundayCW = List.empty(growable: true);
    var urlNorSche = Uri.parse("" + constants.NOR_SCHE_URL + inputCode);

    // Reg - exp
    final patternNumber = new RegExp('[0-9]+'),
        patternUnicodeChar = new RegExp('\\p{L}+'),
        patternText = new RegExp('^((?=.' 'd*)(?=.*[A-Z])(?=[_]*).{1,20})'),
        patternTextOneDigit = new RegExp(r"^[a-zA-Z]{1,3}$"),
        patternOneDigit = new RegExp('[\\d]{1}'),
        patternTwoDigit = new RegExp('0[1-9]'),
        patternDSSV = new RegExp(r'^[a-zA-Z]+'),
        patternDV = new RegExp(r'^DV'),
        patternCBGD = new RegExp("\\b[a-zA-Z]{2,3}\\d{2,3}\\b"),
        patternPlace = new RegExp(r'^[a-zA-Z]{1,4}[-.]{0,1}\d{1,3}$'),
        // patternPlace = new RegExp(r'^(?:\+?88|0088)?01[13-9]\d{8}$'),
        patternNumberConsecutive = new RegExp(r'^[a-z]{1}[A-Z]{1}[a-z]{1}$'),
        patternCourseCode = new RegExp("\\b^[a-zA-Z]{2,3}\\d{5}\\b");

    // check Update Server or Register Date -> Captcha Codes
    try {
      var response =
          await http.get(urlNorSche).timeout(const Duration(seconds: 2));
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
    var response =
        await http.get(urlNorSche).timeout(const Duration(seconds: 2));
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
    if (response.statusCode == 200) {
      var document = parse(response.body);
      var elementsDate = document.getElementsByTagName("span");

      // check whether or not elementsDate has 0 size
      try {
        if (elementsDate[12].text.length == 0 ||
            elementsDate[14].text.length == 0) {
        } else {
          firstDateCount = elementsDate[12].text.substring(
              elementsDate[12].text.length - 11,
              elementsDate[12].text.length - 1);
          currentUpdateDate = elementsDate[14].text;
        }
      } on Exception catch (e) {
        print(e);
        List<String> exceptionStrList = List.empty(growable: true);
        exceptionStrList.add("OtherException");
        return exceptionStrList;
      }

      var elementsAllAbove = document.querySelectorAll(
          "table.body-table > tbody > tr > td > table.body-table > tbody > tr > td");
      // var updateServerConfirmMessDialog =
      //     document.querySelector("img#arrowhead");
      var elementsAllBelow =
          document.querySelectorAll("table.body-table > tbody > tr > td > div");
      var elementsCourseNameFiterAll =
          document.querySelectorAll("table.body-table > tbody > tr > td");
      List<dom.Element> elementsAllImpor =
          document.querySelectorAll("table.body-table > tbody > tr > td");

      var elementUserFullName = document.querySelector(
              "span#" + constants.INFO_SPAN_ID + "TenSV" + ".Label"),
          elementUserClass = document.querySelector(
              "span#" + constants.INFO_SPAN_ID + "LopSV" + ".Label");
      // Fluttertoast.showToast(
      //     msg: 'R Test ',
      //     toastLength: Toast.LENGTH_SHORT,
      //     gravity: ToastGravity.CENTER,
      //     timeInSecForIosWeb: 1,
      //     backgroundColor: Colors.grey,
      //     textColor: Colors.black,
      //     fontSize: 16.0);
      String userFullName = "", userClass = " ";

      // userClass ??= " ";
      // Fluttertoast.showToast(
      //     msg: 'Conffirm Test ',
      //     toastLength: Toast.LENGTH_SHORT,
      //     gravity: ToastGravity.CENTER,
      //     timeInSecForIosWeb: 1,
      //     backgroundColor: Colors.grey,
      //     textColor: Colors.black,
      //     fontSize: 16.0);
      // check whether or not student
      if (elementUserFullName != null &&
          elementUserClass != null &&
          !patternTextOneDigit.hasMatch(inputCode.substring(0, 2))) {
        // Student

        userFullName = "" +
            elementUserFullName.text
                .substring(0, elementUserFullName.text.indexOf("-"))
                .trim();
        userClass = "" +
            elementUserClass.text
                .substring(0, elementUserClass.text.indexOf("-") + 1) +
            elementUserClass.text.substring(
                elementUserClass.text.lastIndexOf(":") + 1,
                elementUserClass.text.length);

        // Fluttertoast.showToast(
        //     msg: 'STU Test ' + userFullName + ", " + userClass,
        //     toastLength: Toast.LENGTH_LONG,
        //     gravity: ToastGravity.CENTER,
        //     timeInSecForIosWeb: 1,
        //     backgroundColor: Colors.grey,
        //     textColor: Colors.black,
        //     fontSize: 16.0);
      } else if (elementUserFullName != null && elementUserClass != null) {
        userFullName = elementUserFullName.text;
        userClass = elementUserClass.text;
        // Fluttertoast.showToast(
        //     msg: 'LEC Test ' + userFullName + ", " + userClass,
        //     toastLength: Toast.LENGTH_LONG,
        //     gravity: ToastGravity.CENTER,
        //     timeInSecForIosWeb: 1,
        //     backgroundColor: Colors.grey,
        //     textColor: Colors.black,
        //     fontSize: 16.0);
      }

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
        int currentDateOfYear =
                Jiffy(DateTime(dt.year, dt.month, dt.day)).dayOfYear,
            kickOffDateOfYear = Jiffy(DateTime(
                    int.parse(firstDateCount.substring(
                        firstDateCount.length - 4, firstDateCount.length)),
                    int.parse(firstDateCount.substring(
                        firstDateCount.length - 7, firstDateCount.length - 5)),
                    int.parse(firstDateCount.substring(0, 2))))
                .dayOfYear,
            currentWeekOfYear = 0,
            currentWeekOfMonth = 0,
            minusDateOfYear = 0;

        currentStudyYear = firstDateCount.substring(
            firstDateCount.length - 4, firstDateCount.length);

        // Case 1: Current study year equal year which start study semester
        if (DateTime.now().year ==
            int.parse(firstDateCount.substring(
                firstDateCount.length - 4, firstDateCount.length))) {
          // Fluttertoast.showToast(
          //     msg: 'Demo 1',
          //     toastLength: Toast.LENGTH_SHORT,
          //     gravity: ToastGravity.CENTER,
          //     timeInSecForIosWeb: 1,
          //     backgroundColor: Colors.grey,
          //     textColor: Colors.black,
          //     fontSize: 16.0);

          minusDateOfYear = currentDateOfYear - kickOffDateOfYear + 1;
          currentWeekOfYear = ((minusDateOfYear ~/ 7) + 1);
          currentWeekOfMonth = currentWeekOfYear % 4;

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

          // Check leap year
          bool isLeap = false;
          int allDays = 365;

          if (dt.year % 4 == 0) {
            if (dt.year % 100 == 0) {
              if (dt.year % 400 == 0) {
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

          if (isLeap) {
            allDays++;
          }

          int dayLastYear = allDays - kickOffDateOfYear + 1 + currentDateOfYear,
              currentWeekOfYear = ((dayLastYear ~/ 7) + 1),
              currentWeekOfMonth = currentWeekOfYear % 4;
        }

        // set current study week
        currentStudyWeek = currentWeekOfYear.toString();

        // set current start date of CSW
        startDateCSW = Jiffy(DateTime(
                int.parse(firstDateCount.substring(
                    firstDateCount.length - 4, firstDateCount.length)),
                int.parse(firstDateCount.substring(
                    firstDateCount.length - 7, firstDateCount.length - 5)),
                int.parse(firstDateCount.substring(0, 2))))
            .add(days: (currentWeekOfYear - 1) * 7)
            .format("dd/MM");

        // set current end date of CSW
        endDateCSW = Jiffy(DateTime(
                int.parse(firstDateCount.substring(
                    firstDateCount.length - 4, firstDateCount.length)),
                int.parse(firstDateCount.substring(
                    firstDateCount.length - 7, firstDateCount.length - 5)),
                int.parse(firstDateCount.substring(0, 2))))
            .add(days: currentWeekOfYear * 7 - 1)
            .format("dd/MM");
      } catch (_) {
        print(_);
      }

      // 2. Add Current Study Week along with its start & end date
      resStrList.add(currentStudyWeek +
          ", " +
          startDateCSW +
          "-" +
          endDateCSW +
          ", " +
          currentStudyYear);

      // 3. Add User Full Name
      resStrList.add(userFullName);

      // 4. Add User Class
      resStrList.add(userClass);

      // Fluttertoast.showToast(
      //     msg: '${userFullName}, ${userClass}  ',
      //     toastLength: Toast.LENGTH_SHORT,
      //     gravity: ToastGravity.CENTER,
      //     timeInSecForIosWeb: 1,
      //     backgroundColor: Colors.grey,
      //     textColor: Colors.black,
      //     fontSize: 16.0);

      // Handle to store Course List elements
      String res = "", noteAllInforBelow = "";
      String demoListTextCourseFilterOne = "";

      // Demo
      int indexStoredNote = 0;
      for (var i = 0; i < elementsAllImpor.length; i++) {
        var st = elementsAllImpor.elementAt(i).text;
        // if (st.length > 50) {
        //   firstStringParseElement = st;
        if (st != '' &&
            st != ' ' &&
            !st.contains('\t') &&
            st.length < 50 &&
            st.length > 0 &&
            !patternPlace.hasMatch(st) &&
            !patternNumberConsecutive.hasMatch(st)) {
          // meet course name
          if (st.contains(' ') &&
              st != ' ' &&
              st != '' &&
              !patternTwoDigit.hasMatch(st)) {
            textRawHandled += st + "*";

            i += 5;
            // print(
            //     ")))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
            continue;
          }

          // meet regexp number like '01'
          // if(patternTwoDigit.hasMatch(st)){
          //   st = elementsAllImpor.elementAt(i+1).text;
          // }

          // check '\n' character
          if (st == '\n' || st.contains('\n') || st == '') {
            st = elementsAllImpor.elementAt(i + 1).text;
          }

          // last index DSSV
          if (st == 'DSSV' || st.contains("DSSV")) {
            indexStoredNote = i;
            textRawHandled += "; ";
          } else {
            textRawHandled += st + "*";
          }
          // add to string filter list
          // listTextCourseFilter.add(textRawHandled);
        }
      }
      // print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"+textRawHandled.substring(textRawHandled.length-1000, textRawHandled.length-1));
      // demoListTextCourseFilterOne += textRawHandled.substring(textRawHandled.length-500 ,textRawHandled.length-1);

      // // Handle course code
      // for (var i = 0; i < elementsCourseNameFiterAll.length; i++) {
      //   var st = elementsCourseNameFiterAll.elementAt(i).text;
      //
      //   if (patternCourseCode.hasMatch(st) &&
      //       i != elementsCourseNameFiterAll.length - 1 &&
      //       !st.contains(' ')) {
      //     // add to course code list
      //     listCourseCode.add(st);
      //     // demoListTextCourseFilterOne += st + ", ** ";
      //   }
      // }
      //
      // // Handle course name
      // for (var i = 0; i < elementsCourseNameFiterAll.length; i++) {
      //   var st = elementsCourseNameFiterAll.elementAt(i).text;
      //
      //   if (st.contains(' ') &&
      //       !st.contains(', ') &&
      //       st != ' ' &&
      //       st != '' &&
      //       !patternTwoDigit.hasMatch(st)) {
      //     // add to course name list
      //     listCourseName.add(st);
      //     // demoListTextCourseFilterOne += st + ",@ ";
      //   }
      // }
      //
      // // Handle the others except the place
      // for (var i = 0; i < elementsAllAbove.length; i++) {
      //   var st = elementsAllAbove.elementAt(i).text;
      //   if (!patternPlace.hasMatch(st) && st != '' && st != ' ' &&
      //       !patternTwoDigit.hasMatch(st)) {
      //     // demoListTextCourseFilterOne += st + ",& ";
      //   }
      // }

      // for (var i = 0; i < elementsAllBelow.length; i++) {
      //   var st = elementsAllBelow.elementAt(i).text;

      // Filter all the folowing cases: empty null string, CBGD like 'SHD08SHD08'
      // & cases in ('01', '02', '03', '04', '05', '06', '07', '08', '09')
      // if (!patternPlace.hasMatch(st) &&
      //     st != '' &&
      //     st != ' ' &&
      //     !patternTwoDigit.hasMatch(st)) {
      // if (patternText.hasMatch(st)) {
      //   noteAllInforBelow += ", ";
      // } else {
      //   noteAllInforBelow += st + "*";
      // }
      //   }
      // }

      // Handle the place
      // for (var i = 0; i < elementsAllAbove.length; i++) {
      //   var st = elementsAllAbove.elementAt(i).text;
      //   if (patternPlace.hasMatch(st)) {
      // demoListTextCourseFilterOne += st + ",! ";
      //   }

      // for (var i = 0; i < elementsAllBelow.length; i++) {
      //   var st = elementsAllBelow.elementAt(i).text;
      //   if (patternPlace.hasMatch(st)) {
      // demoListTextCourseFilterOne += st + ",! ";
      //   }
      // }

      int countRegex(var str1, var str2) {
        var next = 0, n = 0;
        var findedword = 0;
        do {
          n = str2.indexOf(str1, next);
          findedword = findedword + 1;
          next = n + str1.length;
        } while (n >= 0);
        return findedword - 1;
      }

      // Handle soTiet, tietBatDau, tietKetThuc, ngayThu, phongHoc, tuanHoc
      List<String> courseInforStrListFilter = textRawHandled.split("; ");
      List<String> allCaseTHNote = [
        '01',
        '02',
        '03',
        '04',
        '05',
        '06',
        '07',
        '08',
        '09'
      ];
      List<String> allCaseViDOWNote = [
        'Hai',
        'Ba',
        'Tư',
        'Năm',
        'Sáu',
        'Bảy',
        'CN'
      ];
      bool numberOnDeck = false, breakWOR = false, notePassToSchedule = false, dowOnDeck;
      int indexNoteOne = 2, indexNoteSWeek = 0, countDate = 0;
      String strNotePlace = "", strParseAllNote = "";
      List<String> countDateStringList = List.empty(growable: true);

      // for(int i = 0;i<courseInforStrListFilter.length;i++){
      //   demoListTextCourseFilterOne+=courseInforStrListFilter[i]+"-------";
      // }
      //
      //
      // Fluttertoast.showToast(
      //     msg: 'DAMNNNN 0',
      //     toastLength: Toast.LENGTH_SHORT,
      //     gravity: ToastGravity.CENTER,
      //     timeInSecForIosWeb: 1,
      //     backgroundColor: Colors.grey,
      //     textColor: Colors.black,
      //     fontSize: 16.0);

      for (var i = 0; i < courseInforStrListFilter.length - 1; i++) {
        List<String> strListNote = courseInforStrListFilter[i].split("*");
        if (strListNote.toString() == '[]') {
          // print(
          //     "OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
          continue;
        }
        // print("##############################" + strListNote.toString());
        developer.log("#" + strListNote.toString(), name: 'course_in4');
        indexNoteOne = 3;
        indexNoteSWeek = 0;
        countDate = 0;
        notePassToSchedule = false;
        breakWOR = false;
        numberOnDeck = false;
        dowOnDeck = false;

        for (var q = 0; q < strListNote.length; q++) {
          // logger.i(strListNote[indexNoteOne] + ">>>>>>>> " + q.toString());
          if (dowOnDeck == true && numberOnDeck == true && strListNote[indexNoteOne].length >= 8 &&
              strListNote[indexNoteOne].length <= 19) {
            if ((!strListNote[indexNoteOne].contains(new RegExp(r'[a-zA-Z]')) &&
                strListNote[indexNoteOne].contains('-')) ||
                (strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                    int.parse(strListNote[indexNoteOne]) >= 100)) {
              print("damn hot >>>>>>>");
              strNotePlace += " ; ";
              indexNoteSWeek = indexNoteOne + 2;
            } else {
              print("wooo damn hot >>>>>>> "+strListNote[indexNoteOne]);
              notePassToSchedule = true;
              strNotePlace += strListNote[indexNoteOne] + "; ";
            }
            indexNoteSWeek = indexNoteOne + 2;
          }
          // print("OK");

          // check case '1234' TKB
          if ((notePassToSchedule == true &&
              strListNote[indexNoteOne].contains(new RegExp(r'[-]+'))) ||
              (strListNote[indexNoteOne].length > 19 &&
                  strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
                  !strListNote[indexNoteOne].contains('-'))) {
            // do nothing

          } else if (notePassToSchedule == true &&
              (strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne]) >= 100 &&
                  int.parse(strListNote[indexNoteOne]) != 123123 &&
                  int.parse(strListNote[indexNoteOne]) != 123123123 &&
                  (!strListNote[indexNoteOne].contains('0123') &&
                      countRegex('123', strListNote[indexNoteOne]) < 2))) {
            print("OKKKKKKKKKKKKKKKKKKKKKKKK BRUH:  " +
                strListNote[indexNoteOne]);
            print("OK -1 1 " + strListNote[indexNoteOne]);
            numberOnDeck = false;
            break;
          }

          // check case indexNoteOne > strListNote.length
          if (indexNoteOne >= strListNote.length - 1) {
            break;
          }

          // filter string contain '-'
          if ((strListNote[indexNoteOne].length > 19 &&
              strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
              !strListNote[indexNoteOne].contains('-'))) {
            // do nothing
            print("OK case Special " + strListNote[indexNoteOne]);
            strListNote.removeAt(indexNoteOne);
          } else {
            if ((!strListNote[indexNoteOne].contains('-') &&
                strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                int.parse(strListNote[indexNoteOne]) >= 100) ||
                (strListNote[indexNoteOne].contains('-') &&
                    indexNoteOne != indexNoteSWeek &&
                    !strListNote[indexNoteOne]
                        .contains(new RegExp(r'[a-zA-Z]')))) {
              if (strListNote[indexNoteOne].contains('-') &&
                  indexNoteOne != indexNoteSWeek &&
                  !strListNote[indexNoteOne].contains(
                      new RegExp(r'[a-zA-Z]')) &&
                  breakWOR == false) {

                // in case that '-------890123' of course number 5 of CNP07
                if(strListNote[indexNoteOne] == '-------890123'){
                  print("OK -1 1 " + strListNote[indexNoteOne]);
                  numberOnDeck = false;
                  break;
                }

                print("OK -1 1 " + strListNote[indexNoteOne]);
                strListNote.removeAt(indexNoteOne);
                numberOnDeck = false;
                break;
              } else if (strListNote[indexNoteOne].contains('-') &&
                  indexNoteOne != indexNoteSWeek &&
                  !strListNote[indexNoteOne].contains(
                      new RegExp(r'[a-zA-Z]')) &&
                  breakWOR == true &&
                  strListNote[indexNoteOne].length < 16) {
                // case NN014 including case NN012 (course 2)
                if (strListNote.length - indexNoteOne == 4 &&
                    strListNote[indexNoteOne].length < 25) {
                  var strS = strListNote[indexNoteOne] +
                      strListNote[indexNoteOne + 1] +
                      strListNote[indexNoteOne + 2];
                  if (strS.length >= 42) {
                    print("OK -1 1 >> "
                        + strListNote[indexNoteOne] +
                        strListNote[indexNoteOne + 1] +
                        strListNote[indexNoteOne + 2]
                    );
                    numberOnDeck = false;
                    break;
                  } else {

                  }
                }

                // case NN001
                if (strListNote[indexNoteOne] == '---456---456') {
                  print("OK -1 1 case NN001" + strListNote[indexNoteOne]);
                  strListNote.removeAt(indexNoteOne);
                  numberOnDeck = false;
                  break;
                } else {
                  print("OK -1 1 case not NN001 " + strListNote[indexNoteOne]);

                }

              }

              // case the last index
              int subOneLength = strListNote.length - 1;
              // print(indexNoteOne.toString() +
              //     " equal to " +
              //     subOneLength.toString());
              if (indexNoteOne == subOneLength && breakWOR == false) {
                print("OK -1 2 " + strListNote[indexNoteOne]);
                strListNote.removeAt(indexNoteOne);
                numberOnDeck = false;
                break;
              } else if (indexNoteOne == subOneLength && breakWOR == true) {
                print("OK -1 2 " + strListNote[indexNoteOne]);
                numberOnDeck = false;
                break;
              }

              if (((!strListNote[indexNoteOne - 1].contains('-') &&
                  strListNote[indexNoteOne - 1]
                      .startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne - 1]) <= 11) &&
                  (!strListNote[indexNoteOne + 1].contains('-') &&
                      strListNote[indexNoteOne + 1]
                          .startsWith(new RegExp(r'[0-9]')) &&
                      int.parse(strListNote[indexNoteOne + 1]) < 9)) ||
                  ((allCaseViDOWNote
                      .contains("" + strListNote[indexNoteOne - 1])) &&
                      (!strListNote[indexNoteOne - 1].contains('-') &&
                          strListNote[indexNoteOne - 1]
                              .startsWith(new RegExp(r'[0-9]')) &&
                          int.parse(strListNote[indexNoteOne - 1]) <= 11))) {
                // do nothing
              } else {
                // print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<");
                if (strListNote[indexNoteOne].startsWith(
                    new RegExp(r'[0-9]'))) {
                  // do nothing
                } else {
                  if (breakWOR == false ||
                      strListNote[indexNoteOne].length >= 16) {
                    // handle case NN014
                    if (strListNote.length - indexNoteOne == 4 && countRegex('-', strListNote[indexNoteOne]) >= 8 &&
                        strListNote[indexNoteOne].length < 25) {
                      print('Case like NN014 - haha lol <3 <3 !!!!! ');
                      print("OK -1 3 " + strListNote[indexNoteOne]);
                      numberOnDeck = false;
                      break;
                    } else {
                      print("OK -1 3 " + strListNote[indexNoteOne]);
                      strListNote.removeAt(indexNoteOne);
                      numberOnDeck = false;
                      break;
                    }
                  } else {
                    print("OK -1 3 " + strListNote[indexNoteOne]);
                    numberOnDeck = false;
                    break;
                  }
                }
              }
            }
          }

          // print("OK");
          // pass course code & course name at index 1 and 2

          // check to cases like '01'
          if (strListNote[indexNoteOne].contains('-') ||
              (strListNote[indexNoteOne].length > 19 &&
                  strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
                  !strListNote[indexNoteOne].contains('-'))) {
            // do nothing

          } else {
            if (!allCaseViDOWNote.contains("" + strListNote[indexNoteOne]) &&
                (allCaseTHNote.contains("" + strListNote[indexNoteOne]) ||
                    !(strListNote[indexNoteOne]
                        .startsWith(new RegExp(r'[0-9]')) &&
                        int.parse(strListNote[indexNoteOne]) <= 11))) {

              // case NN009 - course number 5
              if (strListNote[indexNoteOne] == '123456789012') {
                print("OK 0 1 " + strListNote[indexNoteOne]);
                numberOnDeck = false;
              } else {

                // in case that '123456' or kind of that spot in here
                if( strListNote[indexNoteOne] == '1' ||
                    strListNote[indexNoteOne] == '12' ||
                    strListNote[indexNoteOne] == '123' ||
                    strListNote[indexNoteOne] == '1234' ||
                    strListNote[indexNoteOne] == '12345' ||
                    strListNote[indexNoteOne] == '123456' ||
                    strListNote[indexNoteOne] == '1234567' ||
                    strListNote[indexNoteOne] == '12345678' ||
                    strListNote[indexNoteOne] == '123456789' ||
                    strListNote[indexNoteOne] == '1234567890' ||
                    strListNote[indexNoteOne] == '12345678901' ||
                    strListNote[indexNoteOne] == '123456789012' ||
                    strListNote[indexNoteOne] == '1234567890123' ||
                    strListNote[indexNoteOne] == '12345678901234' ||
                    strListNote[indexNoteOne] == '123456789012345'){
                  print("OK pass " + strListNote[indexNoteOne]);
                  numberOnDeck = false;
                  break;
                }

                print("OK 0 1 " + strListNote[indexNoteOne]);
                numberOnDeck = false;
                strListNote.removeAt(indexNoteOne);
              }

            } else {
              if (strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne]) <= 11) {
                numberOnDeck = true;
                print("OK numberOnDeck " + strListNote[indexNoteOne]);
              } else {
                numberOnDeck = false;
              }

              // handle the case that double start shift is 1
              if (allCaseViDOWNote
                  .contains("" + strListNote[indexNoteOne - 1]) &&
                  strListNote[indexNoteOne + 1] == '1' &&
                  strListNote[indexNoteOne] == '11') {
                print("OK 0 2 " + strListNote[indexNoteOne]);
                numberOnDeck = false;
                strListNote.removeAt(indexNoteOne);
              } else {
                // print((allCaseViDOWNote
                //     .contains("" + strListNote[indexNoteOne - 1]) ||
                //     strListNote[indexNoteOne - 1]
                //         .startsWith(new RegExp(' ')) &&
                //         (strListNote[indexNoteOne - 1].contains(" ")) &&
                //         (strListNote[indexNoteOne - 1]
                //             .contains(RegExp(r"[A-Za-z]+"))))
                //     .toString() +
                //     ">>>>>>>>>>>>" +
                //     (allCaseViDOWNote
                //         .contains("" + strListNote[indexNoteOne + 1]) ||
                //         (strListNote[indexNoteOne + 1]
                //             .startsWith(new RegExp(r'[0-9]'))))
                //         .toString());
                //check whether or not Viet DOW to increase countDate
                if (
                // (allCaseViDOWNote
                //             .contains("" + strListNote[indexNoteOne - 1]) ||
                //         strListNote[indexNoteOne - 1]
                //                 .startsWith(new RegExp(' ')) &&
                // (strListNote[indexNoteOne - 1].contains(" ")) &&
                //             (strListNote[indexNoteOne - 1]
                //                 .contains(RegExp(r"[A-Za-z]+"))))
                // &&
                (allCaseViDOWNote
                    .contains("" + strListNote[indexNoteOne + 1]) ||
                    (strListNote[indexNoteOne + 1]
                        .startsWith(new RegExp(r'[0-9]')))) &&
                    allCaseViDOWNote
                        .contains("" + strListNote[indexNoteOne])) {
                  countDate++;
                  dowOnDeck = true;
                  countDateStringList.add(strListNote[indexNoteOne]);
                  print("COUNT DATE: " + countDate.toString());
                  print("\$\$\$\$\$\$\$\$\$\$\$\$\$\$\$\$" +
                      strListNote[indexNoteOne]);

                  // check condition to break without removing
                  if (countDate >= 4) {
                    breakWOR = true;
                    print("DAMN");
                  } else {
                    breakWOR = false;
                    print("DAMN " + countDate.toString());
                  }
                }

                print("OK pass 0 " + strListNote[indexNoteOne]);
                if (indexNoteOne < strListNote.length - 1) {
                  indexNoteOne++;
                }
              }
            }
          }

          if (dowOnDeck == true && numberOnDeck == true && strListNote[indexNoteOne].length >= 8 &&
              strListNote[indexNoteOne].length <= 19) {
            if ((!strListNote[indexNoteOne].contains(new RegExp(r'[a-zA-Z]')) &&
                strListNote[indexNoteOne].contains('-')) ||
                (strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                    int.parse(strListNote[indexNoteOne]) >= 100)) {
              print("damn hot >>>>>>>");
              strNotePlace += " ; ";
              indexNoteSWeek = indexNoteOne + 2;
            } else {
              print("wooo damn hot >>>>>>> "+strListNote[indexNoteOne]);
              notePassToSchedule = true;
              strNotePlace += strListNote[indexNoteOne] + "; ";
            }
            indexNoteSWeek = indexNoteOne + 2;
          }

          // check case '1234' TKB
          if ((notePassToSchedule == true &&
              strListNote[indexNoteOne].contains(new RegExp(r'[-]+'))) ||
              (strListNote[indexNoteOne].length > 19 &&
                  strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
                  !strListNote[indexNoteOne].contains('-'))) {
            // do nothing

          } else if (notePassToSchedule == true &&
              (strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne]) >= 100 &&
                  int.parse(strListNote[indexNoteOne]) != 123123 &&
                  int.parse(strListNote[indexNoteOne]) != 123123123 &&
                  (!strListNote[indexNoteOne].contains('0123') &&
                      countRegex('123', strListNote[indexNoteOne]) < 2))) {
            print("OKKKKKKKKKKKKKKKKKKKKKKKK BRUH:  " +
                strListNote[indexNoteOne]);
            print("OK -1 1 " + strListNote[indexNoteOne]);
            numberOnDeck = false;
            break;
          }

          // check case indexNoteOne > strListNote.length
          if (indexNoteOne >= strListNote.length - 1) {
            break;
          }

          // filter string contain '-'
          if ((strListNote[indexNoteOne].length > 19 &&
              strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
              !strListNote[indexNoteOne].contains('-'))) {
            // do nothing
            print("OK case Special " + strListNote[indexNoteOne]);
            strListNote.removeAt(indexNoteOne);
          } else {
            if ((!strListNote[indexNoteOne].contains('-') &&
                strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                int.parse(strListNote[indexNoteOne]) >= 100) ||
                (strListNote[indexNoteOne].contains('-') &&
                    indexNoteOne != indexNoteSWeek &&
                    !strListNote[indexNoteOne]
                        .contains(new RegExp(r'[a-zA-Z]')))) {
              // case NN014 including case NN012 (course 2)
              if (strListNote.length - indexNoteOne == 4 &&
                  strListNote[indexNoteOne].length < 25) {
                var strS = strListNote[indexNoteOne] +
                    strListNote[indexNoteOne + 1] +
                    strListNote[indexNoteOne + 2];
                if (strS.length >= 42) {
                  print("OK -1 1 >> "
                      + strListNote[indexNoteOne] +
                      strListNote[indexNoteOne + 1] +
                      strListNote[indexNoteOne + 2]
                  );
                  numberOnDeck = false;
                  break;
                } else {
                  print("OK -1 1 A" + strListNote[indexNoteOne]);
                  strListNote.removeAt(indexNoteOne);
                  numberOnDeck = false;
                  break;
                }
              }
              if (strListNote[indexNoteOne].contains('-') &&
                  indexNoteOne != indexNoteSWeek &&
                  !strListNote[indexNoteOne].contains(
                      new RegExp(r'[a-zA-Z]')) &&
                  breakWOR == false) {
                print("OK -1 1 B" + strListNote[indexNoteOne]);
                strListNote.removeAt(indexNoteOne);
                numberOnDeck = false;
                break;
              } else if (strListNote[indexNoteOne].contains('-') &&
                  indexNoteOne != indexNoteSWeek &&
                  !strListNote[indexNoteOne].contains(
                      new RegExp(r'[a-zA-Z]')) &&
                  breakWOR == true &&
                  strListNote[indexNoteOne].length < 16) {

                // in case that '1111-2-2' -> 4 buổi học trong 1 tuần duy nhất 1 1 1 1 - course 3 of NN001
                if( strListNote[indexNoteOne+1] == '1'
                    && strListNote[indexNoteOne+2] == '1'
                    && strListNote[indexNoteOne+3] == '1'
                ){
                  // replace '1111-2-2' into 'special case NN001'
                  strListNote[indexNoteOne] = 'special case NN001';
                  numberOnDeck = false;
                  break;
                }

                print("OK -1 1 C" + strListNote[indexNoteOne]);

                numberOnDeck = false;
                break;
              }

              // case the last index
              int subOneLength = strListNote.length - 1;
              print(indexNoteOne.toString() +
                  " equal to " +
                  subOneLength.toString());
              if (indexNoteOne == subOneLength && breakWOR == false) {
                print("OK -1 2 " + strListNote[indexNoteOne]);
                strListNote.removeAt(indexNoteOne);
                numberOnDeck = false;
                break;
              } else if (indexNoteOne == subOneLength && breakWOR == true) {
                print("OK -1 2 " + strListNote[indexNoteOne]);
                numberOnDeck = false;
                break;
              }

              if (((!strListNote[indexNoteOne - 1].contains('-') &&
                  strListNote[indexNoteOne - 1]
                      .startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne - 1]) <= 11) &&
                  (!strListNote[indexNoteOne + 1].contains('-') &&
                      strListNote[indexNoteOne + 1]
                          .startsWith(new RegExp(r'[0-9]')) &&
                      int.parse(strListNote[indexNoteOne + 1]) < 9)) ||
                  ((allCaseViDOWNote
                      .contains("" + strListNote[indexNoteOne - 1])) &&
                      (!strListNote[indexNoteOne - 1].contains('-') &&
                          strListNote[indexNoteOne - 1]
                              .startsWith(new RegExp(r'[0-9]')) &&
                          int.parse(strListNote[indexNoteOne - 1]) <= 11))) {
                // do nothing
              } else {
                // print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<");
                if (strListNote[indexNoteOne].startsWith(
                    new RegExp(r'[0-9]'))) {
                  // do nothing
                } else {
                  if (breakWOR == false ||
                      strListNote[indexNoteOne].length >= 16) {
                    // handle case NN014
                    if (strListNote.length - indexNoteOne == 4 && countRegex('-', strListNote[indexNoteOne]) >= 8 &&
                        strListNote[indexNoteOne].length < 25) {
                      // check case NN001 - course 4 - week: --3--3--3--3---4---4
                      if (strListNote[indexNoteOne].length == 20 &&
                          countRegex('-', strListNote[indexNoteOne]) == 14) {
                        print("Case NN001 - course 4 : " +
                            strListNote[indexNoteOne]);
                        strListNote.removeAt(indexNoteOne);
                        numberOnDeck = false;
                        break;
                      }

                      // print('Case like NN014 - haha lol <3 <3 !!!!! ');
                      print("OK -1 3 " + strListNote[indexNoteOne]);
                      numberOnDeck = false;
                      break;
                    } else {
                      print("OK -1 3 " + strListNote[indexNoteOne]);
                      strListNote.removeAt(indexNoteOne);
                      numberOnDeck = false;
                      break;
                    }
                  } else {
                    print("OK -1 3 " + strListNote[indexNoteOne]);
                    numberOnDeck = false;
                    break;
                  }
                }
              }
            }
          }


          // check case '1234' TKB
          if ((notePassToSchedule == true &&
              strListNote[indexNoteOne].contains(new RegExp(r'[-]+'))) ||
              (strListNote[indexNoteOne].length > 19 &&
                  strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
                  !strListNote[indexNoteOne].contains('-'))) {
            // do nothing
          } else if (notePassToSchedule == true &&
              (strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne]) >= 100 &&
                  int.parse(strListNote[indexNoteOne]) != 123123 &&
                  int.parse(strListNote[indexNoteOne]) != 123123123 &&
                  (!strListNote[indexNoteOne].contains('0123') &&
                      countRegex('123', strListNote[indexNoteOne]) < 2))) {
            print("OKKKKKKKKKKKKKKKKKKKKKKKK BRUH:  " +
                strListNote[indexNoteOne]);
            print("OK -1 1 " + strListNote[indexNoteOne]);
            numberOnDeck = false;
            break;
          }

          // check case indexNoteOne > strListNote.length
          if (indexNoteOne >= strListNote.length - 1) {
            break;
          }

          // check to cases like 'BaNamTu'
          if (strListNote[indexNoteOne].contains('-') ||
              (strListNote[indexNoteOne].length > 19 &&
                  strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
                  !strListNote[indexNoteOne].contains('-'))) {
            // do nothing

          } else {
            if (!allCaseTHNote.contains("" + strListNote[indexNoteOne]) &&
                (allCaseViDOWNote.contains("" + strListNote[indexNoteOne]) ||
                    (strListNote[indexNoteOne]
                        .startsWith(new RegExp(r'[0-9]')) &&
                        int.parse(strListNote[indexNoteOne]) <= 11) ||
                    allCaseViDOWNote
                        .contains("" + strListNote[indexNoteOne]))) {
              // leave it just there, do nothing changes to strListNote

              if (strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne]) <= 11) {
                numberOnDeck = true;
                print("OK numberOnDeck " + strListNote[indexNoteOne]);
              } else {
                numberOnDeck = false;
              }

              // handle the case that double start shift is 1
              if (allCaseViDOWNote
                  .contains("" + strListNote[indexNoteOne - 1]) &&
                  strListNote[indexNoteOne + 1] == '1' &&
                  strListNote[indexNoteOne] == '11') {
                print("OK 1 " + strListNote[indexNoteOne]);
                numberOnDeck = false;
                strListNote.removeAt(indexNoteOne);
              } else {
                print((allCaseViDOWNote
                    .contains("" + strListNote[indexNoteOne - 1]) ||
                    strListNote[indexNoteOne - 1]
                        .startsWith(new RegExp(' ')) &&
                        (strListNote[indexNoteOne - 1].contains(" ")) &&
                        (strListNote[indexNoteOne - 1]
                            .contains(RegExp(r"[A-Za-z]+"))))
                    .toString() +
                    ">>>>>>>>>>>>" +
                    (allCaseViDOWNote
                        .contains("" + strListNote[indexNoteOne + 1]) ||
                        (strListNote[indexNoteOne + 1]
                            .startsWith(new RegExp(r'[0-9]'))))
                        .toString());
                //check whether or not Viet DOW to increase countDate
                if ((allCaseViDOWNote
                    .contains("" + strListNote[indexNoteOne - 1]) ||
                    strListNote[indexNoteOne - 1]
                        .startsWith(new RegExp(' ')) &&
                        (strListNote[indexNoteOne - 1].contains(" ")) &&
                        (strListNote[indexNoteOne - 1]
                            .contains(RegExp(r"[A-Za-z]+")))) &&
                    (allCaseViDOWNote
                        .contains("" + strListNote[indexNoteOne + 1]) ||
                        (strListNote[indexNoteOne + 1]
                            .startsWith(new RegExp(r'[0-9]')))) &&
                    allCaseViDOWNote.contains("" + strListNote[indexNoteOne])) {
                  countDate++;
                  dowOnDeck = true;
                  countDateStringList.add(strListNote[indexNoteOne]);
                  print("COUNT DATE: " + countDate.toString());
                  print("\$\$\$\$\$\$\$\$\$\$\$\$\$\$\$\$" +
                      strListNote[indexNoteOne]);

                  // check condition to break without removing
                  if (countDate >= 4) {
                    breakWOR = true;
                    print("DAMN");
                  } else {
                    breakWOR = false;
                    print("DAMN " + countDate.toString());
                  }
                }

                print("OK pass 1 " + strListNote[indexNoteOne]);
                if (indexNoteOne < strListNote.length - 1) {
                  indexNoteOne++;
                }
              }
            } else {
              // handle case that week spot in here
              if( !strListNote[indexNoteOne].contains('-') &&
    strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
    int.parse(strListNote[indexNoteOne]) >= 100){
                // week
                print("OK pass 1" + strListNote[indexNoteOne]);

              } else {

                // in case that '123456' or kind of that spot in here
                if( strListNote[indexNoteOne] == '1' ||
                    strListNote[indexNoteOne] == '12' ||
                    strListNote[indexNoteOne] == '123' ||
                    strListNote[indexNoteOne] == '1234' ||
                    strListNote[indexNoteOne] == '12345' ||
                    strListNote[indexNoteOne] == '123456' ||
                    strListNote[indexNoteOne] == '1234567' ||
                    strListNote[indexNoteOne] == '12345678' ||
                    strListNote[indexNoteOne] == '123456789' ||
                    strListNote[indexNoteOne] == '1234567890' ||
                    strListNote[indexNoteOne] == '12345678901' ||
                    strListNote[indexNoteOne] == '123456789012' ||
                    strListNote[indexNoteOne] == '1234567890123' ||
                    strListNote[indexNoteOne] == '12345678901234' ||
                    strListNote[indexNoteOne] == '123456789012345'){
                  print("OK pass " + strListNote[indexNoteOne]);
                  numberOnDeck = false;
                  break;
                }

                print("OK 1 " + strListNote[indexNoteOne]);
                numberOnDeck = false;
                strListNote.removeAt(indexNoteOne);
              }

            }
          }

          if (dowOnDeck == true && numberOnDeck == true && strListNote[indexNoteOne].length >= 8 &&
              strListNote[indexNoteOne].length <= 19) {
            if ((!strListNote[indexNoteOne].contains(new RegExp(r'[a-zA-Z]')) &&
                strListNote[indexNoteOne].contains('-')) ||
                (strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                    int.parse(strListNote[indexNoteOne]) >= 100)) {
              print("damn hot >>>>>>>");
              strNotePlace += " ; ";
              indexNoteSWeek = indexNoteOne + 2;
            } else {
              print("wooo damn hot >>>>>>>");
              notePassToSchedule = true;
              strNotePlace += strListNote[indexNoteOne] + "; ";
            }
            indexNoteSWeek = indexNoteOne + 2;
          }

          // check case '1234' TKB
          if ((notePassToSchedule == true &&
              strListNote[indexNoteOne].contains(new RegExp(r'[-]+'))) ||
              (strListNote[indexNoteOne].length > 19 &&
                  strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
                  !strListNote[indexNoteOne].contains('-'))) {
            // do nothing

          } else if (notePassToSchedule == true &&
              (strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne]) >= 100 &&
                  int.parse(strListNote[indexNoteOne]) != 123123 &&
                  int.parse(strListNote[indexNoteOne]) != 123123123 &&
                  (!strListNote[indexNoteOne].contains('0123') &&
                      countRegex('123', strListNote[indexNoteOne]) < 2))) {
            print("OKKKKKKKKKKKKKKKKKKKKKKKK BRUH:  " +
                strListNote[indexNoteOne]);
            print("OK -1 1 " + strListNote[indexNoteOne]);
            numberOnDeck = false;
            break;
          }

          // check case indexNoteOne > strListNote.length
          if (indexNoteOne >= strListNote.length - 1) {
            break;
          }

          // filter string contain '-'
          if ((strListNote[indexNoteOne].length > 19 &&
              strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
              !strListNote[indexNoteOne].contains('-'))) {
            // do nothing
            print("OK case Special " + strListNote[indexNoteOne]);
            strListNote.removeAt(indexNoteOne);
          } else {
            if ((!strListNote[indexNoteOne].contains('-') &&
                strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                int.parse(strListNote[indexNoteOne]) >= 100) ||
                (strListNote[indexNoteOne].contains('-') &&
                    indexNoteOne != indexNoteSWeek &&
                    !strListNote[indexNoteOne]
                        .contains(new RegExp(r'[a-zA-Z]')))) {
              // case NN014 including case NN012 (course 2)
              print("DFF "+(strListNote.length - indexNoteOne).toString());
              if (strListNote.length - indexNoteOne == 4 &&
                  strListNote[indexNoteOne].length < 25) {

                var strS = strListNote[indexNoteOne] +
                    strListNote[indexNoteOne + 1] +
                    strListNote[indexNoteOne + 2];
                if (strS.length >= 42) {
                  print("OK -1 1 >> "
                      + strListNote[indexNoteOne] +
                      strListNote[indexNoteOne + 1] +
                      strListNote[indexNoteOne + 2]
                  );
                  numberOnDeck = false;
                  break;
                } else {
                  print("OK -1 1 D" + strListNote[indexNoteOne]);
                  strListNote.removeAt(indexNoteOne);
                  numberOnDeck = false;
                  break;
                }

                if (strListNote[indexNoteOne].contains('-') &&
                    indexNoteOne != indexNoteSWeek &&
                    !strListNote[indexNoteOne].contains(
                        new RegExp(r'[a-zA-Z]')) &&
                    breakWOR == false) {
                  print("OK -1 1 E" + strListNote[indexNoteOne]);
                  strListNote.removeAt(indexNoteOne);
                  numberOnDeck = false;
                  break;
                } else if (strListNote[indexNoteOne].contains('-') &&
                    indexNoteOne != indexNoteSWeek &&
                    !strListNote[indexNoteOne].contains(
                        new RegExp(r'[a-zA-Z]')) &&
                    breakWOR == true &&
                    strListNote[indexNoteOne].length < 16) {
                  print("OK -1 1 F" + strListNote[indexNoteOne]);
                  numberOnDeck = false;
                  break;
                }

                // case the last index
                int subOneLength = strListNote.length - 1;
                // print(indexNoteOne.toString() +
                //     " equal to " +
                //     subOneLength.toString());
                if (indexNoteOne == subOneLength && breakWOR == false) {
                  print("OK -1 2 " + strListNote[indexNoteOne]);
                  strListNote.removeAt(indexNoteOne);
                  numberOnDeck = false;
                  break;
                } else if (indexNoteOne == subOneLength && breakWOR == true) {
                  print("OK -1 2 " + strListNote[indexNoteOne]);
                  numberOnDeck = false;
                  break;
                }

                if (((!strListNote[indexNoteOne - 1].contains('-') &&
                    strListNote[indexNoteOne - 1]
                        .startsWith(new RegExp(r'[0-9]')) &&
                    int.parse(strListNote[indexNoteOne - 1]) <= 11) &&
                    (!strListNote[indexNoteOne + 1].contains('-') &&
                        strListNote[indexNoteOne + 1]
                            .startsWith(new RegExp(r'[0-9]')) &&
                        int.parse(strListNote[indexNoteOne + 1]) < 9)) ||
                    ((allCaseViDOWNote
                        .contains("" + strListNote[indexNoteOne - 1])) &&
                        (!strListNote[indexNoteOne - 1].contains('-') &&
                            strListNote[indexNoteOne - 1]
                                .startsWith(new RegExp(r'[0-9]')) &&
                            int.parse(strListNote[indexNoteOne - 1]) <= 11))) {
                  // do nothing
                } else {
                  // print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<");
                  if (strListNote[indexNoteOne].startsWith(
                      new RegExp(r'[0-9]'))) {
                    // do nothing
                  } else {
                    if (breakWOR == false ||
                        strListNote[indexNoteOne].length >= 16) {
                      // handle case NN014
                      if (strListNote.length - indexNoteOne == 4 && countRegex('-', strListNote[indexNoteOne]) >= 8 &&
                          strListNote[indexNoteOne].length < 25) {
                        print('Case like NN014 - haha lol <3 <3 !!!!! ');
                        print("OK -1 3 " + strListNote[indexNoteOne]);
                        numberOnDeck = false;
                        break;
                      } else {
                        print("OK -1 3 " + strListNote[indexNoteOne]);
                        strListNote.removeAt(indexNoteOne);
                        numberOnDeck = false;
                        break;
                      }
                    } else {
                      print("OK -1 3 " + strListNote[indexNoteOne]);
                      numberOnDeck = false;
                      break;
                    }
                  }
                }
              } else if (strListNote.length - indexNoteOne == 4 &&
              strListNote[indexNoteOne].length >= 25){
                print("OK -1 4 " + strListNote[indexNoteOne]);
                strListNote.removeAt(indexNoteOne);
                numberOnDeck = false;
                break;
              }
            }


            // check to cases of number that greater than 11, except the case double
            // start shift one
            if (strListNote[indexNoteOne].contains('-') ||
                (strListNote[indexNoteOne].length > 19 &&
                    strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
                    !strListNote[indexNoteOne].contains('-'))) {
              // do nothing

            } else {
              if (!allCaseTHNote.contains("" + strListNote[indexNoteOne]) &&
                  (strListNote[indexNoteOne]
                      .startsWith(new RegExp(r'[0-9]')) &&
                      int.parse(strListNote[indexNoteOne]) <= 11) ||
                  allCaseViDOWNote.contains("" + strListNote[indexNoteOne])) {
                if (strListNote[indexNoteOne].startsWith(
                    new RegExp(r'[0-9]')) &&
                    int.parse(strListNote[indexNoteOne]) <= 11) {
                  numberOnDeck = true;
                  print("OK numberOnDeck " + strListNote[indexNoteOne]);
                } else {
                  numberOnDeck = false;
                }

                // note that include the case that double start shift is 1
                // handle the case that double start shift is 1
                if (allCaseViDOWNote
                    .contains("" + strListNote[indexNoteOne - 1]) &&
                    strListNote[indexNoteOne + 1] == '1' &&
                    strListNote[indexNoteOne] == '11') {
                  print("OK 2 " + strListNote[indexNoteOne]);
                  numberOnDeck = false;
                  strListNote.removeAt(indexNoteOne);
                } else {
                  // print((allCaseViDOWNote
                  //     .contains("" + strListNote[indexNoteOne - 1]) ||
                  //     strListNote[indexNoteOne - 1]
                  //         .startsWith(new RegExp(' ')) &&
                  //         (strListNote[indexNoteOne - 1].contains(" ")) &&
                  //         (strListNote[indexNoteOne - 1]
                  //             .contains(RegExp(r"[A-Za-z]+"))))
                  //     .toString() +
                  //     ">>>>>>>>>>>>" +
                  //     (allCaseViDOWNote
                  //         .contains("" + strListNote[indexNoteOne + 1]) ||
                  //         (strListNote[indexNoteOne + 1]
                  //             .startsWith(new RegExp(r'[0-9]'))))
                  //         .toString());
                  //check whether or not Viet DOW to increase countDate
                  if ((allCaseViDOWNote
                      .contains("" + strListNote[indexNoteOne - 1]) ||
                      strListNote[indexNoteOne - 1]
                          .startsWith(new RegExp(' ')) &&
                          (strListNote[indexNoteOne - 1].contains(" ")) &&
                          (strListNote[indexNoteOne - 1]
                              .contains(RegExp(r"[A-Za-z]+")))) &&
                      (allCaseViDOWNote
                          .contains("" + strListNote[indexNoteOne + 1]) ||
                          (strListNote[indexNoteOne + 1]
                              .startsWith(new RegExp(r'[0-9]')))) &&
                      allCaseViDOWNote.contains(
                          "" + strListNote[indexNoteOne])) {
                    countDate++;
                    dowOnDeck = true;
                    countDateStringList.add(strListNote[indexNoteOne]);
                    print("COUNT DATE: " + countDate.toString());
                    print("\$\$\$\$\$\$\$\$\$\$\$\$\$\$\$\$" +
                        strListNote[indexNoteOne]);

                    // check condition to break without removing
                    if (countDate >= 4) {
                      breakWOR = true;
                      print("DAMN");
                    } else {
                      breakWOR = false;
                      print("DAMN " + countDate.toString());
                    }
                  }

                  print("OK pass 2 " + strListNote[indexNoteOne]);
                  if (indexNoteOne < strListNote.length - 1) {
                    indexNoteOne++;
                  }
                }
                //todo - check the case that double start shift is 1

              } else {
                // handle case that week spot in here
                if( !strListNote[indexNoteOne].contains('-') &&
                    strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                    int.parse(strListNote[indexNoteOne]) >= 100){
                  // week
                  print("OK pass 2" + strListNote[indexNoteOne]);

                } else {
                  print("OK 2 " + strListNote[indexNoteOne]);
                  numberOnDeck = false;
                  strListNote.removeAt(indexNoteOne);
                }
              }
            }

            if (dowOnDeck == true && numberOnDeck == true && strListNote[indexNoteOne].length >= 8 &&
                strListNote[indexNoteOne].length <= 19) {
              if ((!strListNote[indexNoteOne].contains(
                  new RegExp(r'[a-zA-Z]')) &&
                  strListNote[indexNoteOne].contains('-')) ||
                  (strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                      int.parse(strListNote[indexNoteOne]) >= 100)) {
                print("damn hot >>>>>>>");
                strNotePlace += " ; ";
                indexNoteSWeek = indexNoteOne + 2;
              } else {
                print("wooo damn hot >>>>>>>");
                notePassToSchedule = true;
                strNotePlace += strListNote[indexNoteOne] + "; ";
              }
              indexNoteSWeek = indexNoteOne + 2;
            }
          }
//       print("OK " +
//           strListNote[indexNoteOne].toString() +
//           " " +
//           (strListNote[indexNoteOne].contains('-') &&
//                   indexNoteOne != indexNoteSWeek &&
//                   !strListNote[indexNoteOne].contains(new RegExp(r'[a-zA-Z]')))
//               .toString());

          // check case '1234' TKB
          if ((notePassToSchedule == true &&
              strListNote[indexNoteOne].contains(new RegExp(r'[-]+'))) ||
              (strListNote[indexNoteOne].length > 19 &&
                  strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
                  !strListNote[indexNoteOne].contains('-'))) {
            // do nothing
          } else if (notePassToSchedule == true &&
              strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
              int.parse(strListNote[indexNoteOne]) >= 100 &&
              int.parse(strListNote[indexNoteOne]) != 123123 &&
              int.parse(strListNote[indexNoteOne]) != 123123123 &&
              (!strListNote[indexNoteOne].contains('0123') &&
                  countRegex('123', strListNote[indexNoteOne]) < 2)) {
            print("OKKKKKKKKKKKKKKKKKKKKKKKK BRUH:  " +
                strListNote[indexNoteOne]);
            print("OK -1 1 " + strListNote[indexNoteOne]);
            numberOnDeck = false;
            break;
          }

          // check case indexNoteOne > strListNote.length
          if (indexNoteOne >= strListNote.length - 1) {
            break;
          }

          // filter string contain '-'
          if ((strListNote[indexNoteOne].length > 19 &&
              strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
              !strListNote[indexNoteOne].contains('-'))) {
            // do nothing
            print("OK case Special " + strListNote[indexNoteOne]);
            strListNote.removeAt(indexNoteOne);
          } else {
            if ((!strListNote[indexNoteOne].contains('-') &&
                strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                int.parse(strListNote[indexNoteOne]) >= 100) ||
                (strListNote[indexNoteOne].contains('-') &&
                    indexNoteOne != indexNoteSWeek &&
                    !strListNote[indexNoteOne]
                        .contains(new RegExp(r'[a-zA-Z]')))) {
              if (strListNote[indexNoteOne].contains('-') &&
                  indexNoteOne != indexNoteSWeek &&
                  !strListNote[indexNoteOne].contains(
                      new RegExp(r'[a-zA-Z]')) &&
                  breakWOR == false) {
                // case NN014 including case NN012 (course 2)
                if (strListNote.length - indexNoteOne == 4 &&
                    strListNote[indexNoteOne].length < 25) {
                  var strS = strListNote[indexNoteOne] +
                      strListNote[indexNoteOne + 1] +
                      strListNote[indexNoteOne + 2];
                  if (strS.length >= 42) {
                    print("OK -1 1 >> "
                        + strListNote[indexNoteOne] +
                        strListNote[indexNoteOne + 1] +
                        strListNote[indexNoteOne + 2]
                    );
                    numberOnDeck = false;
                    break;
                  } else {
                    print("OK -1 1 E" + strListNote[indexNoteOne]);
                    strListNote.removeAt(indexNoteOne);
                    numberOnDeck = false;
                    break;
                  }
                }
                // print("OK -1 1 " + strListNote[indexNoteOne]+" Note: "+indexNoteOne.toString()+", Others: "+(strListNote.length - indexNoteOne).toString()+", >> "
                // +strListNote[indexNoteOne]+strListNote[indexNoteOne+1]+strListNote[indexNoteOne+2]
                // );

              } else if (strListNote[indexNoteOne].contains('-') &&
                  indexNoteOne != indexNoteSWeek &&
                  !strListNote[indexNoteOne].contains(
                      new RegExp(r'[a-zA-Z]')) &&
                  breakWOR == true &&
                  strListNote[indexNoteOne].length < 16) {
                print("OK -1 1 F" + strListNote[indexNoteOne]);
                numberOnDeck = false;
                break;
              }

              // case the last index
              int subOneLength = strListNote.length - 1;
              // print(indexNoteOne.toString() +
              //     " equal to " +
              //     subOneLength.toString());
              if (indexNoteOne == subOneLength && breakWOR == false) {
                print("OK -1 2 " + strListNote[indexNoteOne]);
                strListNote.removeAt(indexNoteOne);
                numberOnDeck = false;
                break;
              } else if (indexNoteOne == subOneLength && breakWOR == true) {
                print("OK -1 2 " + strListNote[indexNoteOne]);
                numberOnDeck = false;
                break;
              }

              if (((!strListNote[indexNoteOne - 1].contains('-') &&
                  strListNote[indexNoteOne - 1]
                      .startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne - 1]) <= 11) &&
                  (!strListNote[indexNoteOne + 1].contains('-') &&
                      strListNote[indexNoteOne + 1]
                          .startsWith(new RegExp(r'[0-9]')) &&
                      int.parse(strListNote[indexNoteOne + 1]) < 9)) ||
                  ((allCaseViDOWNote
                      .contains("" + strListNote[indexNoteOne - 1])) &&
                      (!strListNote[indexNoteOne - 1].contains('-') &&
                          strListNote[indexNoteOne - 1]
                              .startsWith(new RegExp(r'[0-9]')) &&
                          int.parse(strListNote[indexNoteOne - 1]) <= 11))) {
                // do nothing
              } else {
                print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<");
                if (strListNote[indexNoteOne].startsWith(
                    new RegExp(r'[0-9]'))) {
                  // do nothing
                } else {
                  if (breakWOR == false ||
                      strListNote[indexNoteOne].length >= 16) {
                    // handle case NN014 including case NN012 (course 2)
                    if (strListNote.length - indexNoteOne == 4 && countRegex('-', strListNote[indexNoteOne]) >= 8 &&
                        strListNote[indexNoteOne].length < 25) {
                      print('Case like NN014 - haha lol <3 <3 !!!!! ');
                      print("OK -1 3 " + strListNote[indexNoteOne]);
                      numberOnDeck = false;
                      break;
                    } else {
                      print("OK -1 3 " + strListNote[indexNoteOne]);
                      strListNote.removeAt(indexNoteOne);
                      numberOnDeck = false;
                      break;
                    }
                  } else {
                    print("OK -1 3 " + strListNote[indexNoteOne]);
                    numberOnDeck = false;
                    break;
                  }
                }
              }
            }
          }


          // check to cases of number that greater than 11, except the case double
          // start shift one
          if ((strListNote[indexNoteOne].length > 19 &&
              strListNote[indexNoteOne].startsWith(RegExp(r'[0-9]')) &&
              !strListNote[indexNoteOne].contains('-'))) {
            // do nothing
            print("OK case Special " + strListNote[indexNoteOne]);
            strListNote.removeAt(indexNoteOne);
          } else {
            if (!allCaseTHNote.contains("" + strListNote[indexNoteOne]) &&
                (!strListNote[indexNoteOne].contains(new RegExp(r'[-]+')) &&
                    strListNote[indexNoteOne]
                        .startsWith(new RegExp(r'[0-9]')) &&
                    int.parse(strListNote[indexNoteOne]) <= 11) ||
                allCaseViDOWNote.contains("" + strListNote[indexNoteOne])) {
              if (strListNote[indexNoteOne].startsWith(
                  new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne]) <= 11) {
                numberOnDeck = true;
                print("OK numberOnDeck " + strListNote[indexNoteOne]);
              } else {
                numberOnDeck = false;
              }

              // handle the case that double start shift is 1
              if (allCaseViDOWNote.contains(
                  "" + strListNote[indexNoteOne - 1]) &&
                  strListNote[indexNoteOne + 1] == '1' &&
                  strListNote[indexNoteOne] == '11') {
                print("OK 3 " + strListNote[indexNoteOne]);
                numberOnDeck = false;
                strListNote.removeAt(indexNoteOne);
              } else {
                // print((allCaseViDOWNote
                //     .contains("" + strListNote[indexNoteOne - 1]) ||
                //     strListNote[indexNoteOne - 1]
                //         .startsWith(new RegExp(' ')) &&
                //         (strListNote[indexNoteOne - 1].contains(" ")) &&
                //         (strListNote[indexNoteOne - 1]
                //             .contains(RegExp(r"[A-Za-z]+"))))
                //     .toString() +
                //     ">>>>>>>>>>>>" +
                //     (allCaseViDOWNote
                //         .contains("" + strListNote[indexNoteOne + 1]) ||
                //         (strListNote[indexNoteOne + 1]
                //             .startsWith(new RegExp(r'[0-9]'))))
                //         .toString());
                //check whether or not Viet DOW to increase countDate
                if ((allCaseViDOWNote
                    .contains("" + strListNote[indexNoteOne - 1]) ||
                    strListNote[indexNoteOne - 1]
                        .startsWith(new RegExp(' ')) &&
                        (strListNote[indexNoteOne - 1].contains(" ")) &&
                        (strListNote[indexNoteOne - 1]
                            .contains(RegExp(r"[A-Za-z]+")))) &&
                    (allCaseViDOWNote
                        .contains("" + strListNote[indexNoteOne + 1]) ||
                        (strListNote[indexNoteOne + 1]
                            .startsWith(new RegExp(r'[0-9]')))) &&
                    allCaseViDOWNote.contains(
                        "" + strListNote[indexNoteOne])) {
                  countDate++;
                  dowOnDeck = true;
                  countDateStringList.add(strListNote[indexNoteOne]);
                  print("COUNT DATE: " + countDate.toString());
                  print("\$\$\$\$\$\$\$\$\$\$\$\$\$\$\$\$" +
                      strListNote[indexNoteOne]);

                  // check condition to break without removing
                  if (countDate >= 4) {
                    breakWOR = true;
                    print("DAMN");
                  } else {
                    breakWOR = false;
                    print("DAMN " + countDate.toString());
                  }
                }

                print("OK pass 3 " + strListNote[indexNoteOne]);
                if (indexNoteOne < strListNote.length - 1) {
                  indexNoteOne++;
                }
              }
              //todo - check the case that double start shift is 1

            } else {
              // handle case that week spot in here
              if( !strListNote[indexNoteOne].contains('-') &&
                  strListNote[indexNoteOne].startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(strListNote[indexNoteOne]) >= 100){
                // week
                print("OK pass 3" + strListNote[indexNoteOne]);

              } else {
                print("OK 3 " + strListNote[indexNoteOne]);
                numberOnDeck = false;
                strListNote.removeAt(indexNoteOne);
              }
            }
          }

          // print("OK 4 " + strListNote[indexNoteOne]);
          print("+========================================+ " +
              indexNoteOne.toString());
        }

          print("OK out");

          for (var q = 0; q < strListNote.length; q++) {
            strParseAllNote += strListNote[q] + "*";
          }
          // Fluttertoast.showToast(
          //     msg: 'DAMNNNN 4',
          //     toastLength: Toast.LENGTH_SHORT,
          //     gravity: ToastGravity.CENTER,
          //     timeInSecForIosWeb: 1,
          //     backgroundColor: Colors.grey,
          //     textColor: Colors.black,
          //     fontSize: 16.0);
          strParseAllNote =
              strParseAllNote.substring(0, strParseAllNote.length - 1) + "; ";
          // print(">>>> " + strParseAllNote + "\nPlaces:" + strNotePlace);
        }

        // todo
        // DEBUG VIA PRINTING
        // demoListTextCourseFilterOne +=
        //     strParseAllNote.substring(strParseAllNote.length - 400, strParseAllNote.length - 1) + "\nPlaces:" + strNotePlace;

        // Handle parse String to Object
        int noteIntLengthDateList = countDateStringList.length,
            noteCountDate = 0,
            countToStop = 0,
            noteCountLastIndexVietDOW = 0;
        for (var i = 0; i < strParseAllNote.length; i++) {
          List<String> courseListSplit = strParseAllNote.split('; ');

          // handle inside courseListSplit
          for (var j = 0; j < courseListSplit.length; j++) {
            List<String> allInforOfCourseStringList =
            courseListSplit[j].split('*');

            if (allInforOfCourseStringList.toString() == '[]') {
              continue;
            }

            // add the first element to CourseCode
            listCourseCode.add(allInforOfCourseStringList.elementAt(0));

            // add the second element to CourseName
            listCourseName.add(allInforOfCourseStringList.elementAt(1));

            // pass to element null number 2

            // add a bunch of elements to Viet_DOW list
            noteCountDate = 0;
            String vietDOWNote = '';
            for (var i = 3; i < allInforOfCourseStringList.length; i++) {
              if (noteCountDate > noteIntLengthDateList) {
                break;
              }
              noteCountDate++;
              if (allCaseViDOWNote
                  .contains("" + allInforOfCourseStringList.elementAt(i))) {
                // // case only one
                // if(noteIntLengthDateList == 1){
                //   vietDOWNote += allInforOfCourseStringList.elementAt(i);
                //   noteCountLastIndexVietDOW=i;
                // }

                vietDOWNote += allInforOfCourseStringList.elementAt(i) + "; ";
                noteCountLastIndexVietDOW = i;
              }
            }

            // confirm via logging
            // logger.d("DOW: "+vietDOWNote);
            // print("DOW: "+vietDOWNote);

            // add a bunch of elements to shift list
            String shiftStrNote = '';
            for (var i = noteCountLastIndexVietDOW + 1;
            i < allInforOfCourseStringList.length;
            i++) {
              // print((!allInforOfCourseStringList.elementAt(i).contains(new RegExp(r'[-]+'))).toString()
              //     +" > > "+(allInforOfCourseStringList.elementAt(i).startsWith(RegExp(r'[0-9]'))).toString()
              //     +" >*> ");
              // if(allInforOfCourseStringList.elementAt(i).startsWith(RegExp(r'[0-9]')) == true){
              //   print((int.parse(allInforOfCourseStringList.elementAt(i)) <= 11).toString());
              // }

              // check special case NN001 - course 3
              if(allInforOfCourseStringList
                  .elementAt(i) == 'special case NN001'){
                noteCountLastIndexVietDOW = i;
                  break;
              }

              if (!allInforOfCourseStringList
                  .elementAt(i)
                  .contains(new RegExp(r'[-]+')) &&
                  allInforOfCourseStringList
                      .elementAt(i)
                      .startsWith(new RegExp(r'[0-9]')) &&
                  int.parse(allInforOfCourseStringList.elementAt(i)) <= 11) {
                shiftStrNote += allInforOfCourseStringList.elementAt(i) + "; ";
                noteCountLastIndexVietDOW = i;
              } else {
                // case NN001 - Week: *1111-2-2*1*1*1*1*-2*
                // if (allInforOfCourseStringList
                //     .elementAt(i)
                //     .contains(new RegExp(r'[-]+'))) {
                //   // print("DAMN IRRRRRRRRRRRRRR");
                //   shiftStrNote += "; ";
                //   noteCountLastIndexVietDOW = i - 1;
                //   break;
                // }
              }
            }

            // confirm via logging
            // logger.d("Shift: "+shiftStrNote);
            // print("Shift: "+shiftStrNote);

            // add the others element to week list
            String weekStrNote = '';
            for (var i = noteCountLastIndexVietDOW+1;
            i < allInforOfCourseStringList.length;
            i++) {
              // case NN001 - Week: *1111-2-2*1*1*1*1*-2*
              if (countRegex('1', allInforOfCourseStringList.elementAt(i)) >=
                  3) {
                print("Case NN001 - Week: *1111-2-2*1*1*1*1*-2* : "+allInforOfCourseStringList.elementAt(i));
                noteCountLastIndexVietDOW = i+1;
                // print("DAMN IRRRRRRRRRRRRRR");
              } else {
                weekStrNote += allInforOfCourseStringList.elementAt(i) + "; ";
                noteCountLastIndexVietDOW = i;
              }

              // if(allInforOfCourseStringList.contains('-')
              //     || (allInforOfCourseStringList.elementAt(i).startsWith(new RegExp('r[0-9]'))
              //         && int.parse(allInforOfCourseStringList.elementAt(i)) >100)) {

              // }
            }

            // confirm via logging
            // logger.d("Week: "+weekStrNote);
            // print("Week: "+weekStrNote);

            // start to count
            countToStop++;

            // print to confirm
            if (countToStop < courseListSplit.length) {
              listDayOfWeek.add(vietDOWNote);
              listShift.add(shiftStrNote);
              listWeek.add(weekStrNote);
              // demoListTextCourseFilterOne += "  "+allInforOfCourseStringList.elementAt(0)+" - "+allInforOfCourseStringList.elementAt(1)+"\n";

            }
          }
        }

        // confirm all List
        int indexCaseOneRowDiv = 0;
        if (listShift.toString() == '[]' || listWeek.toString() == '[]') {
          // do nothing
        } else {
          // for(var i=0;i<listDayOfWeek.length;i++){
          //   demoListTextCourseFilterOne+=listDayOfWeek.elementAt(i)+" * \n";
          // }
          // for(var i=0;i<listShift.length;i++){
          //   demoListTextCourseFilterOne+=listShift.elementAt(i)+" * \n";
          // }
          // demoListTextCourseFilterOne+="--------\n";
          //   demoListTextCourseFilterOne+="\n----------------------------------\n";
          for (var i = 0; i < listWeek.length; i++) {
            if (listWeek.elementAt(i) == '; ') {
              indexCaseOneRowDiv = i;
              // logger.i('Index Case One Row: ' + i.toString());
            }
            demoListTextCourseFilterOne += listWeek.elementAt(i) + " * \n";
            // if(i>11){
            //   print("~~~~~~~~~~~~~~~~~~~~~~~~ "+ listWeek.elementAt(i) +"\n");
            // }
          }
        }

        // handle the last element of each course
        for (var i = 0; i < elementsAllBelow.length; i++) {
          var st = elementsAllBelow
              .elementAt(i)
              .text;
        }

        // List<String> courseInforStrListFilter = textRawHandled.split(", ");
        // List<String> allCaseTHNote = ['01', '02', '03', '04', '05', '06', '07', '08', '09'];
        // List<String> allCaseViDOWNote = ['Hai', 'Ba', 'Tư', 'Năm', 'Sáu', 'Bảy', 'CN'];
        // int indexNoteOne = 2, indexNotePlace = 0;
        // String strNotePlace = "", strParseAllNote = "";

        // for(var i = 0;i<courseInforStrListFilter.length;i++){
        //   List<String> strListNote = courseInforStrListFilter[i].split("*");
        //
        //   for(var q = 0;q<strListNote.length;q++) {
        //     // pass course code & course name at index 1 and 2
        //
        //     // check to cases like '01'
        //     if (allCaseTHNote.contains("" + strListNote[indexNoteOne])) {
        //       strListNote.removeAt(indexNoteOne);
        //     } else {
        //       indexNoteOne++;
        //     }
        //
        //     // check to cases like 'BaNamTu'
        //     if(allCaseViDOWNote.contains(""+strListNote[indexNoteOne])){
        //       // leave it just there, do nothing changes to strListNote
        //       indexNoteOne++;
        //     } else {
        //       strListNote.removeAt(indexNoteOne);
        //     }
        //
        //     // check to cases of number that greater than 11, except the case double
        //     // start shift one
        //     if(int.parse(strListNote[indexNoteOne])<=11){
        //       // note that include the case that double start shift is 1
        //       indexNoteOne++;
        //       //todo - check the case that double start shift is 1
        //
        //     } else {
        //       strListNote.removeAt(indexNoteOne);
        //     }
        //
        //     // check to cases of number that greater than 11, except the case double
        //     // start shift one
        //     if(int.parse(strListNote[indexNoteOne])<=11){
        //       // note that include the case that double start shift is 1
        //       indexNotePlace = ++indexNoteOne;
        //       //todo - check the case that double start shift is 1
        //     } else {
        //       strListNote.removeAt(indexNoteOne);
        //     }
        //
        //     // store the Place string which merge all
        //     strNotePlace = strListNote[indexNotePlace];
        //
        //     // remove the CBGD string which merge all
        //     strListNote.removeAt(indexNotePlace+1);
        //
        //     // remove the SWeek String which merge all
        //     strListNote.removeAt(indexNotePlace+2);
        //
        //   }
        //
        //   for(var q = 0;q<strListNote.length;q++) {
        //     strParseAllNote+=strListNote[q]+"*";
        //   }
        //
        //   strParseAllNote+= ", ";
        // }

        // demoListTextCourseFilterOne += strParseAllNote;

        // Find the quantity of "DSSV" words
        // int maxAppearanceQuantity = 0, checkC = 0;
        // String subStringCheck = "DSSV";
        //
        // for (var i = 0; i < listTextCourseFilter.length; i++) {
        //   checkC = 0;
        //   String st = listTextCourseFilter.elementAt(i);
        //   while ((checkC = st.indexOf(subStringCheck, checkC)) != -1) {
        //     maxAppearanceQuantity++;
        //     checkC += subStringCheck.length;
        //     if (checkC == st.length) {
        //       break;
        //     }
        //   }
        // }
        //
        // // Handle "DSSV" clone into double, triple, or etc.
        // int countChar = 0;
        // bool flagCheck = false;
        // String stringClone = "", theLastFourChar = "";
        // for (var i = 0; i < listTextCourseFilter.length; i++) {
        //   stringClone = listTextCourseFilter.elementAt(i);
        //   for (var e = 0; e < maxAppearanceQuantity; e++) {
        //     theLastFourChar =
        //         stringClone.substring(stringClone.length - 4, stringClone.length);
        //
        //     if (theLastFourChar == "DSSV") {
        //       String sb = stringClone;
        //       stringClone = sb.substring(0, sb.length - 5);
        //     } else {
        //       // check duplicate
        //       if (listTextCourseFilterOne.isEmpty &&
        //           countChar < maxAppearanceQuantity) {
        //         listTextCourseFilterOne.add(stringClone);
        //         flagCheck = false;
        //         countChar++;
        //       } else {
        //         for (var f = 0; f < listTextCourseFilterOne.length; f++) {
        //           if (stringClone == listTextCourseFilterOne.elementAt(f)) {
        //             // do nothing
        //
        //           } else {
        //             flagCheck = true;
        //           }
        //         }
        //         if (flagCheck == true &&
        //             countChar < maxAppearanceQuantity &&
        //             countChar == i) {
        //           listTextCourseFilterOne.add(stringClone);
        //           flagCheck = false;
        //           countChar++;
        //         }
        //       }
        //     }
        //   }
        // }
        //
        // // remove all CBGD from listTextCourseFilterOne, merge into listTextCourseFilterTwo
        // List<String> listTextCourseFilterTwo = List.empty(growable: true);
        // for (var q = 0; q < listTextCourseFilterOne.length; q++) {
        //   List<String> arrStringMain =
        //       listTextCourseFilterOne.elementAt(q).split('*');
        //   String storedStr = "", lastStr = "";
        //
        //   // remove all CBGD
        //   for (var w = arrStringMain.length - 1; w >= 0; w--) {
        //     if (patternCBGD.hasMatch(arrStringMain[w])) {
        //       storedStr = arrStringMain[w];
        //       break;
        //     }
        //   }
        //
        //   // merge into listTextCourseFilterTwo
        //   for (var r = 0; r < arrStringMain.length; r++) {
        //     if (arrStringMain[r] != storedStr) {
        //       lastStr += arrStringMain[r] + "*";
        //     }
        //   }
        //
        //   listTextCourseFilterTwo.add(lastStr);
        // }
        //
        // // List string of all courses
        // try {
        //   for (var t = 0; t < listTextCourseFilterTwo.length; t++) {
        //     if (listTextCourseFilterTwo.elementAt(t).contains("*0*")) {
        //       continue;
        //     }
        //     List<String> arrStringMain =
        //         listTextCourseFilterTwo.elementAt(t).split("*");
        //     int index = 0;
        //
        //     // handle Course Name
        //     String courseName = "";
        //     for (var y = 0; y < arrStringMain.length - 1; y++) {
        //       if (patternNumber.hasMatch(arrStringMain[y]) &&
        //           !patternOneDigit.hasMatch(arrStringMain[y])) {
        //         listCourseName
        //             .add(courseName.substring(0, courseName.length - 1));
        //         courseName = "";
        //         index = y;
        //         break;
        //       }
        //       courseName += arrStringMain[y];
        //     }
        //
        //     // handle dayOfWeek
        //     String daysOfWeek = "";
        //     bool findCheck = false;
        //     for (var u = index + 4; u < arrStringMain.length - 1; u++) {
        //       if (patternUnicodeChar.hasMatch(arrStringMain[u]) &&
        //           daysOfWeek == "") {
        //         daysOfWeek += arrStringMain[u];
        //         index = u + 1;
        //       } else {
        //         if (patternUnicodeChar.hasMatch(arrStringMain[u]) &&
        //             daysOfWeek != "") {
        //           daysOfWeek += ", " + arrStringMain[u];
        //           findCheck = true;
        //         }
        //
        //         if (findCheck && patternNumber.hasMatch(arrStringMain[u])) {
        //           index = u;
        //           break;
        //         }
        //       }
        //     }
        //
        //     if (daysOfWeek != "") {
        //       listDayOfWeek.add(daysOfWeek);
        //     }
        //
        //     // handle shifts
        //     String shifts = "";
        //     for (var o = index; o < arrStringMain.length - 1; o++) {
        //       if (patternNumber.hasMatch(arrStringMain[o]) && shifts == "") {
        //         shifts += arrStringMain[o];
        //       } else {
        //         if (patternNumber.hasMatch(arrStringMain[o]) && shifts != "") {
        //           shifts += ", " + arrStringMain[o];
        //         } else {
        //           index = o;
        //           break;
        //         }
        //       }
        //     }
        //     listShift.add(shifts);
        //
        //     // handle locations
        //     String locations = "";
        //     for (var p = index; p < arrStringMain.length - 1; p++) {
        //       if (patternText.hasMatch(arrStringMain[p]) && locations == "") {
        //         locations += arrStringMain[p];
        //       } else {
        //         if (patternText.hasMatch(arrStringMain[p]) && locations != "") {
        //           locations += ", " + arrStringMain[p];
        //         } else {
        //           index = p;
        //           break;
        //         }
        //       }
        //     }
        //     if (listLocation.length <= listDayOfWeek.length) {
        //       listLocation.add(locations);
        //     }
        //
        //     // handle weeks
        //     String weeks = "";
        //     for (var a = index; a < arrStringMain.length; a++) {
        //       if (patternDSSV.hasMatch(arrStringMain[a]) && weeks == "") {
        //         weeks += arrStringMain[a];
        //       } else {
        //         if (patternDSSV.hasMatch(arrStringMain[a]) && weeks != "") {
        //           weeks += ", " + arrStringMain[a];
        //         } else {
        //           continue;
        //         }
        //       }
        //     }
        //
        //     listWeek.add(weeks);
        //   }
        // } catch (_) {
        //   print(_);
        // }
        //
        // // Find final week
        // int finalIndexWeek = 0, subFinalIndexWeek = 0;
        // for (int s = 0; s < listWeek.length; s++) {
        //   String week = listWeek.elementAt(s);
        //   if (week.contains(",")) {
        //     List<String> weekList = week.split(", ");
        //     for (var d = 0; d < weekList.length; d++) {
        //       String weeek = weekList[d];
        //
        //       // check every char
        //       for (var g = 0; g < weeek.length; g++) {
        //         if (weeek[g] == '-') {
        //           // mean not
        //         } else {
        //           subFinalIndexWeek = g + 1;
        //           if (subFinalIndexWeek > finalIndexWeek) {
        //             finalIndexWeek = subFinalIndexWeek;
        //           }
        //         }
        //       }
        //     }
        //   } else {
        //     for (var h = 0; h < week.length; h++) {
        //       if (week[h] == '-') {
        //         // mean not
        //       } else {
        //         subFinalIndexWeek = h + 1;
        //
        //         if (subFinalIndexWeek > finalIndexWeek) {
        //           finalIndexWeek = subFinalIndexWeek;
        //         }
        //       }
        //     }
        //   }
        // }
        //
        // // 5. Add Study Year
        resStrList.add(currentStudyYear);
        //
        // // 6. Add elements demo
        resStrList.add(demoListTextCourseFilterOne);
        //
        // // 7. Add final week
        // resStrList.add(finalIndexWeek.toString());
        //
        // // 8. Add string list -> DayOfWeeks
        // var stDOW = "";
        // for (var c = 0; c < listDayOfWeek.length; c++) {
        //   stDOW += listDayOfWeek.elementAt(c) + " :-: ";
        // }
        // resStrList.add(stDOW);
        //
        // // 9. Add string list -> Shifts
        // var stShift = "";
        // for (var c = 0; c < listShift.length; c++) {
        //   stShift += listShift.elementAt(c) + " :-: ";
        // }
        // resStrList.add(stShift);
        //
        // // 10. Add string list -> Locations
        // var stLocation = "";
        // for (var c = 0; c < listLocation.length; c++) {
        //   stLocation += listLocation.elementAt(c) + " :-: ";
        // }
        // resStrList.add(stLocation);
        //
        // // 11. Add string list -> StudyWeek
        // var stSWeek = "";
        // for (var c = 0; c < listWeek.length; c++) {
        //   stSWeek += listWeek.elementAt(c) + " :-: ";
        // }
        // resStrList.add(stSWeek);
      // }
      // Confirm by logging
      logger.i(
          "Test: " + currentStudyWeek + ", " + startDateCSW + "-" + endDateCSW);
    } else {
      Fluttertoast.showToast(
          msg: 'WRONG INPUT CODE. ',
          toastLength: Toast.LENGTH_SHORT,
          gravity: ToastGravity.CENTER,
          timeInSecForIosWeb: 1,
          backgroundColor: Colors.grey,
          textColor: Colors.black,
          fontSize: 16.0);
    }

    await Future.delayed(Duration(seconds: 1));

    // Fluttertoast.showToast(
    //     msg: '...' + resStrList.toString().substring(5, 55),
    //     toastLength: Toast.LENGTH_LONG,
    //     gravity: ToastGravity.CENTER,
    //     timeInSecForIosWeb: 1,
    //     backgroundColor: Colors.grey,
    //     textColor: Colors.black,
    //     fontSize: 16.0);

    return resStrList;
  }
}
