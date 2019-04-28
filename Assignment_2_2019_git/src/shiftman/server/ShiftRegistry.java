package shiftman.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShiftRegistry{

	private List<Shift> _shifts = new ArrayList<Shift>();
	
	public ShiftRegistry() {
		// TODO Auto-generated constructor stub
	}
	
	public Shift findShift(String time) throws UserErrorException {
		for(Shift pos:_shifts) {
			if(pos.displayTime().equals(time)){
				return pos;
			}
		}
		throw new UserErrorException("ERROR: This shift does not exist");
		
	}
	
	public List<String> shiftCondition(String type){ //type is not user controlled
		List<String> condition = new ArrayList<String>();
		if(_shifts.size()==0) {
			condition.add("");
			return condition;
		}
		for(Shift shift:_shifts) {				
			switch(type) {
			case "Unmanaged": {
				if(!shift.isManaged()) {
					condition.add(shift.convertShiftToString());
				}
				break;
			}
			case "Overstaffed": {
				if (shift.howStaffed().equals("Overstaffed")) {
					condition.add(shift.convertShiftToString());
				}
				break;
			}
			case "Understaffed": {
				if (shift.howStaffed().equals("Understaffed")) {
					condition.add(shift.convertShiftToString());
				}
				break;
			}
			}
		}
		if(condition.size()>0) {
			return condition;
		}else {
			condition.add("");
			return condition;
		}
		
	}
	
	public void addShift(Shift shift) throws UserErrorException{
		for(Shift pos : _shifts) {
			if(pos.whichDay().equals(shift.whichDay())) {
				if(pos.passTime().checkOverlap(shift.passTime())){
					throw new UserErrorException("ERROR: Shift overlaps with an existing shift");
				}
			}
		}
		_shifts.add(shift);
	}
	
	public int numberOfShifts(){
		return _shifts.size();
	}
	
	public void sort() {
		Collections.sort(_shifts,new Comparator<Shift>(){
			public int compare(Shift shift1,Shift shift2) {
				int diff = shift1.whichDay().compareToIgnoreCase(shift2.whichDay());
				if (diff !=0) {
					return diff;
				}
				return shift1.displayTime().compareToIgnoreCase(shift2.displayTime());
			}
		});
	}
	
	public List<String> convertToString(boolean simple){
		List<String> shifts = new ArrayList<String>();
		if (_shifts.size() == 0) {
			shifts.add("");
			return shifts;
		}else {
			sort();
			for(Shift shift:_shifts) {
				if(simple) {
					shifts.add(shift.convertShiftToString());
				}else {
					shifts.add(shift.shiftDescription());
				}
			}
			return shifts;
		}
	}

}
