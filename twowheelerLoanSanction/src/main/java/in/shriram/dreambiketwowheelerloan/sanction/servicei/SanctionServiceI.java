package in.shriram.dreambiketwowheelerloan.sanction.servicei;

import java.util.List;

import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;

public interface SanctionServiceI {



public CustomerDetails getVerifiedCustomer(int customerId, String loanStatus);

}
