package dz.djezzydevs.hrplaning.repositories;


import dz.djezzydevs.hrplaning.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);

    UserEntity findById(String id);

   // UserEntity findById(Long id);


    //UserEntity findByOrganisation

    //UserEntity findByWinSessionIgnoreCase(String session);
}
