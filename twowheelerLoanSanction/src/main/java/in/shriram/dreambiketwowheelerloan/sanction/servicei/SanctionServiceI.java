package in.shriram.dreambiketwowheelerloan.sanction.servicei;


import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;


public interface SanctionServiceI {

	public SanctionLetter generateSactionId(int customerId);

	public SanctionLetter addSanction(int customerId);

}
