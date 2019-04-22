package shiftman.server;

public class Time {

	private String _startTime;
	private String _endTime;
	private int _start;
	private int _end;
	
	public Time(String startTime,String endTime) throws Exception {
		// TODO Auto-generated constructor stub
		_startTime = startTime;
		_endTime = endTime;
		_start = convertTime(_startTime);
		_end = convertTime(_endTime);
		if((_start == _end)||(_start>_end)) {
			throw new Exception("ERROR: Time interval "+ this.displayTime() +" is invalid");
		}
	}

	public int convertTime(String time) throws Exception {
		if(time.matches("\\d\\d:\\d\\d")) {//Checking correct format
			int hours = Integer.parseInt(time.substring(0, 2));
			int mins = Integer.parseInt(time.substring(3, 4));
			if(time.matches("([0-1]\\d|2[0-3]):[0-5]\\d")) {//checking that it's before midnight etc.
				return hours*100 + mins;
			}else {
				throw new Exception("ERROR: Time " + time + " is invalid");
			}
		}else {
			throw new Exception("ERROR: Time " + time + " is in the wrong format");
		}
	}
	
	public boolean checkOverlap(Time time2) {//possibly change this so I can tell if one time is completely within another
		if(_start > time2.showStartTime()) {
			if(_start <= time2.showEndTime()) {
				return true;
			}
		}else if(_start < time2.showStartTime()) {
			if(_end >= time2.showStartTime()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkWithinInterval(Time time2) {
		if((_start > time2.showStartTime())&&(_end < time2.showEndTime())) {
			return true;
		}else {
			return false;
		}
	}
	
	public int showEndTime() {
		return _end;
	}
	
	public int showStartTime() {
		return _start;
	}
	
	public String displayTime() {
		return _startTime + "-" + _endTime;
	}
}
