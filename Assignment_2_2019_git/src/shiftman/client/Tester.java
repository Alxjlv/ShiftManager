package shiftman.client;


import shiftman.server.Day;
import shiftman.server.Shift;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Day.fri.showDay());
		Shift shift = new Shift(Day.fri.showDay(),"8am","10am","5");
		Day.fri.addShift(shift);
		System.out.println(Day.fri.giveShifts());
		System.out.println(Day.thu.giveShifts());
		String a = shift.convertShiftToString();
		System.out.println(a);
	}

}
