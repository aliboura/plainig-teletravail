package dz.djezzydevs.hrplaning.api;

import dz.djezzydevs.hrplaning.dto.EmployeePlaned;
import dz.djezzydevs.hrplaning.dto.EmployeeToPlan;
import dz.djezzydevs.hrplaning.dto.Event;
import dz.djezzydevs.hrplaning.dto.MailRequest;
import dz.djezzydevs.hrplaning.entities.UserAdEntity;
import dz.djezzydevs.hrplaning.repositories.PlaningRepository;
import dz.djezzydevs.hrplaning.repositories.SpecialPlaningRepository;
import dz.djezzydevs.hrplaning.repositories.UserAdRepository;
import dz.djezzydevs.hrplaning.services.MailService;
import dz.djezzydevs.hrplaning.services.PlaningService;
import dz.djezzydevs.hrplaning.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class HomeController {

  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    //    @PreAuthorize("hasAnyAuthority('ROLE_ENGINEER_SITE','ROLE_ENGINEER_OM')")
  //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
/*
  @Autowired
  PlaningRepository planingRepository;
  @Autowired
  UserAdRepository userAdRepository;
  @Autowired
  PlaningService planingService;
    @Autowired
    SpecialPlaningRepository specialPlaningRepository;

  @Autowired
  MailService mailService;
    @GetMapping ("/test")  List<EmployeeToPlan>  home(Principal principal) {

      List<EmployeeToPlan> emp= specialPlaningRepository.findAllByManager(principal.getName());
        return emp;
    }

  @GetMapping ("/test1")  List<EmployeeToPlan>  test1(Principal principal) {

    //findAllByManager1
   // List<EmployeeToPlan> emp= specialPlaningRepository.findAllByManager1(principal.getName());
    return null;
  }



  @GetMapping ("/test3")
  List<Event> test3(Principal principal) {
    return specialPlaningRepository.findAllPlanedByManager1(principal.getName());
  }


//  @GetMapping ("/test4")
//  List<Event> test4(Principal principal ,@RequestParam String start ,@RequestParam String end) {
//    return specialPlaningRepository.findAllPlanedByManager1(principal.getName());
//  }

  //  @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_ADMIN')")
//  @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_ADMIN')")
//    @GetMapping ("/usertest") String usertest() {
//
//        return "admin";
//    }



//  @GetMapping ("/testorg")
//  List<String> testorg(Principal principal) {
//    return specialPlaningRepository.findOrganisations(principal.getName());
//  }

//  @GetMapping ("/testuser")
//  List<Event> testuser(Principal principal) {
//    return specialPlaningRepository.findAllPlanedByUser(principal.getName());
//  }



//  @GetMapping ("/testdirector")
//  List<Event> testdirector(Principal principal) {
//    return specialPlaningRepository.findAllPlanedByDirector(principal.getName());
//  }


  @GetMapping ("/testofficer")
  List<Event> testofficer(Principal principal) {
    return specialPlaningRepository.findAllPlanedByOfficer(principal.getName());
  }


  @GetMapping ("/testmail")
  List<String> testmail(Principal principal) {
   // return  specialPlaningRepository.getEmails(principal.getName());
    List<String> mails=new ArrayList<>();
    mails.add("aliboura@gmail.com");
    mails.add("ali.bouras@djezzy.dz");

    // List<String> emails = Arrays.asList(mails);
    mailService.sendNotifications(new MailRequest("Notifications Test", MailUtils.getMailContent("test titre", "this is  a test  of a subject", "12/04/2021"), mails.toArray(new String[0]), null));
  return mails;

    }

*/


}
