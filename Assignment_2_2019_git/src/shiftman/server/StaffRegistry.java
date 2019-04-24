package shiftman.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StaffRegistry{
	
	private List<Staff> _staffRegistry = new ArrayList<Staff>();
	
	public StaffRegistry() {
		// TODO Auto-generated constructor stub
	}
	
	public void registerStaff(Staff person) throws UserErrorException {
		if (alreadyRegistered(person)) {
			throw new UserErrorException("ERROR: Staff member already registered");
		} else {
			_staffRegistry.add(person);
		}
		
	}
	
	private boolean alreadyRegistered(Staff person) {
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
	
	public List<String> convertToString(){
		List<String> registeredStaff = new ArrayList<String>();
		if (_staffRegistry.size() == 0) {
			registeredStaff.add("");
			return registeredStaff;
		}else {
			sort();
			for(Staff person:_staffRegistry) {
				registeredStaff.add(person.staffName());
			}
			return registeredStaff;
		}
		
	}
	
	public void sort() {
		Collections.sort(_staffRegistry,new Comparator<Staff>(){
			public int compare(Staff person1,Staff person2) {
				int diff = person1.familyName().compareToIgnoreCase(person2.familyName());
				if (diff !=0) {
					return diff;
				}
				return person1.givenName().compareToIgnoreCase(person2.givenName());
			}
		});
	}

}
