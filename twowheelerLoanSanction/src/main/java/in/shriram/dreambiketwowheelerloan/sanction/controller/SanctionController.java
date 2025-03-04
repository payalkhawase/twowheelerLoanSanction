package in.shriram.dreambiketwowheelerloan.sanction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
=======
>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerLoanSanction.git
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
<<<<<<< HEAD
=======
import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;
>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerLoanSanction.git
import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;
import in.shriram.dreambiketwowheelerloan.sanction.servicei.SanctionServiceI;

@RestController
@RequestMapping("/sanction")
public class SanctionController {

	@Autowired
	SanctionServiceI ssi;
	
//	@PutMapping("/generateEmi/{customerId}")
//	public ResponseEntity<CustomerDetails> generateEmi(@PathVariable("customerId") int customerId){
//		
//		CustomerDetails cust=ssi.addData(customerId);		
//		
//		return new ResponseEntity<CustomerDetails>(cust,HttpStatus.OK);
//	}
	

	@PutMapping("/generatePdf/{customerId}")
	public SanctionLetter updateSactionLetter(@PathVariable("customerId") Integer customerId) {

			return ssi.generateSactionId(customerId);
	}
	
	@PostMapping("/addSanction/{customerId}")
	public SanctionLetter addSanction(@PathVariable("customerId") Integer customerId)
	{
		return ssi.addSanction(customerId);
	}
	
<<<<<<< HEAD
	@GetMapping("/customer/{loanStatus}")
	public ResponseEntity<List<Customer>> getAllCustomer(@PathVariable("loanStatus")String loanStatus)
	{
		List<Customer> cu = ssi.getAllCustomer("Scantioned");
		
		return new ResponseEntity<List<Customer>> (cu, HttpStatus.OK);
	}
	
=======
//	@PutMapping("updateSanctionStatus/{customerId}/{status}")
//	public ResponseEntity<Customer> updateSanctionStatus(@PathVariable("customerId") int customerId,
//			@PathVariable("status") String status){
//		
//		Customer sl=ssi.updateSanctionStatus(customerId,status);
//		return null;
//	}
>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerLoanSanction.git
}
