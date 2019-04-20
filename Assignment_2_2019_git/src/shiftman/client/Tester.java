package shiftman.client;

import shiftman.server.ShiftManServer;
import shiftman.server.Time;

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
		try {
			Time tester = new Time("08:00","09:00");
			tester.convertTime("08:00");
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*System.out.println(test.setWorkingHours("Monday", "8", "9"));
		System.out.println(test.registerStaff("Joah", "Noal"));
		System.out.println(test.registerStaff("joah", "noal"));
		System.out.println(test.addShift("Monday", "8", "9", "1"));
		System.out.println(test.getRosterForDay("Monday"));
		test.newRoster("Ebony sounds");
		System.out.println(test.getRosterForDay("Monday"));*/
		
	}

}
