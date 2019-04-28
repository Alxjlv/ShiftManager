package shiftman.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * This wrapper class encapsulates the storage of Shift objects. There are internal validation methods which allow checking
 * if a shift can be stored within the internal Shift list, or retrieved.
 * As a Shift registry, this serves to ensure duplicate or overlapping shifts cannot be stored.
 * There is also internal sorting based on day and time, and a display method to display the contents of the registry.
 * @author Alex Verkerk
 */
public class ShiftRegistry{
	//An array list of shifts with limited access from outside objects
	private List<Shift> _shifts = new ArrayList<Shift>();
	
	public ShiftRegistry() {
		//Default constructor, not used
	}
	/**
	 * This method searches for a specific shift based on the time of the shift. An exception is thrown if the registry
	 * doesn't contain that shift
	 * @param time The time of the shift in the form "startTime-endTime"
	 * @return the reference of the desired shift object, to be stored or used by other objects
	 * @throws UserErrorException An error saying that that shift doesn't exist
	 */
	public Shift findShift(String time) throws UserErrorException {
		for(Shift pos:_shifts) {//looping through all stored shifts
			if(pos.displayTime().equals(time)){
				return pos;
			}
		}
		throw new UserErrorException("ERROR: This shift does not exist");
		
	}
	/**
	 * Checks for one of three predefined conditions a shift can have, and returns all of them with that condition.
	 * Those conditions are "Unmanaged" - shifts without an assigned manager, "Overstaffed" - shifts with too many workers
	 * assigned, and "Understaffed" - shifts with too few workers assigned
	 * @param type One of three conditions: "Unmanaged", "Overstaffed", "Understaffed"
	 * @return A list of shifts that meet the condition that was input
	 */
	public List<String> shiftCondition(String type){ //type is not user controlled
		List<String> condition = new ArrayList<String>();
		if(_shifts.size()==0) {//checks if there are any shifts to begin with
			condition.add("");
			return condition;
		}
		//Loops through every stored shift, and checks if they meet a certain condition based on what type is
		for(Shift shift:_shifts) {				
			switch(type) {
			case "Unmanaged": {
				if(!shift.isManaged()) {//Checks if the shift is managed, then stores the shift in the list if it isn't
					condition.add(shift.convertShiftToString());
				}
				break;
			}
			case "Overstaffed": {
				if (shift.howStaffed().equals("Overstaffed")) {//Checks if it meets the criteria of being overstaffed
					condition.add(shift.convertShiftToString());//Stores the shift in the list
				}
				break;
			}
			case "Understaffed": {
				if (shift.howStaffed().equals("Understaffed")) {//Checks if it meets the criteria of being understaffed
					condition.add(shift.convertShiftToString());//Stores the shift in the list
				}
				break;
			}
			}
		}
		if(condition.size()>0) {
			return condition;
		}else {
			condition.add("");//if the list is empty, add a "" to allow other methods to detect what has happened
			return condition;
		}
		
	}
	/**
	 * This checks if the day of the shift trying to be added is the same as an existing shift, and if it is,
	 * check if it overlaps with that shift. If nothing is amiss, it will be added to the registry
	 * @param shift Is the reference of a shift we want to store
	 * @throws UserErrorException Thrown if the shofts overlap.
	 */
	public void addShift(Shift shift) throws UserErrorException{
		for(Shift pos : _shifts) {
			if(pos.whichDay().equals(shift.whichDay())) {//Checking if they're on the same day
				if(pos.passTime().checkOverlap(shift.passTime())){
					throw new UserErrorException("ERROR: Shift overlaps with an existing shift");
				}
			}
		}
		_shifts.add(shift);
	}
	/**
	 * Simply lets other objects know how many Shifts are stored within this Shift registry
	 * @return an integer of how many shift objects are stored inside the registry
	 */
	public int numberOfShifts(){
		return _shifts.size();
	}
	/**
	 * This method implements the compare method of Comparator, which is necessary for Collections.sort to sort
	 * the list of Shifts. First the day of the shift is compared, and then if the day is the same, shift time is 
	 * compared. Compare will assign a score to each shift, allowing for sorting with collection.sort
	 */
	public void sort() {
		//Creating an anonymous inner class for comparator
		Collections.sort(_shifts,new Comparator<Shift>(){
			public int compare(Shift shift1,Shift shift2) {
				//Comparing the days
				int diff = shift1.whichDay().compareTo(shift2.whichDay());
				if (diff !=0) {
					return diff;
				}
				//Comparing the times if the days are the same
				return shift1.displayTime().compareTo(shift2.displayTime());
			}
		});
	}
	/**
	 * This method takes the contents of the registry and returns them as a list of strings representing
	 * all of the shifts within the registry in day and time order.
	 * If the parameter simple is true, it will simply convert the shift into a string of the form day[start-end]
	 * Otherwise, if simple is false, then shiftDescription will also list the manager and workers on that shift
	 * @param simple If true, then will add simple shift descriptions, if false then will be a more complicated representation
	 * @return A list of shifts, either simple, or comprehensive based on simple
	 */
	public List<String> convertToString(boolean simple){
		List<String> shifts = new ArrayList<String>();
		if (_shifts.size() == 0) {//If there aren't any shifts, returns an empty list
			return shifts;
		}else {
			sort();//Sorts the array then loops through each shift and stores it in the list
			for(Shift shift:_shifts) {
				if(simple) {//Simpler representation
					shifts.add(shift.convertShiftToString());
				}else {//More complicated representation, with worker and manager details
					shifts.add(shift.shiftDescription());
				}
			}
			return shifts;
		}
	}

}
