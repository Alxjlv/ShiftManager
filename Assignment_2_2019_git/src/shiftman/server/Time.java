package shiftman.server;

public class Time {

	private String _startTime;
	private String _endTime;
	private int _start;
	private int _end;
	
	public Time(String startTime,String endTime) {
		// TODO Auto-generated constructor stub
		_startTime = startTime;
		_endTime = endTime;
		_start = convertTime(startTime);
		_end = convertTime(endTime);
	}

	public int convertTime(String time) {
		return -1;
	}
	
	public boolean checkOverlap(Time t) {
		if(_start > t.getStart()) {
			if(_start <= t.getEnd()) {
				return true;
			}else {
				return false;
			}
		}else if(_start < t.getStart()) {
			if(_end >= t.getStart()) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	public int getEnd() {
		return _end;
	}
	
	public int getStart() {
		return _start;
	}
}
