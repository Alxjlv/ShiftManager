package shiftman.server;

import java.util.ArrayList;
import java.util.List;
/**
 * This class is responsible for storing details relevant to the staff member it will encompass
 * This includes concepts such as their name and which shifts they are working and managing.
 * It includes methods that allow checking of if they are working.
 * @author Alex Verkerk
 */
public class Staff {

	private String _familyName;
	private String _givenName;
	private ShiftRegistry _shiftsWorking = new ShiftRegistry();//Storing shifts they work
	private ShiftRegistry _shiftsManaging = new ShiftRegistry();//Storing shifts they manage
	
	/**
	 * @param givenName First name
	 * @param familyName Last name
	 */
	public Staff(String givenName, String familyName) {
		_givenName = givenName;
		_familyName = familyName;
	}
	/**
	 * Checks if they have any shifts stored in either their managing registry or working registry and returns the result
	 * @return true if they are working, false otherwise
	 */
	public boolean working() {
		if((_shiftsWorking.numberOfShifts() == 0)&&(_shiftsManaging.numberOfShifts()==0)) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * This displays the shifts a staff member is working or managing, in order of day then time.
	 * @param managing If true, returns shifts they are managing. Otherwise, returns shifts working
	 * @return a list of shifts, reliant on managing
	 */
	public List<String> shiftsForStaff(boolean managing){
		List<String> shifts = new ArrayList<String>();
		ShiftRegistry holder;
		if(managing) {
			holder = _shiftsManaging;
		}else {
			holder = _shiftsWorking;
		}
		if(holder.numberOfShifts()==0) {
			return shifts;
		}else {//Represents the shifts in string form
			shifts.add(_familyName + ", "+_givenName);
			shifts.addAll(holder.convertToString(true));//Simple form of shifts represented here
			return shifts;
		}
	}
	/**
	 * Gives a representation of the staff members name
	 * @return their name in the format "first name last name"
	 */
	public String staffName() {
		return _givenName + " " + _familyName;
	}
	/**
	 * This is used to add a shift to the shifts registry based on whether they are managing or working
	 * @param shift The shift to add
	 * @param managing true means they are managing, false means working
	 * @throws UserErrorException Can be thrown if the shift overlaps or is invalid
	 */
	public void onShift(Shift shift, boolean managing) throws UserErrorException {
		if(managing) {
			_shiftsManaging.addShift(shift);
		}else {
			_shiftsWorking.addShift(shift);
		}
	}
	/**
	 * @return their last name
	 */
	public String familyName() {
		return _familyName;
	}
	/**
	 * @return their first name
	 */
	public String givenName() {
		return _givenName;
	}

}
