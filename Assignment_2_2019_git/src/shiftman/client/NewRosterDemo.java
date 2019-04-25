package shiftman.client;

import shiftman.server.ShiftMan;
import shiftman.server.ShiftManServer;

/**
 * This shows another use of the ShiftMap API, in particular showing the
 * java -cp softeng_251_test.jar;softeng251_a2_checker.jar shiftman.client.Checker
 * effect of {@link ShiftMan#newRoster(String)}.
 */
public class NewRosterDemo {
	public static void main(String[] args) {
		System.out.println(">>Starting new roster demo");
		ShiftMan scheduler = new ShiftManServer();
		System.out.println(">>TESTING: Accessing roster without creating a roster");
		System.out.println(">>Setting working hours without having created new roster");
		String status = scheduler.setWorkingHours("Monday", "09:00", "17:00");
		// Put status in {} so can tell when get empty string.
		System.out.println("\tGot status {" + status + "}");
		System.out.println(">>Create new roster 'eScooters R Us' (status of {} means no error)");
		status = scheduler.newRoster("eScooters R Us");
		System.out.println("\tGot status {" + status + "}");
	
		System.out.println(">>Set working hours for Monday to 09:00-17:00");
		status = scheduler.setWorkingHours("Monday", "09:00", "17:00");
		System.out.println("\tGot status {" + status + "}");
		
		System.out.println();
		System.out.println(">>TESTING: Attempting to set working hours on an invalid day");
		System.out.println(">>Set working hours for Monday to 09:00-17:00");
		status = scheduler.setWorkingHours("Wedday", "09:00", "17:00");
		System.out.println("\tGot status {" + status + "}");
		System.out.println();

		System.out.println(">>TESTING: Adding shifts & checking validity");
		System.out.println(">>Add shift 09:00-12:00 to Monday");
		status = scheduler.addShift("Monday", "09:00", "12:00", "0");
		System.out.println("\tGot status {" + status + "}");

		System.out.println(">>Add shift 12:01-13:00 to Monday with minimum 1 worker");
		status = scheduler.addShift("Monday", "12:01", "13:00", "1");
		System.out.println("\tGot status {" + status + "}");
		
		System.out.println(">>Add shift 12:30-15:00 to Monday with minimum 1 worker");
		status = scheduler.addShift("Monday", "12:30", "15:00", "1");
		System.out.println("\tGot status {" + status + "}");
		
		System.out.println(">>Add shift 13:00-14:00 to Monday with minimum 1 worker");
		status = scheduler.addShift("Monday", "13:00", "14:00", "1");
		System.out.println("\tGot status {" + status + "}");
		
		System.out.println(">>Add shift 14:00-19:00 to Monday with minimum 1 worker");
		status = scheduler.addShift("Monday", "14:00", "19:00", "1");
		System.out.println("\tGot status {" + status + "}");
		
		System.out.println(">>Add shift 16:00-15:00 to Monday with minimum 1 worker");
		status = scheduler.addShift("Monday", "16:00", "15:00", "1");
		System.out.println("\tGot status {" + status + "}");
		
		System.out.println(">>Add shift 14:00-14:00 to Monday with minimum 1 worker");
		status = scheduler.addShift("Monday", "14:00", "14:00", "1");
		System.out.println("\tGot status {" + status + "}");
		System.out.println();
		
		System.out.println(">>TESTING: adding shifts to a day without working hours");
		System.out.println(">>Add shift 12:30-15:00 to Wednesday with minimum 1 worker");
		status = scheduler.addShift("Wednesday", "12:30", "15:00", "1");
		System.out.println("\tGot status {" + status + "}");
		System.out.println();
		
		System.out.println(">>TESTING: Staff registration");
		System.out.println(">>Register Bayta Darell as a staff member");
		status = scheduler.registerStaff("Bayta", "Darell");
		System.out.println("\tGot status {" + status + "}");

		System.out.println(">>Register Hari Sheldon as a staff member");
		status = scheduler.registerStaff("Hari", "Sheldon");
		System.out.println("\tGot status {" + status + "}");
		
		System.out.println(">>Schedule Darell Bayta as manager to Monday 09:00-12:00");
		status = scheduler.assignStaff("Monday", "09:00", "12:00", "Bayta", "Darell", true);
		System.out.println("\tGot status {" + status + "}");
		
		System.out.println(">>Schedule Darell Bayta as manager to Monday 12:01-13:00");
		status = scheduler.assignStaff("Monday", "12:01", "13:00", "Bayta", "Darell", true);
		System.out.println("\tGot status {" + status + "}");

		System.out.println(">>Schedule Hari Sheldon as worker to Monday 12:01-13:00");
		status = scheduler.assignStaff("Monday", "12:01", "13:00", "Hari", "Sheldon", false);
		System.out.println("\tGot status {" + status + "}");
		
		System.out.println();
		System.out.println(">>Display current roster");
		System.out.println(scheduler.displayRoster());
		System.out.println();		
		
		System.out.println(">>Create a new Roster for 'Socks for Everyone'");
		status = scheduler.newRoster("Socks for Everyone");
		System.out.println("\tGot status {" + status + "}");
		System.out.println(">>Display current roster. Should be empty due to call to newRoster()");
		System.out.println(scheduler.displayRoster());
		System.out.println(">>End new roster demo");
	}
}
