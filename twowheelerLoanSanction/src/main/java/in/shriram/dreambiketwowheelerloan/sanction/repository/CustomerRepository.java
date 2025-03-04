package in.shriram.dreambiketwowheelerloan.sanction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	List<Customer> findAll(String string);

	
}
