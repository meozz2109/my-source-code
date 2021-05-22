import 'package:flutter/foundation.dart';

class MonHoc {
  String _maMonHoc, _tenMonHoc
        , _tietBatDau, _diaDiemHoc
        , _ngayThu, _thoiGianHoc
        , _ngayHoc, _thoiGianBD
        , _thoiGianKT, _chiTietSoTiet;

  MonHoc(this._maMonHoc, this._tenMonHoc, this._diaDiemHoc, this._ngayHoc, this._ngayThu, this._tietBatDau
      , this._thoiGianHoc, this._thoiGianBD, this._thoiGianKT, this._chiTietSoTiet);

  String get maMonHoc => _maMonHoc;

  get ngayHoc => _ngayHoc;

  set ngayHoc(value) {
    _ngayHoc = value;
  }

  get tenMonHoc => _tenMonHoc;

  get chiTietSoTiet => _chiTietSoTiet;

  set chiTietSoTiet(value) {
    _chiTietSoTiet = value;
  }

  get thoiGianKT => _thoiGianKT;

  set thoiGianKT(value) {
    _thoiGianKT = value;
  }

  get thoiGianBD => _thoiGianBD;

  set thoiGianBD(value) {
    _thoiGianBD = value;
  }

  get thoiGianHoc => _thoiGianHoc;

  set thoiGianHoc(value) {
    _thoiGianHoc = value;
  }

  get ngayThu => _ngayThu;

  set ngayThu(value) {
    _ngayThu = value;
  }

  get diaDiemHoc => _diaDiemHoc;

  get tietBatDau => _tietBatDau;

  set tietBatDau(value) {
    _tietBatDau = value;
  }

  @override
  bool operator ==(Object other) {
    if(other == null){
      return false;
    }
    return (other is MonHoc)
        && other._maMonHoc == _maMonHoc;
  }

  @override
  String toString() {
    return "Mã môn học: ${this._maMonHoc}\nTên môn học: ${this._tenMonHoc}"
        "\nNgày học: ${this._ngayHoc}\nNgày thứ: ${this._ngayThu}"
        "\nĐịa điểm: ${this._diaDiemHoc}\nThời gian: ${this._thoiGianHoc}";
  }

}