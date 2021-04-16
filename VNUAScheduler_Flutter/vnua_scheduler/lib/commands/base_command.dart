

import 'package:flutter/material.dart';
import 'package:vnua_scheduler/models/app_model.dart';
import 'package:vnua_scheduler/models/mon_hoc.dart';
import 'package:vnua_scheduler/models/mon_thi.dart';
import 'package:vnua_scheduler/models/user_model.dart';
import 'package:provider/provider.dart';
import 'package:vnua_scheduler/services/user_service.dart';

BuildContext _mainContext;
// The commands will use this to access the Provided models and services.
void init(BuildContext c) => _mainContext = c;

// Provide quick lookup methods for all the top-level models and services. Keeps the Command code slightly cleaner.
class BaseCommand {
  // Models
  UserModel userModel = _mainContext.read();
  AppModel appModel = _mainContext.read();
  // Services
  UserService userService = _mainContext.read();

  void addMonHoc(MonHoc course) {
    // check duplicate via comparing courseID

    userModel.listMonHoc.add(course);
    userModel.notifyListeners();
  }

  void removeMonHoc(int index) {
    userModel.listMonHoc.removeAt(index);
    userModel.notifyListeners();
  }

  void addMonThi(MonThi fec) {
    // check duplicate via comparing fecID

    userModel.listMonThi.add(fec);
    userModel.notifyListeners();
  }

  void removeMonThi(int index) {
    userModel.listMonThi.removeAt(index);
    userModel.notifyListeners();
  }

  void setCurrentInputCode(String inputCode){
    appModel.currentUser = inputCode;
    appModel.notifyListeners();
  }

}
