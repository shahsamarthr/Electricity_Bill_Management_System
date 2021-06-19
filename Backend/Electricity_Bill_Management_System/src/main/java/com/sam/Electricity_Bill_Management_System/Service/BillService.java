package com.sam.Electricity_Bill_Management_System.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.Electricity_Bill_Management_System.Dao.BillDao;
import com.sam.Electricity_Bill_Management_System.Entity.Bill;
import com.sam.Electricity_Bill_Management_System.Repository.BillRepository;

@Service
public class BillService {

	@Autowired
	private BillDao billDao;

	@Autowired
	private BillRepository billRepository;

	public List<Bill> getBill(String userId, String sortBy, String filterBy, Double minamount, Double maxamount,
			Date minDate, Date maxDate) {
		// TODO Auto-generated method stub
		System.out.println("In BillService.getBill()......");
		List<Bill> billList = null;
		if (sortBy != null && filterBy == null) {
			if (sortBy.equalsIgnoreCase("statusdesc")) {
				billList = billRepository.findByUidOrderByStatusDesc(userId);
			} else if (sortBy.equalsIgnoreCase("statusasc")) {
				billList = billRepository.findByUidOrderByStatusAsc(userId);
			} else if (sortBy.equalsIgnoreCase("dueDateasc")) {
				billList = billRepository.findByUidOrderByDuedateAsc(userId);
			} else if (sortBy.equalsIgnoreCase("dueDatedesc")) {
				billList = billRepository.findByUidOrderByDuedateDesc(userId);
			} else if (sortBy.equalsIgnoreCase("paymentDateasc")) {
				billList = billRepository.findByUidOrderByPaymentdateAsc(userId);
			} else if (sortBy.equalsIgnoreCase("paymentDatedesc")) {
				billList = billRepository.findByUidOrderByPaymentdateDesc(userId);
			} else if (sortBy.equalsIgnoreCase("billamountasc")) {
				billList = billRepository.findByUidOrderByBillamountAsc(userId);
			} else if (sortBy.equalsIgnoreCase("billamountdesc")) {
				billList = billRepository.findByUidOrderByBillamountDesc(userId);
			} else if (sortBy.equalsIgnoreCase("paidamountasc")) {
				billList = billRepository.findByUidOrderByPaidamountAsc(userId);
			} else if (sortBy.equalsIgnoreCase("paidamountdesc")) {
				billList = billRepository.findByUidOrderByPaidamountDesc(userId);
			}
		} else if (filterBy != null && sortBy == null) {
			if (filterBy.equalsIgnoreCase("filterbillamountrange")) {
				billList = billDao.findBillBybillamountrange(minamount, maxamount);
			} else if (filterBy.equalsIgnoreCase("filterpaidamountrange")) {
				billList = billDao.findBillBypaidamountrange(minamount, maxamount);
			} else if (filterBy.equalsIgnoreCase("statuspaid")) {
				billList = billDao.findOnlyPaidBill(userId);
			} else if (filterBy.equalsIgnoreCase("statusunpaid")) {
				billList = billDao.findOnlyUnPaidBill(userId);
			} else if (filterBy.equalsIgnoreCase("filterduedate")) {
				billList = billDao.findBillByDueDateRange(minDate, maxDate);
			} else if (filterBy.equalsIgnoreCase("filterpaymentdate")) {
				billList = billDao.findBillByPaymentDateRange(minDate, maxDate);
			}
		}
		return billList;
	}

	public void addBill(Bill bill) {
		// TODO Auto-generated method stub
		System.out.println("In BillService.addBill()....");
		billRepository.save(bill);
	}

	public Bill getOneBill(String billId) {
		// TODO Auto-generated method stub
		System.out.println("In BillService.getOneBill().....");
		Bill bill = billRepository.findByBillid(billId);
		return bill;
	}

	public void deleteBill(String billid) {
		// TODO Auto-generated method stub
		System.out.println("In BillService.deleteBill()....");
		billRepository.deleteByBillid(billid);
	}

	public List<Bill> getLatestFiveBillDetails(String userId) {
		// TODO Auto-generated method stub
		System.out.println("In BillService.getLatestFiveBillDetails().......");
		List<Bill> latestfivebilllist = billDao.getLatestFiveBillDetails(userId);
		return latestfivebilllist;
	}

	public List<Bill> getLatestFivePaidBillDetails(String userId) {
		// TODO Auto-generated method stub
		System.out.println("In BillService.getLatestFivePaidBillDetails()......");
		List<Bill> latestfivepaidbilllist = billDao.getLatestFivePaidBillDetails(userId);
		return latestfivepaidbilllist;
	}

	public List<Bill> getHighestFiveBillAmount(String userId) {
		// TODO Auto-generated method stub
		System.out.println("In BillService.getHighestFiveBillAmount().......");
		List<Bill> highestfivebillamountlist = billDao.getHighestFiveBillAmount(userId);
		return highestfivebillamountlist;
	}

	public List<Bill> getHighestFivePaidAmount(String userId) {
		// TODO Auto-generated method stub
		System.out.println("In BillService.getHighestFivePaidAmount()......");
		List<Bill> highestfivepaidamountlist = billDao.getHighestFivePaidAmount(userId);
		return highestfivepaidamountlist;
	}

	public List<Bill> getBill(String userId) {
		// TODO Auto-generated method stub
		List<Bill> billList = billRepository.findByUid(userId);
		return billList;
	}
}