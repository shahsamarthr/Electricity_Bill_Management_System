package com.sam.Electricity_Bill_Management_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.Electricity_Bill_Management_System.Dao.RegistrationDao;
import com.sam.Electricity_Bill_Management_System.Entity.User;
import com.sam.Electricity_Bill_Management_System.Repository.RegistartionRepository;

@Service
public class RegistrationService {

	@Autowired
	private RegistartionRepository registartionRepository;

	@Autowired
	private RegistrationDao registrationDao;

	public void addUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("In RegistrationService.addUser() .....");
		registartionRepository.save(user);
	}

	public List<User> getUser() {
		// TODO Auto-generated method stub
		List<User> user = registartionRepository.findAll();
		return user;
	}

	public List<User> login(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("In RegistrationService.login()....");
		List<User> user=registrationDao.checklogin(username,password);
		return user;
	}

}
