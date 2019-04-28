package shiftman.server;

import java.util.ArrayList;
import java.util.List;
/**
 * The day class serves as the main storage for every shift & shift registry. With methods to check if the shift is within working
 * hours, making sure the shift is found within this day, and displaying the shifts stored within this day, the Day class is important to
 * keep information stored.
 * @author Alex Verkerk
 */
public class Day {

	private String _day;//The day of the week
	private ShiftRegistry _shifts = new ShiftRegistry();//The shifts for this day
	private TimeInterval _workingHours;//When the working hours are
	
	public Day(String day){
		_day = day;

	}
	/**
	 * Creates a new TimeInterval object to store the working hours and take care of comparisons for shifts
	 * @param startTime Start of the working hours
	 * @param endTime End of the working hours
	 * @throws UserErrorException Thrown if the time period is invalid
	 */
	public void setWorkingHours(String startTime,String endTime) throws UserErrorException {
		_workingHours = new TimeInterval(startTime,endTime);
	}
	/**
	 * Shows what day this is.
	 * @return the day
	 */
	public String showDay() {
		return _day;
	}
	/**
	 * Finds if a shift is stored in this day, and if it is, returns the reference, otherwise throws an exception
	 * @param time the time of the shift
	 * @return reference to the shift object
	 * @throws UserErrorException thrown if the shift isn't stored in here
	 */
	public Shift findShift(String time) throws UserErrorException {
		return _shifts.findShift(time);
	}
	/**
	 * Checks if a shift is within the working hours of the day, and if it is, allows it to be stored
	 * @param shift Passes in the reference to the shift that we want to store
	 * @throws UserErrorException If there are no working hours set, or if the shift is outside the working hours
	 */
	public void addShift(Shift shift) throws UserErrorException {
		if(_workingHours == null) {//if there are no working hours
			throw new UserErrorException("ERROR: No working hours have been set for "+_day);
		}else if(!shift.passTime().checkWithinInterval(_workingHours)) {//If it isn't within work hours
			throw new UserErrorException("ERROR: Shift is not within working hours: " + _workingHours.displayTime());
		}else {
			_shifts.addShift(shift);
		}
		
	}
	/**
	 * Serves as a way to display the condition of the shifts stored inside the Day shift registry. Again the condition
	 * is Unmanaged, Overstaffed, Understaffed (see classes with more extensive implementation of this)
	 * @param type the type of condition we want to display
	 * @return a list of shifts stored in this day that meet that condition
	 */
	public List<String> condition(String type){
		_shifts.sort();
		return _shifts.shiftCondition(type);
	}
	/**
	 * Gives a complete and detailed description of the shifts stored within this day. Firstly, it needs to check if
	 * there are actually shifts stored in here. Then it presents it in a suitably formatted manner
	 * @return a list with detailed descriptions of the shifts for this day, including the manager and workers for the shifts
	 */
	public List<String> giveShiftsDescription(){
		List<String> dayRoster = new ArrayList<String>();
		if((_shifts.numberOfShifts()==0)||(_workingHours == null)) {
			dayRoster.add("");//If there are no shifts or no working hours
			return dayRoster;
		}else {//Adds the day and working hours to the top of the list
			dayRoster.add(_day + " " + _workingHours.displayTime());
		}
		//adds all of the shifts to the list in their detailed form
		dayRoster.addAll(_shifts.convertToString(false));
		return dayRoster;
	}
}
