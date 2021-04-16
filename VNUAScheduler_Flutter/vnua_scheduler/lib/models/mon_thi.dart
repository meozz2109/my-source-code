class MonThi {
  String _tenMonThi, _toThi, _ghepThi, _soLuong, _ngayThi, _ngayThu, _phongThi, _thoiGian, _tietThi;

  String get tenMonThi => _tenMonThi;

  MonThi(this._tenMonThi, this._ghepThi, this._toThi, this._soLuong, this._ngayThi, this._ngayThu,
      this._phongThi, this._thoiGian, this._tietThi);

  get toThi => _toThi;

  get tietThi => _tietThi;

  get thoiGian => _thoiGian;

  get phongThi => _phongThi;

  get ngayThu => _ngayThu;

  get ngayThi => _ngayThi;

  get soLuong => _soLuong;

  get ghepThi => _ghepThi;

  @override
  String toString() {
    return "Tên môn thi: ${this._tenMonThi}\nGhép thi: ${this._ghepThi}"
        "\nTổ thi: ${this._toThi}\nSố lượng: ${this._soLuong}\n"
        "Ngày thi: ${this._ngayThi}\nNgày thứ: ${this._ngayThu}\n"
        "Phòng thi: ${this._phongThi}\nThời gian: ${this._thoiGian}\n"
        "Tiết thi: ${this._tietThi}";
  }
}