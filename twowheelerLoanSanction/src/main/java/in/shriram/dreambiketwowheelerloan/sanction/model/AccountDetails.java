package in.shriram.dreambiketwowheelerloan.sanction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AccountDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;
	private String accounType;
	private double accountBalance;
	private String accountHolderName;
	private String accountStatus;
	private long accountNumber;
	private String bankName;
	private String IFSCCode;
}
