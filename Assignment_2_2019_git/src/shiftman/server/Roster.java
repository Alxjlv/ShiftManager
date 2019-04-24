package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public class Roster {
	
	private String _shopName;
	private StaffRegistry _registeredStaff = new StaffRegistry();
	private List<Day> _week = new ArrayList<Day>(7);
	private Day _day;
	
	public Roster(String shopName) throws UserErrorException {
		_shopName = shopName;
		for(Week element:Week.values()) {
			Day day = new Day(element._dayOfWeek);
			_week.add(day);
		}
	}
	
	public List<Day> tempWeekGetter(){//TEMPORARY
		return _week;
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
	
	public void setWorkingHours(String dayOfWeek, String startTime, String endTime) throws UserErrorException,RuntimeException {
		
		_day.setWorkingHours(startTime,endTime);
	}
	
	public List<String> getRegisteredStaff(){
		return _registeredStaff.convertToString();
	}
}