package shiftman.server;
/**
 * The Shift class is responsible for storing information related to shifts and the time intervals they take up. It contains methods which check whether 
 * staff can be assigned to the shift, what the condition of the shift is (eg overstaffed, understaffed, unmanaged), and to display the contents of the shift
 * @author Alex Verkerk
 */
public class Shift {

	private String _day;
	private TimeInterval _shiftTime;//TimeInterval object representing internal time
	private Staff _manager;//assigned manager
	private int _minWorkers;//minumum number of workers for the shift
	private StaffRegistry _assignedWorkers = new StaffRegistry();//Staff registry meaning duplicate staff cannot be assigned
	
	/**
	 * 
	 * @param day Day the shift takes place on
	 * @param startTime Start of the shift
	 * @param endTime End of the shift
	 * @param minWorkers Minimum workers
	 * @throws UserErrorException If Staff are already assigned to this shift
	 */
	public Shift(String day, String startTime, String endTime, String minWorkers) throws UserErrorException {
		_day = day;
		_shiftTime = new TimeInterval(startTime,endTime);
		_minWorkers = Integer.parseInt(minWorkers);
	}
	/**
	 * Checks to make sure there isn't already a manager, and then if there isn't, assigns that staff member as manager
	 * @param manager A reference to the Staff member the user wants to be manager for this shift
	 * @throws UserErrorException If the manager is already assigned as manager already.
	 */
	public void addManager(Staff manager) throws UserErrorException {
		if (!isManaged()) {
			_manager = manager;
		} else {
			throw new UserErrorException("ERROR: "+_manager.staffName() +" is already assigned as manager to this shift");
		}
	}
	/**
	 * Checks whether the worker is already the manager. If they aren't, then assigns them to this shift
	 * @param worker The worker we want to add to this shift
	 * @throws UserErrorException If they are already managing this shift
	 */
	public void addWorker(Staff worker) throws UserErrorException {//also check if they're already on the shift
		if(isManaged()) {
			if (worker.staffName().equals(_manager.staffName())) {
				throw new UserErrorException("ERROR: "+_manager.staffName()+" is already assigned to this shift as a manager");
			} else {
				_assignedWorkers.registerStaff(worker);
			}
		}else {
			_assignedWorkers.registerStaff(worker);
		}
	}
	/**
	 * Checks if there is a manager on the shift
	 * @return true if there is, false if there is no manager
	 */
	public boolean isManaged() {
		if(_manager == null) {
			return false;
		}else {
			return true;
		}
	}
	/**
	 * Provides a simple description of the shift in the form day[start-end]
	 * @return a simple shift description
	 */
	public String convertShiftToString() {
		return _day+"["+_shiftTime.displayTime()+"]";
	}
	/**
	 * Provides a detailed description of this shift, including the day and time of the shift.
	 * This description also includes the manager, and list of workers on the shift
	 * @return A list with a detailed description of the shift
	 */
	public String shiftDescription() {
		String description = convertShiftToString();
		if(isManaged()) {
			description += " Manager: " + _manager.familyName() +", "+_manager.givenName();
		}else {
			description += " [No manager assigned]";//If there is no manager
		}
		if(_assignedWorkers.numberOfStaff() > 0) {
			description += "[";
			for(String staff:_assignedWorkers.convertToString()) {//Listing all assigned workers
				description += staff+", ";
			}
			description = description.substring(0, description.length()-2);// cutting the last ", " off
			description += "]";
		}else {
			description += "[No workers assigned]";//If there are no workers
		}
		return description;
	}
	/**
	 * Displaying the time the shift takes place in
	 * @return a string representation of the time of the shift
	 */
	public String displayTime() {
		return _shiftTime.displayTime();
	}
	/**
	 * Passes the reference to the TimeInterval object, allowing other objects to interact with it
	 * @return reference to _shiftTime
	 */
	public TimeInterval passTime() {
		return _shiftTime;
	}
	/**
	 * Allows other methods to look at the day this shift takes place on
	 * @return the day the shift is on
	 */
	public String whichDay() {
		return _day;
	}
	/**
	 * Compares the number of assigned workers to the minimum workers for the shift
	 * @return a string saying how staffed the shift is.
	 */
	public String howStaffed() {
		if(_minWorkers<_assignedWorkers.numberOfStaff()) {
			return "Overstaffed";
		}else if(_minWorkers>_assignedWorkers.numberOfStaff()) {
			return "Understaffed";
		}
		return "";
	}


}
