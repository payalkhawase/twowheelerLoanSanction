package in.shriram.dreambiketwowheelerloan.sanction.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;
import in.shriram.dreambiketwowheelerloan.sanction.repository.SanctionRepository;
import in.shriram.dreambiketwowheelerloan.sanction.servicei.SanctionServiceI;

@Service
public class SanctionServiceImpl implements SanctionServiceI{

	@Autowired
	SanctionRepository sr;
	
	@Autowired
	RestTemplate rt;

	

	@Override
	public CustomerDetails getVerifiedCustomer(int customerId, String loanStatus) {
		
		if(customerId > 0  && loanStatus.equals("verified")) {
			
		CustomerDetails	cd = rt.getForObject("http://localhost:7777/apploan/getCustomer/"+customerId+"/"+loanStatus, CustomerDetails.class); 
					
		cd.getDate();
	
	cd.getApplicantname();
	cd.setContactdetails();//auto
	cd.setOnRoadPrice();//user/service
	cd.getLoanAmountScantioned();// cm/s.
	cd.getInteresType();//auto
	cd.getRateofInterest();//cibil
	cd.getLoanTenureMonth();//user/cm
	cd.getMonthlyEmiAmount();//cm
	cd.setStatus();//cm
	}
		return null;
	
	}
	
}
