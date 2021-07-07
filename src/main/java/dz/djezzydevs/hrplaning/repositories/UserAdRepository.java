package dz.djezzydevs.hrplaning.repositories;

import dz.djezzydevs.hrplaning.entities.UserAdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdRepository extends JpaRepository<UserAdEntity,String> {

    //UserAdEntity findById(String id);
    UserAdEntity findByEmployeeId(String id);


    UserAdEntity findByWinSessionIgnoreCase(String id);
}
