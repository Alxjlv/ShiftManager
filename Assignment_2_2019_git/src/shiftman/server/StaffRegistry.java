package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public class StaffRegistry implements Sortable{
	
	private List<Staff> _staffRegistry = new ArrayList<Staff>();
	
	public StaffRegistry() {
		// TODO Auto-generated constructor stub
	}
	
	public void registerStaff(Staff person) throws UserErrorException {
		if (alreadyPresent(person)) {
			throw new UserErrorException("ERROR: Staff member already registered");
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
	

	public boolean checkRegistered(String staffName) {
		
		return false;
	}
	
	public List<String> convertToString(){
		List<String> registeredStaff = new ArrayList<String>();
		if (_staffRegistry.size() == 0) {
			return registeredStaff;
		}else {
			return registeredStaff;
		}
		
	}
	
	public void sort() {
		
	}

}
