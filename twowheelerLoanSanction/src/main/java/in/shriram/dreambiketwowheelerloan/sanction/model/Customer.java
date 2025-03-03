package in.shriram.dreambiketwowheelerloan.sanction.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity 
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private String customerName; 
	private String customerDateOfBirth;
	private int customerAge;
	private String customerGender;
	private String customerMobileNumber;
	private double customerAmountPaidForHome;
	private double customerTotalLoanRequired;
	private String customerEmail;
	private String password;
	private String loanStatus="Submit";

	
}
