package in.shriram.dreambiketwowheelerloan.sanction.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CustomerDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
   private int sanctionedId;
   private Date date;
   private String applicantname;
   private String contactdetails;
   private double onRoadPrice;
   private double loanAmountScantioned;
   private String interesType="Compound Interest";
   private float rateofInterest;
   private int loanTenureMonth;
   private double monthlyEmiAmount;
   private String modeofPayment="Online";
   private String status;
   
	
}
