package com.sam.Electricity_Bill_Management_System.Entity;

public class Constant {

	public static final String SUCCESS = "Successfully Added";
	public static final String ALPRESENT = "Already present";
	public static final String BREQUEST = "Input Parameter Missing";
	public static final String NFOUND = "Not found";
	public static final String FOUND = "Found";

	public static final Boolean nullCheckForPayBill(PayBill payBill) {
		if (payBill.getUid() != null && payBill.getBillid() != null && payBill.getPaid_amount() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static final Boolean nullCheckForBill(Bill bill) {
		if (bill.getUid() != null && bill.getBillid() != null && bill.getMonth() != null && bill.getBillamount() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static final Boolean nullCheckForUser(User user) {
		if (user.getUid() != null && user.getFname() != null && user.getLname() != null && user.getAddress() != null
				&& user.getCity() != null && user.getState() != null && user.getUserName() != null
				&& user.getPassword() != null && user.getCityId() != null
				&& Integer.toString(user.getPincode()).length() == 6) {
			return false;
		} else {
			return true;
		}
	}
}
