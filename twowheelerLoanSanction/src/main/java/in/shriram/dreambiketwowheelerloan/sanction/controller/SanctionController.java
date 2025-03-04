package in.shriram.dreambiketwowheelerloan.sanction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;
import in.shriram.dreambiketwowheelerloan.sanction.servicei.SanctionServiceI;

@RestController
@RequestMapping("/sanction")
public class SanctionController {

	@Autowired
	SanctionServiceI ssi;
	

	@PutMapping("/generatePdf/{customerId}")
	public SanctionLetter updateSactionLetter(@PathVariable("customerId") Integer customerId) {

			return ssi.generateSactionId(customerId);
	}
	
	@PostMapping("/addSanction/{customerId}")
	public SanctionLetter addSanction(@PathVariable("customerId") Integer customerId)
	{
		return ssi.addSanction(customerId);
	}
	
	@GetMapping("/customer/{loanStatus}")
	public ResponseEntity<List<Customer>> getAllCustomer(@PathVariable("loanStatus")String loanStatus)
	{
		List<Customer> cu = ssi.getAllCustomer("Scantioned");
		
		return new ResponseEntity<List<Customer>> (cu, HttpStatus.OK);
	}
	
}
