package in.shriram.dreambiketwowheelerloan.sanction.servicei;



import java.util.List;
import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;


public interface SanctionServiceI {


	public SanctionLetter generateSactionId(int customerId);


	public SanctionLetter addSanction(Integer customerId);
	
	public List getSanctionList();

	public SanctionLetter addSanction(int customerId);

    //public SanctionLetter getSanctionList(int customerId);

	public Customer updateSanctionStatus(int customerId, String status);



}
