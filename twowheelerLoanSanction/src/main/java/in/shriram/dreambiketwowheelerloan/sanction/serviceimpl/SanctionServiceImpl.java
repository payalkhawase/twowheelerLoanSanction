package in.shriram.dreambiketwowheelerloan.sanction.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;
import in.shriram.dreambiketwowheelerloan.sanction.repository.SanctionRepository;
import in.shriram.dreambiketwowheelerloan.sanction.servicei.SanctionServiceI;

@Service
public class SanctionServiceImpl implements SanctionServiceI{

	@Autowired
	SanctionRepository sr;
	
	@Autowired
	RestTemplate rt;

	

	
	
}
