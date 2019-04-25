package shiftman.server;

import java.util.ArrayList;
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
		if(_roster != null) {
			return _roster.getRegisteredStaff();
		}else {
			List<String> empty = new ArrayList<String>();
			empty.add("");
			return empty;
		}
	}
	public List<String> getUnassignedStaff(){
		if(_roster != null) {
			return _roster.getUnassignedStaff();
		}else {
			List<String> empty = new ArrayList<String>();
			empty.add("");
			return empty;
		}
	}
	public List<String> shiftsWithoutManagers(){
		if(_roster != null) {
			return _roster.shiftCondition("Manager");
		}else {
			List<String> empty = new ArrayList<String>();
			empty.add("");
			return empty;
		}
	}
	public List<String> understaffedShifts(){
		if(_roster != null) {
			return _roster.shiftCondition("Understaffed");
		}else {
			List<String> empty = new ArrayList<String>();
			empty.add("");
			return empty;
		}
	}
	public List<String> overstaffedShifts(){
		if(_roster != null) {
			return _roster.shiftCondition("Overstaffed");
		}else {
			List<String> empty = new ArrayList<String>();
			empty.add("");
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
			empty.add("");
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
			empty.add("");
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
			empty.add("");
			return empty;
		}
	}
	public String reportRosterIssues() {
		return null;
	}
	public String displayRoster() {
		return null;
	}
}