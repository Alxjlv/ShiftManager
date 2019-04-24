package shiftman.server;

import java.util.List;
/**
 * This class is the main class responsible for access of the rostering system. 
 * It stores references for other objects which also provide functionality for
 * the server.
 * @author Alex Verkerk
 *
 */
public class ShiftManServer implements ShiftMan{
	private Roster _roster;
	
	public ShiftManServer() {
		
	}
	
	public Roster tempRosterGetter() {//DELETE
		return _roster;
	}
	
	public String newRoster(String shopName) {
		try{
			_roster = new Roster(shopName);
			return "Roster created successfully";
		}catch(UserErrorException e){
			return e.getMessage();
		}
	}
	public String setWorkingHours(String dayOfWeek, String startTime, String endTime) {
		if(_roster != null) {
			try {
				_roster.setWorkingHours(dayOfWeek, startTime, endTime);
				
			}catch(UserErrorException e) {
				return e.getMessage();
			}
			return "Working hours set";
		}else {
			return "ERROR: A roster does not currently exist";
		}
	}
	public String addShift(String dayOfWeek, String startTime, String endTime, String minimumWorkers) {
		if(_roster != null) {
			try {
				_roster.addShift(dayOfWeek, startTime, endTime, minimumWorkers);
			}catch (UserErrorException e){
				return e.getMessage();
			}
			return "Shift added";
		}else {
			return "ERROR: A roster does not currently exist";
		}
	}
	public String registerStaff(String givenname, String familyName) {
		if(_roster != null) {
			Staff newStaff = new Staff(givenname,familyName);
			try {
			_roster.registerStaff(newStaff);
			}
			catch (UserErrorException e) {
				return e.getMessage();
			}
			return "Staff registered";
		}else {
			return "ERROR: A roster does not currently exist";
		}
	}
	public String assignStaff(String dayOfWeek, String startTime, String endTime, String givenName, String familyName, boolean isManager) {
		//need to have something check if the staff member they're trying to assign is registered
		if(_roster != null) {
			try{
				_roster.assignStaff(dayOfWeek, startTime, endTime, givenName, familyName, isManager);
			}catch(UserErrorException u){
				return u.getMessage();
			}
			return givenName + " " + familyName+" assigned";
		}else {
			return "ERROR: A roster does not currently exist";
		}
	}
	public List<String> getRegisteredStaff(){
		return _roster.getRegisteredStaff();
	}
	public List<String> getUnassignedStaff(){
		return _roster.getUnassignedStaff();
	}
	public List<String> shiftsWithoutManagers(){
		return null;
	}
	public List<String> understaffedShifts(){
		return null;
	}
	public List<String> overstaffedShifts(){
		return null;
	}
	public List<String> getRosterForDay(String dayOfWeek){
		return null;
	}
	public List<String> getRosterForWorker(String workerName){
		return null;
	}
	public List<String> getShiftsManagedBy(String managerName){
		return null;
	}
	public String reportRosterIssues() {
		return null;
	}
	public String displayRoster() {
		return null;
	}
}