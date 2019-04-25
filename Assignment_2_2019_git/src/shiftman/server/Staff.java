package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public class Staff {

	private String _familyName;
	private String _givenName;
	private ShiftRegistry _shiftsWorking = new ShiftRegistry();
	private ShiftRegistry _shiftsManaging = new ShiftRegistry();
	
	
	public Staff(String givenName, String familyName) {
		// TODO Auto-generated constructor stub
		_givenName = givenName;
		_familyName = familyName;
	}
	
	public boolean working() {
		if((_shiftsWorking.numberOfShifts() == 0)&&(_shiftsManaging.numberOfShifts()==0)/*_shiftsWorking.isEmpty()&&_shiftsManaging.isEmpty()*/) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<String> shiftsForStaff(boolean managing){ // need to sort
		List<String> shifts = new ArrayList<String>();
		ShiftRegistry holder;
		if(managing) {
			holder = _shiftsManaging;
		}else {
			holder = _shiftsWorking;
		}
		if(holder.numberOfShifts()==0) {
			shifts.add("");
			return shifts;
		}else {
			shifts.add(_familyName + ", "+_givenName);
			shifts.addAll(holder.convertToString(true));
			return shifts;
		}
	}
	
	public String staffName() {
		return _givenName + " " + _familyName;
	}
	
	public void onShift(Shift shift, boolean managing) throws UserErrorException {
		if(managing) {
			_shiftsManaging.addShift(shift);
		}else {
			_shiftsWorking.addShift(shift);
		}
	}

	//ADD METHOD OF ADDING SHIFTS THEY ARE ASSIGNED TO
	
	public String familyName() {//refactor to remove
		return _familyName;
	}
	
	public String givenName() {//refactor to remove
		return _givenName;
	}

}
