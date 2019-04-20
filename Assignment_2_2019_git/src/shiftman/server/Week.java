package shiftman.server;

import java.util.List;

public enum Week {
	Monday("Monday"),Tuesday("Tuesday"),Wednesday("Wednesday"),Thursday("Thursday"),Friday("Friday"),Saturday("Saturday"),Sunday("Sunday");
	private String _day;
	private String[] _workingHours = new String[2];
	private ShiftRegistry _shifts = new ShiftRegistry();
	
	private Week(String day) {
		_day = day;
	}
	
	public String showDay() { //breaking encapsulation, need to change
		return _day;
	}
	
	public void addShift(Shift shift) {
		_shifts.addShift(shift);
	}
	
	public List<String> giveShifts(){ //need to check if it's empty + make sure it's in the right format
		return _shifts.displayShifts();
	}
	
	public void setWorkingHours(String startTime,String endTime) { //need to check that it is a valid working hour period
		_workingHours[0] = startTime;
		_workingHours[1] = endTime;
	}

}
