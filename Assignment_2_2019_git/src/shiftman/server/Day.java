package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public enum Day {
	mon("Monday"),tue("Tuesday"),wed("Wednesday"),thu("Thursday"),fri("Friday"),sat("Saturday"),sun("Sunday");
	private String _day;
	private String[] _workingHours = new String[2];
	private List<String> _shifts = new ArrayList<String>();
	
	private Day(String day) {
		_day = day;
	}
	
	public String showDay() { //breaking encapsulation, need to change
		return _day;
	}
	
	public void addShift(String shift) { //change a bit so it stores shift objects
		_shifts.add(shift);
	}
	
	public List<String> giveShifts(){ //need to check if it's empty + make sure it's in the right format
		return _shifts;
	}
	
	public void setWorkingHours(String startTime,String endTime) { //need to check that it is a valid working hour period
		_workingHours[0] = startTime;
		_workingHours[1] = endTime;
	}

}
