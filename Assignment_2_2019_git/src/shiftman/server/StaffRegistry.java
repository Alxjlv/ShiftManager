package shiftman.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * This wrapper class encapsulates the storage of Staff objects. There are internal validation methods which allow checking
 * if something can be stored within the internal Staff list, or retrieved.
 * As a staff registry, this serves to ensure duplicate staff cannot be registered, and to check if staff are already registered.
 * There is also internal sorting based on last name, and a display method to display the contents of the registry.
 * @author Alex Verkerk
 */
public class StaffRegistry{
	//An array list of staff with limited access from outside objects
	private List<Staff> _staffRegistry = new ArrayList<Staff>();
	
	public StaffRegistry() {
		//Default constructor, not used
	}
	/**
	 * This method checks if someone is already registered, and if not, adds them to the Staff list.
	 * @param person The person we want to check is able to be registered
	 * @throws UserErrorException is thrown if this person is already registered
	 */
	public void registerStaff(Staff person) throws UserErrorException {
		if (alreadyRegistered(person.staffName())) {
			throw new UserErrorException("ERROR: Staff member already registered");
		} else {
			_staffRegistry.add(person);//Adding to the registry
		}
		
	}
	/**
	 * This method checks if this staff member is registered. If there are no Staff stored in the list, then there won't be any staff registered
	 * However, if there are registered staff, then it will loop through each of these and compare the lower case versions of the staff members names
	 * to see if they are already in the registry
	 * @param name The name of the staff member
	 * @return True if they are already registered, false if they are not
	 */
	public boolean alreadyRegistered(String name) {
		if(_staffRegistry.isEmpty()) {
			return false;//If there is no one registered, then the person in question can't be registered
		} else {
			for (Staff person : _staffRegistry) {//Loops through each person in the registry
				if (person.staffName().toLowerCase().equals(name.toLowerCase())) { //checking if registered, case insensitive
					return true;
				}
			}
			return false;
		}
	}
	/**
	 * This method will first check if a staff member is registered, then will return a reference to that object, allowing
	 * that staff member to possibly be stored in other objects, such as StaffRegistries in other objects.
	 * @param name The name of the staff member we want to access
	 * @return The reference to the Staff object with the provided name
	 * @throws UserErrorException If the person is not registered, then an error message saying as much is returned.
	 */
	public Staff registeredMember(String name) throws UserErrorException {
		if(alreadyRegistered(name)){
			for(Staff person:_staffRegistry) {//finding the correct person
				if (person.staffName().toLowerCase().equals(name.toLowerCase())) {
					return person; //returning the reference
				}
			}
		}
		//If they aren't registered, then an exception is thrown
		throw new UserErrorException("ERROR: This person is not registered");
	}
	/**
	 * This method will cycle through every Staff member stored in this registry, and check if they are assigned to
	 * any shifts (either as a manager or worker). If they are unassigned, then they will be added to a list. If there
	 * are no such staff members (ie registry is empty, or no staff are unassigned)
	 * @return A list with all unassigned staff members. This may be empty.
	 */
	public List<String> unassignedStaff(){
		List<String> unassigned = new ArrayList<String>();
		if(_staffRegistry.size()==0) {
			return unassigned;//If there is nothing in the registry, returns an empty array
		}else {
			sort();//Sorts the array, then loops through all of the Staff members to determine who isn't assigned to a shift
			for(Staff person:_staffRegistry) {
				if(!person.working()) {//Adds the person if they aren't working
					unassigned.add(person.staffName());
				}
			}
		}
		return unassigned;
	}
	/**
	 * Simply lets other objects know how many Staff are stored within this Staff registry
	 * @return an integer of how many Staff objects are stored inside the registry
	 */
	public int numberOfStaff(){
		return _staffRegistry.size();
	}
	/**
	 * This method takes the contents of the registry and returns them as a list of strings representing
	 * all of the staff members within the registry in alphabetical order
	 * @return A list of staff names in the format "first name last name" ordered based on last name
	 */
	public List<String> convertToString(){
		List<String> registeredStaff = new ArrayList<String>();
		if (_staffRegistry.size() == 0) {
			return registeredStaff;//returns empty array if there are no staff registered
		}else {
			sort();//Sorts the array then loops through the contents and adds them to the list
			for(Staff person:_staffRegistry) {
				registeredStaff.add(person.staffName());
			}
			return registeredStaff;
		}
		
	}
	/**
	 * This method implements the compare method of Comparator, which is necessary for Collections.sort to sort
	 * the list of Staff. First the family name is compared, and then if the family name is the same, given name is 
	 * compared. Compare will assign a score to each name, allowing for sorting with collection.sort
	 */
	public void sort() {
		//Creating an anonymous inner class for comparator
		Collections.sort(_staffRegistry,new Comparator<Staff>(){
			public int compare(Staff person1,Staff person2) {
				//Scoring the names against each other while ignoring case
				int diff = person1.familyName().compareToIgnoreCase(person2.familyName());
				if (diff !=0) {
					return diff;
				}
				return person1.givenName().compareToIgnoreCase(person2.givenName());
			}
		});
	}

}
