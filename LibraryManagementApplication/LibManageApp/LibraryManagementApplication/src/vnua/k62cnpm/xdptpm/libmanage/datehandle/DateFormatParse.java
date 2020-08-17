package vnua.k62cnpm.xdptpm.libmanage.datehandle;

public class DateFormatParse {
	public DateFormatParse() {

	}

	public String parseDate(String inputDate) {
		String monthInput = inputDate.substring(0, 3), month = "", date = "", year = "", rs = "";
		String[] monthArr = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
				"Dec" };
		switch (monthInput) {
		case "Jan":
			month = "01";
			break;
		case "Feb":
			month = "02";
			break;
		case "Mar":
			month = "03";
			break;
		case "Apr":
			month = "04";
			break;
		case "May":
			month = "05";
			break;
		case "Jun":
			month = "06";
			break;
		case "Jul":
			month = "07";
			break;
		case "Aug":
			month = "08";
			break;
		case "Sep":
			month = "09";
			break;
		case "Oct":
			month = "10";
			break;
		case "Nov":
			month = "11";
			break;
		case "Dec":
			month = "12";
			break;
		default:
			break;
		}
		date = inputDate.substring(3, 5).trim().toString();
		if (Integer.parseInt(date) < 10) {
			date = "0" + date;
		}
		year = inputDate.substring(7, inputDate.length());
		rs = year + "-" + month + "-" + date;
		
		return rs;
	}
}
