package dz.djezzydevs.hrplaning.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="employee")
@Data
public class UserEntity implements Serializable {

	
	private static final long serialVersionUID = -5763827745308343856L;
	
	@Id
	private long id;
	@Column(length=20)
	private String firstname;

	@Column(length=20)
	private String lastname;
	@Column(length=60)
	private String location;

	@Column(length=7)
	private String region;
	@Column
	private Date hiredate;

	@Column(length=60)
	private String sector;

	@Column(length=60)
	private String department;

	@Column(length=60)
	private String organisation;

	private String position;
	private String job;


	private String phone;

	private String email;


	private Date leavingdate ;

	private String pabx ;
	private String gender ;
	private int manager ;
	private String wilaya ;

	private String rib ;
	private String status ;
	private String grade ;
	private String soldeconge ;

	private Long managerplan ;














}
