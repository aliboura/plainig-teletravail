package dz.djezzydevs.hrplaning.repositories;

import dz.djezzydevs.hrplaning.entities.PlaningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PlaningRepository extends JpaRepository<PlaningEntity,Long> {

    public PlaningEntity findByEmployeeSessionIgnoreCase(String id);

    public PlaningEntity findByPlanDateAndEmployeeSessionIgnoreCase(Date planDate, String employeeSession);



}
