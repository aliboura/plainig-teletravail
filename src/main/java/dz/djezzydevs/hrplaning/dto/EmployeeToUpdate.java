package dz.djezzydevs.hrplaning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeToUpdate {
    private String employeeSession;
    private Date planDate;
    private Date oldDate;
    private Date insertDate;
    private String  insertedBy;
    ////
    private String  managerPlan;
    ////
    private Date statrDate;
    private Date endDate;
    private String  site;
    private String  etage;
    private String  region;




}
