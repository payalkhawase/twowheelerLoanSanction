package in.shriram.dreambiketwowheelerloan.sanction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AccountDetails {

	@Id
	private int accountId;
	private String accountType;
	private String bankName;
	private String ifscCode;
	private double accountBalance;
	private String accountHolderName;
	private String accountStatus;
	private long accountNumber;
	
}
