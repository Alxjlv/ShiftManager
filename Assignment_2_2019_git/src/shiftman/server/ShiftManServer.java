package shiftman.server;

import java.util.ArrayList;
import java.util.List;
/**
 * This class is the main class responsible for access of the rostering system. 
 * It stores references for Roster objects created by the newRoster method.
 * ShiftManServer's main functionality is to return messages to the client.
 * These messages are mainly in the form of data the client has requested,
 * or confirmation that tasks have been completed. However, ShiftManServer
 * also handles errors that occur in the code, returning issues back to the
 * client.
 * @author Alex Verkerk
 */
public class ShiftManServer implements ShiftMan{
	private Roster _roster;
	
	public ShiftManServer() {
		
	}
	
	/**
	 * This is the request for creation of a new roster
	 * @param shopName The name of the shop the roster is for
	 * @return the status of the request for roster creation. Blank means successful 
	 */
	public String newRoster(String shopName) {
		//Creating a new roster object
		_roster = new Roster(shopName);
		return "";
	}
	/**
	 * This method is the request to set the working hours for a specific day of the week.
	 * @param dayOfWeek This is the day the client will be setting the work hours for
	 * This can return an exception due to the day given being invalid
	 * @param startTime The start time of the working hours
	 * @param endTime The end time of the working hours
	 * These two parameters can return exceptions related to invalid time, invalid format etc.
	 * @return the status of the request. It can return a blank string upon success, or a specific
	 * error message.
	 */
	public String setWorkingHours(String dayOfWeek, String startTime, String endTime) {
		if(_roster != null) {//Checking if the roster exists
			try {
				//A call to the method in the roster that fulfils this responsibility
				_roster.setWorkingHours(dayOfWeek, startTime, endTime);
			}catch(UserErrorException u) {
				return u.getMessage(); //Error related to invalid time period or format
			}
			return "";
		}else {
			return "ERROR: A roster does not currently exist";
		}
	}
	/**
	 * Request that a shift is added to the roster.
	 * @param dayOfWeek The day the shift is to be added to.
	 * @param startTime The time the shift will begin
	 * @param endTime The end of the shift
	 * @param minimumWorkers The minimum number of workers on the shift
	 * @return a blank string if successful, otherwise an error message. This
	 * error message could be due to the day of the week provided being invalid,
	 * the time interval being incorrect/wrong format, the shift overlapping etc.
	 */
	public String addShift(String dayOfWeek, String startTime, String endTime, String minimumWorkers) {
		if(_roster != null) {//Checking if the roster exists
			try {
				//A call to the method in the roster that fulfils this functionality
				_roster.addShift(dayOfWeek, startTime, endTime, minimumWorkers);
				return "";
			}catch (UserErrorException u){
				return u.getMessage(); //Error related to shift creation & assignment
			}
		}else {
			return "ERROR: A roster does not currently exist";
		}
	}
	/**
	 * Requests that a staff member is registered.
	 * @param givenname The first name of the staff member
	 * @param familyName The last name of the worker
	 * @return status of the request - blank if successful, otherwise
	 * an error message related to the roster or already being registered
	 */
	public String registerStaff(String givenname, String familyName) {
		if(_roster != null) {//Checking if the roster already exists
			Staff newStaff = new Staff(givenname,familyName);
			try {
				//A call to the method in the roster which does the same thing
				_roster.registerStaff(newStaff);
			}
			catch (UserErrorException u) {
				return u.getMessage(); //Related to already being registered
			}
			return "";
		}else {
			return "ERROR: A roster does not currently exist";
		}
	}
	/**
	 * Request that a specific staff member is assigned to a specific shift as 
	 * either a worker or manager
	 * @param dayOfWeek The day of the shift
	 * @param startTime The start time of the shift
	 * @param endTime The end time of the shift
	 * @param givenName The first name of the staff member
	 * @param familyName The last name of the staff member
	 * @param isManager Whether the staff member is managing the shift or not
	 * @return status of the request; blank if successful, error if not.
	 * The errors can be related to the staff member not being registered, the
	 * shift not being booked, the day of the week being invalid, that person already being 
	 * assigned to the shift in another role, or someone else in the manager role
	 */
	public String assignStaff(String dayOfWeek, String startTime, String endTime, String givenName, String familyName, boolean isManager) {
		if(_roster != null) {//Checking if the roster has been created
			try{
				_roster.assignStaff(dayOfWeek, startTime, endTime, givenName, familyName, isManager);
				return "";
			}catch(UserErrorException u){
				return u.getMessage(); //Error related to what might go wrong
			}
		}else {
			return "ERROR: A roster does not currently exist";
		}
	}
	/**
	 * Requests a list of every registered staff member
	 * @return A list with every registered staff member in order
	 * of last name then first name. This can return an error if there
	 * is no roster, or blank if there are no registered staff.
	 */
	public List<String> getRegisteredStaff(){
		if(_roster != null) {
			//Call the matching method inside roster
			return _roster.getRegisteredStaff();
		}else {
			//If there is no roster, then create a list and populate it with an error
			List<String> empty = new ArrayList<String>();
			empty.add("ERROR: no roster has been created");
			return empty;
		}
	}
	/**
	 * Requests every staff member who isn't working any shifts
	 * @return A list of strings containing every staff member who isn't working shifts
	 * This is ordered by last name then first name.
	 */
	public List<String> getUnassignedStaff(){
		if(_roster != null) {
			//Call to the same function inside roster
			return _roster.getUnassignedStaff();
		}else {
			//If there is no roster, then create a list and populate it with an error
			List<String> empty = new ArrayList<String>();
			empty.add("ERROR: no roster has been created");
			return empty;
		}
	}
	/**
	 * Requests the shifts which aren't currently managed/don't have a staff member 
	 * assigned as manager
	 * @return a list of every shift without a manager on it. this can return a blank
	 * list if there are no 
	 */
	public List<String> shiftsWithoutManagers(){
		if(_roster != null) {
			return _roster.shiftCondition("Managed");
		}else {
			List<String> empty = new ArrayList<String>();
			empty.add("ERROR: no roster has been created");
			return empty;
			
		}
	}
	public List<String> understaffedShifts(){
		if(_roster != null) {
			return _roster.shiftCondition("Understaffed");
		}else {
			List<String> empty = new ArrayList<String>();
			empty.add("ERROR: no roster has been created");
			return empty;
		}
	}
	public List<String> overstaffedShifts(){
		if(_roster != null) {
			return _roster.shiftCondition("Overstaffed");
		}else {
			List<String> empty = new ArrayList<String>();
			empty.add("ERROR: no roster has been created");
			return empty;

		}
	}
	public List<String> getRosterForDay(String dayOfWeek){
		List<String> empty = new ArrayList<String>();
		if(_roster != null) {
			try {
				return _roster.getRosterForDay(dayOfWeek);
			}catch(UserErrorException u) {
				empty.add(u.getMessage());
				return empty;
			}
		}else {
			empty.add("ERROR: no roster has been created");
			return empty;

		}
	}
	public List<String> getRosterForWorker(String workerName){
		List<String> empty = new ArrayList<String>();
		if(_roster != null) {
			try {
				return _roster.getRosterForStaff(workerName, false);
			}catch(UserErrorException u) {
				empty.add(u.getMessage());
				return empty;
			}
		}else {
			empty.add("ERROR: no roster has been created");
			return empty;

		}
	}
	public List<String> getShiftsManagedBy(String managerName){
		List<String> empty = new ArrayList<String>();
		if(_roster != null) {
			try {
				return _roster.getRosterForStaff(managerName, true);
			}catch(UserErrorException u) {
				empty.add(u.getMessage());
				return empty;
			}
		}else {
			empty.add("ERROR: no roster has been created");
			return empty;

		}
	}
	public String reportRosterIssues() {
		return null;
	}
	public String displayRoster() {
		String roster = "Roster is: ";
		try {
			roster += _roster.displayRoster();
			return roster;
		}catch(UserErrorException e) {
			return e.getMessage();
		}
	}
}