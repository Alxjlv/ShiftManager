package shiftman.client;

//import java.util.ArrayList;
//import java.util.List;
import shiftman.server.Day;
import shiftman.server.ShiftManServer;
//import shiftman.server.Time;

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
		System.out.println(test.addShift("Monday", "08:00", "09:00", "12"));
		test.newRoster("jam city");
		test.registerStaff("John", "Beet");
		test.registerStaff("John", "Apple");
		test.registerStaff("Alex","Beet");
		test.registerStaff("James", "Apple");
		System.out.println(test.getRegisteredStaff());
		
		for(Day day:test.tempRosterGetter().tempWeekGetter()) {
			System.out.println(day.showDay());
		}
		
		System.out.println(test.setWorkingHours("Monday", "01:00", "23:00"));
		System.out.println(test.setWorkingHours("Tuesday", "01:00", "23:00"));
		System.out.println(test.addShift("Monday", "11:00", "12:00", "1"));
		System.out.println(test.addShift("Monday", "09:00", "10:00", "1"));
		System.out.println(test.addShift("Tuesday", "14:30", "18:30", "2"));
		System.out.println(test.tempRosterGetter().displayShifts());
		/*try {
			Time tester = new Time("08:00","09:00");
			tester.convertTime("08:00");
			System.out.println("Successful");
			for(int i=0;i<7;i++) {
				System.out.println(test.tempRosterGetter().tempWeekGetter().get(i).showDay());
			}
			tester.convertTime("24:00");
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*System.out.println(test.setWorkingHours("Monday", "8", "9"));
		System.out.println(test.registerStaff("Joah", "Noal"));
		System.out.println(test.registerStaff("joah", "noal"));
		System.out.println(test.addShift("Monday", "8", "9", "1"));
		System.out.println(test.getRosterForDay("Monday"));
		test.newRoster("Ebony sounds");
		System.out.println(test.getRosterForDay("Monday"));*/
		
	}

}
