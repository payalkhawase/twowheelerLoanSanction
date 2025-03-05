package in.shriram.dreambiketwowheelerloan.sanction.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;


@Repository
public interface SanctionRepository extends JpaRepository<SanctionLetter, Integer>{


	//List findBySanctionCustomer(String string);

	

	public List findAllByStatus(String string);


}
