package in.shriram.dreambiketwowheelerloan.sanction.servicei;

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;

import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;


public interface SanctionServiceI {


	public SanctionLetter generateSactionId(int customerId);

//	public SanctionLetter generateSactionId(Integer customerId);


	public SanctionLetter addSanction(int customerId);

//	public Customer updateSanctionStatus(int sanctionId, String status);

}
