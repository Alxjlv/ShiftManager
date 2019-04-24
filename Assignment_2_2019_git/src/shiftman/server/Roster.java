package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public class Roster {
	
	private String _shopName;
	private StaffRegistry _registeredStaff = new StaffRegistry();
	private List<Day> _week = new ArrayList<Day>();
	
	public Roster(String shopName) throws UserErrorException {
		_shopName = shopName;
		for(Week week:Week.values()) {
			Day day = new Day(week._dayOfWeek);
			_week.add(day);
		}
	}
	
	public List<Day> tempWeekGetter(){//TEMPORARY - try refactor to remove
		return _week;
	}
	
	public void addShift(String dayOfWeek, String startTime, String endTime, String minimumWorkers) throws UserErrorException {
		for(Day day:_week) {
			if(day.showDay() == dayOfWeek) {
				Shift shift = new Shift(dayOfWeek,startTime,endTime,minimumWorkers);
				day.addShift(shift);
			}
		}
	}
	
	public List<String> displayShifts(){
		List<String> shifts = new ArrayList<String>();
		for(Day day:_week) {
			shifts.addAll(day.giveShifts());
		}
		return shifts;
	}
	
	private enum Week {
		Monday("Monday"),Tuesday("Tuesday"),Wednesday("Wednesday"),Thursday("Thursday"),Friday("Friday"),Saturday("Saturday"),Sunday("Sunday");
		private final String _dayOfWeek;
		
		private Week(String day) {
			_dayOfWeek = day;
		}
	}
	
	public void registerStaff(Staff person) throws UserErrorException{
		_registeredStaff.registerStaff(person);
	}
	
	public String displayShopName() {
		return "This roster is for the shop " + _shopName;
	}
	
	public String assignStaff(String dayOfWeek, String startTime, String endTime, String givenName, String familyName, boolean isManager) {
		return null;
	}
	
	public void setWorkingHours(String dayOfWeek, String startTime, String endTime) throws UserErrorException{
		for(Day day:_week) {
			if(day.showDay().equals(dayOfWeek)) {
				day.setWorkingHours(startTime, endTime);
				return;
			}
		}
		throw new UserErrorException("Error: Day "+dayOfWeek+" is invalid");
	}
	
	public List<String> getRegisteredStaff(){
		return _registeredStaff.convertToString();
	}
}