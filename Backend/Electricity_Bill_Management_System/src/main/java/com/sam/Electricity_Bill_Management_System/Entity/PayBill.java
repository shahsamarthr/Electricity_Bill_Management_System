package com.sam.Electricity_Bill_Management_System.Entity;

public class PayBill {

	private String uid;
	private String billid;
	private double paid_amount;

	public PayBill(String uid, String billid, double paid_amount) {
		super();
		this.uid = uid;
		this.billid = billid;
		this.paid_amount = paid_amount;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public double getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
	}

	@Override
	public String toString() {
		return "PayBill [uid=" + uid + ", billid=" + billid + ", paid_amount=" + paid_amount + "]";
	}

}
