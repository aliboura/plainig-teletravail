package dz.djezzydevs.hrplaning.repositories;

import dz.djezzydevs.hrplaning.entities.Authlogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthLogsRepository extends JpaRepository<Authlogs,Long > {
}
