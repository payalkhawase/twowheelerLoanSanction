package in.shriram.dreambiketwowheelerloan.sanction.controller;

import java.util.List;

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
import org.springframework.web.client.RestTemplate;

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;
import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;
import in.shriram.dreambiketwowheelerloan.sanction.servicei.SanctionServiceI;

@RestController
@RequestMapping("/sanction")
public class SanctionController {

	@Autowired
	SanctionServiceI ssi;
	
	@Autowired
	RestTemplate rt;
	
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
	
	
	//UserLogin and AdminLogin API should be in user login and admin login microservice
	
	@PutMapping("/userLogin/{customerId}/{password}")
	public ResponseEntity<Customer> userLogin(@PathVariable("customerId") int customerId,
			@PathVariable("password") String password){
		
		Customer c=ssi.userLogin(customerId,password);
		return new ResponseEntity<Customer>(c,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/updateSanctionStatus/{customerId}/{status}")
	public ResponseEntity<Customer> updateSanctionStatus(@PathVariable("customerId") int customerId,
			@PathVariable("status") String status){

			Customer c=ssi.updateSanctionStatus(customerId,status);
			return new ResponseEntity<Customer>(c,HttpStatus.OK);

	}

	@GetMapping("/getSanctionList")
	public ResponseEntity<List> getSanctionList()
	{
		List list = ssi.getSanctionList();
		return new ResponseEntity<List>(list,HttpStatus.OK);
	}

//	@PutMapping("updateSanctionStatus/{customerId}/{status}")
//	public ResponseEntity<Customer> updateSanctionStatus(@PathVariable("customerId") int customerId,
//			@PathVariable("status") String status){
//		
//		Customer sl=ssi.updateSanctionStatus(customerId,status);
//		return null;
//	}

}
