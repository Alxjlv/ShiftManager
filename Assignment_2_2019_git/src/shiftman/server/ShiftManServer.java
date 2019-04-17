package shiftman.server;

import java.util.List;

public class ShiftManServer implements ShiftMan {

	public ShiftManServer() {
		// TODO Auto-generated constructor stub
		
	}
	
	public String newRoster(String shopName) {
		return null;
	}
	public String setWorkingHours(String dayOfWeek, String startTime, String endTime) {
		return null;
	}
	public String addShift(String dayOfWeek, String startTime, String endTime, String minimumWorkers) {
		return null;
	}
	public String registerStaff(String givenname, String familyName) {
		return null;
	}
	public String assignStaff(String dayOfWeek, String startTime, String endTime, String givenName, String familyName, boolean isManager) {
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
