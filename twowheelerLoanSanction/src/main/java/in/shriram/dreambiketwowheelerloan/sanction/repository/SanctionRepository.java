package in.shriram.dreambiketwowheelerloan.sanction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;


@Repository
public interface SanctionRepository extends JpaRepository<SanctionLetter, Integer>{

}
