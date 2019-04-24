package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public class Staff {

	private String _familyName;
	private String _givenName;
	private List<Shift> _shiftsWorking = new ArrayList<Shift>();
	private List<Shift> _shiftsManaging = new ArrayList<Shift>();
	
	
	public Staff(String givenName, String familyName) {
		// TODO Auto-generated constructor stub
		_givenName = givenName;
		_familyName = familyName;
	}
	
	public boolean working() {
		if(_shiftsWorking.isEmpty()&&_shiftsManaging.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public String staffName() {
		return "" + _givenName + " " + _familyName;
	}
	
	//ADD METHOD OF ADDING SHIFTS THEY ARE ASSIGNED TO
	
	public String familyName() {//refactor to remove
		return _familyName;
	}
	
	public String givenName() {//refactor to remove
		return _givenName;
	}

}
