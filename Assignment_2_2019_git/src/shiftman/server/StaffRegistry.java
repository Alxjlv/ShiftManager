package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public class StaffRegistry {
	
	private List<Staff> _staffRegistry = new ArrayList<Staff>();
	
	public StaffRegistry() {
		// TODO Auto-generated constructor stub
	}
	
	public void registerStaff(Staff person) throws Exception {
		if (alreadyPresent(person)) {
			throw new Exception("ERROR: Staff member already registered");
		} else {
			_staffRegistry.add(person);
		}
		
	}
	
	private boolean alreadyPresent(Staff person) {
		if(_staffRegistry.isEmpty()) {
			return false;
		} else {
			for (Staff element : _staffRegistry) {
				if (element.staffName().toLowerCase().equals(person.staffName().toLowerCase())) { //checking if registered, case insensitive
					return true;
				}
			}
			return false;
		}
	}
	
	public int numberOfStaff(){
		return _staffRegistry.size();
	}

}
