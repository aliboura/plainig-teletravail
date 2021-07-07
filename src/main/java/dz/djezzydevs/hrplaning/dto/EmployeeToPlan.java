package dz.djezzydevs.hrplaning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeToPlan {
    private Long employee_id;
    private String email;
    private String win_session;
    private String  full_name;
    private String department;
    private String job;
    private String location;
    private String organisation;
    private String region;
    private int manager;


    //private String planingDate;
   // private String site;
}
