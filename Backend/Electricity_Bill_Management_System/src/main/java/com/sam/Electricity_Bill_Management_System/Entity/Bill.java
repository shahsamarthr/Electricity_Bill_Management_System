package com.sam.Electricity_Bill_Management_System.Entity;

import java.util.Date;

import javax.persistence.Column;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bill")
public class Bill {

	@Id
	private ObjectId _id;
	private String uid;
	private Date paymentdate;
	private double billamount;
	private double paidamount;
	private String status;
	private String month;
	private Date duedate;
	@Column(unique = true)
	private String billid;

	public Bill(ObjectId _id, String uid, Date paymentdate, double billamount, double paidamount, String status,
			String month, Date duedate, String billid) {
		super();
		this._id = _id;
		this.uid = uid;
		this.paymentdate = paymentdate;
		this.billamount = billamount;
		this.paidamount = paidamount;
		this.status = status;
		this.month = month;
		this.duedate = duedate;
		this.billid = billid;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date date) {
		this.paymentdate = date;
	}

	public double getBillamount() {
		return billamount;
	}

	public void setBillamount(double billamount) {
		this.billamount = billamount;
	}

	public double getPaidamount() {
		return paidamount;
	}

	public void setPaidamount(double paidamount) {
		this.paidamount = paidamount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date date) {
		this.duedate = date;
	}

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	@Override
	public String toString() {
		return "Bill [_id=" + _id + ", uid=" + uid + ", paymentdate=" + paymentdate + ", billamount=" + billamount
				+ ", paidamount=" + paidamount + ", status=" + status + ", month=" + month + ", duedate=" + duedate
				+ ", billid=" + billid + "]";
	}

}