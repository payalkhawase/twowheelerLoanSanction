package in.shriram.dreambiketwowheelerloan.sanction.servicei;

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;

import java.util.List;

import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;


public interface SanctionServiceI {


	public SanctionLetter generateSactionId(Integer customerId);

	public SanctionLetter addSanction(Integer customerId);
	
	public List getSanctionList();

	public Customer updateSanctionStatus(int customerId, String status);


}
