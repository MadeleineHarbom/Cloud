package com.made.lektion18_1;


public class MyInitScript {
	
	public static void initFunc() {
		if (Storage.drivers.size() == 0) {
			Storage.drivers.add(new Driver(1, "Madeleine", "made"));
			Storage.drivers.add(new Driver(2, "Steffen", "slyngel"));
			Storage.drivers.add(new Driver(3, "Douglas", "prut"));
			Storage.drivers.add(new Driver(4, "Patrick", "fis"));
	
		}
	}
	
	public static void createDriverLocally(int id, String RLname, String username) {
		for (Driver driver : Storage.drivers) {
			if (id == driver.id || username.equals(driver.userName)) {
				return;
			}
		}
		Storage.drivers.add(new Driver(id, RLname, username));
	}
	
	public static void updateDriver(int id, String RLname, String username) {
		if (RLname.trim().length() > 0 && username.trim().length() > 0) {
			for (Driver driver : Storage.drivers) {
				if (id == driver.id) {
					driver.RLname = RLname;
					driver.userName = username;
				}
			}
		}
		
	}
	
	public static void deleteDriver(int id) {
		for (Driver driver : Storage.drivers) {
			if (id == driver.id) {
				Storage.drivers.remove(driver);
			}
		}
	}
	

}
