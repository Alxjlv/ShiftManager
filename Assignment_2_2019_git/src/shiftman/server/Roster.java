package shiftman.server;

import java.util.ArrayList;
import java.util.List;
/**
 * This class stores the majority of the functionality of the roster. It is the main class that implements the functionality for the
 * ShiftManServer class to fulfill the ShiftMan interface.
 * This class also stores the registry of registered staff, alongside a list storing each day of the week.
 * @author Alex Verkerk
 */
public class Roster {
	
	private String _shopName;
	private StaffRegistry _registeredStaff = new StaffRegistry();
	private List<Day> _week = new ArrayList<Day>();
	
	/**
	 * This constructor begins by assigning the shop name, then creating 7 day objects based on the enum Week (which allocates
	 * the appropriate string to each day).
	 * @param shopName is the name of the shop this roster is for.
	 */
	public Roster(String shopName){
		_shopName = shopName;
		for(Week week:Week.values()) {//cycling through each value of the enum Week to create days in the correct order
			Day day = new Day(week._dayOfWeek);
			_week.add(day);
		}
	}
	/**
	 * This method fulfills the functionality of adding shifts to specific days of the week.
	 * It cycles through each day until it finds the correct one, then tries to add the shift to the day using day.addShift(shift)
	 * @param dayOfWeek This is the day of the week the shift is for
	 * @param startTime The start time of the shift
	 * @param endTime  The end time of the shift
	 * @param minimumWorkers The minimum number of workers to staff the shift
	 * @throws UserErrorException This can be caused by the day being input incorrectly, the end & start times being invalid, or the
	 * shift overlapping with another already existing shift etc.
	 */
	public void addShift(String dayOfWeek, String startTime, String endTime, String minimumWorkers) throws UserErrorException {
		for(Day day:_week) {
			if(day.showDay() == dayOfWeek) {//Finding the correct day of the week
				//New shift object constructed
				Shift shift = new Shift(dayOfWeek,startTime,endTime,minimumWorkers);
				//Using internal validation within the Day class to check if it's valid to add this shift
				day.addShift(shift);
				return;
			}
		}
		throw new UserErrorException("ERROR: Day of the week not found");
	}
	/**
	 * This is the request to display the contents of the roster. Incomplete implementation used for displayRoster in ShiftManServer
	 * @return A string showing the entire rosters contents
	 * @throws UserErrorException This exception is unlikely to be thrown
	 */
	public String displayRoster() throws UserErrorException {
		String roster = new String();
		for(Day day:_week) {
			//Constructing one large string per day
			roster += " "+ getRosterForDay(day.showDay());
		}
		return roster;
	}
	/**
	 * This Enum stores a string value for each day of the week, and is used to validate construction of the days of the week
	 * in the correct order to facilitate sorting. Each state is used as storage of a string that cannot be changed.
	 */
	private enum Week {
		Monday("Monday"),Tuesday("Tuesday"),Wednesday("Wednesday"),Thursday("Thursday"),Friday("Friday"),Saturday("Saturday"),Sunday("Sunday");
		private final String _dayOfWeek;//Each state of this enum will have a final string for its day
		
