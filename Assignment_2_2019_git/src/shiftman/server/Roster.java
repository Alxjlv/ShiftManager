package shiftman.server;

public class Roster {
	
	private String _shopName;
	private StaffRegistry _registeredStaff = new StaffRegistry();
	
	public Roster(String shopName) {
		_shopName = shopName;
	}
		
}
