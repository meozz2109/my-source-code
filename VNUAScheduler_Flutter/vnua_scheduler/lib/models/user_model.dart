

import 'package:flutter/foundation.dart';
import 'package:vnua_scheduler/models/mon_hoc.dart';
import 'package:vnua_scheduler/models/mon_thi.dart';

class UserModel extends ChangeNotifier {
  List<MonHoc> _listMonHoc = [];
  List<MonThi> _listMonThi = [];

  List<MonHoc> get listMonHoc => _listMonHoc;

  set listMonHoc(List<MonHoc> value) {
    _listMonHoc = value;
    notifyListeners();
  }

  List<MonThi> get listMonThi => _listMonThi;

  set listMonThi(List<MonThi> value) {
    _listMonThi = value;
    notifyListeners();
  }

// Eventually other stuff would go here, notifications, friends, draft posts, etc

}


