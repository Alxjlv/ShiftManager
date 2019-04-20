package shiftman.server;

import java.util.List;

public class Day {

	private String _day;
	private String[] _workingHours = new String[2];
	private ShiftRegistry _shifts = new ShiftRegistry();
	
	public Day(String day) throws Exception {
		// TODO Auto-generated constructor stub
		if(Week.valueOf(day)._dayOfWeek == day) {
			_day = day;
		}else {
			throw new Exception("ERROR: Incorrect day of the week");
		}
	}
	
	private enum Week {
		Monday("Monday"),Tuesday("Tuesday"),Wednesday("Wednesday"),Thursday("Thursday"),Friday("Friday"),Saturday("Saturday"),Sunday("Sunday");
		private final String _dayOfWeek;
		
		private Week(String day) {
			_dayOfWeek = day;
		}
	}
	
	public void setWorkingHours(String startTime,String endTime) { //need to check that it is a valid working hour period
		_workingHours[0] = startTime;
		_workingHours[1] = endTime;
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
	
}
