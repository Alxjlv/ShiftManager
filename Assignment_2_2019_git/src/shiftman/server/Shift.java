package shiftman.server;

public class Shift {

	private String _day;
	private TimeInterval _shiftTime;
	private Staff _manager = null;
	private int _minWorkers;
	private StaffRegistry _assignedWorkers = new StaffRegistry();//this should really be a staff registry - will allow checking of duplication
	
	public Shift(String day, String startTime, String endTime, String minWorkers) throws UserErrorException { //has day, start time and end time - also manager and workers?
		// TODO Auto-generated constructor stub
		_day = day;
		_shiftTime = new TimeInterval(startTime,endTime);
		_minWorkers = Integer.parseInt(minWorkers);
	}
	
	//NEED CHECKING MECHANISM FOR IF IT IS WITHIN WORKING HOURS
	
	public void addManager(Staff manager) throws UserErrorException {
		if (!isManaged()) {
			_manager = manager;
		} else {
			throw new UserErrorException("ERROR: "+_manager.staffName() +" is already assigned as manager to this shift");
		}
	}
	
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
	
	public boolean isManaged() {
		if(_manager == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public String convertShiftToString() {
		return _day+"["+_shiftTime.displayTime()+"]";
	}
	
	public String shiftDescription() {
		String description = convertShiftToString();
		if(isManaged()) {
			description += " Manager: " + _manager.familyName() +", "+_manager.givenName();
		}else {
			description += " [No manager assigned]";
		}
		if(_assignedWorkers.numberOfStaff() > 0) {
			description += "[";
			for(String staff:_assignedWorkers.convertToString()) {
				description += staff+", ";
			}
			description = description.substring(0, description.length()-2);
			description += "]";
		}else {
			description += "[No workers assigned]";
		}
		return description;
	}
	
	public String displayTime() {
		return _shiftTime.displayTime();
	}
	
	public TimeInterval passTime() {//try refactor to remove
		return _shiftTime;
	}
	
	public String whichDay() {//try refactor to remove
		return _day;
	}
	
	public String howStaffed() {
		if(_minWorkers<_assignedWorkers.numberOfStaff()) {
			return "Overstaffed";
		}else if(_minWorkers>_assignedWorkers.numberOfStaff()) {
			return "Understaffed";
		}
		return "";
	}


}
