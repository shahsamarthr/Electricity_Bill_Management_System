package com.sam.Electricity_Bill_Management_System.Controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sam.Electricity_Bill_Management_System.Entity.ApiError;
import com.sam.Electricity_Bill_Management_System.Entity.Constant;
import com.sam.Electricity_Bill_Management_System.Entity.User;
import com.sam.Electricity_Bill_Management_System.Entity.Userlogin;
import com.sam.Electricity_Bill_Management_System.Service.RegistrationService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/Registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		System.out.println("In RegistrationController.addUser()....");
		if (!Constant.nullCheckForUser(user)) {
			List<User> userList = registrationService.getUser();
			if (userList != null) {
				for (int i = 0; i < userList.size(); i++) {
					if ((userList.get(i).getUserName().equals(user.getUserName()))
							&& (userList.get(i).getPassword().equals(user.getPassword()))) {
						return new ResponseEntity<>(new ApiError(HttpStatus.ALREADY_REPORTED, Constant.ALPRESENT),
								HttpStatus.ALREADY_REPORTED);
					}
				}
			}
			user.set_id(ObjectId.get());
			List<String> role = new ArrayList<>();
			role.add("User");
			user.setRole(role);
			registrationService.addUser(user);
			return new ResponseEntity<Object>(
					new ApiError("Information Added Successfully", HttpStatus.ACCEPTED, Constant.SUCCESS),
					HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, Constant.BREQUEST),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public List<User> getUser() {
		System.out.println("In getUser()....");
		List<User> user = registrationService.getUser();
		for (int i = 0; i < user.size(); i++) {
			System.out.println("Users......" + user.get(i));
		}
		return user;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Userlogin userlogin) {
		System.out.println("In RegistrationController.login().....");
		if (userlogin.getUsername() != null && userlogin.getPassword() != null) {
			List<User> userList = registrationService.login(userlogin.getUsername(), userlogin.getPassword());
			System.out.println(userList);
			if (!userList.isEmpty()) {
				return new ResponseEntity<Object>(new ApiError("Login Successfully",HttpStatus.FOUND, Constant.FOUND), HttpStatus.FOUND);
			}
			return new ResponseEntity<Object>(new ApiError("Bad Credentials",HttpStatus.NOT_FOUND, Constant.NFOUND),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(new ApiError("Bad Request",HttpStatus.BAD_REQUEST, Constant.BREQUEST),
				HttpStatus.BAD_REQUEST);
	}
}