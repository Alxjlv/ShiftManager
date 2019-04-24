package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public class ShiftRegistry implements Sortable{

	private List<Shift> _shifts = new ArrayList<Shift>();
	
	public ShiftRegistry() {
		// TODO Auto-generated constructor stub
	}
	
	public void addShift(Shift shift){//Working hours is optional
		for(Shift pos : _shifts) {
			if(pos.passTime().checkOverlap(shift.passTime())){
				
			}
		}
		_shifts.add(shift);
	}
	
	public List<String> displayShifts() {
		List<String> shifts = new ArrayList<String>();
		for(int i = 0; i < _shifts.size();i++) {
			shifts.add(_shifts.get(i).convertShiftToString());
		}
		return shifts;
	}
	
	public int numberOfShifts(){
		return _shifts.size();
	}
	
	public void sort() {
		
	}
	
	public List<String> convertToString(){
		List<String> shifts = new ArrayList<String>();
		return shifts;
	}

}
