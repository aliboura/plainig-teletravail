package dz.djezzydevs.hrplaning.services;

import dz.djezzydevs.hrplaning.dto.EmployeeToUpdate;
import dz.djezzydevs.hrplaning.entities.PlaningEntity;
import dz.djezzydevs.hrplaning.entities.UserAdEntity;
import dz.djezzydevs.hrplaning.entities.UserEntity;
import dz.djezzydevs.hrplaning.repositories.PlaningRepository;
import dz.djezzydevs.hrplaning.repositories.SpecialPlaningRepository;
import dz.djezzydevs.hrplaning.repositories.UserAdRepository;
import dz.djezzydevs.hrplaning.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PlaningService   {

    @Autowired
    PlaningRepository planingRepository;

    @Autowired
    UserAdRepository userAdRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpecialPlaningRepository specialPlaningRepository;

    public PlaningEntity save(PlaningEntity planingEntity,String insertedBy ) {
       // Principal principal;
      //  PlaningEntity pl = planingRepository.findByEmployeeSessionIgnoreCase(planingEntity.getEmployeeSession());

        PlaningEntity pl = planingRepository.findByPlanDateAndEmployeeSessionIgnoreCase(planingEntity.getPlanDate(),planingEntity.getEmployeeSession());


        if  ( pl !=null) {
            pl.setEmployeeSession(planingEntity.getEmployeeSession());
           // Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(planingEntity.getPlanDate());

            pl.setPlanDate(planingEntity.getPlanDate());
            pl.setInsertedBy(insertedBy);
            pl.setInsertDate(new Date());
           // pl.getInsertDate();
            planingRepository.save(pl);
            return pl;

          //  return planingEntity;
          //  return planingEntity;
        }else  {


            planingEntity.setInsertedBy(insertedBy);
           // planingEntity.setPlanDate();
            planingEntity.setInsertDate(new Date());

            planingRepository.save(planingEntity);
            return planingEntity;
        }
   // return null;
    }


    public PlaningEntity update(EmployeeToUpdate employeeToUpdate, String insertedBy ) {
        // Principal principal;
        //  PlaningEntity pl = planingRepository.findByEmployeeSessionIgnoreCase(planingEntity.getEmployeeSession());

        PlaningEntity pl = planingRepository.findByPlanDateAndEmployeeSessionIgnoreCase(employeeToUpdate.getOldDate(),employeeToUpdate.getEmployeeSession());


        if  ( pl !=null) {
            pl.setPlanDate(employeeToUpdate.getPlanDate());
            // Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(planingEntity.getPlanDate());
            pl.setInsertedBy(insertedBy);
            pl.setInsertDate(new Date());
            planingRepository.save(pl);
            return pl;

            //  return planingEntity;
            //  return planingEntity;
        }else  {


            return null;
        }
        // return null;
    }

    ///////
    public UserAdEntity updateManagerPlan(EmployeeToUpdate employeeToUpdate, String insertedBy ) {
        Long managerId =userAdRepository.findByWinSessionIgnoreCase(insertedBy).getEmployeeId();
        Long empId =userAdRepository.findByWinSessionIgnoreCase(employeeToUpdate.getEmployeeSession()).getEmployeeId();
        UserEntity userEntity=userRepository.findById(empId).get();
        UserAdEntity userAdEntity =userAdRepository.findByWinSessionIgnoreCase(employeeToUpdate.getEmployeeSession());

        // UserEntity managerEntity=userRepository.findByWinSessionIgnoreCase(employeeToUpdate.getEmployeeSession());


        if  ( userEntity !=null) {
            userEntity.setManagerplan(managerId);
            userRepository.save(userEntity);
            return  userAdEntity;
        }else  {
            return null;
        }

    }



    /////update site
    public String updateUserSitePlan(EmployeeToUpdate employeeToUpdate, String insertedBy ) {
        Long managerId =userAdRepository.findByWinSessionIgnoreCase(insertedBy).getEmployeeId();
        Long empId =userAdRepository.findByWinSessionIgnoreCase(employeeToUpdate.getEmployeeSession()).getEmployeeId();
        UserEntity userEntity=userRepository.findById(empId).get();
        UserAdEntity userAdEntity =userAdRepository.findByWinSessionIgnoreCase(employeeToUpdate.getEmployeeSession());

       specialPlaningRepository.updateUserSitePlan(
               employeeToUpdate.getEmployeeSession(),insertedBy,
               employeeToUpdate.getStatrDate(),employeeToUpdate.getEndDate(),
               employeeToUpdate.getSite(),employeeToUpdate.getEtage(),employeeToUpdate.getRegion()
       );


            return "test";



    }


    public String deletePlaning(EmployeeToUpdate planing,String insertedBy ) {
        // Principal principal;
        //  PlaningEntity pl = planingRepository.findByEmployeeSessionIgnoreCase(planingEntity.getEmployeeSession());
        PlaningEntity planingTodelete = planingRepository.findByPlanDateAndEmployeeSessionIgnoreCase(planing.getPlanDate(),planing.getEmployeeSession());
         if (planingTodelete != null) {
             planingRepository.delete(planingTodelete);
             return "ok";
         }else  return "err";


    }

    public UserEntity deleteSubUser(EmployeeToUpdate employeeToUpdate,String insertedBy ) {

        Long managerId =userAdRepository.findByWinSessionIgnoreCase(insertedBy).getEmployeeId();
        Long empId =userAdRepository.findByWinSessionIgnoreCase(employeeToUpdate.getEmployeeSession()).getEmployeeId();
        UserEntity userEntity=userRepository.findById(empId).get();

          if ( userEntity.getManager() ==managerId ) {
              return null ;
          }
       // UserAdEntity userAdEntity =userAdRepository.findByWinSessionIgnoreCase(employeeToUpdate.getEmployeeSession());
         if (userEntity !=null) {
             userEntity.setManagerplan(0L);
             userRepository.save(userEntity);
             return userEntity;
         }
         else {
             return null;
         }
         // userAdEntity.set
//        specialPlaningRepository.updateUserSitePlan(
//                employeeToUpdate.getEmployeeSession(),insertedBy,
//                employeeToUpdate.getStatrDate(),employeeToUpdate.getEndDate(),
//                employeeToUpdate.getSite(),employeeToUpdate.getEtage(),employeeToUpdate.getRegion()
//        );

    }



}
