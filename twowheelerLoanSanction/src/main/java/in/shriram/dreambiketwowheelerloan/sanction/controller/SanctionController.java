package in.shriram.dreambiketwowheelerloan.sanction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;
import in.shriram.dreambiketwowheelerloan.sanction.servicei.SanctionServiceI;

@RestController
@RequestMapping("/sanction")
public class SanctionController {

	@Autowired
	SanctionServiceI ssi;
	

	
	@PostMapping("/getVerifiedCustomer/{customerId}/{loanStatus}")
    public ResponseEntity<CustomerDetails> getVerifiedCustomer(@PathVariable("customerId") int customerId,@PathVariable("loanStatus") String loanStatus)
	{
		CustomerDetails cd= ssi.getVerifiedCustomer(customerId,loanStatus);
	return new ResponseEntity<CustomerDetails>(cd,HttpStatus.OK);
    }
	
}
