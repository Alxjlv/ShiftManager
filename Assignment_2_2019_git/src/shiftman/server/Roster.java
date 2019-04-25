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
			if(day.giveShifts().get(0).equals("")) {
				break;
			}else {
				shifts.addAll(day.giveShifts());
			}
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
	
	public String assignStaff(String dayOfWeek, String startTime, String endTime, String givenName, String familyName, boolean isManager) throws UserErrorException{
		Staff staff = _registeredStaff.registeredMember(givenName+" "+familyName);//If the staff member isn't registered, an exception is thrown
		
		Day day = checkDay(dayOfWeek);//if the dayOfWeek is wrong, an exception is thrown
		
		Shift shift = day.findShift(startTime+"-"+endTime);//if the shift isn't an existing shift, then an exception will be thrown
		
		if(isManager) { //If they are managing the shift, then they are added as a manager to the shift, and have that shift added to them
			shift.addManager(staff);//this will throw an exception if there is already a manager
			staff.onShift(shift,true);//adding this shift to their personal shifts
		}else {
			shift.addWorker(staff);
			staff.onShift(shift,false);
		}
		return "";
	}
	
	public void setWorkingHours(String dayOfWeek, String startTime, String endTime) throws UserErrorException{
		Day dayOfTheWeek = checkDay(dayOfWeek);
		dayOfTheWeek.setWorkingHours(startTime, endTime);
	}
	
	public Day checkDay(String dayOfWeek) throws UserErrorException {
		for(Day day:_week) {
			if(day.showDay().equals(dayOfWeek)) {
				return day;
			}
		}
		throw new UserErrorException("Error: Day "+dayOfWeek+" is invalid");
	}
	
	public List<String> getRosterForStaff(String staffName,boolean managing) throws UserErrorException{
		Staff person = _registeredStaff.registeredMember(staffName);
		return person.shiftsForStaff(managing);
	}
	
	public List<String> getRegisteredStaff(){
		return _registeredStaff.convertToString();
	}
	
	public List<String> getUnassignedStaff(){
		return _registeredStaff.unassignedStaff();
	}
	
	public List<String> shiftCondition(String type){
		List<String> shiftCondition = new ArrayList<String>();
		for(Day day:_week) {
			if(day.condition(type).get(0).equals("")) {
				break;
			}else {
				shiftCondition.addAll(day.condition(type));
			}
		}
		return shiftCondition;
	}
	
}