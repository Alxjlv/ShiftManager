package shiftman.server;

//import java.util.List;

public enum Week {
	Monday("Monday"),Tuesday("Tuesday"),Wednesday("Wednesday"),Thursday("Thursday"),Friday("Friday"),Saturday("Saturday"),Sunday("Sunday");
	private String _day;
	
	private Week(String day) {
		_day = day;
	}
	
	public String showDay() {
		return _day;
	}
	
	

}
