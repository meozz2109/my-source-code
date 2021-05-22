import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

/// The base class for the different types of items the list can contain.
abstract class ListItem {
  /// The title line to show in a list item.
  Widget buildTitle(BuildContext context);

  /// The subtitle line, if any, to show in a list item.
  // Widget buildSubtitle(BuildContext context);
}

/// A ListItem that contains data to display a heading.
class DateHeaderItem implements ListItem {
  final String dateOfWeek;
  final String studyDate;

  DateHeaderItem(this.dateOfWeek, this.studyDate);

  Widget buildTitle(BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text(
          dateOfWeek,
          style: GoogleFonts.markaziText(
            fontSize: 18,
            color: Colors.black,
          ),
        ),
        Text(
          studyDate,
          style: GoogleFonts.markaziText(
            fontSize: 18,
            color: Color(0xff7D5E3C),
          ),
        ),
    ]);
  }

  // Widget buildSubtitle(BuildContext context) => null;
}

/// A ListItem that contains data to display a message.
class MainContentItem implements ListItem {
  final String studyTime
    ,nameOfCourse
  ,detailPeriod
  ,studyPlace;

  MainContentItem(this.studyTime, this.nameOfCourse, this.detailPeriod, this.studyPlace);

  Widget buildTitle(BuildContext context) {
    return Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          // Color Bar

          // Column
          Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Row(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Image.asset(
                        "lib/assets/images/ic_clock_24.png",
                        fit: BoxFit.fitWidth,
                      ),
                      Text(
                        "Thời gian: ",
                        style: GoogleFonts.markaziText(
                          fontSize: 16,
                          color: Color(0xff00667C),
                        ),
                      ),
                      Text(
                        studyTime,
                        style: GoogleFonts.markaziText(
                          fontSize: 16,
                          color: Color(0xff00667C),
                        ),
                      ),
                      ]
                ),
                Row(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      SizedBox(width: 40,),
                      Text(
                        "Tiết ",
                        style: GoogleFonts.markaziText(
                          fontSize: 16,
                          color: Colors.black,
                        ),
                      ),
                      Text(
                        detailPeriod,
                        style: GoogleFonts.markaziText(
                          fontSize: 16,
                          color: Colors.black,
                        ),
                      ),
                      Text(
                        ": ",
                        style: GoogleFonts.markaziText(
                          fontSize: 16,
                          color: Colors.black,
                        ),
                      ),
                      Text(
                        nameOfCourse,
                        style: GoogleFonts.markaziText(
                          fontSize: 16,
                          color: Colors.black,
                        ),
                      ),
                    ]
                ),
                Row(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      SizedBox(width: 40,),
                      Text(
                        "Phòng: ",
                        style: GoogleFonts.markaziText(
                          fontSize: 16,
                          color: Color(0xffFA812F),
                        ),
                      ),
                      Text(
                        studyPlace,
                        style: GoogleFonts.markaziText(
                          fontSize: 16,
                          color: Color(0xffFA812F),
                        ),
                      ),
                    ]
                ),
                ]
          )
        ]
    );
  }

  // Widget buildSubtitle(BuildContext context) => null;
}
