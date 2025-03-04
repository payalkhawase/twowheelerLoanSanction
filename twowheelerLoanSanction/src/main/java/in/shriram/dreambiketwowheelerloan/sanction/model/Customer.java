package in.shriram.dreambiketwowheelerloan.sanction.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity 
@Data
public class Customer {

	@Id
	private int customerId;
	private String customerName; 
	private String customerDateOfBirth;
	private int customerAge;
	private String customerGender;

	private String customerMobileNumber;
	private double customerAdditionalMobileNumber;
	private double customerAmountPaidForHome;
	private double customerTotalLoanRequired;
	private String customerEmail;
	private String password;
	private String loanStatus="Submit";
	private String sanctionStatus="Sanctioned";
	
	private double onRoadPrice;
	private int requiredTenure;
	private String interesType="Compound Interest";
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cibil;

	@OneToOne(cascade = CascadeType.ALL)
	private SanctionLetter sanctionletter;
}