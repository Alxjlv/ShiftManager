package shiftman.server;

import java.util.List;

public class Day {

	private String _day;
	private ShiftRegistry _shifts = new ShiftRegistry();
	private Time _workingHours;
	
	public Day(String day) throws UserErrorException {
		// TODO Auto-generated constructor stub
		_day = day;

	}
	
	public void setWorkingHours(String startTime,String endTime) throws UserErrorException { //need to check that it is a valid working hour period
		_workingHours = new Time(startTime,endTime);
	}
	
	public String showDay() { //breaking encapsulation, need to change
		return _day;
	}
	
	public Shift findShift(String time) throws UserErrorException {
		return _shifts.findShift(time);
	}
	
	public void addShift(Shift shift) throws UserErrorException {//need extra validation - possibly in roster to check if the day is right
		if(_workingHours == null) {
			throw new UserErrorException("ERROR: No working hours have been set for "+_day);
		}else if(shift.passTime().checkWithinInterval(_workingHours)) {
			_shifts.addShift(shift);
		}else {
			throw new UserErrorException("ERROR: Shift is not within working hours: " + _workingHours.displayTime());
		}
		
	}
	
	public List<String> giveShifts(){ //need to check if it's empty + make sure it's in the right format
		return _shifts.convertToString();
	}
}
