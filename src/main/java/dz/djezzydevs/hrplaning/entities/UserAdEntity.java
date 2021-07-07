package dz.djezzydevs.hrplaning.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="employee_ad")
@Data
public class UserAdEntity {

    @Column
    private Long employeeId;
    @Column(length=20)
    private String firstName;

    @Column(length=20)
    private String lastName;
    @Column(length=60)
    private String fullName;
    private String position;
    private String department;
    private String location;
    private String email;
    @Column(length=60 )
    @Id
    private String winSession;
    private String phone;
//    @ManyToMany
//    private List<RoleEntity> roles=new ArrayList<>();



}
