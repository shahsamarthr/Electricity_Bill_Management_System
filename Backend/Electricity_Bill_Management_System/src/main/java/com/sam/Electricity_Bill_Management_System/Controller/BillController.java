package com.sam.Electricity_Bill_Management_System.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sam.Electricity_Bill_Management_System.Entity.ApiError;
import com.sam.Electricity_Bill_Management_System.Entity.Bill;
import com.sam.Electricity_Bill_Management_System.Entity.Constant;
import com.sam.Electricity_Bill_Management_System.Entity.PayBill;
import com.sam.Electricity_Bill_Management_System.Service.BillService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/Bill")
public class BillController {

	@Autowired
	private BillService billService;

	@RequestMapping(value = { "/getBill", "/getBill/{userId}/{sortBy}", "/getBill/{userId}/filter/{filterBy}",
			"/getBill/{userId}/filter/date/{filterBy}/{minDate}/{maxDate}",
			"/getBill/{userId}/filter/amount/{filterBy}/{minamount}/{maxamount}" }, method = RequestMethod.GET)
	public ResponseEntity<Object> getBill(@PathParam(value = "userId") String userId,
			@PathVariable(required = false, value = "sortBy") String sortBy,
			@PathVariable(required = false, value = "filterBy") String filterBy,
			@PathVariable(required = false, value = "minamount") Double minamount,
			@PathVariable(required = false, value = "maxamount") Double maxamount,
			@PathVariable(required = false, value = "minDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime minDateLocal,
			@PathVariable(required = false, value = "maxDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime maxDateLocal) {
		System.out.println("In BillController.getBill()....");

		if (userId != null) {
			if (sortBy != null) {
				if (!sortBy.equalsIgnoreCase("statusasc") && !sortBy.equalsIgnoreCase("statusdesc")
						&& !sortBy.equalsIgnoreCase("dueDateasc") && !sortBy.equalsIgnoreCase("dueDatedesc")
						&& !sortBy.equalsIgnoreCase("paymentDateasc") && !sortBy.equalsIgnoreCase("paymentDatedesc")
						&& !sortBy.equalsIgnoreCase("billamountasc") && !sortBy.equalsIgnoreCase("billamountdesc")
						&& !sortBy.equalsIgnoreCase("paidamountasc") && !sortBy.equalsIgnoreCase("paidamountdesc")
						&& !filterBy.equalsIgnoreCase("filterbillamountrange")
						&& !filterBy.equalsIgnoreCase("filterpaidamountrange")
						&& !filterBy.equalsIgnoreCase("statuspaid") && !filterBy.equalsIgnoreCase("statusunpaid")
						&& !filterBy.equalsIgnoreCase("filterduedate")
						&& !filterBy.equalsIgnoreCase("filterpaymentdate")) {
					return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, Constant.BREQUEST),
							HttpStatus.BAD_REQUEST);
				}
			}
			List<Bill> billList = null;
			if (sortBy != null || filterBy != null) {
				Date minDate = Date.from(minDateLocal.atZone(ZoneId.systemDefault()).toInstant());
				Date maxDate = Date.from(maxDateLocal.atZone(ZoneId.systemDefault()).toInstant());
				billList = billService.getBill(userId, sortBy, filterBy, minamount, maxamount, minDate, maxDate);
			} else {
				billList = billService.getBill(userId);
			}
			if (!billList.isEmpty()) {
				return new ResponseEntity<Object>(new ApiError(billList, HttpStatus.FOUND, Constant.FOUND),
						HttpStatus.FOUND);
			} else {
				return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, Constant.NFOUND),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, Constant.BREQUEST),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getOneBill/{billId}")
	public ResponseEntity<Object> getOneBill(@PathVariable("billId") String billId) {
		System.out.println("In BillController.getOneBill().....");
		if (billId != null) {
			Bill bill = billService.getOneBill(billId);
			if (bill != null) {
				return new ResponseEntity<Object>(new ApiError(bill, HttpStatus.FOUND, Constant.FOUND),
						HttpStatus.FOUND);
			} else {
				return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, Constant.NFOUND),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, Constant.BREQUEST),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getlatestfiveBilldetails/{userId}")
	public ResponseEntity<Object> getLatestFiveBillDetails(@PathVariable("userId") String userId) {
		System.out.println("In BillController.getLatestFiveBillDetails()......");
		if (userId != null) {
			List<Bill> latestfiveBillList = billService.getLatestFiveBillDetails(userId);
			if (!latestfiveBillList.isEmpty()) {
				return new ResponseEntity<Object>(new ApiError(latestfiveBillList, HttpStatus.FOUND, Constant.FOUND),
						HttpStatus.FOUND);
			} else {
				return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, Constant.NFOUND),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, Constant.BREQUEST),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getlatestfivepaidbilldetails/{userId}")
	public ResponseEntity<Object> getLatestFivePaidBillDetails(@PathVariable("userId") String userId) {
		System.out.println("In BillController.getLatestFivePaidDetails().......");
		if (userId != null) {
			List<Bill> latestfivepaidbilllist = billService.getLatestFivePaidBillDetails(userId);
			if (!latestfivepaidbilllist.isEmpty()) {
				return new ResponseEntity<Object>(
						new ApiError(latestfivepaidbilllist, HttpStatus.FOUND, Constant.FOUND), HttpStatus.FOUND);
			} else {
				return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, Constant.NFOUND),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, Constant.BREQUEST),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/gethighestfivebillamount/{userId}")
	public ResponseEntity<Object> getHighestFiveBillAmount(@PathVariable("userId") String userId) {
		System.out.println("In BillController.getHighestFiveBillAmount().......");
		if (userId != null) {
			List<Bill> highestfivebillamountlist = billService.getHighestFiveBillAmount(userId);
			if (!highestfivebillamountlist.isEmpty()) {
				return new ResponseEntity<Object>(
						new ApiError(highestfivebillamountlist, HttpStatus.FOUND, Constant.FOUND), HttpStatus.FOUND);
			} else {
				return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, Constant.NFOUND),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, Constant.BREQUEST),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/gethighestfivepaidamount/{userId}")
	public ResponseEntity<Object> getHighestFivePaidAmount(@PathVariable("userId") String userId) {
		System.out.println("In BillController.getHighestFivePaidAmount().......");
		if (userId != null) {
			List<Bill> highestfivepaidamountlist = billService.getHighestFivePaidAmount(userId);
			if (!highestfivepaidamountlist.isEmpty()) {
				return new ResponseEntity<Object>(
						new ApiError(highestfivepaidamountlist, HttpStatus.FOUND, Constant.FOUND), HttpStatus.FOUND);
			} else {
				return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, Constant.NFOUND),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, Constant.BREQUEST),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/payBill")
	public ResponseEntity<Object> payBill(@RequestBody PayBill payBill) throws Exception {
		System.out.println("In BillController.payBill().......");
		if (!Constant.nullCheckForPayBill(payBill)) {
			Bill bill = billService.getOneBill(payBill.getBillid());
			if (!(bill.getStatus().equalsIgnoreCase("paid"))) {
				if (bill != null) {
					bill.setPaymentdate(new Date());
					bill.setStatus("paid");
					bill.setPaidamount(payBill.getPaid_amount());
					billService.deleteBill(payBill.getBillid());
					billService.addBill(bill);
					return new ResponseEntity<Object>(new ApiError(HttpStatus.ACCEPTED, Constant.SUCCESS),
							HttpStatus.ACCEPTED);
				} else {
					return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, Constant.NFOUND),
							HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Object>(new ApiError(HttpStatus.ALREADY_REPORTED, Constant.ALPRESENT),
						HttpStatus.ALREADY_REPORTED);
			}
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, Constant.BREQUEST),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/addBill")
	public ResponseEntity<Object> addBill(@RequestBody Bill bill) throws Exception {
		System.out.println("In BillController.addBill().....");
		if (!Constant.nullCheckForBill(bill)) {
			Bill mycustbill = billService.getOneBill(bill.getBillid());
			if (mycustbill != null) {
				return new ResponseEntity<Object>(new ApiError(HttpStatus.ALREADY_REPORTED, Constant.ALPRESENT),
						HttpStatus.ALREADY_REPORTED);
			}
			bill.set_id(ObjectId.get());
			bill.setPaymentdate(new Date());
			bill.setStatus("unpaid");
			bill.setDuedate(new Date());
			billService.addBill(bill);
			return new ResponseEntity<Object>(new ApiError(HttpStatus.ACCEPTED, Constant.SUCCESS), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, Constant.BREQUEST),
					HttpStatus.BAD_REQUEST);
		}
	}
}