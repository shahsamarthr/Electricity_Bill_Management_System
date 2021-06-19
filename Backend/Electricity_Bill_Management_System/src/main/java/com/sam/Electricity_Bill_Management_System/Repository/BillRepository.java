package com.sam.Electricity_Bill_Management_System.Repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sam.Electricity_Bill_Management_System.Entity.Bill;

@Repository
public interface BillRepository extends MongoRepository<Bill, ObjectId> {

	List<Bill> findByUid(String userId);

	Bill findByBillid(String billId);

	void deleteByBillid(String billId);

	List<Bill> findByUidOrderByStatusDesc(String userId);

	List<Bill> findByUidOrderByStatusAsc(String userId);

	List<Bill> findByUidOrderByDuedateAsc(String userId);

	List<Bill> findByUidOrderByDuedateDesc(String userId);

	List<Bill> findByUidOrderByPaymentdateAsc(String userId);

	List<Bill> findByUidOrderByPaymentdateDesc(String userId);

	List<Bill> findByUidOrderByBillamountAsc(String userId);

	List<Bill> findByUidOrderByBillamountDesc(String userId);

	List<Bill> findByUidOrderByPaidamountAsc(String userId);

	List<Bill> findByUidOrderByPaidamountDesc(String userId);

}
