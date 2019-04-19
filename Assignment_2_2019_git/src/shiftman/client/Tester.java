package shiftman.client;


import shiftman.server.Day;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Day.fri.showDay());
		Day.fri.addShift("8am");
		System.out.println(Day.fri.giveShifts());
		System.out.println(Day.thu.giveShifts());

	}

}
