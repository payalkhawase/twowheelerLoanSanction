package in.shriram.dreambiketwowheelerloan.sanction.servicei;

<<<<<<< HEAD
import java.util.List;
=======
import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;
>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerLoanSanction.git

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;

public interface SanctionServiceI {


	public SanctionLetter generateSactionId(Integer customerId);

	public SanctionLetter addSanction(Integer customerId);

<<<<<<< HEAD
	public List<Customer> getAllCustomer(String loanStatus);

=======
//	public Customer updateSanctionStatus(int sanctionId, String status);
>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerLoanSanction.git

}
