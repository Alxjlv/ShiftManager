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
	
	public String newRoster(String shopName) { // new shop object? - new Roster object
		_roster = new Roster(shopName);
		return "Roster created successfully";
	}
	public String setWorkingHours(String dayOfWeek, String startTime, String endTime) {
		try {
			_roster.setWorkingHours(dayOfWeek, startTime, endTime);
		}catch(RuntimeException r) {
			return("ERROR: Illegal day of the week. Check your spelling");
		}catch(Exception e) {
			return("ERROR: Working hours are incorrect");
		}
		return "";
	}
	public String addShift(String dayOfWeek, String startTime, String endTime, String minimumWorkers) {
		return null;
	}
	public String registerStaff(String givenname, String familyName) {
		Staff newStaff = new Staff(givenname,familyName);
		try {
		_roster.registerStaff(newStaff);
		}
		catch (Exception e) {
			return e.getMessage();
		}
		return "";
	}
	public String assignStaff(String dayOfWeek, String startTime, String endTime, String givenName, String familyName, boolean isManager) {
		//need to have something check if the staff member they're trying to assign is registered
		return null;
	}
	public List<String> getRegisteredStaff(){
		return null;
	}
	public List<String> getUnassignedStaff(){
		return null;
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