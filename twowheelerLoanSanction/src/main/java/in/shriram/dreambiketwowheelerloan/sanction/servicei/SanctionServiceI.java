package in.shriram.dreambiketwowheelerloan.sanction.servicei;

<<<<<<< HEAD

import java.util.List;
import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;
=======
>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerLoanSanction.git

<<<<<<< HEAD
import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
=======

import java.util.List;
>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerLoanSanction.git
import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;

public interface SanctionServiceI {


	public SanctionLetter generateSactionId(int customerId);


	public SanctionLetter addSanction(Integer customerId);
	
	public List getSanctionList();

<<<<<<< HEAD
	public List<Customer> getAllCustomer(String loanStatus);

//	public Customer updateSanctionStatus(int sanctionId, String status);
=======
	public SanctionLetter addSanction(int customerId);

    //public SanctionLetter getSanctionList(int customerId);

	public Customer updateSanctionStatus(int customerId, String status);


>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerLoanSanction.git

}
