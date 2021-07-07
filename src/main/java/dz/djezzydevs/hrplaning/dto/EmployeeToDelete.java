package dz.djezzydevs.hrplaning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeToDelete {
    private String employeeSession;
    private Date planDate;

}
