package shiftman.server;

public class Shift {

	private String _day;
	private Time _shiftTime;
	private Staff _manager;
	private int _minWorkers;
	private StaffRegistry _assignedWorkers = new StaffRegistry();//this should really be a staff registry - will allow checking of duplication
	
	public Shift(String day, String startTime, String endTime, String minWorkers) throws UserErrorException { //has day, start time and end time - also manager and workers?
		// TODO Auto-generated constructor stub
		_day = day;
		_shiftTime = new Time(startTime,endTime);
		_minWorkers = Integer.parseInt(minWorkers);
	}
	
	//NEED CHECKING MECHANISM FOR IF IT IS WITHIN WORKING HOURS
	
	public void addManager(Staff manager) throws UserErrorException {
		if (_manager.equals(null)) {
			_manager = manager;
		} else {
			throw new UserErrorException("ERROR: "+_manager.staffName() +" is already assigned as manager to this shift");
		}
	}
	
	public void addWorker(Staff worker) throws UserErrorException {//also check if they're already on the shift
		if (worker.staffName().equals(_manager.staffName())) {
			throw new UserErrorException("ERROR: "+_manager.staffName()+" is already assigned to this shift as a manager");
		} else {
			_assignedWorkers.registerStaff(worker);
		}
	}
	
	public String convertShiftToString() {
		return _day+"["+_shiftTime.displayTime()+"]";
	}
	
	public String displayTime() {
		return _shiftTime.displayTime();
	}
	
	public Time passTime() {//try refactor to remove
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
		return"";
	}


}
