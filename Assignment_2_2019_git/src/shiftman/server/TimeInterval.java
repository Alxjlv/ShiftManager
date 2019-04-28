package shiftman.server;
/**
 * TimeInterval as a class removes time validity checking and overlap checking from other classes where it would clutter the classes.
 * It is responsible for storing the time intervals of shifts and days, while also validating that the times given are not invalid, and don't
 * overlap etc. It also has capabilities of checking if another time interval is fully within it's own interval
 * @author Alex Verkerk
 */
public class TimeInterval {

	private String _startTime;
	private String _endTime;
	private int _start;//Integer representation of start time to make comparisons simpler
	private int _end;//Integer representation of end time to make comparisons simpler
	
	/**
	 * Initialises internal fields by performing validation and conversion immediately
	 * @param startTime The start of the time interval
	 * @param endTime the end of the time interval
	 * @throws UserErrorException Thrown if the time was supplied in the wrong format or if the time is invalid
	 */
	public TimeInterval(String startTime,String endTime) throws UserErrorException {
		_startTime = startTime;
		_endTime = endTime;
		_start = convertTime(_startTime);
		_end = convertTime(_endTime);
		if((_start == _end)||(_start>_end)) {//checks if the time is valid
			throw new UserErrorException("ERROR: Time interval "+ this.displayTime() +" is invalid");
		}
	}
	/**
	 * This method converts the start and end time strings into an integer representation, while also doing several validation
	 * and checking steps to make sure it is in the correct format
	 * @param time The time that is being converted
	 * @return an integer representation of the time
	 * @throws UserErrorException Thrown if the time was in the wrong format, or ended after midnight
	 */
	public int convertTime(String time) throws UserErrorException {
		if(time.matches("\\d\\d:\\d\\d")) {//Checking correct format
			int hours = Integer.parseInt(time.substring(0, 2));//cutting the correct parts of the string out
			int mins = Integer.parseInt(time.substring(3, 5));
			if(time.matches("([0-1]\\d|2[0-3]):[0-5]\\d")) {//checking that it's before midnight etc.
				return hours*100 + mins;
			}else {//after midnight etc.
				throw new UserErrorException("ERROR: Time " + time + " is invalid");
			}
		}else {//Incorrect format - doesn't match the regex
			throw new UserErrorException("ERROR: Time " + time + " is in the wrong format");
		}
	}
	/**
	 * Checks whether another TimeInterval overlaps with this one
	 * @param time2 the reference to the time interval we want to check overlap on
	 * @return true if there is overlap, false otherwise
	 */
	public boolean checkOverlap(TimeInterval time2) {
		if(_start > time2.showStartTime()) {
			if(_start <= time2.showEndTime()) {
				return true;
			}
		}else if(_start < time2.showStartTime()) {
			if(_end >= time2.showStartTime()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Checking if this time interval is completely within another time interval
	 * @param time2 the interval we want to check if this one is within
	 * @return true if it is within that second interval, false otherwise
	 */
	public boolean checkWithinInterval(TimeInterval time2) {
		if((_start >= time2.showStartTime())&&(_end <= time2.showEndTime())) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * @return the end of the time interval
	 */
	public int showEndTime() {
		return _end;
	}
	/**
	 * @return the start of the time interval
	 */
	public int showStartTime() {
		return _start;
	}
	/**
	 * Displays the time in a nice way
	 * @return time interval in the format "start-end"
	 */
	public String displayTime() {
		return _startTime + "-" + _endTime;
	}
}
