package com.sam.Electricity_Bill_Management_System.Dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sam.Electricity_Bill_Management_System.Entity.Bill;

@Repository
public class BillDao {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public BillDao(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<Bill> findBillBybillamountrange(double minBillamount, double maxBillamount) {
		System.out.println("In BillDao.findBillBybillamountrange()......");
		Query query = new Query();
		query.addCriteria(Criteria.where("billamount").lt(maxBillamount).gte(minBillamount));
		return mongoTemplate.find(query, Bill.class);
	}

	public List<Bill> findBillBypaidamountrange(double minBillamount, double maxBillamount) {
		System.out.println("In BillDao.findBillBypaidamountrange().......");
		Query query = new Query();
		query.addCriteria(Criteria.where("paidamount").lt(maxBillamount).gte(minBillamount));
		return mongoTemplate.find(query, Bill.class);
	}

	public List<Bill> findOnlyPaidBill(String userId) {
		System.out.println("In BillDao.findOnlyPaidBill()........");
		String status = "paid";
		Query query = new Query();
		query.addCriteria(Criteria.where("uid").is(userId).andOperator(Criteria.where("status").is(status)));
		return mongoTemplate.find(query, Bill.class);
	}

	public List<Bill> findOnlyUnPaidBill(String userId) {
		System.out.println("In BillDao.findOnlyUnpaidBill().......");
		String status = "unpaid";
		Query query = new Query();
		query.addCriteria(Criteria.where("uid").is(userId).andOperator(Criteria.where("status").is(status)));
		return mongoTemplate.find(query, Bill.class);
	}

	public List<Bill> findBillByDueDateRange(Date minDate, Date maxDate) {
		System.out.println("In BillDao.findBillByDueDateRange().........");
		Query query = new Query();
		query.addCriteria(Criteria.where("duedate").gte(minDate).lt(maxDate));
		return mongoTemplate.find(query, Bill.class);
	}

	public List<Bill> findBillByPaymentDateRange(Date minDate, Date maxDate) {
		System.out.println("In BillDao.findBillByPaymentDateRange()......");
		Query query = new Query();
		query.addCriteria(Criteria.where("paymentdate").gte(minDate).lt(maxDate));
		return mongoTemplate.find(query, Bill.class);
	}

	public List<Bill> getLatestFiveBillDetails(String userId) {
		System.out.println("In BillDao.getLatestFiveBillDetails().....");
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "duedate"));
		query.addCriteria(Criteria.where("uid").is(userId));
		query.limit(5);
		return mongoTemplate.find(query, Bill.class);
	}

	public List<Bill> getLatestFivePaidBillDetails(String userId) {
		System.out.println("In BillDao.getLatestFivePaidBillDetails().....");
		String status = "paid";
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "paymentdate"));
		query.addCriteria(Criteria.where("uid").is(userId).andOperator(Criteria.where("status").is(status)));
		query.limit(5);
		return mongoTemplate.find(query, Bill.class);
	}

	public List<Bill> getHighestFiveBillAmount(String userId) {
		System.out.println("In BillDao.getHighestFiveBillAmount().......");
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "billamount"));
		query.addCriteria(Criteria.where("uid").is(userId));
		query.limit(5);
		return mongoTemplate.find(query, Bill.class);
	}

	public List<Bill> getHighestFivePaidAmount(String userId) {
		System.out.println("In BillDao.getHighestFivePaidAmount().......");
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "paidamount"));
		query.addCriteria(Criteria.where("uid").is(userId));
		query.limit(5);
		return mongoTemplate.find(query, Bill.class);
	}
}
