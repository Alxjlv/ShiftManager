package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public class StaffRegistry {
	
	private List<Staff> _registry = new ArrayList<Staff>();
	
	public StaffRegistry() {
		// TODO Auto-generated constructor stub
	}
	
	public String registerStaff(Staff person) {
		if (!_registry.isEmpty()) {
			for (Staff element : _registry) {
				if (element.staffName().toLowerCase().equals(person.staffName().toLowerCase())) { //checking if registered, case insensitive
					return "Staff member already registered";
				}
			}
			_registry.add(person);
			return "Staff member registered";
		} else {
			_registry.add(person);
			return "Staff member registered";
		}
		
	}

}
