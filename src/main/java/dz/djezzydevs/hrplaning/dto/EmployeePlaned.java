package dz.djezzydevs.hrplaning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePlaned {
    private Long employeeId;
    private String employeeSession;
    private String  fullName;
    private String planDate;


}
