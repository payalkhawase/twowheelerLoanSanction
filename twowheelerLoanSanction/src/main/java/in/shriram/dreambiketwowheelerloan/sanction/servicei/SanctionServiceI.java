package in.shriram.dreambiketwowheelerloan.sanction.servicei;

import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;

import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;


public interface SanctionServiceI {


	public SanctionLetter generateSactionId(Integer customerId);

	public SanctionLetter addSanction(Integer customerId);

	public SanctionLetter updateSanctionStatus(int sanctionId, String status);

}
