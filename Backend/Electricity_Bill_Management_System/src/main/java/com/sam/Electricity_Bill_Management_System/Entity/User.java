package com.sam.Electricity_Bill_Management_System.Entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {

	@Id
	private ObjectId _id;
	private String uid;
	private String fname;
	private String lname;
	private String address;
	private String city;
	private String state;
	private String country;
	private String userName;
	private String password;
	private String cityId;
	private int pincode;
	private List<String> role;

	public User() {

	}

	public User(ObjectId _id, String uid, String fname, String lname, String address, String city, String state,
			String country, String userName, String password, String cityId, int pincode, List<String> role) {
		super();
		this._id = _id;
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.userName = userName;
		this.password = password;
		this.cityId = cityId;
		this.pincode = pincode;
		this.role = role;
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

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [_id=" + _id + ", uid=" + uid + ", fname=" + fname + ", lname=" + lname + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", userName=" + userName
				+ ", password=" + password + ", cityId=" + cityId + ", pincode=" + pincode + ", role=" + role + "]";
	}

}