package in.shriram.dreambiketwowheelerloan.sanction.servicei;


import java.util.List;

import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;


public interface SanctionServiceI {

	public SanctionLetter generateSactionId(Integer customerId);

	public SanctionLetter addSanction(Integer customerId);

	public List<SanctionLetter> ShowsanctionCustomer();

}
