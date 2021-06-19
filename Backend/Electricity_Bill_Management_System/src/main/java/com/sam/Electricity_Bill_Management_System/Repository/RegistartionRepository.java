package com.sam.Electricity_Bill_Management_System.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sam.Electricity_Bill_Management_System.Entity.User;

@Repository
public interface RegistartionRepository extends MongoRepository<User, String> {

}
