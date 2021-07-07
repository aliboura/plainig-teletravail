package dz.djezzydevs.hrplaning.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="planing")
@Data
@NoArgsConstructor
public class PlaningEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name="employee_session")
private String  employeeSession;

    @Column(name="plan_date")
  private Date planDate;

    @Column(name="insert_date")
    private Date insertDate;

    @Column(name="inserted_by")
    private String  insertedBy;

    private String site;
    private String etage;
    private String region;




}
