package dz.djezzydevs.hrplaning.api;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import dz.djezzydevs.hrplaning.dto.EmployeeToPlan;
import dz.djezzydevs.hrplaning.dto.EmployeeToUpdate;
import dz.djezzydevs.hrplaning.dto.Event;
import dz.djezzydevs.hrplaning.dto.MailRequest;
import dz.djezzydevs.hrplaning.entities.Authlogs;
import dz.djezzydevs.hrplaning.entities.PlaningEntity;
import dz.djezzydevs.hrplaning.entities.UserAdEntity;
import dz.djezzydevs.hrplaning.entities.UserEntity;
import dz.djezzydevs.hrplaning.repositories.PlaningRepository;
import dz.djezzydevs.hrplaning.repositories.SpecialPlaningRepository;
import dz.djezzydevs.hrplaning.repositories.UserAdRepository;
import dz.djezzydevs.hrplaning.services.LogService;
import dz.djezzydevs.hrplaning.services.MailService;
import dz.djezzydevs.hrplaning.services.PlaningService;
import dz.djezzydevs.hrplaning.utils.MailUtils;
import dz.djezzydevs.hrplaning.utils.UserExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlaningController {

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

    @Autowired
    LogService logService;


//   public PlaningController() {
//       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//       if (auth != null)   logService.login(auth.getName());
//
//   }




    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @PostMapping
    public PlaningEntity save(@RequestBody PlaningEntity plan , Principal principal) {

       return  planingService.save(plan,principal.getName());

    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @PostMapping("/update")
    public PlaningEntity update(@RequestBody EmployeeToUpdate emp , Principal principal) {

        return  planingService.update(emp,principal.getName());

    }

    @GetMapping("/log-logout")

    public String logoutLog(Principal principal) {

       logService.logout(principal.getName());
        return "ok";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @PostMapping("/update-manager")
    public UserAdEntity updateManager(@RequestBody EmployeeToUpdate emp , Principal principal) {

        return  planingService.updateManagerPlan(emp,principal.getName());

    }


    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @GetMapping("/allusers")
    public List<UserAdEntity> getallUsers(Principal principal) {

        return  userAdRepository.findAll();

    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @GetMapping ("/list-events-by-manager")
    List<Event> listEventsByManager(Principal principal) {
        return specialPlaningRepository.findAllPlanedByManager1(principal.getName());

    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @GetMapping ("/list-users-by-manager")
    List<EmployeeToPlan> listUsersByManager(Principal principal) {
        return specialPlaningRepository.findAllByManager1(principal.getName());

    }



    @GetMapping ("/list-events-by-user")
    List<Event> finEventsByUser(Principal principal) {
        return specialPlaningRepository.findAllPlanedByUser(principal.getName());

    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_DIRECTOR')")
    @GetMapping ("/list-events-by-director")
    List<Event> finEventsByDirector(Principal principal) {
        return specialPlaningRepository.findAllPlanedByDirector(principal.getName());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_OFFICER')")
    @GetMapping ("/list-events-by-officer")
    List<Event> finEventsByOfficer(Principal principal) {
        return specialPlaningRepository.findAllPlanedByOfficer(principal.getName());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_HRBP_TECHNOLOGY','ROLE_RH_PLANING_HRBP_COMERCIAL','ROLE_RH_PLANING_HRBP_ADMINISTRATION','ROLE_RH_PLANING_HRBP_CUSTOMERCARE')")
    @PostMapping ("/list-events-by-hrbp")
   List<Event> finEventsByHrBp(Principal principal,@RequestBody  List<String> sectors) {

        return specialPlaningRepository.findAllPlanedByHrBp(principal.getName(),sectors);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @GetMapping ("/mail-by-list")
    List<String> sendMailByList(Principal principal) {

     String managerEmail=userAdRepository.findByWinSessionIgnoreCase(principal.getName()).getEmail();

        String[]  ccs =new String[]{managerEmail};
        List<String> emails = specialPlaningRepository.getTodayEmails(principal.getName());
        mailService.sendNotifications(new MailRequest("Notification du planning de télétravail mensuel", MailUtils.getMailContent("Notification du planning de télétravail  mensuel ", "Veuillez être informé que votre planning de télétravail   est disponible à travers le lien suivant : <a href='https://digitalapps.djezzy.dz/rh-planing/#/login'>Lien</a>", ""), emails.toArray(new String[0]), ccs));
        return emails;

    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @GetMapping ("/mail")
    List<String> testmail(Principal principal) {
        // return  specialPlaningRepository.getEmails(principal.getName());
        List<String> mails=new ArrayList<>();
        mails =specialPlaningRepository.getEmails(principal.getName());
        // List<String> emails = Arrays.asList(mails);
        mailService.sendNotifications(new MailRequest("Notification du planning de télétravail mensuel", MailUtils.getMailContent("Notification du planning de télétravail mensuel ", "Veuillez être informé que votre planning de télétravail  est disponible à travers le lien suivant : <a href='https://www.google.com'>Lien</a>", ""), mails.toArray(new String[0]), null));
        return mails;

    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @PostMapping("/update-user-site")
    public String updateSite(@RequestBody EmployeeToUpdate emp , Principal principal) {

          return planingService.updateUserSitePlan(emp,principal.getName());


    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @PostMapping("/delete-user-plan")
    public EmployeeToUpdate delete(@RequestBody EmployeeToUpdate emp , Principal principal) {

         planingService.deletePlaning(emp,principal.getName());
         return emp;
       // return new ResponseEntity<>("done", HttpStatus.OK);


    }

    @PreAuthorize("hasAnyAuthority('ROLE_RH_PLANING_MANAGER')")
    @PostMapping("/delete-sub-user")
    public ResponseEntity<UserEntity> deleteSubUser(@RequestBody EmployeeToUpdate emp , Principal principal) {

        UserEntity user=    planingService.deleteSubUser(emp,principal.getName());

       if  (user !=null) {
           return new ResponseEntity<UserEntity>(user, HttpStatus.OK);

       }

        return new ResponseEntity<UserEntity>(user, HttpStatus.BAD_REQUEST);


        //  return "deleted";
        // return new ResponseEntity<>("done", HttpStatus.OK);

    }



    @GetMapping("/export/excel")
    public List<String> exportToExcel(Principal principal) throws IOException {
//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);

        List<String> emails =specialPlaningRepository.getTodayEmails(principal.getName());
        List<Event> listUsers = specialPlaningRepository.findAllPlanedByList(emails);

        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

       // excelExporter.
        DataSource aAttachment=  excelExporter.exportToBinary();

        mailService.sendNotifications(new MailRequest("Notification du planning de télétravail mensuel", MailUtils.getMailContent("Notification du planning de télétravail  mensuel ", "Veuillez être informé que votre planning de télétravail    est disponible à travers le lien suivant : <a href='https://digitalapps.djezzy.dz/rh-planing/#/login'>Lien</a>", ""), emails.toArray(new String[0]), null),aAttachment);

        return emails;
    }





}
