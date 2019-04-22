package shiftman.server;

public class Shift {

	private String _day;
	private Time _shiftTime;
	private Staff _manager;
	private int _minWorkers;
	private StaffRegistry _assignedWorkers = new StaffRegistry();//this should really be a staff registry - will allow checking of duplication
	
	public Shift(String day, String startTime, String endTime, String minWorkers) throws Exception { //has day, start time and end time - also manager and workers?
		// TODO Auto-generated constructor stub
		_day = day;
		_shiftTime = new Time(startTime,endTime);
		_minWorkers = Integer.parseInt(minWorkers);
	}
	
	public void addManager(Staff manager) throws Exception {
		if (_manager.equals(null)) {
			_manager = manager;
		} else {
			throw new Exception("ERROR: there is a manager already assigned to this shift");
		}
	}
	
	public void addWorker(Staff worker) throws Exception {//also check if they're already on the shift
		if (worker.staffName().equals(_manager.staffName())) {
			throw new Exception("ERROR: this worker is already assigned to this shift as a manager");
		} else {
			_assignedWorkers.registerStaff(worker);
		}
	}
	
	public String convertShiftToString() {
		return _day+"["+_shiftTime.displayTime()+"]";
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
