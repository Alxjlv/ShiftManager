package shiftman.server;

public class Roster {
	
	private String _shopName;
	private StaffRegistry _registeredStaff = new StaffRegistry();
	
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
		
		Day.valueOf(dayOfWeek).setWorkingHours(startTime,endTime);
	}
}