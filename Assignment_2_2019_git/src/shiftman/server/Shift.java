package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public class Shift {

	private String _day;
	private String _startTime;
	private String _endTime;
	private Staff _manager;
	private List<Staff> _workers = new ArrayList<Staff>();
	
	public Shift(String day, String startTime, String endTime) { //has day, start time and end time - also manager and workers?
		// TODO Auto-generated constructor stub
		_day = day;
		_startTime = startTime;
	}

}