		private Week(String day) {
			_dayOfWeek = day;
		}
	}
	/**
	 * This method is used to register staff members using the internal validation methods of the _registeredStaff
	 * StaffRegistry to make sure that they aren't already registered.
	 * @param person The person that we're trying to register
	 * @throws UserErrorException An exception likely thrown due to the user trying to register someone who is already
	 * registered
	 */
	public void registerStaff(Staff person) throws UserErrorException{
		_registeredStaff.registerStaff(person);
	}
	/**
	 * This method is responsible for checking several parameters to make sure the shift that a staff member is trying to be assigned to
	 * exists, while also making sure the day is right, and the shift exists. Once this is done, then the shift is added to the staff members 
	 * personal shift registry (managing or working) and they are added to the shifts staff registry.
	 * @param dayOfWeek The day of the week the shift is on
	 * @param startTime The start time of the shift
	 * @param endTime The end time of the shift
	 * @param givenName First name of the staff member
	 * @param familyName Last name of the staff member
	 * @param isManager Whether they are managing this shift or not
	 * @return a string with the status of the assignment. This will usually be "", or an exception is thrown before the final return statement can
	 * be reached
	 * @throws UserErrorException This will be thrown if the staff member isn't registered, the shift doesn't exist, or the day is invalid. 
	 * UserErrorExceptions will also be thrown if the staff member is already assigned as a manager etc.
	 */
	public String assignStaff(String dayOfWeek, String startTime, String endTime, String givenName, String familyName, boolean isManager) throws UserErrorException{
		Staff staff = _registeredStaff.registeredMember(givenName+" "+familyName);//If the staff member isn't registered, an exception is thrown
		
		Day day = checkDay(dayOfWeek);//if the dayOfWeek is wrong, an exception is thrown
		
		Shift shift = day.findShift(startTime+"-"+endTime);//if the shift isn't an existing shift, then an exception will be thrown
		
		if(isManager) { //If they are managing the shift, then they are added as a manager to the shift, and have that shift added to them
			shift.addManager(staff);//this will throw an exception if there is already a manager
			staff.onShift(shift,true);//adding this shift to their personal managed shifts
		}else {
			shift.addWorker(staff);//This will throw an exception if they are already managing the shift
			staff.onShift(shift,false);//adding the shift to their personal worked shifts
		}
		return "";//status if the assignment is correct
	}
	/**
	 * This method simply checks if the day of the week is valid, then passes the time into the day object retrieved
	 * using checkDay.
	 * @param dayOfWeek The day of the week we're setting working hours for
	 * @param startTime The start of the working hours
	 * @param endTime The end of the working hours
	 * @throws UserErrorException This can be thrown as a result of the day passed in not being valid, or
	 * the working hours being invalid
	 */
	public void setWorkingHours(String dayOfWeek, String startTime, String endTime) throws UserErrorException{
		Day dayOfTheWeek = checkDay(dayOfWeek);//Retrieves the day object with that name
		dayOfTheWeek.setWorkingHours(startTime, endTime);//Then passes in the time for the working hours
	}
	/**
	 * This method cycles through each day of the week, and checks if it has the same name as the given string.
	 * If it does, then the reference of that day is returned
	 * @param dayOfWeek The name of the day of the week that we're looking for
	 * @return The reference of the Day object sharing the same name as the string
	 * @throws UserErrorException This will be thrown if the loop completes without finding a match.
	 */
	public Day checkDay(String dayOfWeek) throws UserErrorException {
		for(Day day:_week) {
			if(day.showDay().equals(dayOfWeek)) {
				return day;
			}
		}
		throw new UserErrorException("Error: Day "+dayOfWeek+" is invalid");
	}
	/**
	 * This method will return the shifts a staff member is managing OR working. If they aren't working any shifts, 
	 * this will return nothing, similarly to if there aren't any registered staff.
	 * @param staffName The name of the staff member that we need to return the shifts for
	 * @param managing A boolean for if we need the shifts they are managing (true) or working (false)
	 * @return A list of the shifts they are working/managing
	 * @throws UserErrorException This is most likely due to the staff member not being registered.
	 */
	public List<String> getRosterForStaff(String staffName,boolean managing) throws UserErrorException{
		if (_registeredStaff.numberOfStaff()==0) {
			List<String> empty = new ArrayList<String>();
			return empty; //If there are no staff members registered then this will return nothing
		}
		Staff person = _registeredStaff.registeredMember(staffName);//retrieves the staff member reference from the StaffRegistry
		return person.shiftsForStaff(managing);//Retrieves the shifts in string form that the staff member works/manages
	}
	/**
	 * This invokes the internal method of the StaffRegistry to print out the staff it stores as strings
	 * @return A list of strings of the registered staff members
	 */
	public List<String> getRegisteredStaff(){
		if (_registeredStaff.numberOfStaff()==0) {
			List<String> empty = new ArrayList<String>();
			return empty;//If there are no staff members, returns an empty list
		}
		return _registeredStaff.convertToString();
	}
	/**
	 * This will take a day of the week, and will return a list in the correct format for everything important 
	 * on that day. It does this by cycling through until it finds the correct day of the week, then checks if the 
	 * description of that day is blank (in which case it returns an empty list), and will then add the shop name as
	 * the first entry, and then add all the descriptions of the shifts for that day.
	 * @param dayOfWeek The day of the week we want a roster for.
	 * @return A list with the shop name as the first entry, then working hours & day, then the shifts on that day
	 * @throws UserErrorException This exception will be thrown if the day of the week is invalid
	 */
	public List<String> getRosterForDay(String dayOfWeek) throws UserErrorException{
		List<String> dayRoster = new ArrayList<String>();
		for(Day day:_week) {
			if(day.showDay() == dayOfWeek) {
				if(day.giveShiftsDescription().get(0).equals("")) {
					return dayRoster;//returning an empty list if there aren't any shifts for the day
				}else {
					dayRoster.add(_shopName);//Adding the shop name as the first entry in the list 
					dayRoster.addAll(day.giveShiftsDescription());//getting a description of the shifts from the day object
				}
				return dayRoster;
			}
		}
		throw new UserErrorException("ERROR: Day " + dayOfWeek + " is invalid");
	}
	/**
	 * Checks if there are any registered staff, and if there are, then calls a method in the staff registry that will check
	 * whether staff are unassigned
	 * @return A list of the staff members who aren't assigned to any shifts as either manager or worker
	 */
	public List<String> getUnassignedStaff(){
		if (_registeredStaff.numberOfStaff()==0) {
			List<String> empty = new ArrayList<String>();
			return empty;//checking if there are any registered staff
		}
		return _registeredStaff.unassignedStaff();
	}
	/**
	 * Checks for one of three predefined conditions a shift can have, and returns all of them with that condition.
	 * Those conditions are "Unmanaged" - shifts without an assigned manager, "Overstaffed" - shifts with too many workers
	 * assigned, and "Understaffed" - shifts with too few workers assigned
	 * @param type One of three strings: "Unmanaged","Overstaffed","Understaffed"
	 * @return a list of strings corresponding to the shifts that meet the condition.
	 */
	public List<String> shiftCondition(String type){
		List<String> shiftCondition = new ArrayList<String>();
		for(Day day:_week) {
			if(day.condition(type).get(0).equals("")) {
				continue;//If the day doesn't have any shifts that meet the condition, go to the next day
			}else {
				shiftCondition.addAll(day.condition(type));//Add all shifts that meet the condition the the list.
			}
		}
		return shiftCondition;
	}
	
}