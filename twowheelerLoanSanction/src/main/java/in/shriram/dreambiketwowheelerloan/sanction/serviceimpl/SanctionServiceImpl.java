package in.shriram.dreambiketwowheelerloan.sanction.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
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
	public CustomerDetails addData(int cd) {
		
		CustomerDetails cust=new CustomerDetails();
		
		Customer e=rt.getForObject("http://desktop-13cev9m:7777/apploan/getCustomer/"+cd, Customer.class);
	
		cust.setDate(new Date());
		cust.setApplicantname(e.getCustomerName());
		cust.setContactdetails(e.getCustomerMobileNumber());
		cust.setOnRoadPrice(e.getOnRoadPrice());			
		cust.setInteresType(cust.getInteresType());	
		
		//SANCTIONED LOAN AMOUNT WILL BE 80% OF ON ROAD PRICE
		cust.setLoanAmountScantioned(0.8*e.getOnRoadPrice());	//Check input of onRoadPrice
		
		//LOGIC FOR RATE OF INTEREST
		if(e.getCibil().getCibilRemark()=="Good") {
			cust.setRateofInterest(10.2f);
		}
		else if(e.getCibil().getCibilRemark()=="Very Good") {
			cust.setRateofInterest(9.1f);
		}
		else if(e.getCibil().getCibilRemark()=="Excellent") {
			cust.setRateofInterest(7.9f);
		}
		
		//LOGIC FOR LOAN TENURE (IN MONTHS)
		if(e.getOnRoadPrice()>=50000) {
			cust.setLoanTenureMonth(12);
		}
		else if(e.getOnRoadPrice()>=100000) {
			cust.setLoanTenureMonth(24);
		}
		else if(e.getOnRoadPrice()>=150000) {
			cust.setLoanTenureMonth(36);
		}
		else {
			cust.setLoanTenureMonth(48);
		}
		
		//LOGIC FOR COMPOUND INTEREST CALCULATION
		int compoundingFrequency=12;
		double totalAmountPayable=cust.getLoanAmountScantioned()*Math.pow(1+(cust.getRateofInterest()/compoundingFrequency),
				compoundingFrequency*cust.getLoanTenureMonth());
		
		
		//LOGIC FOR EMI
		double emi=totalAmountPayable/cust.getLoanTenureMonth();
		
		return cust;
	}
}
