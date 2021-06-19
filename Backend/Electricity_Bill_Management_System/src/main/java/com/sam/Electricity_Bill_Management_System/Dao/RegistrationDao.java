package com.sam.Electricity_Bill_Management_System.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sam.Electricity_Bill_Management_System.Entity.User;

@Repository
public class RegistrationDao {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public RegistrationDao(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<User> checklogin(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("In RegistrationDao.checklogin().....");
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(username).andOperator(Criteria.where("password").is(password)));
		return mongoTemplate.find(query, User.class);
	}

}
