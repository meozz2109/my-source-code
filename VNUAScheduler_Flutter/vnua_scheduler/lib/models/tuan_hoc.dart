import 'package:vnua_scheduler/models/mon_hoc.dart';

class TuanHoc {
  String _maTuan, _ngayBatDau, _ngayKetThuc;
  List<MonHoc> _dsMonHoc;

  // first full constructor
  TuanHoc(this._maTuan, this._ngayBatDau, this._ngayKetThuc, this._dsMonHoc);

  // empty constructor redirecting
  TuanHoc.empty() : this("", "", "", []);

  String get maTuan => _maTuan;

  get ngayBatDau => _ngayBatDau;

  get ngayKetThuc => _ngayKetThuc;

  List<MonHoc> get dsMonHoc => _dsMonHoc;

  set maTuan(String value) {
    _maTuan = value;
  }

  set dsMonHoc(List<MonHoc> value) {
    _dsMonHoc = value;
  }

  @override
  String toString() {
    String dsMonHocTS = "";
    for(int i=0;i<_dsMonHoc.length;i++){
      dsMonHocTS += _dsMonHoc.elementAt(i).toString()+"\n";
    }
    return "Mã tuần: ${this._maTuan}\nNgày bắt đầu: ${this._ngayBatDau}"
        "\nNgày kết thúc: ${this._ngayKetThuc}\nDanh sách môn học: "
        +dsMonHocTS;
  }
}