package shiftman.client;



import shiftman.server.Day;
import shiftman.server.Shift;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Day.Friday.showDay());
		Shift shift = new Shift(Day.Friday.showDay(),"8am","10am","5");
		Day.Friday.addShift(shift);
		System.out.println(Day.Friday.giveShifts());
		System.out.println(Day.Thursday.giveShifts());
		String a = shift.convertShiftToString();
		System.out.println(a);
	}

}
