package in.shriram.dreambiketwowheelerloan.sanction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


}
