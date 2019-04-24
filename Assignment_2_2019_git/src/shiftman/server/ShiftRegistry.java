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
	
	public void addShift(Shift shift) throws UserErrorException{
		for(Shift pos : _shifts) {
			if(pos.passTime().checkOverlap(shift.passTime())){
				throw new UserErrorException("ERROR: Shift overlaps with an existing shift");
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
	
	public List<String> convertToString(){
		List<String> shifts = new ArrayList<String>();
		if (_shifts.size() == 0) {
			shifts.add("");
			return shifts;
		}else {
			sort();
			for(Shift shift:_shifts) {
				shifts.add(shift.convertShiftToString());
			}
			return shifts;
		}
	}

}
