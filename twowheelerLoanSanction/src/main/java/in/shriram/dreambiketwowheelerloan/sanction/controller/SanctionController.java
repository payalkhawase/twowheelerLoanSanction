package in.shriram.dreambiketwowheelerloan.sanction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;
import in.shriram.dreambiketwowheelerloan.sanction.servicei.SanctionServiceI;

@RestController
@RequestMapping("/sanction")
public class SanctionController {

	@Autowired
	SanctionServiceI ssi;
	
	@PutMapping("/generateEmi/{customerId}")
	public ResponseEntity<CustomerDetails> generateEmi(@PathVariable("customerId") int customerId){
		
		CustomerDetails cust=ssi.addData(customerId);		
		
		return new ResponseEntity<CustomerDetails>(cust,HttpStatus.OK);
	}
	

	
}
