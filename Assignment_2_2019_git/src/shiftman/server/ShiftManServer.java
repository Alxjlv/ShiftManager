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
	//This stores the roster object when it is created
	private Roster _roster;
	
	/**
	 * Default constructor
	 */
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
			//Creating a new Staff object with the name given.
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
	 * @return a list of every shift without a manager on it. This can return a blank
	 * list if there are no shifts without managers
	 */
	public List<String> shiftsWithoutManagers(){
		if(_roster != null) {
			//Selecting one of the states of shiftCondition
			return _roster.shiftCondition("Unmanaged");
		}else {
			//If there is no roster, then create a list and populate it with an error
			List<String> empty = new ArrayList<String>();
			empty.add("ERROR: no roster has been created");
			return empty;
		}
	}
	/**
	 * Requests the shifts that are understaffed (as in there are fewer workers than the
	 * minimum workers parameter of the shift)
	 * @return a list of each shift without enough staff. If there aren't any understaffed
	 * shifts then the list will be empty
	 */
	public List<String> understaffedShifts(){
		if(_roster != null) {
			//Selecting one of the other states of shiftCondition
			return _roster.shiftCondition("Understaffed");
		}else {
			//If there is no roster, then create a list and populate it with an error
			List<String> empty = new ArrayList<String>();
			empty.add("ERROR: no roster has been created");
			return empty;
		}
	}
	/**
	 * Requests the shifts that are overstaffed (more workers than the minimum)
	 * @return a list of each shift with too many staff. If there aren't any overstaffed
	 * shifts then the list will be empty.
	 */
	public List<String> overstaffedShifts(){
		if(_roster != null) {
			//Selecting one of the other states of shiftCondition
			return _roster.shiftCondition("Overstaffed");
		}else {
			//If there is no roster, then create a list and populate it with an error
			List<String> empty = new ArrayList<String>();
			empty.add("ERROR: no roster has been created");
			return empty;

		}
	}
	/**
	 * Requests a suitably formatted roster for a specific day. This will be a list
	 * with the shop name, then working hours, then a list of shifts
	 * @param dayOfWeek The day of the week the roster is for
	 * @return A list of strings with shop name, working hours, and a list of shifts
	 * on that day. This list can be blank if there aren't any shifts on that day.
	 */
	public List<String> getRosterForDay(String dayOfWeek){
		List<String> empty = new ArrayList<String>();
		if(_roster != null) {
			try {
				//Calls the corresponding method in the Roster
				return _roster.getRosterForDay(dayOfWeek);
			}catch(UserErrorException u) {
				//Catching exceptions caused by the day of week being invalid
				empty.add(u.getMessage());
				return empty;
			}
		}else {
			//If a roster doesn't exist
			empty.add("ERROR: no roster has been created");
			return empty;
		}
	}
	/**
	 * Requests the shifts a worker is working on
	 * @param workerName The name of the worker in the format "first name last name"
	 * @return Returns a list of shifts that this person is working on
	 * If they aren't working any shifts, then the list will be blank. If the worker isn't
	 * registered, then an exception will be caught.
	 */
	public List<String> getRosterForWorker(String workerName){
		List<String> empty = new ArrayList<String>();
		if(_roster != null) {
			try {
				//call to getRosterForStaff, where false means it is for a worker
				return _roster.getRosterForStaff(workerName, false);
			}catch(UserErrorException u) {
				//This error is likely to occur due to the worker not being registered
				empty.add(u.getMessage());
				return empty;
			}
		}else {
			//If a roster doesn't exist
			empty.add("ERROR: no roster has been created");
			return empty;

		}
	}
	/**
	 * Requests all the shifts a specific worker is managing
	 * @param managerName The name of the manager in the format "first name last name"
	 * @return Returns a list of shifts that this person is managing
	 * If they aren't managing any shifts, then the list will be blank. If the manager isn't
	 * registered, then an exception will be caught.
	 */
	public List<String> getShiftsManagedBy(String managerName){
		List<String> empty = new ArrayList<String>();
		if(_roster != null) {
			try {
				//call to getRosterForStaff, where true means it is for a manager
				return _roster.getRosterForStaff(managerName, true);
			}catch(UserErrorException u) {
				//This error is likely to occur due to the manager not being registered
				empty.add(u.getMessage());
				return empty;
			}
		}else {
			//If a roster doesn't exist
			empty.add("ERROR: no roster has been created");
			return empty;

		}
	}
	/**
	 * Unimplemented, but this method was meant to @return the issues with the roster
	 */
	public String reportRosterIssues() {
		return null;
	}
	/**
	 * Partially implemented, this method gives a very basic printout of everything in the
	 * roster.
	 * @return returns a single string containing every shift and every registered staff member
	 */
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