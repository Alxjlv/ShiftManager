package shiftman.client;



import shiftman.server.Day;
import shiftman.server.Roster;
import shiftman.server.Shift;
import shiftman.server.ShiftManServer;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*System.out.println(Day.Friday.showDay());
		Shift shift = new Shift(Day.Friday.showDay(),"8am","10am","5");
		Day.Friday.addShift(shift);
		System.out.println(Day.Friday.giveShifts());
		System.out.println(Day.Thursday.giveShifts());
		//String a = shift.convertShiftToString();
		//System.out.println(a);
		String dayOfTheWeek = "Monday";
		System.out.println(Day.valueOf(dayOfTheWeek).showDay());*/
		
		ShiftManServer test = new ShiftManServer();
		test.newRoster("jam city");
		System.out.println(test.setWorkingHours("Monday", "8", "9"));
		System.out.println(test.registerStaff("Joah", "Noal"));
		System.out.println(test.registerStaff("joah", "noal"));
	}

}
