package in.shriram.dreambiketwowheelerloan.sanction.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class CustomerAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerAddressId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private permanentAddress paddr;
	
	@OneToOne(cascade = CascadeType.ALL)
	private localAddress laddr;

}
