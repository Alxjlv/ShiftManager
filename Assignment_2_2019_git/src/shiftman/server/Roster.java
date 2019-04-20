package shiftman.server;

//import java.util.List;

public class Roster {
	
	private String _shopName;
	private StaffRegistry _registeredStaff = new StaffRegistry();
	private Day _day;
	
	public Roster(String shopName) {
		_shopName = shopName;
		
	}
	
	
	public void registerStaff(Staff person) throws Exception{
		_registeredStaff.registerStaff(person);
	}
	
	public String displayShopName() {
		return "This roster is for the shop " + _shopName;
	}
	
	public String assignStaff(String dayOfWeek, String startTime, String endTime, String givenName, String familyName, boolean isManager) {
		return null;
	}
	
	public void setWorkingHours(String dayOfWeek, String startTime, String endTime) throws Exception,RuntimeException {
		
		_day.setWorkingHours(startTime,endTime);
	}
}