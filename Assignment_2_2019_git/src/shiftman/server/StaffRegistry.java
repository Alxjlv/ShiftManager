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
		if (alreadyRegistered(person.staffName())) {
			throw new UserErrorException("ERROR: Staff member already registered");
		} else {
			_staffRegistry.add(person);
		}
		
	}
	
	public boolean alreadyRegistered(String name) {
		if(_staffRegistry.isEmpty()) {
			return false;
		} else {
			for (Staff person : _staffRegistry) {
				if (person.staffName().toLowerCase().equals(name.toLowerCase())) { //checking if registered, case insensitive
					return true;
				}
			}
			return false;
		}
	}
	
	public Staff registeredMember(String name) throws UserErrorException {
		if(alreadyRegistered(name)){
			for(Staff person:_staffRegistry) {
				if (person.staffName().toLowerCase().equals(name.toLowerCase())) {
					return person;
				}
			}
		}
		throw new UserErrorException("ERROR: This person is not registered");
	}
	
	public List<String> unassignedStaff(){
		List<String> unassigned = new ArrayList<String>();
		if(_staffRegistry.size()==0) {
			unassigned.add("");
		}else {
			sort();
			for(Staff person:_staffRegistry) {
				if(!person.working()) {
					unassigned.add(person.staffName());
				}
			}
		}
		return unassigned;
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
