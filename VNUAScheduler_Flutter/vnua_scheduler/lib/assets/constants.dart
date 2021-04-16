
// URLs
const NOR_SCHE_URL = "http://daotao.vnua.edu.vn/Default.aspx?page=thoikhoabieu&sta=1&id=";
const FIN_EXAM_SCHE_URL = "http://daotao.vnua.edu.vn/Default.aspx?page=xemlichthi&id=";
const SDH_FIN_EXAM_SCHE_URL = "http://daotao.vnua.edu.vn/Default.aspx?page=xemlichthigk&id=";

// Start & End Shift List
const START_SHIFT_ARRAY = ["7h00", "7h55", "8h50", "9h55", "10h55", "12h45",
"13h40", "14h35", "15h40", "16h35", "18h00", "18h55", "19h50" ];
const END_SHIFT_ARRAY = ["7h50", "8h45", "9h40", "10h45", "11h40", "13h35",
  "14h30", "15h25", "16h30", "17h25", "18h50", "19h45", "20h40"];

// Span IDs
const LESS_SPAN_ID = "ctl00_ContentPlaceHolder1_ctl00_gvXem_ctl0";
const GREATER_SPAN_ID = "ctl00_ContentPlaceHolder1_ctl00_gvXem_ctl";
const INFO_SPAN_ID = "ctl00_ContentPlaceHolder1_ctl00_lblContent";

// Hard fix: MSV - 651134, MGV - CNP02, CNP09, MHVCH - 29021042 (Thuy San), 29141036 (Moi Truong), 29081002 (Nong Hoc), 29031010 (CNSH), 29281023 (Chan Nuoi)

// Enums
enum WhyFarther { harder, smarter, selfStarter, tradingCharter }
