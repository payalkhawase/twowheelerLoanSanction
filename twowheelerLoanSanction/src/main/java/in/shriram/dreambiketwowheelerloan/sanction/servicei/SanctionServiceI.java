package in.shriram.dreambiketwowheelerloan.sanction.servicei;



import java.util.List;
import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;


public interface SanctionServiceI {


	public SanctionLetter generateSactionId(int customerId);

	public SanctionLetter addSanction(int customerId);

    public List getSanctionList();

//	public Customer updateSanctionStatus(int sanctionId, String status);


}
