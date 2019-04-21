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
		validate(this);
	}

	public int convertTime(String time) throws Exception {
		if(time.matches("\\d\\d:\\d\\d")) {
			int hours = Integer.parseInt(time.substring(0, 2));
			int mins = Integer.parseInt(time.substring(3, 4));
			if((hours < 24)&&(hours>=0)&&(mins<60)&&(mins>=0)) {
				return hours*100 + mins;
			}else {
				throw new Exception("ERROR: Invalid time");
			}
		}else {
			throw new Exception("ERROR: Time is given in the wrong format");
		}
	}
	
	public void compareTimes(Time t) throws Exception {//possibly change this so I can tell if one time is completely within another
		if(_start > t.showStartTime()) {
			if(_start <= t.showEndTime()) {
				throw new Exception("ERROR: Time interval overlaps; Starts before another time interval ends");
			}
		}else if(_start < t.showStartTime()) {
			if(_end >= t.showStartTime()) {
				throw new Exception("ERROR: Time interval overlaps; Ends after another time interval starts");
			}
		}
	}
	
	public boolean checkWithinInterval(Time t) {
		if((_start > t.showStartTime())&&(_end < t.showEndTime())) {
			return true;
		}else {
			return false;
		}
	}
	
	public void validate(Time t) throws Exception {
		if((_start >=2400)||(_end >=2400)||(_start == _end)||(_start>_end)||(_start<0)||(_end<0)) {
			throw new Exception("ERROR: Time interval given is invalid");
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
